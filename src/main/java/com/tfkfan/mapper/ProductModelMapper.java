package com.tfkfan.mapper;

import com.tfkfan.domain.ProductModel;
import com.tfkfan.web.rest.dto.CreateModelRequest;
import com.tfkfan.web.rest.dto.FindModelsResponse;
import com.tfkfan.web.rest.dto.Model;
import com.tfkfan.web.rest.dto.ModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Baltser Artem tfkfan
 */
@Mapper(componentModel = "spring")
public interface ProductModelMapper extends DtoMapper<ProductModel, Model>{
    com.tfkfan.domain.ProductModel toEntity(CreateModelRequest request);

    List<Model> toDtos(List<ProductModel> entities);

    @Mapping(target = "model", source = "entity", qualifiedByName = DTO_MAPPING)
    ModelResponse toCreateResponse(ProductModel entity);

    @Mapping(target = "model", source = "entity", qualifiedByName = DTO_MAPPING)
    ModelResponse toUpdateResponse(ProductModel entity);

    default FindModelsResponse toFindResponse(Page<ProductModel> page) {
        FindModelsResponse resp = new FindModelsResponse();

        resp.setModels(toDtos(page.getContent()));
        resp.setPageInfo(pageInfo(page));
        return resp;
    }
}
