package com.tfkfan.web.rest.dto;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
public class FindCategoriesResponse {
    private List<Category> categories;
    private PageInfo pageInfo;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
