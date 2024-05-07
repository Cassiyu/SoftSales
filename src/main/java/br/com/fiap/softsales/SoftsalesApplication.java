package br.com.fiap.softsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Controller
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "SoftSales API",
		version = "1.0.0",
		description = "API do app de gestão de vendas",
		contact = @Contact(name = "Soft Assistance", email = "softassistance@fiap.com.br")
	)
)
public class SoftsalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftsalesApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String home() {
		return "Soft Sales";
	}

}
