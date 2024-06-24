package com.alurareto.Literalura;

import com.alurareto.Literalura.models.DatosLibro;
import com.alurareto.Literalura.services.ConsumoAPI;
import com.alurareto.Literalura.services.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=71efe179");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosLibro.class);
	}
}
