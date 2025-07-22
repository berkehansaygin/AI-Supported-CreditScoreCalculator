package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditHistorySummaryDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.service.CreditHistorySummaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/credithistsummary")
@RequiredArgsConstructor


public class CreditHistorySummaryController {
    private final CreditHistorySummaryService creditHistorySummaryService;

    @GetMapping("/getSummaryById/{id}")
    public ResponseEntity<ResponseDTO<CreditHistorySummaryDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(creditHistorySummaryService.getCreditHistorySummaryById(id));
    }

    @GetMapping("/getAllSummaries")
    public ResponseEntity<ResponseDTOs<CreditHistorySummaryDTO>> getAllSummaries() {
        return ResponseEntity.ok(creditHistorySummaryService.getAllCreditHistorySummaries());
    }

    @PostMapping("/createSummary")
    public ResponseEntity<ResponseDTO<CreditHistorySummaryDTO>> create(@Valid @RequestBody CreditHistorySummaryDTO dto) {
        return ResponseEntity.ok(creditHistorySummaryService.createCreditHistorySummary(dto));
    }

    @PutMapping("/updateSummary")
    public ResponseEntity<ResponseDTO<CreditHistorySummaryDTO>> update(@Valid @RequestBody CreditHistorySummaryDTO dto) {
        return ResponseEntity.ok(creditHistorySummaryService.updateCreditHistorySummary(dto));
    }

    @DeleteMapping("/deleteSummaryById/{id}")
    public ResponseEntity<String> deleteSummary(@PathVariable Long id) {
        try {
            creditHistorySummaryService.deleteCreditHistorySummaryById(id);
            String message = "Credit history summary deleted successfully with ID " + id;
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Credit history summary not found with ID " + id);
        }
    }
}
