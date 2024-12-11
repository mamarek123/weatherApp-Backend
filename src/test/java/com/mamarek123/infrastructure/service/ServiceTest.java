package com.mamarek123.infrastructure.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamarek123.domain.energyCalculator.ExpectedEnergyGenerationCalculator;
import com.mamarek123.domain.service.ForecastService;
import com.mamarek123.domain.service.SummaryService;
import com.mamarek123.domain.service.implementaion.ForecastServiceImpl;
import com.mamarek123.domain.service.implementaion.SummaryServiceImpl;
import com.mamarek123.infrastructure.model.ForecastResponse;
import com.mamarek123.infrastructure.model.SummaryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Value("${photovoltaicInstallationPower}") float photovoltaicInstallationPower;
    @Value("${panelEfficiency}") float panelEfficiency;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void summaryServiceTest(){
        SummaryService summaryService = new SummaryServiceImpl(new OpenMeteoServiceTestImpl(objectMapper));

        SummaryResponse weeklySummary = summaryService.getWeeklySummary(40.0, 40.0);
        Assertions.assertNotNull(weeklySummary);
        Assertions.assertEquals(weeklySummary.getPrecipitation(),"A week with precipitation");
        Assertions.assertEquals((int)weeklySummary.getAveragePressure(),1017);
    }

    @Test
    void forecastServiceTest(){
        ForecastService forecastService = new ForecastServiceImpl(new OpenMeteoServiceTestImpl(objectMapper), new ExpectedEnergyGenerationCalculator(photovoltaicInstallationPower, panelEfficiency));

        ForecastResponse forecastResponse = forecastService.getForecast(40.0,40.0);
        Assertions.assertNotNull(forecastResponse);
        Assertions.assertEquals(forecastResponse.getDates().size(),7);
        Assertions.assertEquals(forecastResponse.getGeneratedEnergy().intValue(),9);


    }
}
