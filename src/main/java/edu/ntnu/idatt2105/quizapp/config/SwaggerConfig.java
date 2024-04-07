package edu.ntnu.idatt2105.quizapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Class used to configure Swagger.
 */
@OpenAPIDefinition(

    info = @Info(
        title = "QuizApp API",
        version = "1.0",
        description = "API for QuizApp, a project in IDATT2105 - Full-stack applikasjonsutvikling"
            + " 2024",
        contact = @Contact(
            name = "the developers (Tobias Oftedal, Ramtin Samavat & Jeffrey Tabiri)",
            email = "tobiasof@ntnu.no"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Local server"
        ),
    }
)
public class SwaggerConfig {

}
