package org.merc.component;

import org.merc.service.ExternalExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static ExternalExchangeRateService externalExchangeRateService;

    @Autowired
    public ScheduledTasks(ExternalExchangeRateService externalExchangeRateService) {
        this.externalExchangeRateService = externalExchangeRateService;
    }

    @Scheduled(cron = "0 0 */2 * * ?") // every 2 hours
    public void fetchDataScheduler() {
        externalExchangeRateService.fetchConversionRates();
    }

    @Scheduled(cron = "0 0 1 * * ?") // every day at 01:00
    public void dailyReportScheduler() {
        externalExchangeRateService.createDailyReport(externalExchangeRateService.fetchConversionRates());
    }
}
