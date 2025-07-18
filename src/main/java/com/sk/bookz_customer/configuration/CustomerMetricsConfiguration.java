package com.sk.bookz_customer.configuration;

import com.sk.bookz_customer.service.ICustomerService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
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
}
