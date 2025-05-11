package com.fibonacci.fibonacci.api.persistance.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa las estadísticas de consultas realizadas a un número de Fibonacci específico.
 * Almacena cuántas veces se ha consultado un valor de n.
 */
@Entity
@Table(name = "fibonacci_query_stadistics")
public class FibonacciQueryStadisticsEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "n", unique = true, nullable = false)
        private int n;

        @Column(name = "query_count", nullable = false)
        private int queryCount;

        @Column(name = "created_at_time", nullable = false)
        private LocalDateTime createdAtTime;

        @Column(name = "updated_at_time", nullable = false)
        private LocalDateTime updatedAtTime;

        /**
         * Obtiene el ID único de la entidad.
         *
         * @return el ID de la entidad.
         */
        public Long getId() {
                return id;
        }

        /**
         * Obtiene el valor de n para el cual se realizaron consultas.
         *
         * @return el valor de n.
         */
        public int getN() {
                return n;
        }

        /**
         * Establece el valor de n para el cual se realizarán estadísticas de consulta.
         *
         * @param n el número de Fibonacci consultado.
         */
        public void setN(int n) {
                this.n = n;
        }

        /**
         * Obtiene el número de veces que se ha consultado el valor de n.
         *
         * @return la cantidad de consultas realizadas.
         */
        public int getQueryCount() {
                return queryCount;
        }

        /**
         * Establece la cantidad de veces que se ha consultado el valor de n.
         *
         * @param queryCount el número de consultas.
         */
        public void setQueryCount(int queryCount) {
                this.queryCount = queryCount;
        }

        /**
         * Obtiene la fecha y hora en que se creó esta estadística.
         *
         * @return la fecha y hora de creación.
         */
        public LocalDateTime getCreatedAtTime() {
                return createdAtTime;
        }

        /**
         * Establece la fecha y hora de creación de esta estadística.
         *
         * @param createdAtTime la fecha y hora de creación.
         */
        public void setCreatedAtTime(LocalDateTime createdAtTime) {
                this.createdAtTime = createdAtTime;
        }

        /**
         * Obtiene la fecha y hora en que se actualizó esta estadística por última vez.
         *
         * @return la fecha y hora de última actualización.
         */
        public LocalDateTime getUpdatedAtTime() {
                return updatedAtTime;
        }

        /**
         * Establece la fecha y hora de la última actualización de esta estadística.
         *
         * @param updatedAtTime la fecha y hora de actualización.
         */
        public void setUpdatedAtTime(LocalDateTime updatedAtTime) {
                this.updatedAtTime = updatedAtTime;
        }
}
