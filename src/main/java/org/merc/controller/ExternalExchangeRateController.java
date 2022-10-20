package org.merc.controller;

import org.merc.entity.Exchangerates;
import org.merc.service.ExternalExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExternalExchangeRateController {

    private static ExternalExchangeRateService externalExchangeRateService;

    @Autowired
    public ExternalExchangeRateController(ExternalExchangeRateService externalExchangeRateService) {
        this.externalExchangeRateService = externalExchangeRateService;
    }

    @GetMapping()
    public static Exchangerates conversionRates() {
        return externalExchangeRateService.fetchConversionRates();
    }

    @GetMapping(value = "/pairConversion")
    public static Optional<Exchangerates> pairConversionRates(@RequestParam String baseCode, @RequestParam String targetCode) {
        return externalExchangeRateService.fetchPairConversionRates(baseCode, targetCode);
    }

    @GetMapping(value = "/dailyReport")
    public static Optional<Exchangerates> readDailyReport() {
        return externalExchangeRateService.readDailyReport();
    }

}
