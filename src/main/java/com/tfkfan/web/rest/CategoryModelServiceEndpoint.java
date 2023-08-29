package com.tfkfan.web.rest;

import com.tfkfan.service.CategoryModelApiService;
import com.tfkfan.web.rest.dto.BasePageableRequest;
import com.tfkfan.web.rest.dto.CategoryModelResponse;
import com.tfkfan.web.rest.dto.CategoryModelRequest;
import com.tfkfan.web.rest.dto.FindModelsByCategoryCodeResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Baltser Artem tfkfan
 */
@RestController
@RequestMapping("/v1/categoryModels")
public class CategoryModelServiceEndpoint   {
    private final CategoryModelApiService apiService;

    public CategoryModelServiceEndpoint(CategoryModelApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping
    public CategoryModelResponse linkCategoryToModel(@Valid @RequestBody CategoryModelRequest categoryModelRequest){
        return apiService.linkCategoryToModel(categoryModelRequest);
    }

    @DeleteMapping
    public CategoryModelResponse unlinkCategoryToModel(@Valid @RequestBody CategoryModelRequest unlinkCategoryToModelRequest) {
        return apiService.unlinkCategoryToModel(unlinkCategoryToModelRequest);
    }

    @GetMapping("/{code}")
    public FindModelsByCategoryCodeResponse findModelsByCategoryCode(@PathVariable(name = "code") String categoryCode,
                                                                     BasePageableRequest request){
        return apiService.findModelsByCategoryCode(categoryCode, request);
    }
}
