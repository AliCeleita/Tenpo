package com.challenge.tenpo.service.implement;

import com.challenge.tenpo.exception.Errors;
import com.challenge.tenpo.exception.UnavailableException;
import com.challenge.tenpo.service.ExternalService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Cacheable(value = "porcentaje")
    @Override
    public double getPercentage(){
        try{
            double percentage = 10.0;
            return updatePercentage(percentage);
        }catch (Exception e) {
            throw new UnavailableException(Errors.ERROR_PERCENTAGE.getMessage());
        }
    }

    @CachePut(value = "porcentaje")
    public double updatePercentage(double newPercentage) {
        return newPercentage;
    }
}
