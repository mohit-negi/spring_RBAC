package com.astute.RBAC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//@EnableJpaRepositories
//@EnableJpaRepositories(value = "com.astute.RBAC.entiites.repo")
//@EntityScan(value = "com.astute.RBAC.entities")
//@OpenAPIDefinition(info = @Info(title = "Basic JWT Role-base authentication/authorization api docs", version = "1.0.0", description = "This project is for learning only!", contact = @Contact(name = "Mohit Negi", email = "negi.astute@gmail.com")))

@SpringBootApplication
public class RbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);
	}

}
