package org.example.test.testazure.exceptionhandler;

import org.example.test.testazure.exceptionhandler.exception.ErrorResponse;
import org.example.test.testazure.exceptionhandler.exception.NoDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataException.class)
    protected ResponseEntity<?> nodataExceptionHandler(NoDataException e) {
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.NO_DATA.getErrorCode())
                .message(ErrorCode.NO_DATA.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
//        ErrorResponse response = ErrorResponse.builder()
//                .errorCode(ErrorCode.ERROR_DATA.getErrorCode())
//                .message(e.getFieldError().getDefaultMessage())
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
