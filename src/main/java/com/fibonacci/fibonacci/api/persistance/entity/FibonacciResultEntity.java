package com.fibonacci.fibonacci.api.persistance.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa el resultado del cálculo de un número de Fibonacci.
 */
@Entity
@Table(name = "fibonacci_results")
public class FibonacciResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "n", nullable = false)
    private Integer n;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "created_at_time", nullable = false)
    private LocalDateTime createdAtTime;

    @Column(name = "updated_at_time", nullable = false)
    private LocalDateTime updatedAtTime;

    /**
     * Constructor (JPA lo requiere).
     */
    public FibonacciResultEntity() {}

    /**
     * Obtiene el valor de n utilizado en el cálculo.
     *
     * @return el valor de n.
     */
    public Integer getN() {
        return n;
    }

    /**
     * Establece el valor de n utilizado en el cálculo.
     *
     * @param n el valor de entrada n.
     */
    public void setN(Integer n) {
        this.n = n;
    }

    /**
     * Obtiene el resultado del cálculo del número de Fibonacci.
     *
     * @return el valor como string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Establece el resultado del número de Fibonacci.
     *
     * @param value el valor como string.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene la fecha y hora en que se creó el registro.
     *
     * @return fecha de creación.
     */
    public LocalDateTime getCreatedAtTime() {
        return createdAtTime;
    }

    /**
     * Establece la fecha y hora de creación del registro.
     *
     * @param createdAtTime fecha de creación.
     */
    public void setCreatedAtTime(LocalDateTime createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    /**
     * Obtiene la fecha y hora en que se actualizó el registro por última vez.
     *
     * @return fecha de actualización.
     */
    public LocalDateTime getUpdatedAtTime() {
        return updatedAtTime;
    }

    /**
     * Establece la fecha y hora de la última actualización del registro.
     *
     * @param updatedAtTime fecha de actualización.
     */
    public void setUpdatedAtTime(LocalDateTime updatedAtTime) {
        this.updatedAtTime = updatedAtTime;
    }

    /**
     * Retorna una representación en texto del objeto.
     *
     * @return una cadena representando la entidad.
     */
    @Override
    public String toString() {
        return "FibonacciResultEntity{" +
                "id=" + id +
                ", n=" + n +
                ", value='" + value + '\'' +
                '}';
    }
}