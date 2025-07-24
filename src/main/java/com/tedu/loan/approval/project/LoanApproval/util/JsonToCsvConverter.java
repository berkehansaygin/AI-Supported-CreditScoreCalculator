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
import java.util.Random;

public class JsonToCsvConverter {

    private static final String FEATURES_DIR = "src/main/resources/features";
    private static final String OUTPUT_CSV = "src/main/resources/features/all_features.csv" ;

    public static void convertAllJsonToCsv() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Random random = new Random();

        try (CSVWriter writer = new CSVWriter(new FileWriter(OUTPUT_CSV))) {

            String[] header = {
                    "personTc","loanId","incomeStabilityScore","debtToIncomeRatio",
                    "paymentDisciplineRatio","latePaymentSeverity","ageIncomeRatio",
                    "creditExperienceRatio","avgDebtPerCount","onTimeAmountRatio",
                    "lateDebtFrequency","monthlyPaymentCapacity","loanDurationExperience",
                    "collateralToIncome","collateralPerMonth","creditUtilizationRatio",
                    "age","monthlyIncome","employmentStatusCode","educationLevelCode",
                    "maritalStatusCode","loanTypeCode","loanTerm","totalDebtCount",
                    "totalDebtAmount","onTimeDebtCount","onTimeDebtAmount",
                    "lateDebtCount","lateDebtAmount","loanApplicationsLastYear",
                    "creditCardLimit","currentCardDebt","creditCardUsageDuration",
                    "collateralCount","totalCollateralValue",
                    "creditScore","eligibleLoanAmount","riskLevel"
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

                            // Dummy credit score and loan amount
                            int creditScore = random.nextInt(1801) + 100; // 100-1900
                            int eligibleLoanAmount = (int) (dto.getMonthlyIncome() * (4 + random.nextDouble() * 6)); // 4x–10x
                            String riskLevel = calculateRiskLevel(creditScore, eligibleLoanAmount);

                            row.add(String.valueOf(creditScore));
                            row.add(String.valueOf(eligibleLoanAmount));
                            row.add(riskLevel);

                            writer.writeNext(row.toArray(new String[0]));
                        }
                    }
                }
            }
        }
    }
    private static String calculateRiskLevel(int creditScore, int eligibleLoanAmount) {
        if (creditScore >= 1600 && eligibleLoanAmount >= 80000) {
            return "LOW";
        } else if (creditScore >= 1000) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }
    public static void main(String[] args) throws IOException {
        convertAllJsonToCsv();
    }
}
