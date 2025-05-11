package com.fibonacci.fibonacci.api.controller;

import com.fibonacci.fibonacci.api.service.FibonacciService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class FibonacciController {

    /**
     * fibonacciService el fibonacciService.
     */
    private final FibonacciService fibonacciService;

    /**
     * Constructor.
     * @param fibonacciService El servicio encargado de calcular el número de Fibonacci.
     */
    public FibonacciController(final FibonacciService fibonacciService) {
        this.fibonacciService = Objects.requireNonNull(fibonacciService, "FibonacciService cannot be null");
    }

    /**
     * Endpoint GET que calcula el enesimo número de Fibonacci.
     * Recibe un parámetro (n) a través de la solicitud HTTP y delega el cálcul al servicio {@link FibonacciService}.
     *
     * @param n El valor de la posición para la que se desea calcular el número de Fibonacci.
     * @return El número de Fibonacci correspondiente a la posición 'n'.
     */
    @GetMapping("/fibonacci")
    public String getFibonacci(@RequestParam final int n) {
        return fibonacciService.calculateFibonacci(n);
    }
}
