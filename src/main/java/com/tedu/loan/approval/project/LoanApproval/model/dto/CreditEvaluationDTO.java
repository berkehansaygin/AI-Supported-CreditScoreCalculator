package com.tedu.loan.approval.project.LoanApproval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditEvaluationDTO {

    private double incomeStabilityScore;
    private double debtToIncomeRatio;
    private double paymentDisciplineRatio;
    private double latePaymentSeverity;
    private double ageIncomeRatio;
    private double creditExperienceRatio;
    private double avgDebtPerCount;
    private double onTimeAmountRatio;
    private double lateDebtFrequency;
    private double monthlyPaymentCapacity;
    private double loanDurationExperience;
    private double collateralToIncome;
    private double collateralPerMonth;
    private double creditUtilizationRatio;

    private long personTc;
    private long loanId;
    private int age;
    private double monthlyIncome;

    private int employmentStatusCode;
    private int educationLevelCode;
    private int maritalStatusCode;
    private int loanTypeCode;
    private int loanTerm; //Month
    private int totalDebtCount;
    private double totalDebtAmount;
    private int onTimeDebtCount;
    private double onTimeDebtAmount;
    private int lateDebtCount;
    private double lateDebtAmount;
    private int loanApplicationsLastYear;
    private double creditCardLimit;
    private double currentCardDebt;
    private int creditCardUsageDuration; // Year

    private int collateralCount;
    private double totalCollateralValue;

    private int creditScore;
    private int eligibleLoanAmount;


}
