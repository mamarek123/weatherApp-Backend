package com.mamarek123.domain.meteoIntegration.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MeteoForecastResponse {

    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private DailyUnits daily_units;
    private Daily daily;

    @Data
    public static class DailyUnits {
        private String time;
        private String weather_code;
        private String temperature_2m_max;
        private String temperature_2m_min;
        private String daylight_duration;
        private String sunshine_duration;
        private String precipitation_sum;
        private String precipitation_hours;
        private String precipitation_probability_max;
    }

    @Data
    public static class Daily {
        private List<LocalDate> time;
        private List<Integer> weather_code;
        private List<Double> temperature_2m_max;
        private List<Double> temperature_2m_min;
        private List<Double> daylight_duration;
        private List<Double> sunshine_duration;
        private List<Double> precipitation_sum;
        private List<Integer> precipitation_hours;
        private List<Integer> precipitation_probability_max;
    }
}
