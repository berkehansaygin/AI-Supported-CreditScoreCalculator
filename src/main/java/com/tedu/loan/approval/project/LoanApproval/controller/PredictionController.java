package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.PredictResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.service.VertexPredictionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    private final VertexPredictionService predictionService;

    public PredictionController(VertexPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public PredictResponseDTO predict(@RequestBody Map<String, Object> input) throws Exception {
        return predictionService.predict(input);
    }
}