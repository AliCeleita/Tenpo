package com.challenge.tenpo.service.impl;

import com.challenge.tenpo.exception.Errors;
import com.challenge.tenpo.exception.UnavailableException;
import com.challenge.tenpo.service.implement.CalculateServiceImpl;
import com.challenge.tenpo.service.implement.ExternalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CalculateServiceTest {

    private CalculateServiceImpl calculateService;
    private ExternalServiceImpl externalService;

    @BeforeEach
    void setUp() {
        externalService = Mockito.mock(ExternalServiceImpl.class);
        calculateService = new CalculateServiceImpl(externalService);
    }

    @Test
    void testCalculateWithValidNumbers() {
        when(externalService.getPercentage()).thenReturn(10.0);
        double result = calculateService.calculate(100, 50);
        assertEquals(165.0, result, "El resultado debe ser 165.0 con un 10% adicional");
    }

    @Test
    void testCalculateWithZeroPercentage() {
        when(externalService.getPercentage()).thenReturn(0.0);
        double result = calculateService.calculate(100, 50);
        assertEquals(150.0, result, "El resultado debe ser 150.0 sin porcentaje adicional");
    }

    @Test
    void testCalculateThrowsExceptionForNegativeSum() {
        assertThrows(IllegalArgumentException.class, () -> calculateService.calculate(-100, -50), "Debe lanzar IllegalArgumentException para sumas negativas");
    }

    @Test
    void testCalculateHandlesExternalServiceException() {
        when(externalService.getPercentage()).thenThrow(new UnavailableException(Errors.ERROR_PERCENTAGE.getMessage()));
        assertThrows(UnavailableException.class, () -> calculateService.calculate(50, 50), "Debe lanzar UnavailableException si falla el servicio externo");
    }
}
