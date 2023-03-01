package com.sparta.crudassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudassignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudassignmentApplication.class, args);
    }

}
