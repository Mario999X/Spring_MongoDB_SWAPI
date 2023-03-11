package resa.mario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import resa.mario.models.Personaje;

@Repository
public interface PersonajeRepository extends CrudRepository<Personaje, String> {
}
