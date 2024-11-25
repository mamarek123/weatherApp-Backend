package com.mamarek123.infrastructure;

import com.mamarek123.domain.service.ForecastService;
import com.mamarek123.domain.service.SummaryService;
import com.mamarek123.infrastructure.model.ForecastResponse;
import com.mamarek123.infrastructure.model.SummaryResponse;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api/v1")
public class WeatherAppRestController {

    private final ForecastService forecastService;
    private final SummaryService summaryService;



    @GetMapping("/weekly/forecast")
    public ResponseEntity<ForecastResponse> getWeeklyForecast(@RequestParam @DecimalMin("-90.0") @DecimalMax("90.0") Double latitude, @RequestParam @DecimalMin("-90.0") @DecimalMax("90.0") Double longitude){
        return ResponseEntity.ok(forecastService.getForecast(latitude, longitude));
    }

    @GetMapping("/weekly/summary")
    public ResponseEntity<SummaryResponse> getWeeklySummary(@RequestParam @DecimalMin("-90.0") @DecimalMax("90.0") Double latitude, @RequestParam @DecimalMin("-90.0") @DecimalMax("90.0") Double longitude){
        return ResponseEntity.ok(summaryService.getWeeklySummary(latitude, longitude));
    }


}
