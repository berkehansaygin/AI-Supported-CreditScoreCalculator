package com.tedu.loan.approval.project.LoanApproval.mapper;


import com.tedu.loan.approval.project.LoanApproval.model.CreditHistorySummary;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditHistorySummaryDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CreditHistorySummaryMapper {
    CreditHistorySummaryDTO toDto(CreditHistorySummary entity);

    CreditHistorySummary toEntity(CreditHistorySummaryDTO dto);
}