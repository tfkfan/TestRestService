package com.tfkfan.web.rest;

import com.tfkfan.service.CategoryApiService;
import com.tfkfan.web.rest.dto.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Baltser Artem tfkfan
 */
@RestController
@RequestMapping("/v1/categories")
public class CategoryServiceEndpoint{
    private final CategoryApiService apiService;

    public CategoryServiceEndpoint(CategoryApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return apiService.save(createCategoryRequest);
    }

    @PatchMapping
    public CategoryResponse updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return apiService.update(updateCategoryRequest);
    }

    @GetMapping
    public FindCategoriesResponse findCategories(@Valid FindCategoriesRequest findCategoriesRequest) {
        return apiService.findAll(findCategoriesRequest);
    }
}
