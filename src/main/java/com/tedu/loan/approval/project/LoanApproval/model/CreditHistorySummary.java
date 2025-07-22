package com.tedu.loan.approval.project.LoanApproval.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_history_summaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditHistorySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalDebtCount;
    private Double totalDebtAmount;
    private Integer onTimeDebtCount;
    private Double onTimeDebtAmount;
    private Integer lateDebtCount;
    private Double lateDebtAmount;

    private Integer loanApplicationsLastYear;  // Last 1 Year
    private Double creditCardLimit;
    private Double currentCardDebt;
    private Integer creditCardUsageDuration;   //Year


    @OneToOne
    @JoinColumn(name = "person_tc")
    private Person person;


}

