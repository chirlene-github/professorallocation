package com.project.professor.allocation.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class ProfessorAllocationConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }

    /*@Bean
    public Flyway flywayConfigurer(DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).locations("db" + File.separator + "migration").baselineOnMigrate(true).load();
        flyway.baseline();
        flyway.migrate();
        return flyway;
    }*/
}
