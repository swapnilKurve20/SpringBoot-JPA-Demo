package com.example.demo.dao;

public interface DaoCommon {

	// Need to dynamically populate credentials at time of deployment
	static final String driverClassName = "org.postgresql.Driver";
	static final String url = "jdbc:postgresql://localhost/postgres";
    static final String username = "postgres";
    static final String password = "root";
}
