package br.com.dbcarkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DbcarKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbcarKafkaApplication.class, args);
	}

}
