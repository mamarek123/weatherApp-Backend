package com.mamarek123.domain.service.implementaion;

import com.mamarek123.domain.energyCalculator.ExpectedEnergyGenerationCalculator;
import com.mamarek123.domain.meteoIntegration.response.MeteoForecastResponse;
import com.mamarek123.domain.meteoIntegration.OpenMeteoService;
import com.mamarek123.domain.service.ForecastService;
import com.mamarek123.infrastructure.model.ForecastResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final OpenMeteoService openMeteoService;
    private final ExpectedEnergyGenerationCalculator energyGenerationCalculator;

    @Override
    public ForecastResponse getForecast(Double latitude, Double longitude) {
        MeteoForecastResponse meteoResponse = openMeteoService.getMeteoForecastResponse(latitude, longitude);

        double weeklySunshineDurationTotal = getWeeklySunshineDuration(meteoResponse);

        double weeklyGeneratedEnergy = energyGenerationCalculator.calculate(weeklySunshineDurationTotal);

        return ForecastResponse.builder()
                .weatherCodes(meteoResponse.getDaily().getWeather_code())
                .minTemperatures(meteoResponse.getDaily().getTemperature_2m_min())
                .maxTemperatures(meteoResponse.getDaily().getTemperature_2m_max())
                .dates(meteoResponse.getDaily().getTime())
                .generatedEnergy(weeklyGeneratedEnergy)
                .build();
    }

    private double getWeeklySunshineDuration(MeteoForecastResponse meteoResponse) {
        return meteoResponse.getDaily().getSunshine_duration().stream().mapToDouble(Double::doubleValue).sum();
    }
}
