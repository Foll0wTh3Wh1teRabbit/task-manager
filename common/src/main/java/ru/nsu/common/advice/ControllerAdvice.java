package ru.nsu.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nsu.common.dto.Error;

@Slf4j
@RestControllerAdvice(basePackages = "ru.nsu")
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error exceptionHandler(RuntimeException e) {
        log.warn(e.getMessage());

        return new Error(e.getMessage());
    }

}
