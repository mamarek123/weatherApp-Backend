package com.mamarek123.domain.service.implementaion;

import com.mamarek123.domain.meteoIntegration.response.MeteoSummaryResponse;
import com.mamarek123.domain.meteoIntegration.OpenMeteoService;
import com.mamarek123.domain.service.SummaryService;
import com.mamarek123.infrastructure.model.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final OpenMeteoService openMeteoService;

    @Override
    public SummaryResponse getWeeklySummary(Double latitude, Double longitude) {
        MeteoSummaryResponse meteoSummaryResponse = openMeteoService.getMeteoSummaryResponse(latitude, longitude);

        //zakładamy że dzień z 4mm opadów(precipitaion) jest z opadami i co najmniej 4 takie dni w tygodni będą oznaczać tydzień z opadami (może i nienajlepsze kryterium ale to narazie tylko demo)

        String precipitaion;
        long rainyDais = meteoSummaryResponse.getDaily().getPrecipitation_sum().stream().filter(x -> x < 4).count();
        if (rainyDais >= 4) precipitaion = "A week with precipitation";
        else precipitaion = "A week with no precipitation";


        return SummaryResponse.builder()
                .averagePressure(meteoSummaryResponse.getHourly().getSurface_pressure().stream().mapToDouble(Double::doubleValue).average().orElseThrow(() -> new RuntimeException("Unexpected problem while calculating average pressure")))
                .averageSunExpositionTime(meteoSummaryResponse.getDaily().getSunshine_duration().stream().mapToDouble(Double::doubleValue).average().orElseThrow(() -> new RuntimeException("Unexpected problem while calculating average sunshine")))
                .maxTemperature(meteoSummaryResponse.getDaily().getTemperature_2m_max().stream().max(Double::compare).orElseThrow(() -> new RuntimeException("Unexpected problem while looking for max temp")))
                .minTemperature(meteoSummaryResponse.getDaily().getTemperature_2m_min().stream().min(Double::compare).orElseThrow(() -> new RuntimeException("Unexpected problem while looking for min temp")))
                .precipitation(precipitaion)
                .date(LocalDate.now())
                .build();
    }

}
