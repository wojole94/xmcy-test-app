package com.xmcy.test.recommendation.service.exception;

import com.xmcy.test.recommendation.service.dto.CryptoProcessingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CryptoProcessingResult> handleRuntimeException(Exception exception) {
        List<String> errors = List.of(
                String.format("Cannot process request because: %s", exception)
        );
        return ResponseEntity.ofNullable(CryptoProcessingResult.builder()
                .errors(errors)
                .build());
    }

    @ExceptionHandler(ExpectedDataTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CryptoProcessingResult> handleFileNotFoundException(Exception exception) {
        List<String> errors = List.of(
                exception.getMessage()
        );
        return ResponseEntity.ofNullable(CryptoProcessingResult.builder()
                .errors(errors)
                .build());
    }

    @ExceptionHandler(MalformedInputDataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<CryptoProcessingResult> handleMalformedInputDataException(Exception exception) {
        List<String> errors = List.of(
                exception.getMessage()
        );
        return ResponseEntity.ofNullable(CryptoProcessingResult.builder()
                .errors(errors)
                .build());
    }

}
