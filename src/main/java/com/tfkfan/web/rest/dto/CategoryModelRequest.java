package com.tfkfan.web.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Baltser Artem tfkfan
 */
public class CategoryModelRequest {
    private @NotNull @NotEmpty String categoryCode;
    private @NotNull @NotEmpty  String modelCode;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
}
