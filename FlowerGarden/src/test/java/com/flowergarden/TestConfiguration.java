package com.flowergarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = "com.flowergarden")
@PropertySource(value = {"classpath:db.properties"})
public class TestConfiguration {

    @Autowired
    private Environment env;

    @Bean
    @Profile("test")
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println(Arrays.deepToString(env.getActiveProfiles()));
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("CONNECT!");
        } catch (SQLException e) {

        }
        return dataSource;
    }
}


