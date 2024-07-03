package com.alurareto.Literalura.repository;

import com.alurareto.Literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByIdiomas(String idioma);

}
