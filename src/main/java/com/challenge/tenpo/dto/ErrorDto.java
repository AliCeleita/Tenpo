package com.challenge.tenpo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class ErrorDto {

    @Schema(description = "Código del error", example = "200")
    private int statusCode;

    @Schema(description = "Mensaje del error", example = "OK")
    private String message;

    @Schema(description = "Detalles del error")
    private String details;

    private ErrorDto() {}
}
