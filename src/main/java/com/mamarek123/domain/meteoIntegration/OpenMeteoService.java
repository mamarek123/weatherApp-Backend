package com.mamarek123.domain.meteoIntegration;

import com.mamarek123.domain.meteoIntegration.response.MeteoForecastResponse;
import com.mamarek123.domain.meteoIntegration.response.MeteoSummaryResponse;

public interface OpenMeteoService {
  MeteoForecastResponse getMeteoForecastResponse(Double latitude, Double longitude);

  MeteoSummaryResponse getMeteoSummaryResponse(Double latitude, Double longitude);
}
