package com.tedu.loan.approval.project.LoanApproval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTOs<T> {
    private String message;
    private List<T> responseDtos;
}
