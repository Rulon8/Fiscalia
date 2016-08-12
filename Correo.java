package ucr.casoUso;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {
	
	public boolean enviar(String corrUsuario, String newPass) {
		boolean resultado = false;
		final String username = "appdefiscalia";
		final String password = "fiscalia";
		String from = "appdefiscalia@gmail.com";
		String to = corrUsuario;

		String estilo = "style=\" color:red; font-weight: bold;\"";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");  
		props.put("mail.smtp.port", "587");             
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.timeout", 10000);
        
		props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(to));
			
			message.setSubject("Asunto" );
			
			String mensaje = "<p>Su cuenta ha sido creada en el sistema de Fiscalía</p>";
			mensaje += "<br><p>Su nombre de usuario es su numero de cédula";
			mensaje += "<br><p>Se le ha asignado la siguiente contraseña temporal:</p>";
			mensaje += newPass;
			mensaje += "<br><p>Por motivos de seguridad es recomendable que modifique esta contraseña.</p>";
			
			message.setContent(mensaje, "text/html");

			System.out.println("Enviando correo...");

			Transport.send(message);

			resultado = true;

		} catch (MessagingException e) {
			resultado = false;
			System.err.println(e);
		}
		return resultado;
	}
}



/*
 *	Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.zoho.com");  //smtp.gmail.com
		props.put("mail.smtp.port", "465");             //587
		props.put("mail.smtp.starttls.enable", "true"); 
 * */
 