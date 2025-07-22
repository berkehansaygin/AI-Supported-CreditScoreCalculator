package com.tedu.loan.approval.project.LoanApproval.repository;

import com.tedu.loan.approval.project.LoanApproval.model.CreditHistorySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CreditHistorySummaryRepository extends JpaRepository<CreditHistorySummary, Long> {
    Optional<CreditHistorySummary> findByPerson_Tc(Long tc);
}