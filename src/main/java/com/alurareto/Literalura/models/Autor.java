package com.alurareto.Literalura.models;


public class Autor {
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    private Libro libro;

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    public String toString(){
        return nombre + ", año de nacimiento: " + fechaDeNacimiento + ", año de muerte: " + fechaDeMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
