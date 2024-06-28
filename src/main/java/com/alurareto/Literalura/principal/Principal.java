package com.alurareto.Literalura.principal;

import com.alurareto.Literalura.models.Autor;
import com.alurareto.Literalura.models.DatosCrudos;
import com.alurareto.Literalura.models.DatosLibro;
import com.alurareto.Literalura.models.Libro;
import com.alurareto.Literalura.repository.LibroRepository;
import com.alurareto.Literalura.services.ConsumoAPI;
import com.alurareto.Literalura.services.ConvierteDatos;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private List<Libro> libros;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {

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
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;

                case 2:
                    listaLibrosRegistrados();
                    break;

                case 3:
                    ListaAutoresRegistrados();
                    break;

                case 4:
                    //                ListaAutoresVivosAño();
                    break;

                case 5:
                    //                ListaLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Has elegido salir, gracias por usar nuestros servicios, vuelva pronto");
                    break;

                default:
                    System.out.println("Opción inválida, elige un número válido.");
            }
        }
    }



    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        String libroABuscar = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + libroABuscar.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosCrudos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(libroABuscar.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
//            DatosCrudos datos = getDatosLibro();
//            Libro libro = new Libro(datos);
//            repositorio.save(libro);
//            System.out.println(datos);
        } else {
            System.out.println("Libro no encontrado");
        }
    }


    // Opción 2
    private void listaLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    // Opción 3
    private void ListaAutoresRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo));
 }

}
