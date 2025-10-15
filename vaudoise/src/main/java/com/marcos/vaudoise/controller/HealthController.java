package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.service.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final HealthService healthService;
    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping(value = "/smoke", produces = "text/plain")
    public ResponseEntity<String> smoke() {
        return ResponseEntity.ok(healthService.smokePing());
    }

}
