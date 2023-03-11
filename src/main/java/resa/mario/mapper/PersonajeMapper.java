package resa.mario.mapper;

import org.springframework.stereotype.Component;
import resa.mario.dto.PersonajeDTO;
import resa.mario.models.Personaje;

@Component
public class PersonajeMapper {
    //private final ModelMapper modelMapper = new ModelMapper();

    // Debido al tema del ID, al final no usare modelMapper en este caso y lo hare a mano
    public Personaje fromDTO(PersonajeDTO personajeDTO, String id) {
        return Personaje.builder()
                .id(id)
                .birthYear(personajeDTO.getBirthYear())
                .name(personajeDTO.getName())
                .films(personajeDTO.getFilms())
                .build();
    }
}
