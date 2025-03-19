package com.challenge.tenpo.controller;

import com.challenge.tenpo.service.CalculateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculos")
@Tag(name = "Cálculos", description = "Operaciones de cálculos")
public class CalculateController {

    private final CalculateService calculateService;

    @Operation(summary = "Realizar cálculo", description = "Suma dos parámetros dados por el usuario y adiciona un porcentaje obtenido por un servicio externo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna el resultado de la operacion"),
            @ApiResponse(responseCode = "400", description = "La suma de los números no puede ser negativa"),
            @ApiResponse(responseCode = "500", description = "No se pudo obtener el porcentaje y no hay caché disponible")
    })
    @PostMapping("/{num1}/{num2}")
    public ResponseEntity<Double> calculate( @Parameter(description = "Número 1 para sumar", example = "15")
                                             @PathVariable int num1,
                                             @Parameter(description = "Número 2 para sumar", example = "2")
                                             @PathVariable int num2) {
        Double resultado = calculateService.calculate(num1, num2);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
