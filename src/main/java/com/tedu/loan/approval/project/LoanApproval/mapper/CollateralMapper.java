package com.tedu.loan.approval.project.LoanApproval.mapper;

import com.tedu.loan.approval.project.LoanApproval.model.Collateral;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CollateralDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CollateralMapper {

    @Mapping(source = "type", target = "collateralType")
    @Mapping(source = "loanApplication.id", target = "loanApplication.id")
    CollateralDTO toDTO(Collateral collateral);

    @Mapping(source = "collateralType", target = "type")
    @Mapping(source = "loanApplication.id", target = "loanApplication.id")
    Collateral toEntity(CollateralDTO dto);
}
