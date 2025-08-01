package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.LoanApplicationDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.service.LoanApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loanApplications")
@RequiredArgsConstructor
@Tag(name = "LoanApplicationController", description = "API for Person Loan Process")
public class LoanApplicationController {
    private final LoanApplicationService service;


    @GetMapping("getAllLoans")
    @Operation(description = "Veritabanındaki tüm loanların listesini döner")
    public ResponseEntity<ResponseDTOs<LoanApplicationDTO>> getAll() {
        return ResponseEntity.ok(service.getAllLoanApplications());
    }

    @GetMapping("/getById/{id}")
    @Operation(description = "Veritabanındaki id ye göre loan döndürür")
    public ResponseEntity<ResponseDTO<LoanApplicationDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLoanApplicationById(id));
    }

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDTO<LoanApplicationDTO>> create(@Valid @RequestBody LoanApplicationDTO dto) {
        return ResponseEntity.ok(service.createLoanApplication(dto));
    }

    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDTO<LoanApplicationDTO>> update(@Valid @RequestBody LoanApplicationDTO dto) {
        return ResponseEntity.ok(service.updateLoanApplication(dto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteLoanApplication(@PathVariable Long id) {
        try {
            service.deleteLoanApplication(id);
            String message = "Loan application deleted successfully with ID " + id;
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Loan application not found with ID " + id);
        }
    }
}
