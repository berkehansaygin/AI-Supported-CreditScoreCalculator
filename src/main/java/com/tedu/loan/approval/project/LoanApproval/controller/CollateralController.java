package com.tedu.loan.approval.project.LoanApproval.controller;

import com.tedu.loan.approval.project.LoanApproval.model.dto.CollateralDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.model.dto.ResponseDTOs;
import com.tedu.loan.approval.project.LoanApproval.service.CollateralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collaterals")
@RequiredArgsConstructor
public class CollateralController {

    private final CollateralService collateralService;

    @GetMapping("/getAllCollaterals")
    public ResponseEntity<ResponseDTOs<CollateralDTO>> getAll() {
        return ResponseEntity.ok(collateralService.getAllCollaterals());
    }

    @GetMapping("/getCollateralById/{id}")
    public ResponseEntity<ResponseDTO<CollateralDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(collateralService.getCollateralById(id));
    }

    @PostMapping("/createCollateral")
    public ResponseEntity<ResponseDTO<CollateralDTO>> create(@Valid @RequestBody CollateralDTO dto) {
        return ResponseEntity.ok(collateralService.createCollateral(dto));
    }

    @PutMapping("/updateCollateral")
    public ResponseEntity<ResponseDTO<CollateralDTO>> update(@Valid @RequestBody CollateralDTO dto) {
        return ResponseEntity.ok(collateralService.updateCollateral(dto));
    }

    @DeleteMapping("/deleteCollateralById/{id}")
    public ResponseEntity<String> deleteCollateral(@PathVariable Long id) {
        try {
            collateralService.deleteCollateralById(id);
            return ResponseEntity.ok("Collateral deleted successfully with ID " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}