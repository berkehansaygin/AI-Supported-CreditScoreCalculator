package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.tedu.loan.approval.project.LoanApproval.enums.CollateralType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollateralDTO {

    private Long id;

    @NotNull(message = "Collateral type must be specified")
    private CollateralType collateralType;

    @Min(value = 0, message = "Collateral value cannot be negative")
    private Double collateralValue;

    private LoanApplicationDTO loanApplication;


}
