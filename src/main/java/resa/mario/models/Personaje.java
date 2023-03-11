package resa.mario.models;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("personajes")
public class Personaje {
    @BsonId
    String id;
    String birthYear;
    String name;
    List<String> films;

    public Personaje(String id, String birthYear, String name, List<String> films) {
        this.id = id;
        this.birthYear = birthYear;
        this.name = name;
        this.films = films;
    }
}
