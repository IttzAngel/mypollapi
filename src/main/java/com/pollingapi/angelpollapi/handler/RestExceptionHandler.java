package com.pollingapi.angelpollapi.handler;

import com.pollingapi.angelpollapi.error.ErrorDetail;
import com.pollingapi.angelpollapi.error.ValidationError;
import com.pollingapi.angelpollapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RestExceptionHandler extends ResponseEntityExceptionHandler {

}
