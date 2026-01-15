package com.granoAndClick.granoAndClick.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.granoAndClick.granoAndClick.model.TiposUsuarios;
import com.granoAndClick.granoAndClick.repository.TiposUsuariosRepository;

@Configuration
@Order(1)
public class TiposUsuariosInitializer {

    @Bean
    CommandLineRunner initTiposUsuarios(TiposUsuariosRepository tiposRep) {
        return args -> {
            // Cargar variables desde .env
            Dotenv dotenv = Dotenv.load();

            String tipoUsuarioNombre1 = dotenv.get("TYPE_USER_DEFAULT_NOMBRE_1");
            String tipoUsuarioDescripcion1 = dotenv.get("TYPE_USER_DEFAULT_DESCRIPCION_1");
            String tipoUsuarioNombre2 = dotenv.get("TYPE_USER_DEFAULT_NOMBRE_2");
            String tipoUsuarioDescripcion2 = dotenv.get("TYPE_USER_DEFAULT_DESCRIPCION_2");
            if (tiposRep.count() == 0) {
                TiposUsuarios admin = new TiposUsuarios(tipoUsuarioNombre1, tipoUsuarioDescripcion1);
                TiposUsuarios user = new TiposUsuarios(tipoUsuarioNombre2, tipoUsuarioDescripcion2);

                tiposRep.save(admin);
                tiposRep.save(user);

                System.out.println("Tipos de usuario iniciales creados");
            }
        };
    }
}
