package com.ciblorgasport.api.result.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.result.dto.AthletePerformanceResponse;
import com.ciblorgasport.api.result.dto.CreateResultRequest;
import com.ciblorgasport.api.result.dto.ResultResponse;
import com.ciblorgasport.api.result.service.ResultService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public ApiResponse<List<ResultResponse>> getAllResults() {
        return ApiResponse.success("Results retrieved successfully", resultService.getAllResults());
    }

    @GetMapping("/athlete/{athleteId}")
    public ApiResponse<List<AthletePerformanceResponse>> getAthletePerformances(@PathVariable Long athleteId) {
        return ApiResponse.success(
                "Athlete performances retrieved successfully",
                resultService.getAthletePerformances(athleteId));
    }

    @GetMapping("/performances")
    public ApiResponse<List<AthletePerformanceResponse>> getAllPerformances() {
        return ApiResponse.success(
                "All performances retrieved successfully",
                resultService.getAllPerformances());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ResultResponse> createResult(@Valid @RequestBody CreateResultRequest request) {
        return ApiResponse.success("Result created successfully", resultService.createResult(request));
    }
}