package edu.ifma.SI.LPWeb.campeonatoFutebol;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CampeonatoFutebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampeonatoFutebolApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
