package com.example.demomultipledatasources.persona.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demomultipledatasources.persona.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {

}
