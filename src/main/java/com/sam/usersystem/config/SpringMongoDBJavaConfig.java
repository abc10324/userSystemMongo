package com.sam.usersystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages="com.sam.usersystem.model.dao")
public class SpringMongoDBJavaConfig extends AbstractMongoConfiguration {

	@Override
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}

	@Override
	protected String getDatabaseName() {
		return "UserSystem";
	}

}
