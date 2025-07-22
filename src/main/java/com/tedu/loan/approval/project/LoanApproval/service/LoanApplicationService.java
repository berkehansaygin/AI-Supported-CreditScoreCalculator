package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.LoanApplicationDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;


public interface LoanApplicationService {
    ResponseDTO<LoanApplicationDTO> createLoanApplication(LoanApplicationDTO dto);

    ResponseDTO<LoanApplicationDTO> updateLoanApplication(LoanApplicationDTO dto);

    void deleteLoanApplication(Long id);

    ResponseDTO<LoanApplicationDTO> getLoanApplicationById(Long id);

    ResponseDTOs<LoanApplicationDTO> getAllLoanApplications();
}