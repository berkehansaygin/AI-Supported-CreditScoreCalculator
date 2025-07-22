
package com.tedu.loan.approval.project.LoanApproval.model;

import com.tedu.loan.approval.project.LoanApproval.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "loan_applications")
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    private Integer loanTerm;

    @ManyToOne
    @JoinColumn(name = "person_tc", nullable = false)
    private Person person;

}