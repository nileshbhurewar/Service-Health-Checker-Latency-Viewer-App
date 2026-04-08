package Devops.Service.Health.Checker.Latency.Viewer.service;


import Devops.Service.Health.Checker.Latency.Viewer.model.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthService {

	private final RestTemplate restTemplate = new RestTemplate();

    public HealthResponse checkHealth(String url) {
        HealthResponse response = new HealthResponse();
        response.setUrl(url);

        long startTime = System.currentTimeMillis();

        try {
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

            long endTime = System.currentTimeMillis();

            response.setStatus("UP");
            response.setStatusCode(entity.getStatusCode().value());
            response.setResponseTime(endTime - startTime);

        } catch (Exception e) {
            long endTime = System.currentTimeMillis();

            response.setStatus("DOWN");
            response.setStatusCode(0);
            response.setResponseTime(endTime - startTime);
        }

        return response;
    }
}
