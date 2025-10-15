package com.marcos.vaudoise.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class HealthService {

    public String smokePing() {
        log.trace("Smoke ping requested");
        return "Vaudoise Service is up and running!";
    }
}
