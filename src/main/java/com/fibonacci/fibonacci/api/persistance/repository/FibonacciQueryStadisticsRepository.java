package com.fibonacci.fibonacci.api.persistance.repository;

import com.fibonacci.fibonacci.api.persistance.entity.FibonacciQueryStadisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Repositorio para la entidad {@link FibonacciQueryStadisticsEntity}.
 */
@Repository
public interface FibonacciQueryStadisticsRepository extends JpaRepository<FibonacciQueryStadisticsEntity, Long> {

    /**
     * Busca una entidad {@link FibonacciQueryStadisticsEntity} por el valor de {@code n}.
     *
     * @param n el número del input de entrada.
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no.
     */
    Optional<FibonacciQueryStadisticsEntity> findByN(int n);
}