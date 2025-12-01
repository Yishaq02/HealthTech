package com.zacks.HealthTech.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "HealthTech API", version = "1.0", description = "API para gestión de citas médicas, doctores y pacientes.", contact = @Contact(name = "Isaac (Zacks)", email = "isaacatilano123@gmail.com", url = "https://tu-portafolio.com"), license = @License(name = "Apache 2.0", url = "http://springdoc.org")))
public class OpenApiConfig {
}