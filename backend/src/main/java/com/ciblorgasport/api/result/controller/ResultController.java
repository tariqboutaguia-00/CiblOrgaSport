package com.ciblorgasport.api.result.controller;

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
    public List<ResultResponse> getAllResults() {
        return resultService.getAllResults();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponse createResult(@Valid @RequestBody CreateResultRequest request) {
        return resultService.createResult(request);
    }
}