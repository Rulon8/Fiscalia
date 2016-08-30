package ucr.casoUso;

import java.util.ArrayList;

public class ControladorLogs {

	VistaLogs interfazLogs;
	String numExpediente;
	ModeloLogs modeloLogs;
	private static ControladorLogs primeraInstancia = null;
	
	public ControladorLogs() {
	}
	
	public void iniciar(String numExp){
		modeloLogs = ModeloLogs.obtenerInstancia();
		numExpediente = numExp;
		interfazLogs = new VistaLogs();
		
	}
	
	public static ControladorLogs obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorLogs();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(interfazLogs);
	}
	
	public String getNumExp(){
		return numExpediente;
	}
	
	public ArrayList<String[]> pedirDatos(){
		return modeloLogs.pedirDatos(numExpediente);
	}
}
