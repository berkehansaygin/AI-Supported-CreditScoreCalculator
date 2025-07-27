package com.tedu.loan.approval.project.LoanApproval.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.tedu.loan.approval.project.LoanApproval.enums.RiskLevel;
import com.tedu.loan.approval.project.LoanApproval.model.Prediction;
import com.tedu.loan.approval.project.LoanApproval.model.dto.PredictResponseDTO;
import com.tedu.loan.approval.project.LoanApproval.service.VertexPredictionService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class VertexPredictionServiceImpl implements VertexPredictionService {

    private static final String PROJECT_ID = "red-octane-467003-v9";
    private static final String LOCATION = "us-central1";
    private static final String ENDPOINT_ID = "6419737232793403392";
    private static final String SERVICE_ACCOUNT_JSON = "red-octane-467003-v9-1a6a2e47bf64.json";

    @Override
    public PredictResponseDTO predict(Map<String, Object> instanceData) throws Exception {
        String accessToken = getAccessToken();

        String endpoint = String.format(
                "https://%s-aiplatform.googleapis.com/v1/projects/%s/locations/%s/endpoints/%s:predict",
                LOCATION, PROJECT_ID, LOCATION, ENDPOINT_ID);

        Map<String, Object> payload = new HashMap<>();
        payload.put("instances", List.of(instanceData));
        payload.put("parameters", new HashMap<>());

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        connection.getOutputStream().write(jsonPayload.getBytes());
        InputStream responseStream;
        if (connection.getResponseCode() >= 400) {
            responseStream = connection.getErrorStream();
        } else {
            responseStream = connection.getInputStream();
        }

        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();
        return mapPredictToDto(response);
    }

    private String getAccessToken() throws Exception {
        // Burada dosyayı classpath'ten yükle, böylece src/main/resources altında olan dosyayı okuyabilir
        InputStream serviceAccountStream = getClass().getClassLoader().getResourceAsStream(SERVICE_ACCOUNT_JSON);
        if (serviceAccountStream == null) {
            throw new RuntimeException("Credential dosyası bulunamadı: " + SERVICE_ACCOUNT_JSON);
        }

        GoogleCredentials credentials = GoogleCredentials
                .fromStream(serviceAccountStream)
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }


    private PredictResponseDTO mapPredictToDto(String response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PredictResponseDTO predictResponseDTO = new PredictResponseDTO();
        Double score = 0.0;
        try {
            predictResponseDTO = objectMapper.readValue(response, PredictResponseDTO.class);
            predictResponseDTO.setModelTrainer("@Berkehan");
            for (Prediction p : predictResponseDTO.getPredictions()) {
                score = Double.parseDouble(p.getValue());
                if (score > 0 && score <= 50000) {
                    p.setRiskLevel(RiskLevel.HIGH.getDescription());
                } else if (score > 50001 && score <= 100000) {
                    p.setRiskLevel(RiskLevel.MEDIUM.getDescription());
                }
                if (score > 100001) {
                    p.setRiskLevel(RiskLevel.LOW.getDescription());
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return predictResponseDTO;
    }
}
