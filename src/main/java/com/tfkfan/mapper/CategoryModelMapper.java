package com.tfkfan.mapper;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;
import com.tfkfan.web.rest.dto.CategoryModelResponse;
import com.tfkfan.web.rest.dto.FindModelsByCategoryCodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Mapper(componentModel = "spring")
public abstract class CategoryModelMapper implements BaseMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductModelMapper modelMapper;

    public CategoryModelResponse toResponse(CategoryProductModel entity) {
        CategoryModelResponse response = new CategoryModelResponse();
        response.setCategory(categoryMapper.toDto(entity.getCategory()));
        response.setModel(modelMapper.toDto(entity.getModel()));

        return response;
    }

    public FindModelsByCategoryCodeResponse toFindModelsByCategoryCodeResponse(Category category, Page<ProductModel> page) {
        FindModelsByCategoryCodeResponse resp = new FindModelsByCategoryCodeResponse();
        resp.setCategory(categoryMapper.toDto(category));
        resp.setPageInfo(pageInfo(page));
        resp.setModels(modelMapper.toDtos(page.getContent()));

        return resp;
    }
}
