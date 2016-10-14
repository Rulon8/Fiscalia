package ucr.casoUso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class ControladorNuevoPassword {
	
	private static ControladorNuevoPassword primeraInstancia = null;
	private VistaNuevoPassword interfazNuevoPassword;
	private ModeloNuevoPassword modeloPassword;
	private Correo correo;
	
	public static ControladorNuevoPassword obtenerInstancia() {
		if (primeraInstancia == null)
			primeraInstancia = new ControladorNuevoPassword();
		return primeraInstancia;
	}
	
	public void iniciar() {
		interfazNuevoPassword = new VistaNuevoPassword();
		modeloPassword = ModeloNuevoPassword.obtenerInstancia();
		correo = new Correo();
	}
	
	public void setVista() {
		interfazNuevoPassword.setVista();
	}

	public boolean enviarDatos(String usuario) {
		boolean exito = false;
		String correoUsuario;
		String[] pass = new String[2];
		correoUsuario = modeloPassword.autenticar(usuario);
		if (correoUsuario != "") {
			pass = crearPassword();
			exito = modeloPassword.actualizarPass(usuario, pass[1]);
			if (exito)
				correo.enviar(correoUsuario, pass[0]);
		}
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
		
		passwords[0] = pass;
		passwords[1] = passHash;
		
		return passwords;
	}
}