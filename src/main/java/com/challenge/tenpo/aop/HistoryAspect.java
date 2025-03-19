package com.challenge.tenpo.aop;

import com.challenge.tenpo.service.CallHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class HistoryAspect {

    private final CallHistoryService callHistoryService;

    @AfterReturning(pointcut = "execution(* com.challenge.tenpo.controller..*.*(..))", returning = "response")
    public void logExitoso(JoinPoint joinPoint, Object response) {
        procesarLlamada(joinPoint, response);
    }

    @AfterThrowing(pointcut = "execution(* com.challenge.tenpo.controller..*.*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        procesarLlamada(joinPoint, ex.getMessage());
    }

    private void procesarLlamada(JoinPoint joinPoint, Object respuesta) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String url = request.getRequestURI();
        Object[] args = joinPoint.getArgs();

        callHistoryService.registrarLlamada(url, Arrays.toString(args), respuesta);
    }

}
