package com.mamarek123.infrastructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamarek123.domain.meteoIntegration.OpenMeteoService;
import com.mamarek123.domain.meteoIntegration.response.MeteoForecastResponse;
import com.mamarek123.domain.meteoIntegration.response.MeteoSummaryResponse;

public class OpenMeteoServiceTestImpl implements OpenMeteoService,OpenMetoResponses {

    public OpenMeteoServiceTestImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    ObjectMapper objectMapper;

    @Override
    public MeteoForecastResponse getMeteoForecastResponse(Double latitude, Double longitude) {
        try {
            return objectMapper.readValue(FORECAST_RESPONSE, MeteoForecastResponse.class );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MeteoSummaryResponse getMeteoSummaryResponse(Double latitude, Double longitude) {
        try {
            return objectMapper.readValue(SUMMARY_RESPONSE, MeteoSummaryResponse.class );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
