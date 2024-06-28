package com.alurareto.Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.OptionalDouble;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private int fechaDeNacimiento;
    private int fechaDeMuerte;
    @ManyToOne
    private Libro libro;

    public Autor(){    }

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = Integer.valueOf(datosAutor.fechaDeNacimiento());
        this.fechaDeMuerte = Integer.valueOf(datosAutor.fechaDeMuerte());
    }

    public String toString(){
        return "Autores: " + nombre + ", nacimiento: " + fechaDeNacimiento + ", muerte: " + fechaDeMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(int fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }


}


