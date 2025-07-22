package com.tedu.loan.approval.project.LoanApproval.mapper;

import com.tedu.loan.approval.project.LoanApproval.model.Person;
import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toEntity(PersonDTO dto);

    PersonDTO toDto(Person person);
}
