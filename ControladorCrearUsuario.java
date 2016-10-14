package ucr.casoUso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vaadin.data.Container;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import com.vaadin.data.Container;

public class ControladorCrearUsuario {
	
	VistaCrearUsuario interfazCrearUsuario;
	ModeloCrearUsuario modelo;
	private static ControladorCrearUsuario primeraInstancia = null;
	Correo correo;
	
	public static ControladorCrearUsuario obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorCrearUsuario();
		}
		return primeraInstancia;
	}
	
	public void iniciar() {
		interfazCrearUsuario = new VistaCrearUsuario();
		modelo = ModeloCrearUsuario.obtenerInstancia();
		correo = new Correo();
		actualizar();
	}
	
	public void actualizar() {
		List<String> instructores = modelo.getInfoInstructores2();
		Collections.sort(instructores);
		interfazCrearUsuario.addVariosComboBoxInstructor2(instructores);
	}
	
	public boolean enviarDatos(String [] user){
		boolean exito;
		String pass[] = crearPassword();
		
		user[6] = pass[1];
		
		exito = modelo.setNewUsuario(user);
		correo.enviar(user[3], pass[0]);
		actualizar();
		return exito;
	}
	
	private String[] crearPassword() {
		String alfabeto = "abcdefghijklmnopqrstuvwxyz";
		String pass = "";
		String passHash = "";
		String[] passwords = new String[2];
		Random r = new Random();
		
		for (int i = 0; i < 8; i++) {
			pass += alfabeto.charAt(r.nextInt(26));
		}
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(pass.getBytes());
			passHash = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error de hash");
		}
		
		//Cambiar comillas para que sean aceptadas por SQL
		passHash.replaceAll("'", "''");
		
		passwords[0] = pass;
		passwords[1] = passHash;
		
		return passwords;
	}
	
	public void setVista() {
		interfazCrearUsuario.setVista();
	}
	
}
