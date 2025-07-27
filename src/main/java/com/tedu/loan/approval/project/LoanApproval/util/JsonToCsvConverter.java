package com.tedu.loan.approval.project.LoanApproval.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.tedu.loan.approval.project.LoanApproval.model.dto.CreditEvaluationDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonToCsvConverter {

    private static final String FEATURES_DIR = "src/main/resources/features";
    private static final String OUTPUT_CSV = "src/main/resources/features/all_features.csv";

    public static int calculateCreditScore(CreditEvaluationDTO dto) {
        double score = 1000;

        score += dto.getAge() * 3;
        score += dto.getMonthlyIncome() * 0.5;
        score += dto.getEmploymentStatusCode() * 50;
        score += dto.getEducationLevelCode() * 40;
        score += dto.getMaritalStatusCode() * 20;
        score += dto.getCollateralCount() * 80;
        score += dto.getTotalCollateralValue() * 0.02;
        score += dto.getOnTimeDebtCount() * 30;
        score += dto.getOnTimeDebtAmount() * 0.01;

        score -= dto.getTotalDebtCount() * 25;
        score -= dto.getTotalDebtAmount() * 0.03;
        score -= dto.getLateDebtCount() * 100;
        score -= dto.getLateDebtAmount() * 0.05;
        score -= dto.getLoanApplicationsLastYear() * 40;

        score -= dto.getCurrentCardDebt() * 0.04;
        score += dto.getCreditCardUsageDuration() * 5;
        score += dto.getCreditCardLimit() * 0.005;

        score += dto.getCreditScore() * 0.2;


        int finalScore = (int) Math.round(score);
        return finalScore;
    }

    public static long calculateEligibleLoanAmount(CreditEvaluationDTO dto) {
        double amount = 50_000;


        amount += dto.getMonthlyIncome() * 2.5;


        amount -= dto.getTotalDebtAmount() * 0.3;


        amount -= dto.getCurrentCardDebt() * 0.5;


        amount += dto.getCollateralCount() * 5_000;
        amount += dto.getTotalCollateralValue() * 0.1;


        amount -= dto.getLoanApplicationsLastYear() * 1_000;
        amount += dto.getOnTimeDebtCount() * 500;


        amount += dto.getEmploymentStatusCode() * 2_000;
        if (dto.getAge() < 30) amount -= 5_000;
        else if (dto.getAge() > 60) amount -= 3_000;


        amount += dto.getCreditScore() * 100;


        long finalAmount = Math.round(amount);
        finalAmount = Math.max(0, finalAmount);
        finalAmount = Math.min(1_000_000, finalAmount);
        return finalAmount;
    }

    public static void convertAllJsonToCsv() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (CSVWriter writer = new CSVWriter(new FileWriter(OUTPUT_CSV))) {

            String[] header = {
                    "personTc", "loanId", "incomeStabilityScore", "debtToIncomeRatio",
                    "paymentDisciplineRatio", "latePaymentSeverity", "ageIncomeRatio",
                    "creditExperienceRatio", "avgDebtPerCount", "onTimeAmountRatio",
                    "lateDebtFrequency", "monthlyPaymentCapacity", "loanDurationExperience",
                    "collateralToIncome", "collateralPerMonth", "creditUtilizationRatio",
                    "age", "monthlyIncome", "employmentStatusCode", "educationLevelCode",
                    "maritalStatusCode", "loanTypeCode", "loanTerm", "totalDebtCount",
                    "totalDebtAmount", "onTimeDebtCount", "onTimeDebtAmount",
                    "lateDebtCount", "lateDebtAmount", "loanApplicationsLastYear",
                    "creditCardLimit", "currentCardDebt", "creditCardUsageDuration",
                    "collateralCount", "totalCollateralValue",
                    "creditScore", "eligibleLoanAmount"
            };
            writer.writeNext(header);

            File baseDir = Paths.get(FEATURES_DIR).toFile();
            File[] personDirs = baseDir.listFiles(File::isDirectory);
            if (personDirs != null) {
                for (File personDir : personDirs) {
                    File[] jsonFiles = personDir.listFiles((d, name) -> name.endsWith(".json"));
                    if (jsonFiles != null) {
                        for (File jsonFile : jsonFiles) {
                            CreditEvaluationDTO dto = mapper.readValue(jsonFile, CreditEvaluationDTO.class);
                            List<String> row = new ArrayList<>();
                            row.add(String.valueOf(dto.getPersonTc()));
                            row.add(String.valueOf(dto.getLoanId()));
                            row.add(String.valueOf(dto.getIncomeStabilityScore()));
                            row.add(String.valueOf(dto.getDebtToIncomeRatio()));
                            row.add(String.valueOf(dto.getPaymentDisciplineRatio()));
                            row.add(String.valueOf(dto.getLatePaymentSeverity()));
                            row.add(String.valueOf(dto.getAgeIncomeRatio()));
                            row.add(String.valueOf(dto.getCreditExperienceRatio()));
                            row.add(String.valueOf(dto.getAvgDebtPerCount()));
                            row.add(String.valueOf(dto.getOnTimeAmountRatio()));
                            row.add(String.valueOf(dto.getLateDebtFrequency()));
                            row.add(String.valueOf(dto.getMonthlyPaymentCapacity()));
                            row.add(String.valueOf(dto.getLoanDurationExperience()));
                            row.add(String.valueOf(dto.getCollateralToIncome()));
                            row.add(String.valueOf(dto.getCollateralPerMonth()));
                            row.add(String.valueOf(dto.getCreditUtilizationRatio()));
                            row.add(String.valueOf(dto.getAge()));
                            row.add(String.valueOf(dto.getMonthlyIncome()));
                            row.add(String.valueOf(dto.getEmploymentStatusCode()));
                            row.add(String.valueOf(dto.getEducationLevelCode()));
                            row.add(String.valueOf(dto.getMaritalStatusCode()));
                            row.add(String.valueOf(dto.getLoanTypeCode()));
                            row.add(String.valueOf(dto.getLoanTerm()));
                            row.add(String.valueOf(dto.getTotalDebtCount()));
                            row.add(String.valueOf(dto.getTotalDebtAmount()));
                            row.add(String.valueOf(dto.getOnTimeDebtCount()));
                            row.add(String.valueOf(dto.getOnTimeDebtAmount()));
                            row.add(String.valueOf(dto.getLateDebtCount()));
                            row.add(String.valueOf(dto.getLateDebtAmount()));
                            row.add(String.valueOf(dto.getLoanApplicationsLastYear()));
                            row.add(String.valueOf(dto.getCreditCardLimit()));
                            row.add(String.valueOf(dto.getCurrentCardDebt()));
                            row.add(String.valueOf(dto.getCreditCardUsageDuration()));
                            row.add(String.valueOf(dto.getCollateralCount()));
                            row.add(String.valueOf(dto.getTotalCollateralValue()));

                            int creditScore = calculateCreditScore(dto);
                            double eligibleLoanAmount = calculateEligibleLoanAmount(dto);


                            row.add(String.valueOf(creditScore));
                            row.add(String.valueOf(eligibleLoanAmount));

                            writer.writeNext(row.toArray(new String[0]));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        convertAllJsonToCsv();

    }
}
