package com.tfkfan.web.rest.dto;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
public class FindModelsResponse {
    private List<Model> models;
    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
