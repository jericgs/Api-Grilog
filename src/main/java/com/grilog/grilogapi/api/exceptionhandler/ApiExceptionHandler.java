package com.grilog.grilogapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.grilog.grilogapi.domain.exception.BusinessException;
import com.grilog.grilogapi.domain.exception.EntityNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new Field(name, message));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle("One or more invalid fields. Fill in correctly.");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFound(final EntityNotFoundException ex, final WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Problem problem = new Problem();
        problem.setStatus(httpStatus.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handlerBusiness(final BusinessException ex, final WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(httpStatus.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), httpStatus, request);
    }

}
