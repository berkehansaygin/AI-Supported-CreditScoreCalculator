package com.tedu.loan.approval.project.LoanApproval.model.dto;

import com.tedu.loan.approval.project.LoanApproval.enums.EducationLevel;
import com.tedu.loan.approval.project.LoanApproval.enums.EmploymentStatus;
import com.tedu.loan.approval.project.LoanApproval.enums.MaritalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5122441787350750284L;

    @NotNull
    private Long tc;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private Integer age;

    @NotNull(message = "Employment status cannot be null")
    private EmploymentStatus employmentStatus;

    @NotNull(message = "EducationLevel cannot be null")
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @NotNull(message = "MaritalStatus cannot be null")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotNull(message = "Monthly income cannot be null")
    @PositiveOrZero(message = "Monthly income must be zero or positive")
    private Double monthly_income;
}
