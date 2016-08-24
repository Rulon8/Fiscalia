package ucr.casoUso;

public class ControladorCrearExpediente {
	
	ModeloCrearExpediente modelo;
	VistaCrearExpediente InterfazCrearExpediente;
	private static ControladorCrearExpediente primeraInstancia = null;
	
	public ControladorCrearExpediente(){
	}
	
	public void iniciar(){
		modelo = ModeloCrearExpediente.obtenerInstancia();
		InterfazCrearExpediente = new VistaCrearExpediente();
		
	}
	
	public static ControladorCrearExpediente obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorCrearExpediente();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(InterfazCrearExpediente);
	}

}
