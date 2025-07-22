package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.tedu.loan.approval.project.LoanApproval.mapper.CreditHistorySummaryMapper;
import com.tedu.loan.approval.project.LoanApproval.mapper.PersonMapper;
import com.tedu.loan.approval.project.LoanApproval.model.CreditHistorySummary;
import com.tedu.loan.approval.project.LoanApproval.model.Person;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditHistorySummaryDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.repository.CreditHistorySummaryRepository;
import com.tedu.loan.approval.project.LoanApproval.service.CreditHistorySummaryService;
import com.tedu.loan.approval.project.LoanApproval.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CreditHistorySummaryServiceImpl implements CreditHistorySummaryService {

    private final PersonService personService;
    private final CreditHistorySummaryRepository repository;
    private final CreditHistorySummaryMapper mapper;
    private final PersonMapper personMapper;


    @Override
    public ResponseDTO<CreditHistorySummaryDTO> createCreditHistorySummary(CreditHistorySummaryDTO dto) {
        ResponseDTO<CreditHistorySummaryDTO> response = new ResponseDTO<>();
        Long personTc = dto.getPerson().getTc();
        if (Objects.isNull(personTc)) {
            response.setMessage("Person TC is null! Please provide a valid person TC.");
            response.setResponseDto(null);
            return response;
        }

        if (repository.findByPerson_Tc(personTc).isPresent()) {
            response.setMessage("Credit history summary already exists for this person.");
            response.setResponseDto(null);
            return response;
        }
        ResponseDTO<PersonDTO> personResp = personService.getPersonByTc(personTc);
        if (Objects.isNull(personResp.getResponseDto())) {
            response.setMessage("Credit history could not be added because person was not found. Please provide a valid person TC.");
            response.setResponseDto(null);
            return response;
        }
        CreditHistorySummary entity = mapper.toEntity(dto);
        Person person = personMapper.toEntity(personResp.getResponseDto());
        entity.setPerson(person);

        CreditHistorySummary savedEntity = repository.save(entity);

        CreditHistorySummaryDTO savedDto = mapper.toDto(savedEntity);
        response.setMessage("Credit history summary created successfully.");
        response.setResponseDto(savedDto);

        return response;
    }


    @Override
    public ResponseDTOs<CreditHistorySummaryDTO> getAllCreditHistorySummaries() {
        ResponseDTOs<CreditHistorySummaryDTO> response = new ResponseDTOs<>();
        List<CreditHistorySummaryDTO> list = repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        if (list.isEmpty()) {
            response.setMessage("No credit history summary found.");
            response.setResponseDtos(null);
            return response;
        }
        response.setMessage("All credit history summaries are below.");
        response.setResponseDtos(list);
        return response;
    }


    @Override
    public ResponseDTO<CreditHistorySummaryDTO> getCreditHistorySummaryById(Long id) {
        ResponseDTO<CreditHistorySummaryDTO> response = new ResponseDTO<>();
        CreditHistorySummary entity = repository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            response.setMessage("Credit history summary not found. Please provide a correct ID. ");
            response.setResponseDto(null);
            return response;
        }
        CreditHistorySummaryDTO dto = mapper.toDto(entity);
        response.setMessage("Credit history is below for id " + id);
        response.setResponseDto(dto);
        return response;
    }


    @Override
    public ResponseDTO<CreditHistorySummaryDTO> updateCreditHistorySummary(CreditHistorySummaryDTO dto) {
        ResponseDTO<CreditHistorySummaryDTO> response = new ResponseDTO<>();

        if (dto.getId() == null) {
            response.setMessage("Credit history summary ID cannot be null. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        Long personTc = dto.getPerson() != null ? dto.getPerson().getTc() : null;
        if (personTc == null) {
            response.setMessage("Person TC cannot be null. Please provide a valid person TC.");
            response.setResponseDto(null);
            return response;
        }

        ResponseDTO<PersonDTO> personResp = personService.getPersonByTc(personTc);
        if (personResp.getResponseDto() == null) {
            response.setMessage("Credit history could not be updated because person was not found.");
            response.setResponseDto(null);
            return response;
        }

        CreditHistorySummary existingEntity = repository.findById(dto.getId()).orElse(null);
        if (existingEntity == null) {
            response.setMessage("Credit history summary not found. Please provide a correct ID.");
            response.setResponseDto(null);
            return response;
        }

        CreditHistorySummary entity = mapper.toEntity(dto);
        entity.setPerson(personMapper.toEntity(personResp.getResponseDto()));
        repository.save(entity);

        response.setMessage("Credit history summary updated successfully.");
        dto.setPerson(personMapper.toEntity(personResp.getResponseDto()));
        response.setResponseDto(dto);

        return response;
    }

    @Override
    public void deleteCreditHistorySummaryById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Credit history summary not found. Please provide a correct ID.");
        }
        repository.deleteById(id);
    }


}












