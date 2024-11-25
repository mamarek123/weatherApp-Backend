package com.mamarek123.domain.service;

import com.mamarek123.infrastructure.model.SummaryResponse;

public interface SummaryService {
    SummaryResponse getWeeklySummary(Double Latitude, Double Longitude);
}
