package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.tedu.loan.approval.project.LoanApproval.mapper.CollateralMapper;
import com.tedu.loan.approval.project.LoanApproval.mapper.LoanApplicationMapper;
import com.tedu.loan.approval.project.LoanApproval.model.Collateral;
import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CollateralDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.LoanApplicationDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.repository.CollateralRepository;
import com.tedu.loan.approval.project.LoanApproval.repository.LoanApplicationRepository;
import com.tedu.loan.approval.project.LoanApproval.service.CollateralService;
import com.tedu.loan.approval.project.LoanApproval.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CollateralServiceImpl implements CollateralService {

    private final CollateralRepository collateralRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final CollateralMapper collateralMapper;
    private final LoanApplicationService loanApplicationService;
    private final LoanApplicationMapper loanApplicationMapper;

    @Override
    public ResponseDTO<CollateralDTO> createCollateral(CollateralDTO dto) {
        ResponseDTO<CollateralDTO> response = new ResponseDTO<>();

        if (dto.getLoanApplication() == null || dto.getLoanApplication().getId() == null) {
            response.setMessage("Loan ID cannot be null. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        Long loanAppId = dto.getLoanApplication().getId();

        LoanApplication loanApplication = loanApplicationRepository.findById(loanAppId).orElse(null);
        if (loanApplication == null) {
            response.setMessage("Loan not found. Please provide a correct loan ID.");
            response.setResponseDto(null);
            return response;
        }

        Collateral entity = collateralMapper.toEntity(dto);
        entity.setLoanApplication(loanApplication);

        Collateral savedEntity = collateralRepository.save(entity);

        response.setMessage("Collateral created successfully.");

        CollateralDTO savedDto = collateralMapper.toDTO(savedEntity);
        response.setResponseDto(savedDto);

        return response;
    }

    @Override
    public ResponseDTOs<CollateralDTO> getAllCollaterals() {
        ResponseDTOs<CollateralDTO> response = new ResponseDTOs<>();

        List<CollateralDTO> list = collateralRepository.findAll()
                .stream()
                .map(collateralMapper::toDTO)
                .toList();

        if (list.isEmpty()) {
            response.setMessage("No collaterals found.");
            response.setResponseDtos(null);
            return response;
        }

        response.setMessage("All collaterals found is listed below:");
        response.setResponseDtos(list);
        return response;
    }

    @Override
    public ResponseDTO<CollateralDTO> getCollateralById(Long id) {
        ResponseDTO<CollateralDTO> response = new ResponseDTO<>();
        Collateral collateral = collateralRepository.findById(id).orElse(null);

        if (Objects.isNull(collateral)) {
            response.setMessage("Collateral not found. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        response.setMessage("Collateral found successfully.");
        response.setResponseDto(collateralMapper.toDTO(collateral));
        return response;
    }

    @Override
    public ResponseDTO<CollateralDTO> updateCollateral(CollateralDTO dto) {
        ResponseDTO<CollateralDTO> response = new ResponseDTO<>();

        if (dto.getId() == null) {
            response.setMessage("Collateral ID cannot be null. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        Long loanAppId = dto.getLoanApplication() != null ? dto.getLoanApplication().getId() : null;
        if (loanAppId == null) {
            response.setMessage("Loan ID cannot be null.Please provide a correct loan ID.");
            response.setResponseDto(null);
            return response;
        }

        ResponseDTO<LoanApplicationDTO> loanAppResp = loanApplicationService.getLoanApplicationById(loanAppId);
        if (loanAppResp.getResponseDto() == null) {
            response.setMessage("Collateral could not be updated because loan  was not found.");
            response.setResponseDto(null);
            return response;
        }

        Collateral existingEntity = collateralRepository.findById(dto.getId()).orElse(null);
        if (existingEntity == null) {
            response.setMessage("Collateral not found. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        Collateral entity = collateralMapper.toEntity(dto);
        entity.setLoanApplication(loanApplicationMapper.toEntity(loanAppResp.getResponseDto()));
        collateralRepository.save(entity);

        response.setMessage("Collateral updated successfully.");
        dto.setLoanApplication(loanAppResp.getResponseDto());
        response.setResponseDto(dto);

        return response;
    }

    @Override
    public void deleteCollateralById(Long id) {
        if (!collateralRepository.existsById(id)) {
            throw new RuntimeException("Collateral not found. Please provide a correct ID.");
        }
        collateralRepository.deleteById(id);
    }


}