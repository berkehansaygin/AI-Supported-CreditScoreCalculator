package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.tedu.loan.approval.project.LoanApproval.enums.LoanType;
import com.tedu.loan.approval.project.LoanApproval.model.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationDTO {

    private Long id;

    @NotNull(message = "Loan type cannot be null")
    private LoanType loanType;

    @NotNull(message = "Loan term cannot be null")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    private Integer loanTerm;

    private Person person;
}