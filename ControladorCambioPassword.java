package ucr.casoUso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class ControladorCambioPassword {
	
	private static ControladorCambioPassword primeraInstancia = null;
	private VistaCambioPassword interfazCambioPassword;
	private ModeloCambioPassword modeloPassword;
	private Correo correo;
	
	public static ControladorCambioPassword obtenerInstancia() {
		if (primeraInstancia == null)
			primeraInstancia = new ControladorCambioPassword();
		return primeraInstancia;
	}
	
	public void iniciar() {
		interfazCambioPassword = new VistaCambioPassword();
		modeloPassword = ModeloCambioPassword.obtenerInstancia();
		correo = new Correo();
	}
	
	public void setVista() {
		interfazCambioPassword.setVista();
	}
}