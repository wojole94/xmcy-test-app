package com.xmcy.test.recommendation.service.exception;

public class MalformedInputDataException extends RuntimeException{
    public MalformedInputDataException(String message, Throwable ex){
        super(message, ex);
    }
}
