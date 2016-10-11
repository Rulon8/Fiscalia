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
		InterfazCrearExpediente.setVista();
		//MyUI.getCurrent().setContent(InterfazCrearExpediente);
	}
	
	public boolean comprobarNumExp(String valor){
		return modelo.comprobarNumExp(valor);
	}
	
	public boolean comprobarInstructor(String valor){
		return modelo.comprobarInstructor(valor);
	}
	
	public void ingresarExpediente(String consulta){
		modelo.ingresarExpediente(consulta);
	}
	
	public int consultarCodigo(){
		int cantidad = modelo.consultarCodigo();
		return cantidad;
	}

}
