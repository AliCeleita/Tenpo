package com.challenge.tenpo.service.implement;

import com.challenge.tenpo.model.Call;
import com.challenge.tenpo.repository.CallHistoryRepository;
import com.challenge.tenpo.service.CallHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CallHistoryServiceImpl implements CallHistoryService {

    private final CallHistoryRepository callHistoryRepository;
    private final ObjectMapper objectMapper;

    private final Set<String> registroLlamadas = ConcurrentHashMap.newKeySet();

    public CallHistoryServiceImpl(CallHistoryRepository callHistoryRepository, ObjectMapper objectMapper) {
        this.callHistoryRepository = callHistoryRepository;
        this.objectMapper = objectMapper;
    }

    @Async
    @Override
    public CompletableFuture<Void> registrarLlamada(String endpoint, Object parametros, Object respuesta) {
        String key = endpoint + "-" + parametros.hashCode();

        if (registroLlamadas.contains(key)) {
            return CompletableFuture.completedFuture(null);
        }

        registroLlamadas.add(key);

        try {
            Call call = new Call(endpoint, objectMapper.writeValueAsString(parametros), objectMapper.writeValueAsString(respuesta));
            callHistoryRepository.save(call);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(5000);
                    registroLlamadas.remove(key);
                } catch (InterruptedException ignored) { }
            });
        }

        return CompletableFuture.completedFuture(null);
    }

    public Page<Call> getHistory(Pageable pageable){
        return callHistoryRepository.findAllByOrderByFechaDesc(pageable);
    }
}
