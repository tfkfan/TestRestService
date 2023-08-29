package com.tfkfan.service;

import com.tfkfan.domain.Category;
import com.tfkfan.web.rest.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Category}.
 */
public interface CategoryApiService extends PageableService{
    /**
     * Save a request.
     *
     * @param request the entity to save.
     * @return the persisted entity.
     */
    CategoryResponse save(CreateCategoryRequest request);

    /**
     * Partially updates a category.
     *
     * @param request the entity to update partially.
     * @return the persisted entity.
     */
    CategoryResponse update(UpdateCategoryRequest request);


    /**
     * Get all the categories.
     *
     * @param request the request information.
     * @return the list of entities.
     */
    FindCategoriesResponse findAll(FindCategoriesRequest request);
}
