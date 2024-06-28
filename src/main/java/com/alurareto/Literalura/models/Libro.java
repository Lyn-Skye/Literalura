package com.alurareto.Literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity //Entity es para indicar que será para la base de datos
@Table(name="libros") // para cambiarle le nombre a nivel base de Datos
public class Libro {

    @Id //para indicar que el número siguiente será el que almacene el ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany
    private List<Autor> autores;
    private List<String> idiomas;


    public Libro (){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.idiomas = datosLibro.idiomas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }
}
