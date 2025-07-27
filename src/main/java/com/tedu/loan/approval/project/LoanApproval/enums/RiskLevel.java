package com.tedu.loan.approval.project.LoanApproval.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RiskLevel {
    LOW("LOW", "Credit score > 100001 Risk-Level is LOW !"),
    MEDIUM("MEDIUM", "Credit score 50001-100000 Risk-Level is MEDIUM !"),
    HIGH("HIGH", "Credit score 0-50000 Risk-Level is HIGH !");

    private final String value;
    private final String description;
}