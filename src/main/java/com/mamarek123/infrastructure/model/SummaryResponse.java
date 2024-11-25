package com.mamarek123.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SummaryResponse {
    private LocalDate date;

    @JsonProperty("average_pressure")
    private double averagePressure;

    @JsonProperty("average_sun_exposition_time")
    private double averageSunExpositionTime;

    @JsonProperty("max_temperature")
    private double maxTemperature;

    @JsonProperty("min_temperature")
    private double minTemperature;

    private String precipitation;
}
