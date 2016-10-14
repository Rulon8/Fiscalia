package ucr.casoUso;

import java.util.ArrayList;

public class ControladorLogs {

	VistaLogs interfazLogs;
	String codigoExp;
	ModeloLogs modeloLogs;
	private static ControladorLogs primeraInstancia = null;
	
	public ControladorLogs() {
	}
	
	public void iniciar(String codExp){
		modeloLogs = ModeloLogs.obtenerInstancia();
		codigoExp = codExp;
		interfazLogs = new VistaLogs();
		
	}
	
	public static ControladorLogs obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorLogs();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		interfazLogs.setVista();
	}
	
	public String getCodExp(){
		return codigoExp;
	}
	
	public String getNumExp(){
		return modeloLogs.getNumExp(codigoExp);
	}
	
	public ArrayList<String[]> pedirDatos(){
		return modeloLogs.pedirDatos(codigoExp);
	}
	
	public void generarReporte(){
		modeloLogs.generarReporte(codigoExp);
	}
}
