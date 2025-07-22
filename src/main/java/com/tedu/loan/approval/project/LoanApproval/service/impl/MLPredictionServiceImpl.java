package com.tedu.loan.approval.project.LoanApproval.service.impl;


import com.tedu.loan.approval.project.LoanApproval.enums.LoanType;
import com.tedu.loan.approval.project.LoanApproval.enums.RiskLevel;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditEvaluationDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.MLResultDTO;
import com.tedu.loan.approval.project.LoanApproval.service.FeatureEngineeringService;
import com.tedu.loan.approval.project.LoanApproval.service.MLPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MLPredictionServiceImpl implements MLPredictionService {

    private final FeatureEngineeringService featureEngineeringService;

    @Override
    public MLResultDTO predict(Long loanApplicationId) {
        CreditEvaluationDTO features = featureEngineeringService.generateFeatures(loanApplicationId);
        String message = ("Your loan types, credit scores,risk levels and approved amounts are:");
        double dummyCreditScore = 750.0;
        RiskLevel dummyRiskLevel = RiskLevel.MEDIUM;
        double dummyApprovedAmount = 50000.0;
        return MLResultDTO.builder()
                .message(message)
                .loanType(LoanType.values()[features.getLoanTypeCode() - 1])
                .creditScore(dummyCreditScore)
                .riskLevel(dummyRiskLevel)
                .approvedAmount(dummyApprovedAmount)
                .build();
    }
}
