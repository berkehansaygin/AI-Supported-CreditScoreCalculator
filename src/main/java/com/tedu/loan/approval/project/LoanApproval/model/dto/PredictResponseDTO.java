package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedu.loan.approval.project.LoanApproval.model.Prediction;
import lombok.Data;

import java.util.List;

@Data
public class PredictResponseDTO {
    private List<Prediction> predictions;
    private String deployedModelId;
    @JsonIgnore
    private String model;
    private String modelDisplayName;
    @JsonIgnore
    private String modelVersionId;
    private String modelTrainer;

}

