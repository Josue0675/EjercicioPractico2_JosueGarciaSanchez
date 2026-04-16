package ejerciciopractico2_josuegarcias.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ejerciciopractico2_josuegarcias.service.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService {
    private final JavaMailSender mailSender;
    private final boolean mailEnabled;

    public CorreoServiceImpl(JavaMailSender mailSender,
                             @Value("${app.mail.enabled:false}") boolean mailEnabled) {
        this.mailSender = mailSender;
        this.mailEnabled = mailEnabled;
    }

    public void enviarBienvenida(String destino, String nombre) {
        if (!mailEnabled || destino == null || destino.isBlank()) return;
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destino);
            mensaje.setSubject("Bienvenido a la Plataforma de Eventos");
            mensaje.setText("Hola " + nombre + ", su cuenta fue creada correctamente.");
            mailSender.send(mensaje);
        } catch (Exception ex) {
            // no interrumpir el flujo
        }
    }
}
