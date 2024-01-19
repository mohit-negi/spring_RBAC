package com.astute.RBAC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.astute.JwtRoleBaseAuthorization.repository")
@EntityScan(value = "com.harry.JwtRoleBaseAuthorization.model")
@OpenAPIDefinition(info = @Info(title = "Basic JWT Role-base authentication/authorization api docs", version = "1.0.0", description = "This project is for learning only!", contact = @Contact(name = "Harry Nguyen", email = "chihieunguyen9a2@gmail.com")))
public class RbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);
	}

}
