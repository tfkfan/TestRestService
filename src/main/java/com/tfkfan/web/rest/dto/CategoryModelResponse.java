package com.tfkfan.web.rest.dto;

/**
 * @author Baltser Artem tfkfan
 */
public class CategoryModelResponse {
    private Category category;
    private Model model;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
