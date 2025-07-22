package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.MLResultDTO;

public interface MLPredictionService {
    MLResultDTO predict(Long loanApplicationId);
}
