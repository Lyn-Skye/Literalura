package com.alurareto.Literalura.principal;

import com.alurareto.Literalura.models.*;
import com.alurareto.Literalura.repository.AutorRepository;
import com.alurareto.Literalura.repository.LibroRepository;
import com.alurareto.Literalura.services.ConsumoAPI;
import com.alurareto.Literalura.services.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;


    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
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
                    ListaAutoresVivosFecha();
                    break;

                case 5:
                    ListaLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Has elegido salir, gracias por usar nuestros servicios, vuelva pronto");
                    break;

                default:
                    System.out.println("Opción inválida, elige un número válido.");
            }
        }
    }


    private DatosCrudos buscaDatosCrudos() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        String libroABuscar = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + libroABuscar.replace(" ", "+"));
        return conversor.obtenerDatos(json, DatosCrudos.class);
    }


    // Opcion 1
    private void buscarLibroWeb() {
        DatosCrudos datosCrudos = buscaDatosCrudos();
        if (datosCrudos == null || datosCrudos.resultado().isEmpty()) {
            System.out.println("El libro buscado no se encuentra en Gutendex.com");
            System.out.println("\n");
        } else {
            DatosLibro datosLibro = datosCrudos.resultado().get(0);
            Libro libro = new Libro(datosLibro);
            List<Autor> autores = new ArrayList<>();
            for (DatosAutor datosAutor : datosLibro.autores()) {
                Autor autor = new Autor(datosAutor);
                autor.setLibro(libro);
                autores.add(autor);
            }
            libro.setAutores(autores);
            System.out.println("Libro encontrado: " + libro);
            try {
                repositorio.save(libro);
                System.out.println("El libro se ha guardado en la base de datos");
                System.out.println("\n");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Este libro ya existe en la base de datos");
                System.out.println("\n");
            }
        }
    }


    // Opción 2
    private void listaLibrosRegistrados() {
        System.out.println("Estos son los libros almacenados");
        libros = repositorio.findAll();
        libros.forEach(System.out::println);
        System.out.println("\n");
    }


    // Opción 3
    private void ListaAutoresRegistrados() {
        System.out.println("Estos son los autores almacenados");
        autores = autorRepository.findAll();
        autores.forEach(System.out::println);
        System.out.println("\n");
    }


    //Opción 4
    private void ListaAutoresVivosFecha() {
        System.out.println("Indica el año en el que quieres revisar que autores estaban vivos: ");
        String fecha = teclado.nextLine();
        autores = autorRepository.autoresVivos(fecha);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año" + fecha + " en el registro");
        } else {
            System.out.println("Autores vivos en el año: " + fecha);
            autores.forEach(System.out::println);
            System.out.println("\n");
        }
    }


    // Opción 5
    private void ListaLibrosPorIdioma() {
        System.out.println("Escribe la clave de dos caracteres del idioma en el formato ISO 639-1:2002 para los idiomas. ");
        System.out.println("Ejemplo: Inglés= en, Español= es, Francés= fr, Italiano= it, etc");
        String idioma = teclado.nextLine();
        try {
            libros = repositorio.findByIdiomas(idioma);

            if (libros == null) {
                System.out.println("No se encuentran libros en ese idioma en la base de datos");
            } else {
                libros.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Error en la busqueda");
        }
    }
}
