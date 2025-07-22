package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.tedu.loan.approval.project.LoanApproval.mapper.LoanApplicationMapper;
import com.tedu.loan.approval.project.LoanApproval.mapper.PersonMapper;
import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import com.tedu.loan.approval.project.LoanApproval.model.dto.LoanApplicationDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.repository.LoanApplicationRepository;
import com.tedu.loan.approval.project.LoanApproval.service.LoanApplicationService;
import com.tedu.loan.approval.project.LoanApproval.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final PersonService personService;
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationMapper loanApplicationMapper;
    private final PersonMapper personMapper;


    @Override
    public ResponseDTO<LoanApplicationDTO> createLoanApplication(LoanApplicationDTO loanAppDto) {
        ResponseDTO<LoanApplicationDTO> responseDTO = new ResponseDTO<>();
        Long personTc = loanAppDto.getPerson().getTc();
        if (Objects.isNull(personTc)) {
            responseDTO.setMessage("Person TC is null !! Please check your person TC.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        ResponseDTO<PersonDTO> personDto = personService.getPersonByTc(personTc);

        if (Objects.isNull(personDto.getResponseDto())) {
            responseDTO.setMessage("Loan could not be added because the person could not be found.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        loanAppDto.setPerson(personMapper.toEntity(personDto.getResponseDto()));
        LoanApplication savedEntity = loanApplicationRepository.save(loanApplicationMapper.toEntity(loanAppDto));
        LoanApplicationDTO savedDto = loanApplicationMapper.toDto(savedEntity);
        responseDTO.setMessage("Loan created successfully");
        responseDTO.setResponseDto(savedDto);

        return responseDTO;
    }

    @Override
    public ResponseDTO<LoanApplicationDTO> updateLoanApplication(LoanApplicationDTO loanAppDto) {
        ResponseDTO<LoanApplicationDTO> responseDTO = new ResponseDTO<>();

        if (loanAppDto.getId() == null) {
            responseDTO.setMessage("Loan ID can not be null !! Please check your loan ID.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        Long personTc = loanAppDto.getPerson().getTc();
        if (Objects.isNull(personTc)) {
            responseDTO.setMessage("Person TC can not be null !! Please check your TC.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        ResponseDTO<PersonDTO> personDto = personService.getPersonByTc(personTc);
        if (Objects.isNull(personDto.getResponseDto())) {
            responseDTO.setMessage("Loan could not be updated because the person could not be found.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        LoanApplication existingLoanApp = loanApplicationRepository.findById(loanAppDto.getId()).orElse(null);
        if (Objects.isNull(existingLoanApp)) {
            responseDTO.setMessage("Loan not found. Please provide a correct loan ID");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        LoanApplication updatedEntity = loanApplicationMapper.toEntity(loanAppDto);

        updatedEntity.setId(existingLoanApp.getId());

        updatedEntity.setPerson(personMapper.toEntity(personDto.getResponseDto()));

        LoanApplication savedEntity = loanApplicationRepository.save(updatedEntity);

        LoanApplicationDTO savedDto = loanApplicationMapper.toDto(savedEntity);

        responseDTO.setMessage("Loan updated successfully.");
        responseDTO.setResponseDto(savedDto);

        return responseDTO;
    }

    @Override
    public ResponseDTO<LoanApplicationDTO> getLoanApplicationById(Long id) {
        ResponseDTO<LoanApplicationDTO> responseDTO = new ResponseDTO<>();
        LoanApplication loan = loanApplicationRepository.findById(id).orElse(null);
        if (Objects.isNull(loan)) {
            responseDTO.setResponseDto(null);
            responseDTO.setMessage("Loan not found. Please provide a correct loan ID");
            return responseDTO;
        }

        responseDTO.setResponseDto(loanApplicationMapper.toDto(loan));
        responseDTO.setMessage("Person found successfully.");
        return responseDTO;
    }

    @Override
    public ResponseDTOs<LoanApplicationDTO> getAllLoanApplications() {
        ResponseDTOs<LoanApplicationDTO> responseDTO = new ResponseDTOs<>();
        List<LoanApplicationDTO> loanList = loanApplicationRepository.findAll().stream()
                .map(loanApplicationMapper::toDto)
                .toList();
        if (loanList.isEmpty()) {
            responseDTO.setResponseDtos(null);
            responseDTO.setMessage("Loan not found. Please provide a correct Loan ID");
        }
        responseDTO.setResponseDtos(loanList);
        responseDTO.setMessage("All loans found successfully.");
        return responseDTO;
    }


    @Override
    public void deleteLoanApplication(Long id) {
        if (!loanApplicationRepository.existsById(id)) {
            throw new RuntimeException("Loan not found. Please provide a correct Loan ID");
        }
        loanApplicationRepository.deleteById(id);
    }
}
