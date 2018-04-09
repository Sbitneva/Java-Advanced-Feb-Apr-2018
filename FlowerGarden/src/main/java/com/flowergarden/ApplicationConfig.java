package com.flowergarden;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;
import com.flowergarden.service.GetAllBouquetsService;
import com.flowergarden.service.GetBouquetFlowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.flowergarden")
@PropertySource(value = {"classpath:db.properties"})
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        return dataSource;
    }

    @Bean
    public GetBouquetFlowersService getBouquetFlowersService(){
        return new GetBouquetFlowersService();
    }

    @Bean
    public GetAllBouquetsService getAllBouquetsService(){
        return new GetAllBouquetsService();
    }
}
