package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.util.JsonToCsvConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeatureCsvController {

    @PostMapping("/exportCsv")
    public ResponseEntity<String> exportAllFeaturesCsv() {
        try {
            JsonToCsvConverter.convertAllJsonToCsv();
            return ResponseEntity.ok("CSV file generated.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to generate CSV: " + e.getMessage());
        }
    }
}