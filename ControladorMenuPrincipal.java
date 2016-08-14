package ucr.casoUso;

public class ControladorMenuPrincipal {
	
	private static ControladorMenuPrincipal primeraInstancia;
	private VistaMenuPrincipal interfazMenuPrincipal;
	
	public static ControladorMenuPrincipal obtenerInstancia() {
		
		if (primeraInstancia == null) {
			primeraInstancia = new ControladorMenuPrincipal();
		}
		
		return primeraInstancia;
	}
	
	public void iniciar() {
		interfazMenuPrincipal = new VistaMenuPrincipal();
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(interfazMenuPrincipal);
	}
	
}
