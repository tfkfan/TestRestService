package com.tfkfan.service;

import com.tfkfan.web.rest.dto.BasePageableRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Baltser Artem tfkfan
 */
public interface PageableService {
    Pageable pageable(BasePageableRequest pageSettings);
}
