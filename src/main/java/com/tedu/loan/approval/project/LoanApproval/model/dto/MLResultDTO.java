package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.tedu.loan.approval.project.LoanApproval.enums.LoanType;
import com.tedu.loan.approval.project.LoanApproval.enums.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MLResultDTO {
    private String message;
    private LoanType loanType;
    private double creditScore;
    private RiskLevel riskLevel;
    private double approvedAmount;
}
