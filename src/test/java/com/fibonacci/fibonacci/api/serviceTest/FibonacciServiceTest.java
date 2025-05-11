package com.fibonacci.fibonacci.api.serviceTest;

import com.fibonacci.fibonacci.api.exceptions.FibonacciExceptions;
import com.fibonacci.fibonacci.api.persistance.entity.FibonacciQueryStadisticsEntity;
import com.fibonacci.fibonacci.api.persistance.entity.FibonacciResultEntity;
import com.fibonacci.fibonacci.api.persistance.repository.FibonacciQueryStadisticsRepository;
import com.fibonacci.fibonacci.api.persistance.repository.FibonacciResultRepository;
import com.fibonacci.fibonacci.api.service.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @Mock
    private FibonacciResultRepository resultRepository;

    @Mock
    private FibonacciQueryStadisticsRepository stadisticsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateFibonacciForZero() throws FibonacciExceptions {
        int n = 0;
        FibonacciResultEntity result = new FibonacciResultEntity();
        result.setN(n);
        result.setValue("0");
        result.setCreatedAtTime(LocalDateTime.now());
        result.setUpdatedAtTime(LocalDateTime.now());

        when(resultRepository.findByN(n)).thenReturn(null);
        when(resultRepository.save(any(FibonacciResultEntity.class))).thenReturn(result);

        // Act
        String resultStr = fibonacciService.calculateFibonacci(n);

        // Assert
        assertEquals("El resultado para el valor 0 es: 0", resultStr);
        verify(resultRepository, times(1)).save(any(FibonacciResultEntity.class));
        verify(stadisticsRepository, times(1)).save(any(FibonacciQueryStadisticsEntity.class));
    }

    @Test
    void shouldThrowExceptionForNegativeNumber() {
        // Arrange
        int n = -5;

        // Act & Assert
        FibonacciExceptions exception = assertThrows(FibonacciExceptions.class, () -> fibonacciService.calculateFibonacci(n));
        assertEquals("n debe ser >= 0", exception.getMessage());
    }

    @Test
    void shouldReturnCachedResultIfExists() throws FibonacciExceptions {
        // Arrange
        int n = 5;
        FibonacciResultEntity cachedResult = new FibonacciResultEntity();
        cachedResult.setN(n);
        cachedResult.setValue("5");
        cachedResult.setCreatedAtTime(LocalDateTime.now());
        cachedResult.setUpdatedAtTime(LocalDateTime.now());

        when(resultRepository.findByN(n)).thenReturn(cachedResult);

        // Act
        String resultStr = fibonacciService.calculateFibonacci(n);

        // Assert
        assertEquals("El resultado para el valor 5 es: 5", resultStr);
        verify(resultRepository, never()).save(any(FibonacciResultEntity.class));
        verify(stadisticsRepository, times(1)).save(any(FibonacciQueryStadisticsEntity.class));
    }

    @Test
    void shouldCalculateAndSaveIntermediateResults() throws FibonacciExceptions {
        // Arrange
        int n = 10;
        FibonacciResultEntity intermediateResult = new FibonacciResultEntity();
        intermediateResult.setCreatedAtTime(LocalDateTime.now());
        intermediateResult.setUpdatedAtTime(LocalDateTime.now());

        // No está en cache
        when(resultRepository.findByN(n)).thenReturn(null);
        when(resultRepository.save(any(FibonacciResultEntity.class))).thenReturn(intermediateResult);

        // Act
        fibonacciService.calculateFibonacci(n);

        // Assert
        verify(resultRepository, times(n - 1)).save(any(FibonacciResultEntity.class));
    }

    @Test
    void shouldUpdateQueryStatisticsForExistingN() {

        int n = 8;
        FibonacciQueryStadisticsEntity existingEntity = new FibonacciQueryStadisticsEntity();
        existingEntity.setN(n);
        existingEntity.setQueryCount(1);

        when(stadisticsRepository.findByN(n)).thenReturn(Optional.of(existingEntity));
        when(stadisticsRepository.save(any(FibonacciQueryStadisticsEntity.class))).thenReturn(existingEntity);

        // Act
        fibonacciService.calculateFibonacci(n);

        // Assert
        verify(stadisticsRepository, times(1)).save(any(FibonacciQueryStadisticsEntity.class));
        assertEquals(2, existingEntity.getQueryCount());
    }

    @Test
    void shouldCreateNewStatisticsIfNotExist() {
        // Arrange
        int n = 10;
        when(stadisticsRepository.findByN(n)).thenReturn(Optional.empty());

        FibonacciQueryStadisticsEntity newEntity = new FibonacciQueryStadisticsEntity();
        newEntity.setN(n);
        newEntity.setQueryCount(1);
        when(stadisticsRepository.save(any(FibonacciQueryStadisticsEntity.class))).thenReturn(newEntity);

        // Act
        fibonacciService.calculateFibonacci(n);

        // Assert
        verify(stadisticsRepository, times(1)).save(any(FibonacciQueryStadisticsEntity.class));
        assertEquals(1, newEntity.getQueryCount());
    }
}
