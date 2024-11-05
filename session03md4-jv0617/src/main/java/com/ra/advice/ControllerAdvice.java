package com.ra.advice;

import com.ra.model.dto.errors.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerErrorValidException(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        return new  DataError<>(map,400);
    }

    // bắt hết câc mã lỗi exception ở đây
    /**
     *
     * not found 404
     *
     * exist data 409 conflict
     *
     * max upload file MaxUploadSizeExceededException
     *
     * */
}
