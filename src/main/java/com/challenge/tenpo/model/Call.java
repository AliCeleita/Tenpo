package com.challenge.tenpo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Call {

    @Schema(description = "Id de registro", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Fecha cuando se hizo el registro", example = "2024-03-03 01:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @Schema(description = "Servicio que se llamó", example = "http://localhost:8080/consultar")
    private String endpoint;

    @Schema(description = "Parámetros enviados al servicio", example = "12/85")
    @Column(columnDefinition = "TEXT")
    private String parametros;

    @Schema(description = "Mensaje retornado en el servicio, ya sea la respuesta o un error ", example = "{\\\"headers\\\":{},\\\"body\\\":66.54313621195776,\\\"statusCode\\\":\\\"OK\\\",\\\"statusCodeValue\\\":200}")
    @Column(columnDefinition = "TEXT")
    private String mensajeRespuesta;

    public Call() {
        this.fecha = LocalDateTime.now();
    }

    public Call(String endpoint, String parametros, String mensajeRespuesta) {
        this.endpoint = endpoint;
        this.parametros = parametros;
        this.mensajeRespuesta = mensajeRespuesta;
        this.fecha = LocalDateTime.now();
    }
}
