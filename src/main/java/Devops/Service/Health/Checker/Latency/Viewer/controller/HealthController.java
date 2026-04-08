package Devops.Service.Health.Checker.Latency.Viewer.controller;

import Devops.Service.Health.Checker.Latency.Viewer.service.HealthService;
import Devops.Service.Health.Checker.Latency.Viewer.model.HealthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HealthController {

	private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/check")
    public String checkHealth(@RequestParam("url") String url, Model model) {

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            model.addAttribute("error", "Invalid URL. Must start with http:// or https://");
            return "index";
        }

        HealthResponse response = healthService.checkHealth(url);
        model.addAttribute("result", response);

        return "index";
    }
}
