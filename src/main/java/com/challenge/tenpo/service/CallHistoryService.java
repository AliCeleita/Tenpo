package com.challenge.tenpo.service;

import com.challenge.tenpo.model.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public interface CallHistoryService {
    CompletableFuture<Void> registrarLlamada(String endpoint, Object parametros, Object respuesta);
    Page<Call> getHistory(Pageable pageable);
}
