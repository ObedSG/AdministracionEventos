package org.ipn.mx.administracioneventos;

import org.ipn.mx.administracioneventos.util.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdministracionEventosApplication implements CommandLineRunner {
//
//    @Autowired
//    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {

//        String texto="La energía renovable se ha convertido en uno de los pilares fundamentales para enfrentar el cambio climático. " +
//                "Tecnologías como la solar y la eólica no solo reducen las emisiones de gases de efecto invernadero, sino que también " +
//                "promueven la independencia energética de los países. A medida que los costos de producción disminuyen y la eficiencia " +
//                "de los sistemas mejora, cada vez más comunidades adoptan soluciones sostenibles para generar electricidad. Este cambio" +
//                " no solo" +
//                " impacta el medio ambiente, sino también la economía global, creando empleos y fomentando la innovación tecnológica.\n";
//
//
//
//                String to ="obeddyc2000@gmail.com";
//                String subject = "Correo de prueba desde spring boot ejemplo mio";
//
//                emailService.enviarCorreo(to,subject,texto);

    }


    public static void main(String[] args) {
        SpringApplication.run(AdministracionEventosApplication.class, args);
    }

}
