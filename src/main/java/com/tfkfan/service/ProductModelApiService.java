package com.tfkfan.service;

import com.tfkfan.domain.ProductModel;
import com.tfkfan.web.rest.dto.*;

import java.util.Optional;

/**
 * Service Interface for managing {@link ProductModel}.
 */
public interface ProductModelApiService {
    /**
     * Save a request.
     *
     * @param request the entity to save.
     * @return the persisted entity.
     */
    ModelResponse save(CreateModelRequest request);

    /**
     * updates a model.
     *
     * @param request the entity to update partially.
     * @return the persisted entity.
     */
    ModelResponse update(UpdateModelRequest request);

    /**
     * Get all the models.
     *
     * @param request the request with pagination information.
     * @return the list of entities.
     */

    FindModelsResponse findAll(FindModelsRequest request);

}
