package com.flowergarden.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Configuration
@ComponentScan(basePackages = "com.flowergarden")
@PropertySource(value = {"classpath:db.properties"})
public class ApplicationConfig {

    @Autowired
    private Environment env;
    @Profile("production")
    @Bean

    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("CONNECT!");
        } catch (SQLException e) {

        }
        return dataSource;
    }
}
