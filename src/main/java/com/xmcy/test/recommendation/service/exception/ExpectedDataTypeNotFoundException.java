package com.xmcy.test.recommendation.service.exception;

public class ExpectedDataTypeNotFoundException extends RuntimeException{
    public ExpectedDataTypeNotFoundException(String message) {
        super(message);
    }
    public ExpectedDataTypeNotFoundException(String message, Throwable cause){
        super(message, cause);
    }


}
