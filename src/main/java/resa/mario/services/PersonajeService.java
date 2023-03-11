package resa.mario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import resa.mario.dto.PersonajeDTO;
import resa.mario.models.Personaje;
import resa.mario.repository.PersonajeRepository;

import java.time.Duration;
import java.util.List;

@Service
public class PersonajeService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient webClient;

    private final PersonajeRepository repository;

    @Autowired
    public PersonajeService(WebClient webClient, PersonajeRepository repository) {
        this.webClient = webClient;
        this.repository = repository;
    }

    public Mono<PersonajeDTO> getPersonaje(String id) {
        return webClient
                .get()
                .uri("people/" + id)
                .retrieve()
                .bodyToMono(PersonajeDTO.class)
                .onErrorReturn(new PersonajeDTO());
    }

    @CachePut("personajes")
    public Personaje findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @CachePut("personajes")
    public Personaje savePersonaje(Personaje personaje) {
        return repository.save(personaje);
    }

    public List<Personaje> getAllPersonajes() {
        return (List<Personaje>) repository.findAll();
    }

    @CacheEvict("personajes")
    public void deleteAll() {
        repository.deleteAll();
    }


}
