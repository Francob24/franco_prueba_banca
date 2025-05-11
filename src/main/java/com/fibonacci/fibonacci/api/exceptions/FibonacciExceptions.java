package com.fibonacci.fibonacci.api.exceptions;

/**
 * Excepción personalizada para manejar errores específicos relacionados con el cálculo de Fibonacci.
 * Esta excepción se lanza cuando el valor proporcionado para calcular el número de Fibonacci no es válido
 * (por ejemplo, si el valor es negativo).
 */
public class FibonacciExceptions extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message El mensaje que describe el error ocurrido.
     */
    public FibonacciExceptions(String message) {
        super(message);
    }
}