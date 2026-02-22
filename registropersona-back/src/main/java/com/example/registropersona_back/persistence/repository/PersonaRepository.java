package com.example.registropersona_back.persistence.repository;

import com.example.registropersona_back.persistence.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository <Persona,Long> {

}
