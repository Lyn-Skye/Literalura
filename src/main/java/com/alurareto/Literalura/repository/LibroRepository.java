package com.alurareto.Literalura.repository;

import com.alurareto.Literalura.models.Autor;
import com.alurareto.Literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {


}
