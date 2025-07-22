package com.tedu.loan.approval.project.LoanApproval.repository;

import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByPerson_Tc(Long tc);
}
