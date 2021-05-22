package com.example.Bakalaurinis.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@Configurable
public class PostgresDatasource {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
