package com.systalk.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
"com.systalk.sys.dao" }, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")

public class JPAConfig {
}
