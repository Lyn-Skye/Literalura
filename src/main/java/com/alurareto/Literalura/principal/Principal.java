package com.alurareto.Literalura.principal;

import com.alurareto.Literalura.models.DatosCrudos;
import com.alurareto.Literalura.models.DatosLibro;
import com.alurareto.Literalura.services.ConsumoAPI;
import com.alurareto.Literalura.services.ConvierteDatos;

import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();


    public void  muestraElMenu(){
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, DatosCrudos.class);
        int opcion;

        System.out.println("""
                Elige la opción a través de su número:
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """);
        opcion = teclado.nextInt();

        switch (opcion){
            case 1:
                System.out.println("Escribe el nombre del libro que deseas buscar");
                var libroABuscar = teclado.nextLine();
                json = consumoApi.obtenerDatos(URL_BASE+libroABuscar.replace(" ","+"));
                var datosBusqueda = conversor.obtenerDatos(json, DatosCrudos.class);
                Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                        .filter(l->l.titulo().toUpperCase().contains(libroABuscar.toUpperCase()))
                        .findFirst();
                if (libroBuscado.isPresent()){
                    System.out.println("Libro encontrado");
                    System.out.println(libroBuscado.get());
                } else{
                    System.out.println("Libro no encontrado");
                }
        }
    }
}
