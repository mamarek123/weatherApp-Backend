package com.mamarek123.infrastructure;

import com.mamarek123.domain.service.ForecastService;
import com.mamarek123.domain.service.SummaryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherAppRestController.class)
@RunWith(SpringRunner.class)
class WeatherAppRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ForecastService forecastService;

    @MockitoBean
    private SummaryService summaryService;

    private final static String FORECAST_PATH = "/api/v1/weekly/forecast";
    private final static String SUMMARY_PATH = "/api/v1/weekly/summary";
    @Test
    void shouldBeBadRequestOnForecastEndpointWhenLatOrLenOutside9090limit() throws Exception {
        Mockito.when(forecastService.getForecast(any(),any())).thenReturn(null);

        mockMvc.perform(get(FORECAST_PATH)
                        .param("latitude","-91")
                        .param("longitude","91")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldBeOkOnSummaryEndpointWhenLatOrLenInside9090limit() throws Exception {
        Mockito.when(summaryService.getWeeklySummary(any(),any())).thenReturn(null);


        mockMvc.perform(get(SUMMARY_PATH)
                        .param("latitude","45")
                        .param("longitude","45")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void shouldBeBadRequestOnForecastEndpointWhenLatOrLenMissing() throws Exception {
        Mockito.when(forecastService.getForecast(any(),any())).thenReturn(null);

        mockMvc.perform(get(FORECAST_PATH)
                        .param("latitude","-91")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}