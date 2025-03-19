package com.challenge.tenpo.exception;

import lombok.Getter;

@Getter
public enum Errors {

    ERROR_PERCENTAGE("No se pudo obtener el porcentaje y no hay caché disponible");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

}
