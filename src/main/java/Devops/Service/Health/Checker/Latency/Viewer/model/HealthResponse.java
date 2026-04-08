package Devops.Service.Health.Checker.Latency.Viewer.model;

import lombok.*;

@Data
public class HealthResponse {

	 private String url;
	 private String status;
	 private int statusCode;
	 private long responseTime;
}
