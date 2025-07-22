package com.tedu.loan.approval.project.LoanApproval.model;

import com.tedu.loan.approval.project.LoanApproval.enums.CollateralType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "collaterals")
@NoArgsConstructor
@AllArgsConstructor

public class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CollateralType type;

    private Double collateralValue;


    @ManyToOne
    @JoinColumn(name = "loan_app_id", nullable = false)
    private LoanApplication loanApplication;


}