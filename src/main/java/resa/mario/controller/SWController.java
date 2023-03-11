package resa.mario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import resa.mario.mapper.PersonajeMapper;
import resa.mario.models.Personaje;
import resa.mario.repository.PersonajeRepository;
import resa.mario.services.PersonajeService;

import java.util.List;
import java.util.Objects;

@Controller
public class SWController {

    private final PersonajeService personajeService;
    private final PersonajeMapper personajeMapper;

    @Autowired
    public SWController(PersonajeService personajeService, PersonajeMapper personajeMapper) {
        this.personajeService = personajeService;
        this.personajeMapper = personajeMapper;
    }

    @Cacheable("personajes")
    public Personaje getPersonaje(String id) throws Exception {
        try {

            System.out.println("🔎");

            Personaje personajeMongo = personajeService.findById(id);

            System.err.println(personajeMongo);

            if (personajeMongo == null) {
                System.out.println("🔎🔎");

                Personaje personajeApi = personajeMapper.fromDTO(personajeService.getPersonaje(id), id);

                System.err.println(personajeApi);

                if (!Objects.equals(personajeApi.getName(), null)) {
                    personajeMongo = personajeService.savePersonaje(personajeApi);
                } else System.err.println("Personaje inexistente");
            }

            return personajeMongo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Personaje> findAll() {
        return personajeService.getAllPersonajes();
    }

    public void deleteAll() {
        personajeService.deleteAll();
    }

}
