package com.mamarek123.domain.meteoIntegration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamarek123.domain.meteoIntegration.response.MeteoForecastResponse;
import com.mamarek123.domain.meteoIntegration.response.MeteoSummaryResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenMetoServiceImpl implements OpenMeteoService{
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    public OpenMetoServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        webClient = WebClient.builder()
                .baseUrl("https://api.open-meteo.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    //example URL
    //https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=weather_code,temperature_2m_max,temperature_2m_min,daylight_duration,sunshine_duration,precipitation_sum,precipitation_hours,precipitation_probability_max
    private final static String FORECAST_DAILY = "weather_code,temperature_2m_max,temperature_2m_min,daylight_duration,sunshine_duration,precipitation_sum,precipitation_hours,precipitation_probability_max";

    @Override
    public MeteoForecastResponse getMeteoForecastResponse(Double latitude, Double longitude) {
        String url = UriComponentsBuilder.fromPath("/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily", FORECAST_DAILY)
                .toUriString();

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return objectMapper.readValue(response, MeteoForecastResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    //example URL
    //https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=surface_pressure&daily=temperature_2m_max,temperature_2m_min,sunshine_duration,precipitation_sum
    private final static String SUMMARY_DAILY = "temperature_2m_max,temperature_2m_min,sunshine_duration,precipitation_sum";
    private final static String SUMMARY_HOURLY = "surface_pressure";
    @Override
    public MeteoSummaryResponse getMeteoSummaryResponse(Double latitude, Double longitude) {
        String url = UriComponentsBuilder.fromPath("/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily", SUMMARY_DAILY)
                .queryParam("hourly",SUMMARY_HOURLY)
                .toUriString();

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return objectMapper.readValue(response, MeteoSummaryResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
