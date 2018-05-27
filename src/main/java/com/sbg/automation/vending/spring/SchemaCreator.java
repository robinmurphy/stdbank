package com.sbg.automation.vending.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaCreator {

    private static Logger logger = LoggerFactory.getLogger(SchemaCreator.class);

    private DataSource dataSource;
    private String schemaName;

    public SchemaCreator(DataSource dataSource, String schemaName) {
        this.setSchemaName(schemaName);
        this.setDataSource(dataSource);

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE SCHEMA " + schemaName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
