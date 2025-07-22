package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditHistorySummaryDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;


public interface CreditHistorySummaryService {
    ResponseDTOs<CreditHistorySummaryDTO> getAllCreditHistorySummaries();

    ResponseDTO<CreditHistorySummaryDTO> createCreditHistorySummary(CreditHistorySummaryDTO dto);

    ResponseDTO<CreditHistorySummaryDTO> getCreditHistorySummaryById(Long id);

    ResponseDTO<CreditHistorySummaryDTO> updateCreditHistorySummary(CreditHistorySummaryDTO dto);

    void deleteCreditHistorySummaryById(Long id);
}
