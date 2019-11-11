package com.bfsu.myproject_01;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@EnableRabbit  //开启基于注解的RabbitMQ模式
@EnableCaching
@SpringBootApplication
public class Myproject01Application {

	public static void main(String[] args) {
		SpringApplication.run(Myproject01Application.class, args);
	}

}
