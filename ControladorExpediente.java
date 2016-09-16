package ucr.casoUso;

import java.util.HashMap;

public class ControladorExpediente {
	
	VistaExpediente interfazExpediente;
	ModeloExpediente modeloExpediente;
	private static ControladorExpediente primeraInstancia = null;
	String codigoExpediente;
		
	public ControladorExpediente() {
	
	}
	
	public void iniciar(){
		
		codigoExpediente = "33";
		modeloExpediente = ModeloExpediente.obtenerInstancia();
		interfazExpediente = new VistaExpediente();
	
	}
	
	public void iniciar(String codExpediente){
		
		codigoExpediente = codExpediente;
		modeloExpediente = ModeloExpediente.obtenerInstancia();
		interfazExpediente = new VistaExpediente();
		
	}
	
	public static ControladorExpediente obtenerInstancia() {

		if(primeraInstancia == null){
			primeraInstancia = new ControladorExpediente();
		}
		return primeraInstancia;
	
	}
	
	public HashMap<String, String> obtenerDatos() {
		HashMap<String, String> datos = null;
		datos = modeloExpediente.traerDatosExpediente(codigoExpediente);
		return datos;
	}
	
	public void enviarDatos(String nuevoDato, String columna, String campo, String instructor, String numExp, String valorViejo) {
		modeloExpediente.actualizarDatos(nuevoDato, columna, codigoExpediente);
		if (campo == "Archivado") {
			nuevoDato = "Archivado";
		} else {
			campo = campo.replace(":", "");
		}
		modeloExpediente.actualizarLogs(codigoExpediente, numExp, instructor, valorViejo, nuevoDato, campo);
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(interfazExpediente);
	}
	
}
