package ucr.casoUso;

public class ModeloCrearExpediente {
	private static ModeloCrearExpediente primeraInstancia;
	public ModeloCrearExpediente(){
		
	}
	
	public static ModeloCrearExpediente obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloCrearExpediente();
		}
		return primeraInstancia;
	}
}
