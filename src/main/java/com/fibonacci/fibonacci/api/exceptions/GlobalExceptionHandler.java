package com.fibonacci.fibonacci.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que maneja globalmente las excepciones en la aplicación.
 * Esta clase es responsable de capturar excepciones lanzadas en cualquier parte de la aplicación
 * y devolver una respuesta adecuada al cliente.
 * En este caso, maneja específicamente las excepciones {@link FibonacciExceptions}.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Este método captura las excepciones de {@link FibonacciExceptions} y devuelve un mensaje de error
     * con el estado HTTP 400 (Bad Request) al cliente.
     *
     * @param message La excepción {@link FibonacciExceptions} que contiene el mensaje de error.
     * @return Una respuesta con el mensaje de la excepción y el estado HTTP 400 (Bad Request).
     */
    @ExceptionHandler(FibonacciExceptions.class)
    public ResponseEntity<String> handleFibonacciInputException(FibonacciExceptions message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
