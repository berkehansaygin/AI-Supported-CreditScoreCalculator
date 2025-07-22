package com.tedu.loan.approval.project.LoanApproval.service;

import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;

public interface PersonService {
    ResponseDTO<PersonDTO> addPerson(PersonDTO personDTO);

    ResponseDTO<PersonDTO> getPersonByTc(Long tc);

    void deletePerson(Long tc);

    ResponseDTO<PersonDTO> updatePerson(PersonDTO personDTO);

    ResponseDTOs<PersonDTO> getAllPersons();

}