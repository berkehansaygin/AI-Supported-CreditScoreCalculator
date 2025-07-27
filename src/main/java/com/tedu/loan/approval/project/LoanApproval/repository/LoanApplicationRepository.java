package com.tedu.loan.approval.project.LoanApproval.repository;

import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}
