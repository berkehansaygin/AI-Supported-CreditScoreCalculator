package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.PersonDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("getAllPersons")
    public ResponseEntity<ResponseDTOs<PersonDTO>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/getPersonByTc/{tc}")
    public ResponseEntity<ResponseDTO<PersonDTO>> getPersonByTc(@PathVariable Long tc) {
        return ResponseEntity.ok(personService.getPersonByTc(tc));
    }


    @PostMapping("/createPerson")
    public ResponseEntity<ResponseDTO<PersonDTO>> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.addPerson(personDTO));
    }


    @PutMapping("/updatePerson")
    public ResponseEntity<ResponseDTO<PersonDTO>> updatePerson(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(personDTO));
    }

    @DeleteMapping("/deletePersonByTc/{tc}")
    public ResponseEntity<String> deletePerson(@PathVariable Long tc) {
        try {
            personService.deletePerson(tc);
            return ResponseEntity.ok("Deleted person with TC : " + tc);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with TC : " + tc);
        }
    }
}