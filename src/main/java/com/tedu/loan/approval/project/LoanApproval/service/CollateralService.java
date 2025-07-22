package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CollateralDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import org.springframework.web.bind.annotation.PathVariable;

public interface CollateralService {
    ResponseDTO<CollateralDTO> getCollateralById(@PathVariable Long id);

    ResponseDTOs<CollateralDTO> getAllCollaterals();

    ResponseDTO<CollateralDTO> createCollateral(CollateralDTO collateralDTO);

    ResponseDTO<CollateralDTO> updateCollateral(CollateralDTO collateralDTO);

    void deleteCollateralById(Long id);
}
