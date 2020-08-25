package com.mall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class TakeMallMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakeMallMemberApplication.class, args);
	}

}
