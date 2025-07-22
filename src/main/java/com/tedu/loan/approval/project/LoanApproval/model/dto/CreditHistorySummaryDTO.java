package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.tedu.loan.approval.project.LoanApproval.model.Person;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditHistorySummaryDTO {

    private Long id;

    @Min(value = 0, message = "totalDebtCount cannot be negative")
    private int totalDebtCount;

    @Min(value = 0, message = "totalDebtAmount cannot be negative")
    private double totalDebtAmount;

    @Min(value = 0, message = "onTimeDebtCount cannot be negative")
    private int onTimeDebtCount;

    @Min(value = 0, message = "onTimeDebtAmount cannot be negative")
    private double onTimeDebtAmount;

    @Min(value = 0, message = "lateDebtCount cannot be negative")
    private int lateDebtCount;

    @Min(value = 0, message = "lateDebtAmount cannot be negative")
    private double lateDebtAmount;

    @Min(value = 0, message = "loanApplicationsLastYear cannot be negative")
    private Integer loanApplicationsLastYear;

    @Min(value = 0, message = "creditCardLimit cannot be negative")
    private Double creditCardLimit;

    @Min(value = 0, message = "currentCardDebt cannot be negative")
    private Double currentCardDebt;

    @Min(value = 0, message = "creditCardUsageDuration cannot be negative")
    private Integer creditCardUsageDuration;   //Year


    private Person person;

}
