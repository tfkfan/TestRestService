package com.tfkfan.exception;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public abstract class BusinessException extends RuntimeException   {
    private final String code;
    private final Map<String,String> details;

    public BusinessException(String code, Map<String, String> details) {
        this.code = code;
        this.details = details;
    }

    public BusinessException(String message, String code, Map<String, String> details) {
        super(message);
        this.code = code;
        this.details = details;
    }

    public BusinessException(String message, Throwable cause, String code, Map<String, String> details) {
        super(message, cause);
        this.code = code;
        this.details = details;
    }

    public BusinessException(Throwable cause, String code, Map<String, String> details) {
        super(cause);
        this.code = code;
        this.details = details;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, Map<String, String> details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public Map<String,String> getDetails() {
        return details;
    }

}
