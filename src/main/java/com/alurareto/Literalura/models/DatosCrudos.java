package com.alurareto.Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosCrudos(@JsonAlias("results") List<DatosLibro> resultado) {
}

