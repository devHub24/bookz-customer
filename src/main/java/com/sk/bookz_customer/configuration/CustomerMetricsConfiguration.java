package com.sk.bookz_customer.configuration;

import com.sk.bookz_customer.service.ICustomerService;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerMetricsConfiguration {

    @Bean
    public Gauge getGauge(MeterRegistry meterRegistry, ICustomerService customerService) {

        return Gauge.builder("api.customer.created.gauge", customerService::count)
                .description("Total number of Customer")
                .register(meterRegistry);
    }

    @Bean
    public Timer getTimer(MeterRegistry meterRegistry) {
        return Timer.builder("api.customer.created.timer")
                .description("Total Time to create a  Customer")
                .register(meterRegistry);
    }

    @Bean
    public DistributionSummary getDistributionSummary(MeterRegistry meterRegistry) {
        return DistributionSummary.builder("api.customer.created.summary")
                .description("Distrobution summary")
                .register(meterRegistry);
    }
}
