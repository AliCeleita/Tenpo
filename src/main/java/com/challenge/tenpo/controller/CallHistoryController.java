package com.challenge.tenpo.controller;

import com.challenge.tenpo.model.Call;
import com.challenge.tenpo.service.CallHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historiales")
@Tag(name = "Historial de llamadas", description = "Operaciones CRUD del historial de llamadas")
public class CallHistoryController {

    private final CallHistoryService callHistoryService;

    @Operation(summary = "Obtener historial de llamadas", description = "Retorna el historial de todas las llamadas de los servicios del programa, sus parámetros y su resultado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna el historial de llamadas, según paginación")
    })
    @GetMapping()
    public ResponseEntity<Page<Call>> getAllCalls(Pageable pageable){
        Page<Call> calls = callHistoryService.getHistory(pageable);
        return new ResponseEntity<>(calls, HttpStatus.OK);
    }
}
