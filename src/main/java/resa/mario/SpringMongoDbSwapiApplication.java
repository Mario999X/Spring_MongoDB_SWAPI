package resa.mario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import resa.mario.controller.SWController;

@SpringBootApplication
@EnableCaching
public class SpringMongoDbSwapiApplication implements CommandLineRunner {

    @Autowired
    SWController swController;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoDbSwapiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("\t -- INICIANDO TEST MONGODB CON SWAPI --");
        swController.deleteAll();

        /*while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("Escribe el id:");
            String id = input.nextLine();

            Personaje personaje = swController.getPersonaje(id);

            System.out.println(personaje);

            //swController.findAll().forEach(System.out::println);
        }*/
    }
}
