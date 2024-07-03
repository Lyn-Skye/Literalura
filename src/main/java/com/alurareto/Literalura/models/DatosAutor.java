package com.alurareto.Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaDeNacimiento,
        @JsonAlias("death_year") String fechaDeMuerte){
}
