package fr.simplon.festivals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application de visualisation des festivals bretons sur une carte interactive. L'application permet également de
 * référencer de nouveaux festivals ou de modifier les festivals existants.
 */
@SpringBootApplication
public class FestivalsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FestivalsApplication.class, args);
    }
}
