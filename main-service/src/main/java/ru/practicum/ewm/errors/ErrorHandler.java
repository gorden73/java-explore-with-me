package ru.practicum.ewm.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.exceptions.BadRequestException;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.ForbiddenException;
import ru.practicum.ewm.exceptions.NotFoundException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        log.error("Ошибка, {}", e.getMessage());
        return new ApiError(e, ErrorStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final MethodArgumentNotValidException e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, "Ошибка валидации входных данных.", "Неверно заполнены поля.",
                ErrorStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final ConstraintViolationException e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, "Ошибка валидации входных данных.", "Неверно заполнены поля.",
                ErrorStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, ErrorStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException(final ForbiddenException e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, ErrorStatus.FORBIDDEN);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final ConflictException e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, ErrorStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInternalServerErrorException(final Throwable e) {
        log.error("Ошибка: {}", e.getMessage());
        return new ApiError(e, e.getMessage(), ErrorStatus.INTERNAL_SERVER_ERROR);
    }
}
