package com.granoAndClick.granoAndClick.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.granoAndClick.granoAndClick.model.TiposUsuarios;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.repository.UsuariosRepository;
import com.granoAndClick.granoAndClick.repository.TiposUsuariosRepository;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@Order(2)
public class DataInitializer {

    @Bean
    CommandLineRunner initUser(UsuariosRepository usuarioRep,
                               TiposUsuariosRepository tiposRep,
                               PasswordEncoder encoder) {
        return args -> {
            // Cargar variables desde .env
            Dotenv dotenv = Dotenv.load();

            String nombre = dotenv.get("USER_DEFAULT_NOMBRE");
            String apellido = dotenv.get("USER_DEFAULT_APELLIDO");
            String correo = dotenv.get("USER_DEFAULT_CORREO");
            String fechaNacimiento = dotenv.get("USER_DEFAULT_FECHA_NACIMIENTO");
            String calleNumero = dotenv.get("USER_DEFAULT_CALLE_NUMERO");
            String colonia = dotenv.get("USER_DEFAULT_COLONIA");
            String municipio = dotenv.get("USER_DEFAULT_MUNICIPIO");
            String codigoPostal = dotenv.get("USER_DEFAULT_CODIGO_POSTAL");
            String telefono = dotenv.get("USER_DEFAULT_TELEFONO");
            String password = dotenv.get("USER_DEFAULT_PASSWORD");

            if (usuarioRep.findByCorreo(correo).isEmpty()) {
                TiposUsuarios adminTipo = tiposRep.findById(1L)
                        .orElseThrow(() -> new IllegalStateException("Tipo de usuario ADMIN no existe en la BD"));

                Usuarios admin = new Usuarios();
                admin.setNombres(nombre);
                admin.setApellidos(apellido);
                admin.setCorreo(correo);
                admin.setTelefono(telefono);
                admin.setFechaNacimiento(LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                admin.setCalleNumero(calleNumero);
                admin.setMunicipio(municipio);
                admin.setColonia(colonia);
                admin.setCodigoPostal(codigoPostal);
                admin.setContrasena(encoder.encode(password));
                admin.setTiposUsuario(adminTipo);

                usuarioRep.save(admin);
                System.out.println("Usuario admin creado");
            }
        };
    }
}
