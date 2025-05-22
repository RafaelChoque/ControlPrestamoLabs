package PersonalAcademico;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void enviarCorreo(String destinatario, String asunto, String mensajeTexto) {
        // ⚠️ Establecer las propiedades TLS justo al inicio
        System.setProperty("https.protocols", "TLSv1.2");
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        final String remitente = "proyectolosjacksonrej@gmail.com"; // Tu correo
        final String password = "qgjz uluf seaj mrko"; // Contraseña de aplicación (no la real)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 🔐 Reafirmamos el protocolo TLS para compatibilidad
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(mensajeTexto);

            Transport.send(mensaje);
            System.out.println("Correo enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace(); // 👈 Aquí te muestra el error si hay problemas
        }
    }
}
