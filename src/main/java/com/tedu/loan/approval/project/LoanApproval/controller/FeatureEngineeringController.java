package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditEvaluationDTO;
import com.tedu.loan.approval.project.LoanApproval.service.FeatureEngineeringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/features")
@RequiredArgsConstructor
public class FeatureEngineeringController {

    private final FeatureEngineeringService featureEngineeringService;

    @GetMapping("/calculate/{loanApplicationId}")
    public ResponseEntity<CreditEvaluationDTO> generateFeatures(@PathVariable Long loanApplicationId) {
        CreditEvaluationDTO dto = featureEngineeringService.generateFeatures(loanApplicationId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/generateAll")
    public ResponseEntity<String> generateAllFeatures() {
        featureEngineeringService.generateAllFeaturesJsonFiles();
        return ResponseEntity.ok("All feature JSON files have been generated.");
    }
}