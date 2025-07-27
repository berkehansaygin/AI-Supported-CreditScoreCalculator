package com.tedu.loan.approval.project.LoanApproval.model;

import lombok.Data;

@Data
public class Prediction {
    private String lower_bound;
    private String upper_bound;
    private String value;
    private String riskLevel;
}
