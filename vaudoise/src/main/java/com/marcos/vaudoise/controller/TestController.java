package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.service.TestService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
@RequestMapping(path = "/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "generate-data", produces = "text/plain")
    public String generateData(@RequestParam("client_amount") int clientAmount, @RequestParam("contract_amount_per_client") int contractAmountPerClient) {
        log.info("Requested get all people route");
        return testService.generateData(clientAmount, contractAmountPerClient);
    }
}
