package ucr.casoUso;

public class ControladorExpediente {
	
	VistaExpediente interfazExpediente;
	ModeloExpediente modeloExpediente;
	private static ControladorExpediente primeraInstancia = null;
		
	public ControladorExpediente() {
	
	}
	
	public void iniciar(){
		
		interfazExpediente = new VistaExpediente();
		modeloExpediente = ModeloExpediente.obtenerInstancia();
	
	}
	
	public static ControladorExpediente obtenerInstancia() {

		if(primeraInstancia == null){
			primeraInstancia = new ControladorExpediente();
		}
		return primeraInstancia;
	
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(interfazExpediente);
	}
	
}
