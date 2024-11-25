package com.mamarek123.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ForecastResponse {
    @JsonProperty("date")
    private List<LocalDate> dates;

    @JsonProperty("weather_code")
    private List<Integer> weatherCodes;

    @JsonProperty("temperature_2m_max")
    private List<Double> maxTemperatures;

    @JsonProperty("temperature_2m_min")
    private List<Double> minTemperatures;

    @JsonProperty("generated_energy")
    private Double generatedEnergy;
}
