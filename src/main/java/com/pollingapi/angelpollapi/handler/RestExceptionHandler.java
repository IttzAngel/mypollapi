package com.pollingapi.angelpollapi.handler;

import com.pollingapi.angelpollapi.error.ErrorDetail;
import com.pollingapi.angelpollapi.error.ValidationError;
import com.pollingapi.angelpollapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource messageSource;

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
//        errorDetail.setTitle("Resource not found take the L");
//        errorDetail.setDetail(rnfe.getMessage());
//        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
//        return new ResponseEntity<>(rnfe, null, HttpStatus.NOT_FOUND);
//    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message not readable :(");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail= new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri", 1);
        if(requestPath == null){
            requestPath = request.getHeader("Header test");
        }
        errorDetail.setTitle("Validation failed :(");
        errorDetail.setDetail("Input validation failed :(");
        errorDetail.setDeveloperMessage(manve.getClass().getName());
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if (validationErrorList == null){
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            //errorDetail.getErrors().put(fe.getField(), validationErrorList);
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

    //    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value()); // this does not seem to work correctly
//        errorDetail.setTitle("Resource not found :( My apologies");
//        errorDetail.setDetail(rnfe.getMessage());
//        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
//        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
//        ErrorDetail errorDetail= new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if(requestPath == null){
//            requestPath = request.getRequestURI();
//        }
//        errorDetail.setTitle("Validation failed :(");
//        errorDetail.setDetail("Input validation failed :(");
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe : fieldErrors) {
//            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
//            if (validationErrorList == null){
//                validationErrorList = new ArrayList<ValidationError>();
//                errorDetail.getErrors().put(fe.getField(), validationErrorList);
//            }
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(fe.getDefaultMessage());
//            validationErrorList.add(validationError);
//        }
//        return errorDetail;
//    }
}
