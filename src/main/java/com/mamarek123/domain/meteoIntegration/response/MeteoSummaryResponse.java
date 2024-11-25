package com.mamarek123.domain.meteoIntegration.response;

import lombok.Data;

import java.util.List;

@Data
public class MeteoSummaryResponse {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private HourlyUnits hourly_units;
    private Hourly hourly;
    private DailyUnits daily_units;
    private Daily daily;

    @Data
    public static class HourlyUnits {
        private String time;
        private String surface_pressure;
    }
    @Data
    public static class Hourly {
        private List<String> time;
        private List<Double> surface_pressure;
    }
    @Data
    public static class DailyUnits {
        private String time;
        private String temperature_2m_max;
        private String temperature_2m_min;
        private String sunshine_duration;
        private String precipitation_sum;
    }
    @Data
    public static class Daily {
        private List<String> time;
        private List<Double> temperature_2m_max;
        private List<Double> temperature_2m_min;
        private List<Double> sunshine_duration;
        private List<Double> precipitation_sum;
    }
}
