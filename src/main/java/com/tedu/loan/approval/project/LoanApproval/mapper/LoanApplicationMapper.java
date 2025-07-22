package com.tedu.loan.approval.project.LoanApproval.mapper;

import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import com.tedu.loan.approval.project.LoanApproval.model.dto.LoanApplicationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
    LoanApplicationDTO toDto(LoanApplication loanApplication);

    LoanApplication toEntity(LoanApplicationDTO dto);
}