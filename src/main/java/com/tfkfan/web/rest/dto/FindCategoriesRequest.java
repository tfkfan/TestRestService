package com.tfkfan.web.rest.dto;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
public class FindCategoriesRequest extends BasePageableRequest{
    private List<String> codes;
    private String name;
    private String description;
    private List<String> parentCategoryCodes;
    private Boolean isHidden;
    private Boolean onlyParent;

    public FindCategoriesRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public List<String> getParentCategoryCodes() {
        return parentCategoryCodes;
    }

    public void setParentCategoryCodes(List<String> parentCategoryCodes) {
        this.parentCategoryCodes = parentCategoryCodes;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Boolean getOnlyParent() {
        return onlyParent;
    }

    public void setOnlyParent(Boolean onlyParent) {
        this.onlyParent = onlyParent;
    }
}
