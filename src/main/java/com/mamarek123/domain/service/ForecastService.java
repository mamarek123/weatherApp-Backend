package com.mamarek123.domain.service;

import com.mamarek123.infrastructure.model.ForecastResponse;


public interface ForecastService {
    
    ForecastResponse getForecast(Double latitude, Double longitude);
    
}
