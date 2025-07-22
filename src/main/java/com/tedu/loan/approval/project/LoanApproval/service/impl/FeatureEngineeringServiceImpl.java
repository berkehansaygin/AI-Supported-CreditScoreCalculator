package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.loan.approval.project.LoanApproval.enums.EducationLevel;
import com.tedu.loan.approval.project.LoanApproval.enums.EmploymentStatus;
import com.tedu.loan.approval.project.LoanApproval.enums.LoanType;
import com.tedu.loan.approval.project.LoanApproval.enums.MaritalStatus;
import com.tedu.loan.approval.project.LoanApproval.model.Collateral;
import com.tedu.loan.approval.project.LoanApproval.model.CreditHistorySummary;
import com.tedu.loan.approval.project.LoanApproval.model.LoanApplication;
import com.tedu.loan.approval.project.LoanApproval.model.Person;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditEvaluationDTO;
import com.tedu.loan.approval.project.LoanApproval.repository.CollateralRepository;
import com.tedu.loan.approval.project.LoanApproval.repository.CreditHistorySummaryRepository;
import com.tedu.loan.approval.project.LoanApproval.repository.LoanApplicationRepository;
import com.tedu.loan.approval.project.LoanApproval.repository.PersonRepository;
import com.tedu.loan.approval.project.LoanApproval.service.FeatureEngineeringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureEngineeringServiceImpl implements FeatureEngineeringService {

    private final LoanApplicationRepository loanAppRepo;
    private final PersonRepository personRepo;
    private final CreditHistorySummaryRepository creditHistoryRepo;
    private final CollateralRepository collateralRepo;

    @Override
    public CreditEvaluationDTO generateFeatures(Long loanApplicationId) {
        LoanApplication loanApp = loanAppRepo.findById(loanApplicationId)
                .orElseThrow(() -> new RuntimeException("LoanApplication not found with ID: " + loanApplicationId));

        Person person = loanApp.getPerson();
        if (person == null) {
            throw new RuntimeException("Person not found for LoanApplication ID: " + loanApplicationId);
        }


        CreditHistorySummary creditHistory = creditHistoryRepo.findByPerson_Tc(person.getTc())
                .orElseThrow(() -> new RuntimeException("CreditHistorySummary not found for person with TC: " + person.getTc()));

        List<Collateral> collaterals = collateralRepo.findByLoanApplication(loanApp);


        double income = person.getMonthly_income();
        int age = person.getAge();
        int loanTerm = loanApp.getLoanTerm();

        double debtToIncome = (income != 0) ? creditHistory.getTotalDebtAmount() / income : 0.0;
        double paymentDiscipline = (creditHistory.getTotalDebtCount() != 0) ? creditHistory.getOnTimeDebtCount() / (double) creditHistory.getTotalDebtCount() : 0.0;
        double latePaymentSeverity = (creditHistory.getTotalDebtAmount() != 0) ? creditHistory.getLateDebtAmount() / creditHistory.getTotalDebtAmount() : 0.0;
        double ageIncomeRatio = (age != 0) ? income / age : 0.0;
        double creditExperienceRatio = (age != 0) ? creditHistory.getCreditCardUsageDuration() / (double) age : 0.0;
        double avgDebtPerCount = (creditHistory.getTotalDebtCount() != 0) ? creditHistory.getTotalDebtAmount() / (double) creditHistory.getTotalDebtCount() : 0.0;
        double onTimeAmountRatio = (creditHistory.getTotalDebtAmount() != 0) ? creditHistory.getOnTimeDebtAmount() / creditHistory.getTotalDebtAmount() : 0.0;
        double lateDebtFrequency = (creditHistory.getTotalDebtCount() != 0) ? creditHistory.getLateDebtCount() / (double) creditHistory.getTotalDebtCount() : 0.0;
        double monthlyPaymentCapacity = (loanTerm != 0) ? income / loanTerm : 0.0;
        double loanDurationExperience = (creditHistory.getCreditCardUsageDuration() != 0) ? loanTerm / (double) creditHistory.getCreditCardUsageDuration() : 0.0;

        double totalCollateralValue = collaterals.stream().mapToDouble(Collateral::getCollateralValue).sum();
        double collateralToIncome = (income != 0) ? totalCollateralValue / income : 0.0;
        double collateralPerMonth = (loanTerm != 0) ? totalCollateralValue / loanTerm : 0.0;

        double creditUtilizationRatio = (creditHistory.getCreditCardLimit() != 0) ? creditHistory.getCurrentCardDebt() / creditHistory.getCreditCardLimit() : 0.0;


        double incomeStability = calculateIncomeStability(person.getEmploymentStatus(), person.getEducationLevel());
        int employmentStatusCode = getEmploymentStatusCode(person.getEmploymentStatus());
        int educationLevelCode = getEducationLevelCode(person.getEducationLevel());
        int maritalStatusCode = getMaritalStatusCode(person.getMaritalStatus());
        int loanTypeCode = getLoanTypeCode(loanApp.getLoanType());


        CreditEvaluationDTO buildDto = CreditEvaluationDTO.builder()
                .incomeStabilityScore(incomeStability)
                .debtToIncomeRatio(debtToIncome)
                .paymentDisciplineRatio(paymentDiscipline)
                .latePaymentSeverity(latePaymentSeverity)
                .ageIncomeRatio(ageIncomeRatio)
                .creditExperienceRatio(creditExperienceRatio)
                .avgDebtPerCount(avgDebtPerCount)
                .onTimeAmountRatio(onTimeAmountRatio)
                .lateDebtFrequency(lateDebtFrequency)
                .monthlyPaymentCapacity(monthlyPaymentCapacity)
                .loanDurationExperience(loanDurationExperience)
                .collateralToIncome(collateralToIncome)
                .collateralPerMonth(collateralPerMonth)
                .creditUtilizationRatio(creditUtilizationRatio)

                .personTc(person.getTc())
                .loanId(loanApplicationId)
                .age(age)
                .monthlyIncome(income)
                .employmentStatusCode(employmentStatusCode)
                .educationLevelCode(educationLevelCode)
                .maritalStatusCode(maritalStatusCode)
                .loanTypeCode(loanTypeCode)
                .loanTerm(loanTerm)
                .totalDebtCount(creditHistory.getTotalDebtCount())
                .totalDebtAmount(creditHistory.getTotalDebtAmount())
                .onTimeDebtCount(creditHistory.getOnTimeDebtCount())
                .onTimeDebtAmount(creditHistory.getOnTimeDebtAmount())
                .lateDebtCount(creditHistory.getLateDebtCount())
                .lateDebtAmount(creditHistory.getLateDebtAmount())
                .loanApplicationsLastYear(creditHistory.getLoanApplicationsLastYear())
                .creditCardLimit(creditHistory.getCreditCardLimit())
                .currentCardDebt(creditHistory.getCurrentCardDebt())
                .creditCardUsageDuration(creditHistory.getCreditCardUsageDuration())
                .collateralCount(collaterals.size())
                .totalCollateralValue(totalCollateralValue)
                .build();

        dtoToJsonWriter(buildDto);
        return buildDto;

    }

    private double calculateIncomeStability(EmploymentStatus employmentStatus, EducationLevel educationLevel) {
        double employmentScore = (employmentStatus != null) ? switch (employmentStatus) {
            case FULL_TIME -> 1.0;
            case PART_TIME -> 0.8;
            case SELF_EMPLOYED -> 0.9;
            case UNEMPLOYED -> 0.3;
            case RETIRED -> 0.5;
            case STUDENT -> 0.4;
            case CONTRACTOR -> 0.6;
        } : 0.0;

        double educationScore = (educationLevel != null) ? switch (educationLevel) {
            case DOCTORATE -> 1.0;
            case MASTER -> 0.9;
            case BACHELOR -> 0.8;
            case HIGH_SCHOOL -> 0.6;
            case SECONDARY -> 0.4;
            case PRIMARY -> 0.3;
        } : 0.0;

        return (employmentScore + educationScore) / 2.0;
    }

    private int getEmploymentStatusCode(EmploymentStatus status) {
        return (status != null) ? switch (status) {
            case FULL_TIME -> 1;
            case PART_TIME -> 2;
            case SELF_EMPLOYED -> 3;
            case UNEMPLOYED -> 4;
            case RETIRED -> 5;
            case STUDENT -> 6;
            case CONTRACTOR -> 7;
        } : 0;
    }

    private int getEducationLevelCode(EducationLevel level) {
        return (level != null) ? switch (level) {
            case PRIMARY -> 1;
            case SECONDARY -> 2;
            case HIGH_SCHOOL -> 3;
            case BACHELOR -> 4;
            case MASTER -> 5;
            case DOCTORATE -> 6;
        } : 0;
    }

    private int getMaritalStatusCode(MaritalStatus status) {
        return (status != null) ? switch (status) {
            case SINGLE -> 1;
            case MARRIED -> 2;
            case DIVORCED -> 3;
        } : 0;
    }

    private int getLoanTypeCode(LoanType type) {
        return (type != null) ? switch (type) {
            case PERSONAL -> 1;
            case AUTO -> 2;
            case MORTGAGE -> 3;
            case CREDIT_CARD -> 4;
            case BUSINESS -> 5;
        } : 0;
    }

    private void dtoToJsonWriter(CreditEvaluationDTO buildDto) {
        String tc = String.valueOf(buildDto.getPersonTc());
        File outputFile = Paths.get("src", "main", "resources", "features", tc, "loanId_" + buildDto.getLoanId() + ".json").toFile();
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                throw new RuntimeException("Unable to create directory " + parentDir.getAbsolutePath());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, buildDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}