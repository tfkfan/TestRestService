package com.tfkfan.service;

import com.tfkfan.web.rest.dto.BasePageableRequest;
import com.tfkfan.web.rest.dto.CategoryModelRequest;
import com.tfkfan.web.rest.dto.CategoryModelResponse;
import com.tfkfan.web.rest.dto.FindModelsByCategoryCodeResponse;

/**
 * @author Baltser Artem tfkfan
 */
public interface CategoryModelApiService {
    CategoryModelResponse linkCategoryToModel(CategoryModelRequest request);

    CategoryModelResponse unlinkCategoryToModel(CategoryModelRequest request);

    FindModelsByCategoryCodeResponse findModelsByCategoryCode(String categoryCode, BasePageableRequest request);
}
