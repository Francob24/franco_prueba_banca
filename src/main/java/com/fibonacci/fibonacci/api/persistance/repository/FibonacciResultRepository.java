package com.fibonacci.fibonacci.api.persistance.repository;

import com.fibonacci.fibonacci.api.persistance.entity.FibonacciQueryStadisticsEntity;
import com.fibonacci.fibonacci.api.persistance.entity.FibonacciResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Repositorio para la entidad {@link FibonacciResultEntity}.
 */
@Repository
public interface FibonacciResultRepository extends JpaRepository<FibonacciResultEntity, Long> {

    /**
     * Busca una entidad {@link FibonacciResultEntity} por el valor de {@code n}.
     *
     * @param n el número del input de entrada.
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no.
     */
    FibonacciResultEntity findByN(int n);
}