package resa.mario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resa.mario.config.APIConfig;
import resa.mario.dto.PersonajeDTO;
import resa.mario.mapper.PersonajeMapper;
import resa.mario.models.Personaje;
import resa.mario.services.PersonajeService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

@RestController
@RequestMapping(APIConfig.API_PATH)
public class SWController {

    private final PersonajeService personajeService;
    private final PersonajeMapper personajeMapper;

    @Autowired
    public SWController(PersonajeService personajeService, PersonajeMapper personajeMapper) {
        this.personajeService = personajeService;
        this.personajeMapper = personajeMapper;
    }

    @Cacheable("personajes")
    @GetMapping("personaje/{id}")
    public ResponseEntity<Personaje> getPersonaje(@PathVariable String id) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("🔎");

        Personaje personajeMongo = personajeService.findById(id);

        //System.err.println(personajeMongo);

        if (personajeMongo == null) {
            System.out.println("🔎🔎");

            // Generamos una pool
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            // Generamos una promesa o future, donde esperaremos a que se realice la llamada y se obtenga el personaje
            Callable<PersonajeDTO> callable = () -> personajeService.getPersonaje(id).block();
            Future<PersonajeDTO> future = executorService.submit(callable);

            PersonajeDTO personajeApi = future.get(10, TimeUnit.SECONDS);

            // Cerramos la pool que no usaremos mas
            executorService.shutdown();
            if (!Objects.equals(personajeApi.getName(), null)) {
                personajeMongo = personajeService.savePersonaje(personajeMapper.fromDTO(personajeApi, id));
            } else throw new RuntimeException("Personaje no encontrado con id: " + id);
        }

        return ResponseEntity.ok(personajeMongo);
    }

    @GetMapping("/personaje/list")
    public ResponseEntity<List<Personaje>> findAll() {
        return ResponseEntity.ok(personajeService.getAllPersonajes());
    }

    @DeleteMapping("")
    public void deleteAll() {
        personajeService.deleteAll();
    }

}
