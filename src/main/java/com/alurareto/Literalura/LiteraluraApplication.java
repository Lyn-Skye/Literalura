package com.alurareto.Literalura;

import com.alurareto.Literalura.repository.AutorRepository;
import com.alurareto.Literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alurareto.Literalura.principal.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner { //Se tuvo que agregar el implements CommandLineRunner

	@Autowired //Como la clase principal la creamos nosotros y no por Spring, no la reconoce. Pero esta sí, por eos le agregamos el @autowired para indicar que queremos hacer una inyección de independencia
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorRepository);
		principal.muestraElMenu();
	}
}
