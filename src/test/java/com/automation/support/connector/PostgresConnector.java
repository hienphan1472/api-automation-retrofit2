package com.automation.support.connector;


import com.automation.support.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by pro on 21/10/2015.
 */
public class PostgresConnector {

    private JdbcTemplate jdbcTemplate;
    private Configuration configuration = Configuration.get();
    private String service;

    // The service string is used later to look up the connection details from the test config.
    public PostgresConnector(String service) {
        this.service = service;
        setUpJDBCTemplate();
    }

    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }

    private void setUpJDBCTemplate(){

        if (jdbcTemplate != null){
            return;
        }

        String url = configuration.getTestProperty("postgresql."+service+".url");
        String dbUsername = configuration.getTestProperty("postgresql."+service+".user");
        String dbPassword = configuration.getTestProperty("postgresql."+service+".password");
        String driverClassName = "org.postgresql.Driver";

        DataSource dataSource = getDataSource(url, dbUsername, dbPassword, driverClassName);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private DriverManagerDataSource getDataSource(String url, String dbUsername, String dbPassword, String driverClassName) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);

        dataSource.setUrl(url);

        dataSource.setUsername(dbUsername);

        dataSource.setPassword(dbPassword);

        return dataSource;
    }

}
