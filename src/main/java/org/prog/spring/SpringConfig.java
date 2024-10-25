package org.prog.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dmds =
                new DriverManagerDataSource(getDbHost(), "user", "password");
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        return dmds;
    }

    private String getDbHost() {
        String driverType = System.getProperty("driver.type", "chrome");
        if ("remote-mvn".equals(driverType)) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        } else {
            return "jdbc:mysql://localhost:3306/db";
        }
    }
}
