package ua.knu.csc.emagazine.api.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.knu.csc.emagazine.domain.service.CategoryAlreadyExistsException;
import ua.knu.csc.emagazine.domain.service.CategoryNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BindingResultErrorDTO handleBindException(BindException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return new BindingResultErrorDTO(errors);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Category already exists");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ErrorDTO(404, "Category with given id does not exist");
    }

}
