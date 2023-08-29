package com.tfkfan.mapper;

import com.tfkfan.domain.Category;
import com.tfkfan.web.rest.dto.CategoryResponse;
import com.tfkfan.web.rest.dto.CreateCategoryRequest;
import com.tfkfan.web.rest.dto.FindCategoriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

/**
 * @author Baltser Artem tfkfan
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends DtoMapper<Category, com.tfkfan.web.rest.dto.Category> {
    @Mapping(target = "parentCategoryCode", ignore = true)
    Category toEntity(CreateCategoryRequest request);

    List<com.tfkfan.web.rest.dto.Category> toDtos(List<Category> entities);

    @Mapping(target = "category", source = "entity", qualifiedByName = DTO_MAPPING)
    CategoryResponse toCreateResponse(Category entity);

    @Mapping(target = "category", source = "entity", qualifiedByName = DTO_MAPPING)
    CategoryResponse toUpdateResponse(Category entity);

    default FindCategoriesResponse toFindResponse(Page<Category> page) {
        FindCategoriesResponse resp = new FindCategoriesResponse();

        resp.setCategories(toDtos(page.getContent()));
        resp.setPageInfo(pageInfo(page));
        return resp;
    }
}
