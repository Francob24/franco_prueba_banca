package com.fibonacci.fibonacci.api.service;

import com.fibonacci.fibonacci.api.exceptions.FibonacciExceptions;
import com.fibonacci.fibonacci.api.persistance.entity.FibonacciQueryStadisticsEntity;
import com.fibonacci.fibonacci.api.persistance.entity.FibonacciResultEntity;
import com.fibonacci.fibonacci.api.persistance.repository.FibonacciQueryStadisticsRepository;
import com.fibonacci.fibonacci.api.persistance.repository.FibonacciResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Servicio encargado de calcular el enesimo número de Fibonacci.
 * Este servicio proporciona la lógica para calcular el número de Fibonacci
 * Y Guarda en base de datos la informacion relevante.
 */
@Service
@SpringBootApplication(scanBasePackages = "com.fibonacci.fibonacci")
public class FibonacciService {

    /**
     * Logger El logger.
     */
    @NotNull
    private final Logger logger = LoggerFactory.getLogger(FibonacciService.class);

    /**
     * resultRepository El resultRepository.
     */
    @NotNull
    private final FibonacciResultRepository resultRepository;

    /**
     * resultRepository El resultRepository.
     */
    @NotNull
    private final FibonacciQueryStadisticsRepository stadisticsRepository;

    /**
     * Constructor.
     *
     * @param resultRepository el resultRepository.
     * @param stadisticsRepository el stadisticsRepository.
     *
     */
    public FibonacciService(@NotNull final FibonacciResultRepository resultRepository, @NotNull final FibonacciQueryStadisticsRepository stadisticsRepository) {
        this.resultRepository = Objects.requireNonNull(resultRepository, "resultRepository cannot be null");
        this.stadisticsRepository = Objects.requireNonNull(stadisticsRepository, "stadisticsRepository cannot be null");
    }

    /**
     * Calcula el enesimo número de Fibonacci.
     * Si el valor de (n) es negativo, se lanzará {@link FibonacciExceptions}.
     * Si (n) es 0 o 1, retorna n.
     * Para otros valores de (n), realiza el calculo de Fibonacci.
     *
     * @param n el valor de la posición para calcular el número de Fibonacci.
     * @return El número de Fibonacci correspondiente a la posición (n).
     * @throws FibonacciExceptions Si el valor de (n) es negativo.
     */
    public String calculateFibonacci(int n) throws FibonacciExceptions {
        StringBuilder sb = new StringBuilder();

        logger.info("Calculando fibonacci para n = {} ", n);
        if (n < 0) {
            logger.error("Se recibió un valor negativo: {}", n);
            throw new FibonacciExceptions("n debe ser >= 0");
        }

        FibonacciResultEntity cachedResult = resultRepository.findByN(n);
        if (cachedResult != null) {
            logger.info("Resultado obtenido de la cache para n = {}", n);
            updateQueryStadistics(n);
            return "El resultado para el valor " + n + " es: " + cachedResult.getValue();
        }

        if (n == 0 || n == 1) {
            FibonacciResultEntity result = new FibonacciResultEntity();
            result.setN(n);
            result.setValue(String.valueOf(n));
            result.setCreatedAtTime(LocalDateTime.now());
            result.setUpdatedAtTime(LocalDateTime.now());
            resultRepository.save(result);

            sb.append("El resultado para el valor ").append(n).append(" es: ").append(n);
            logger.info("Resultado exitoso para n = {}, el resultado es {}", n, n);
            updateQueryStadistics(n);
            return sb.toString();
        }

        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;

            //Guarda resultados intermedios (espero que sea asi por lo que entendi)
            FibonacciResultEntity intermediate = new FibonacciResultEntity();
            intermediate.setN(i);
            intermediate.setValue(String.valueOf(b));
            intermediate.setCreatedAtTime(LocalDateTime.now());
            intermediate.setUpdatedAtTime(LocalDateTime.now());
            resultRepository.save(intermediate);
            logger.info("Resultado guardado para n = {}, el resultado es {}", n, b);
        }
        logger.info("Resultado exitoso para n = {}, el resultado es {}", n, b);
        updateQueryStadistics(n); //
        return sb.append("El resultado para el valor ").append(n).append(" es : ").append(b).toString();
    }

    private void updateQueryStadistics(int n) {
        Optional<FibonacciQueryStadisticsEntity> existingOpt = stadisticsRepository.findByN(n);

        if (existingOpt.isPresent()) {
            FibonacciQueryStadisticsEntity existing = existingOpt.get();
            existing.setQueryCount(existing.getQueryCount() + 1);
            existing.setUpdatedAtTime(LocalDateTime.now());
            logger.info("ya existe el valor ingresado para {}", n);
            stadisticsRepository.save(existing);
        } else {
            FibonacciQueryStadisticsEntity newEntry = new FibonacciQueryStadisticsEntity();
            newEntry.setN(n);
            newEntry.setQueryCount(1);
            newEntry.setCreatedAtTime(LocalDateTime.now());
            newEntry.setUpdatedAtTime(LocalDateTime.now());
            logger.info("el valor es nuevo y se guarda {}", n);
            stadisticsRepository.save(newEntry);
        }
    }
}
