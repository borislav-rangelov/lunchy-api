package com.brangelov.lunchy;

import com.brangelov.lunchy.database.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DatabaseConfiguration.class)
public class LunchyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchyApplication.class, args);
	}

}
