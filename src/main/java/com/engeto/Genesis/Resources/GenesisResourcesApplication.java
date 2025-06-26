package com.engeto.Genesis.Resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class GenesisResourcesApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(GenesisResourcesApplication.class, args);

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todos","root","Avatar1981SQLDATA");

		Statement statement = connection .createStatement();

		ResultSet resultSet = statement.executeQuery();



	}

}
