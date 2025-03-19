package com.challenge.tenpo.service.implement;

import com.challenge.tenpo.service.CalculateService;
import com.challenge.tenpo.service.ExternalService;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final ExternalService externalService;

    public CalculateServiceImpl(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public Double calculate(int num1, int num2) {

        int sum = num1 + num2;

        if(sum<0){
            throw new IllegalArgumentException("La suma de los números no puede ser negativa");
        }

        double percentage = externalService.getPercentage();
        return sum + (sum * (percentage/100));
    }
}
