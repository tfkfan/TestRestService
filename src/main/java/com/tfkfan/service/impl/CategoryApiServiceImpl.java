package com.tfkfan.service.impl;

import com.tfkfan.domain.Category;
import com.tfkfan.exception.EntityAlreadyExistsException;
import com.tfkfan.exception.EntityNotFoundException;
import com.tfkfan.exception.EntitySubordinationException;
import com.tfkfan.mapper.CategoryMapper;
import com.tfkfan.repository.CategoryRepository;
import com.tfkfan.service.CategoryApiService;
import com.tfkfan.service.criteria.CategoryQueryService;
import com.tfkfan.service.criteria.model.CategoryCriteria;
import com.tfkfan.web.rest.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryApiServiceImpl extends BasePageableServiceImpl implements CategoryApiService {
    private final Logger log = LoggerFactory.getLogger(CategoryApiServiceImpl.class);

    private final CategoryMapper mapper;
    private final CategoryRepository repository;
    private final CategoryQueryService queryService;

    public CategoryApiServiceImpl(CategoryMapper mapper, CategoryRepository repository, CategoryQueryService queryService) {
        this.mapper = mapper;
        this.repository = repository;
        this.queryService = queryService;
    }

    @Override
    public CategoryResponse save(CreateCategoryRequest request) {
        log.debug("Request to save Category : {}", request);
        if (!StringUtils.isEmpty(request.getParentCategoryCode()) && request.getCode().equals(request.getParentCategoryCode()))
            throw  EntitySubordinationException.buildForCategory(Map.of("parentCategoryCode", request.getParentCategoryCode(),
                "code", request.getCode()));

        final Category category = mapper.toEntity(request);
        if (!StringUtils.isEmpty(request.getParentCategoryCode())) {
            final Category parent = queryService
                .findOneByCriteria(new CategoryCriteria()
                    .code((StringFilter) new StringFilter().setEquals(request.getParentCategoryCode()))
                    .parentCategoryIdNull(true))
                .orElseThrow(() -> EntityNotFoundException.buildForParentCategory(request.getParentCategoryCode()));
            category.setParentCategory(parent);
        }

        queryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter()
                .setEquals(request.getCode())))
            .ifPresent((e) -> {
                throw EntityAlreadyExistsException.buildForCategory(request.getCode());
            });

        return mapper.toCreateResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(UpdateCategoryRequest request) {
        log.debug("Request to partially update Category : {}", request);

        final Category entity = queryService
            .findOneByCriteria(new CategoryCriteria().code((StringFilter) new StringFilter().setEquals(request.getCode())))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(request.getCode()));

        if (!StringUtils.isEmpty(request.getParentCategoryCode()) && entity.getCode().equals(request.getParentCategoryCode()))
            throw EntitySubordinationException.buildForCategory(Map.of("parentCategoryCode",
                request.getParentCategoryCode(), "code", entity.getCode()));

        if (!StringUtils.isEmpty(request.getParentCategoryCode())) {
            final Category parent = queryService
                .findOneByCriteria(new CategoryCriteria()
                    .code((StringFilter) new StringFilter().setEquals(request.getParentCategoryCode()))
                    .parentCategoryIdNull(true))
                .orElseThrow(() -> EntityNotFoundException.buildForParentCategory(request.getParentCategoryCode()));

            entity.setParentCategory(parent);
        }

        if (Objects.nonNull(request.getName()))
            entity.setName(request.getName());
        if (Objects.nonNull(request.getIsHidden()))
            entity.setIsHidden(request.getIsHidden());

        entity.setDescription(request.getDescription());

        return mapper.toUpdateResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public FindCategoriesResponse findAll(FindCategoriesRequest request) {
        log.debug("Request to get all Categories");

        Pageable pageable = pageable(request);

        if (Objects.nonNull(request.getParentCategoryCodes()) && !request.getParentCategoryCodes().isEmpty()) {
            List<Category> parentCategories = queryService.findByCriteria(new CategoryCriteria()
                .code((StringFilter) new StringFilter().setIn(request.getParentCategoryCodes()))
                .parentCategoryIdNull(true));

            if(Objects.isNull(request.getCodes()))
                request.setCodes(new ArrayList<>());

            request.getCodes().addAll(queryService
                .findByCriteria(new CategoryCriteria()
                    .parentCategoryId((LongFilter)
                        new LongFilter().setIn(parentCategories
                            .stream()
                            .map(Category::getId)
                            .collect(Collectors.toList()))))
                .stream()
                .map(Category::getCode)
                .collect(Collectors.toList()));
        }

        CategoryCriteria criteria = new CategoryCriteria();
        if (Objects.nonNull(request.getCodes()) && !request.getCodes().isEmpty())
            criteria.setCode((StringFilter) new StringFilter().setIn(request.getCodes()));
        if (!StringUtils.isEmpty(request.getName()))
            criteria.setName(new StringFilter().setContains(request.getName()));
        if (!StringUtils.isEmpty(request.getDescription()))
            criteria.setDescription(new StringFilter().setContains(request.getDescription()));
        if (Objects.nonNull(request.getIsHidden()))
            criteria.setIsHidden((BooleanFilter) new BooleanFilter().setEquals(request.getIsHidden()));
        if (Objects.nonNull(request.getOnlyParent()))
            criteria.setParentCategoryIdNull(true);

        return mapper.toFindResponse(queryService.findByCriteria(criteria, pageable));
    }
}
