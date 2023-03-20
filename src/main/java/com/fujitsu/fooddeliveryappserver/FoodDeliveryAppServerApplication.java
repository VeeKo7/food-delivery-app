package com.fujitsu.fooddeliveryappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

		SpringApplication.run(FoodDeliveryAppServerApplication.class, args);
	}

}
