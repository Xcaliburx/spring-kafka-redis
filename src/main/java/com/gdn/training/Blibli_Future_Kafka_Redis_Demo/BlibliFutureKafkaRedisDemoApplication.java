package com.gdn.training.Blibli_Future_Kafka_Redis_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class BlibliFutureKafkaRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlibliFutureKafkaRedisDemoApplication.class, args);
	}

}
