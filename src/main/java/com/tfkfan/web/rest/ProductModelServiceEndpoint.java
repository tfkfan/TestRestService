package com.tfkfan.web.rest;

import com.tfkfan.service.ProductModelApiService;
import com.tfkfan.web.rest.dto.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Baltser Artem tfkfan
 */
@RestController
@RequestMapping("/v1/models")
public class ProductModelServiceEndpoint   {
    private final ProductModelApiService apiService;

    public ProductModelServiceEndpoint(ProductModelApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping
    public ModelResponse createModel(@Valid @RequestBody CreateModelRequest createModelRequest){
        return apiService.save(createModelRequest);
    }

    @PatchMapping
    public ModelResponse updateModel(@Valid  @RequestBody UpdateModelRequest updateModelRequest){
        return apiService.update(updateModelRequest);
    }

    @GetMapping
    public FindModelsResponse findModels(@Valid FindModelsRequest findModelsRequest) {
        return apiService.findAll(findModelsRequest);
    }
}
