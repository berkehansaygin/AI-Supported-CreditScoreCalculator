package com.tedu.loan.approval.project.LoanApproval.controller;


import com.tedu.loan.approval.project.LoanApproval.model.dto.MLResultDTO;
import com.tedu.loan.approval.project.LoanApproval.service.MLPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ml")
@RequiredArgsConstructor
public class MLPredictionController {

    private final MLPredictionService mlPredictionService;

    @GetMapping("/predict/{loanApplicationId}")
    public ResponseEntity<MLResultDTO> predict(@PathVariable Long loanApplicationId) {
        MLResultDTO result = mlPredictionService.predict(loanApplicationId);
        return ResponseEntity.ok(result);
    }
}