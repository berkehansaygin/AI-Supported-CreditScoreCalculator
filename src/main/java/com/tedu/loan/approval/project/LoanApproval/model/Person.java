package com.tedu.loan.approval.project.LoanApproval.model;

import com.tedu.loan.approval.project.LoanApproval.enums.EducationLevel;
import com.tedu.loan.approval.project.LoanApproval.enums.EmploymentStatus;
import com.tedu.loan.approval.project.LoanApproval.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private Long tc;

    private String name;
    private String surname;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "monthly_income")
    private Double monthly_income;

}