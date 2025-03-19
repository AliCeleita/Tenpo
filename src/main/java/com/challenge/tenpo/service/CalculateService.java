package com.challenge.tenpo.service;

import org.springframework.stereotype.Component;

@Component
public interface CalculateService {
    Double calculate(int num1, int num2);
}
