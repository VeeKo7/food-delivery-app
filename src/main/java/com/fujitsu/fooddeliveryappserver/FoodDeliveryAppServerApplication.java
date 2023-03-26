package com.fujitsu.fooddeliveryappserver;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
//enables scheduler of the app
@EnableScheduling
public class FoodDeliveryAppServerApplication {

	/**
	 * @Scheduled triggers the scheduler for a specific time period
	 * */
	@Scheduled
	public void cronJobSch() throws Exception {}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("DriverManagerDataSource.xml");

		SpringApplication.run(FoodDeliveryAppServerApplication.class, args);
	}

}
