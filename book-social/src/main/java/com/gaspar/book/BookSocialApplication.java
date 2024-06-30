package com.gaspar.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //To allow AuditingEntityListener in the User
public class BookSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSocialApplication.class, args);
	}

}
