package com.pluralsight.NorthwindTradersSpringBoot.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseConfig {
    private BasicDataSource basicDataSource;

    @Bean
    public DataSource dataSource() {
        return basicDataSource;
    }

    @Autowired
    public DatabaseConfig(@Value("${datasource.url}") String url,
                          @Value("${data.source.username}") String username,
                          @Value("${datasource.password}") String password)
    {
        basicDataSource= new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
}
