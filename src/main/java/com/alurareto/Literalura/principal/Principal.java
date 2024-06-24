package com.alurareto.Literalura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);

    public void  muestraElMenu(){
        System.out.println("""
                Elige la opción a través de su número:
                1- Buscar libor por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """);
        var nombreLibro = teclado.nextLine();
    }
}
