package com.ciblorgasport.api.statistics.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.statistics.dto.StatisticsResponse;
import com.ciblorgasport.api.statistics.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ApiResponse<StatisticsResponse> getOverview() {
        return ApiResponse.success("Statistics retrieved successfully", statisticsService.getOverview());
    }
}