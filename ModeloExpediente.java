package ucr.casoUso;

public class ModeloExpediente {
	
	private static ModeloExpediente primeraInstancia;
	
	public static ModeloExpediente obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloExpediente();
		}
		return primeraInstancia;
	}
}
