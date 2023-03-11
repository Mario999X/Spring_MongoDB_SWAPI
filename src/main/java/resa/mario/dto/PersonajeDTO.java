package resa.mario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

// Lo que se obtiene de la API
// https://mercyjemosop.medium.com/a-model-from-a-json-response-b2f56ea40b3a
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PersonajeDTO {
    @JsonProperty("birth_year")
    String birthYear;
    @JsonProperty("name")
    String name;
    @JsonProperty("films")
    List<String> films;

    @Override
    public String toString() {
        return "PersonajeDTO{" +
                "birthYear='" + birthYear + '\'' +
                ", name='" + name + '\'' +
                ", films=" + films +
                '}';
    }
}
