package com.rentingbook.api;

import com.rentingbook.api.audit.AuditorAwareImpl;
import com.rentingbook.api.model.account.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApiApplication {
    @Bean
    public AuditorAware<Account> auditorAware() {
        return new AuditorAwareImpl();
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
