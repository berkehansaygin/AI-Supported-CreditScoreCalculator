package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditEvaluationDTO;

public interface FeatureEngineeringService {
    CreditEvaluationDTO generateFeatures(Long loanApplicationId);
}