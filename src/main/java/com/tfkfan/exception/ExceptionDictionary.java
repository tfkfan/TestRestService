package com.tfkfan.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * @author Baltser Artem tfkfan
 */
public enum ExceptionDictionary {
    ENTITY_SUBORDINATION_EXCEPTION("CODE_1", "EntitySubordinationException",
        "DEFAULT MSG 1", HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND_EXCEPTION("CODE_2","EntityNotFoundException",
        "DEFAULT MSG 2", HttpStatus.NOT_FOUND),
    DATABASE_EXCEPTION("CODE_3", "DatabaseException",
        "DEFAULT MSG 3", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_ALREADY_EXISTS_EXCEPTION("CODE_4","EntityAlreadyExistsException",
        "DEFAULT_MSG 4", HttpStatus.CONFLICT),
    VALIDATION_EXCEPTION("CODE_5","ValidationException",
        "Не заполнены обязательные поля", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_EXCEPTION("CODE_6", "InternalServerException",
        "DEFAULT MSG 6", HttpStatus.INTERNAL_SERVER_ERROR);

    private String code;
    private String name;
    private String message;
    private HttpStatus status;

    ExceptionDictionary(String code, String name, String message,  HttpStatus status) {
        this.code = code;
        this.name = name;
        this.message = message;
        this.status = status;
    }

    public static HttpStatus getStatusByCode(String code){
        return Arrays
            .stream(values())
            .filter(e->e.code.equals(code))
            .findFirst()
            .map(e->e.status)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static String getNameByCode(String code){
        return Arrays
            .stream(values())
            .filter(e->e.code.equals(code))
            .findFirst()
            .map(e->e.name)
            .orElse(ExceptionDictionary.INTERNAL_SERVER_EXCEPTION.name);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
