package com.tfkfan.service.impl;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;
import com.tfkfan.exception.EntityAlreadyExistsException;
import com.tfkfan.exception.EntityNotFoundException;
import com.tfkfan.mapper.CategoryModelMapper;
import com.tfkfan.repository.CategoryProductModelRepository;
import com.tfkfan.service.CategoryModelApiService;
import com.tfkfan.service.criteria.CategoryQueryService;
import com.tfkfan.service.criteria.ProductModelQueryService;
import com.tfkfan.service.criteria.model.CategoryCriteria;
import com.tfkfan.service.criteria.model.ProductModelCriteria;
import com.tfkfan.web.rest.dto.BasePageableRequest;
import com.tfkfan.web.rest.dto.CategoryModelRequest;
import com.tfkfan.web.rest.dto.CategoryModelResponse;
import com.tfkfan.web.rest.dto.FindModelsByCategoryCodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.StringFilter;

/**
 * @author Baltser Artem tfkfan
 */
@Service
@Transactional
public class CategoryModelApiServiceImpl extends BasePageableServiceImpl implements CategoryModelApiService {
    private final Logger log = LoggerFactory.getLogger(CategoryModelApiServiceImpl.class);

    private final CategoryQueryService categoryQueryService;
    private final ProductModelQueryService modelQueryService;
    private final CategoryProductModelRepository repository;
    private final CategoryModelMapper categoryModelMapper;

    public CategoryModelApiServiceImpl(CategoryQueryService categoryQueryService,
                                       ProductModelQueryService modelQueryService,
                                       CategoryProductModelRepository repository,
                                       CategoryModelMapper categoryModelMapper) {
        this.categoryQueryService = categoryQueryService;
        this.modelQueryService = modelQueryService;
        this.repository = repository;
        this.categoryModelMapper = categoryModelMapper;
    }

    @Override
    public CategoryModelResponse linkCategoryToModel(CategoryModelRequest request) {
        final CategoryProductModel catmodel = createModelFromExistingEntities(request.getCategoryCode(), request.getModelCode());
        repository.findById(catmodel.getId()).ifPresent(categoryProductModel -> {
            throw EntityAlreadyExistsException.buildForCategoryModel(request.getCategoryCode(), request.getModelCode());
        });

        return categoryModelMapper.toResponse(repository.save(catmodel));
    }

    @Override
    public CategoryModelResponse unlinkCategoryToModel(CategoryModelRequest request) {
        final CategoryProductModel catmodel = createModelFromExistingEntities(request.getCategoryCode(), request.getModelCode());
        return repository.findById(catmodel.getId()).map(e -> {
            repository.delete(e);
            return categoryModelMapper.toResponse(e);
        }).orElseThrow(() -> EntityNotFoundException.buildForCategoryModel(request.getCategoryCode(),
            request.getModelCode()));
    }

    @Override
    public FindModelsByCategoryCodeResponse findModelsByCategoryCode(String categoryCode, BasePageableRequest request) {
        final Category category = categoryQueryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter().setEquals(categoryCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(categoryCode));

        Pageable pageable = pageable(request);

        Page<ProductModel> page = modelQueryService.findByCriteria(
            new ProductModelCriteria()
                .categoryCode((StringFilter) new StringFilter().setEquals(category.getCode())), pageable
        );
        return categoryModelMapper.toFindModelsByCategoryCodeResponse(category, page);
    }

    private CategoryProductModel createModelFromExistingEntities(String categoryCode, String modelCode) {
        final Category category = categoryQueryService.findOneByCriteria(new CategoryCriteria()
            .code((StringFilter) new StringFilter().setEquals(categoryCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForCategory(categoryCode));

        final ProductModel model = modelQueryService.findOneByCriteria(new ProductModelCriteria()
            .code((StringFilter) new StringFilter().setEquals(modelCode)))
            .orElseThrow(() -> EntityNotFoundException.buildForModel(modelCode));

        return new CategoryProductModel(category, model);
    }
}
