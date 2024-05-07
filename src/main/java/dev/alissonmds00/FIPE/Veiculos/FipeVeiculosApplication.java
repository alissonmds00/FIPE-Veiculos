package dev.alissonmds00.FIPE.Veiculos;

import dev.alissonmds00.FIPE.Veiculos.main.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeVeiculosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeVeiculosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal menu = new Principal();
		menu.iniciarMenu();
	}
};

