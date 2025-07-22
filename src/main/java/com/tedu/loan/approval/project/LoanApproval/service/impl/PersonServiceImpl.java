package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.tedu.loan.approval.project.LoanApproval.mapper.PersonMapper;
import com.tedu.loan.approval.project.LoanApproval.model.Person;
import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.repository.PersonRepository;
import com.tedu.loan.approval.project.LoanApproval.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public ResponseDTO<PersonDTO> addPerson(PersonDTO personDTO) {
        ResponseDTO<PersonDTO> responseDTO = new ResponseDTO<>();
        personRepository.save(personMapper.toEntity(personDTO));

        responseDTO.setMessage("Person added successfully.");
        responseDTO.setResponseDto(personDTO);
        return responseDTO;
    }

    @Override

    public ResponseDTO<PersonDTO> getPersonByTc(Long tc) {
        ResponseDTO<PersonDTO> responseDTO = new ResponseDTO<>();
        Person person = personRepository.findByTc(tc);
        if (person == null) {
            responseDTO.setMessage("Person not found. Please enter the correct TC value.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }
        responseDTO.setMessage("Person found with TC : " + person.getTc());
        responseDTO.setResponseDto(personMapper.toDto(person));
        return responseDTO;

    }

    @Override
    public void deletePerson(Long tc) {
        if (!personRepository.existsById(tc)) {
            throw new RuntimeException("Person not found with TC: " + tc);
        }
        personRepository.deleteById(tc);
    }


    @Override
    public ResponseDTO<PersonDTO> updatePerson(PersonDTO personDTO) {
        ResponseDTO<PersonDTO> responseDTO = new ResponseDTO<>();
        Person existingPerson = personRepository.findById(personDTO.getTc()).orElse(null);
        if (existingPerson == null) {
            responseDTO.setMessage("Person not found. Please enter the correct TC value.");
            responseDTO.setResponseDto(null);
            return responseDTO;
        }

        personRepository.save(personMapper.toEntity(personDTO));
        responseDTO.setMessage("Person updated successfully.");
        responseDTO.setResponseDto(personDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTOs<PersonDTO> getAllPersons() {
        ResponseDTOs<PersonDTO> responseDTOs = new ResponseDTOs<>();
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty()) {
            responseDTOs.setMessage("No persons found.");
            responseDTOs.setResponseDtos(null);
            return responseDTOs;
        }

        List<PersonDTO> personList = persons.stream()
                .map(personMapper::toDto)
                .toList();
        responseDTOs.setResponseDtos(personList);
        responseDTOs.setMessage("All persons successfully listed below:");
        return responseDTOs;
    }

}
