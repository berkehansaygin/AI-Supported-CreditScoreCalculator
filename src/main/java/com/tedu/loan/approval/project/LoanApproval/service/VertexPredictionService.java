package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.PredictResponseDTO;

import java.util.Map;


public interface VertexPredictionService {
    PredictResponseDTO predict(Map<String, Object> instanceData) throws Exception;
}
