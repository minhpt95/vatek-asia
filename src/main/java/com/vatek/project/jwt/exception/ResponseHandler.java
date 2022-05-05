package com.catdev.project.jwt.exception;

import com.catdev.project.constant.ErrorConstant;
import com.catdev.project.dto.ErrorBindingDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseHandler {

    @Autowired
    private ObjectMapper mapper;

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleProductException(ProductException ex, WebRequest request) {
        return ex.getErrorResponse();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorConstant.Code.SUCCESS);
        errorResponse.setMessage("You do not have permission to access");
        return errorResponse;
    }


    @ExceptionHandler(value = DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleDisableException(DisabledException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorConstant.Code.USER_INACTIVE);
        errorResponse.setErrorType(ErrorConstant.Type.USER_INACTIVE);
        errorResponse.setMessage(ErrorConstant.Message.USER_INACTIVE);
        return errorResponse;
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorConstant.Code.SUCCESS);
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindingErrors(MethodArgumentNotValidException ex) throws JsonProcessingException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorConstant.Code.SUCCESS);

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ErrorBindingDto> errorBindingDtos = errors.stream().map(x -> {
            ErrorBindingDto errorBindingDto = new ErrorBindingDto();
            errorBindingDto.setFieldError(x.getField());
            errorBindingDto.setErrorMessage(x.getDefaultMessage());
            return errorBindingDto;
        }).collect(Collectors.toList());

        errorResponse.setMessage(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorBindingDtos));

        return errorResponse;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public ErrorResponse handleBindingErrors(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorConstant.Code.SUCCESS);
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
