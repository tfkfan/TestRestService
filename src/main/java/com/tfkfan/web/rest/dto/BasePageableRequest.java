package com.tfkfan.web.rest.dto;

import java.io.Serializable;

/**
 * @author Baltser Artem tfkfan
 */
public class BasePageableRequest implements Serializable {
    private Long pageNum;
    private Long pageSize;
    private String sortField;
    private Boolean descending;

    public BasePageableRequest() {
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }
}
