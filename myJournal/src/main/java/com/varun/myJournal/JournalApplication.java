package com.varun.myJournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory)
	{
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemp(){
		return new RestTemplate();
	}

}

//In order to make our @Transactional work, we need to make an implementation of PlatformTransactionManager
//as shown above

//PlatformTransactionManager----> Interface--that contains commit() and rollback() methods
//MongoTransactionManager-------> implements PlatformTransactionManager(i.e commit() and rollback)
//MongoTransactionManager is responsible for the commit() and rollback()
// of the @Transaction methods.

//MongoTransactionManager: This is the class that manages transactions for MongoDB.
//It is a Spring-provided transaction manager specifically for MongoDB.

//MongoDatabaseFactory helps us to make connection with MongoDB database.
//Database Sessions can be made using MongoDatabaseFactory.
//Sessions in Spring at higher level are called Transactional Context.

//The @Bean annotation is used in Spring to define a method that returns an object
// which Spring should manage as a bean in its application context.

//You don’t need to define @Bean for every predefined class in Spring Boot.
// Many beans are autoconfigured and available for injection without explicit bean
// definitions. Use @Bean only when you need to customize the default behavior,
// configure third-party classes, or control how certain objects are created.

//@Scheduling annotation allows Spring to know that we are using @Scheduled method
