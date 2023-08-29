package com.tfkfan.web.rest.dto;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
public class FindModelsRequest extends BasePageableRequest{
    private List<String> codes;
    private String name;
    private String description;


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
}
