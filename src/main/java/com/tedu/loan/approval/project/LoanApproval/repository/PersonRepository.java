package com.tedu.loan.approval.project.LoanApproval.repository;

import com.tedu.loan.approval.project.LoanApproval.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByTc(Long tc);
}