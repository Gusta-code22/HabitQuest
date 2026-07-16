package br.com.Gusta_code22.habitquest.common;

import br.com.Gusta_code22.habitquest.dto.ApiError;
import br.com.Gusta_code22.habitquest.exception.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice(basePackages = "br.com.Gusta_code22.habitquest.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(HabitAlreadyExecutedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHabitAlreadyExecutedException(HabitAlreadyExecutedException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(InvalidHabitDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidHabitData(InvalidHabitDataException ex) {
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFoundException(UserNotFoundException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(HabitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleHabitNotFoundException(HabitNotFoundException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }

    @Hidden
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception e){
        String message = String.format("Internal Error: %s. Contact the administration",
                e.getMessage());
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(message))
                .build();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException(ForbiddenException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.FORBIDDEN.value())
                .status(HttpStatus.FORBIDDEN.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(InvalidHeroCredentialsException.class)
    public ApiError handleInvalidHeroCredentialsException(InvalidHeroCredentialsException ex){
        return ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .errors(List.of(ex.getMessage()))
                .build();
    }


}
