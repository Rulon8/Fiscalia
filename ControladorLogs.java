package ucr.casoUso;

public class ControladorLogs {

	VistaLogs interfazLogs;
	String numExpediente;
	ModeloLogs modeloLogs;
	private static ControladorLogs primeraInstancia = null;
	
	public ControladorLogs() {
	}
	
	public void iniciar(String numExp){
		interfazLogs = new VistaLogs();
		modeloLogs = ModeloLogs.obtenerInstancia(numExp);
		numExpediente = numExp;
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
	
}
