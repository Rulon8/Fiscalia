package ucr.casoUso;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

public class ControladorListaExpedientesArchivados {
	ModeloListaExpedientesArchivados modelo;
	VistaListaExpedientesArchivados InterfazListaExpedientesArchivados;
	private static ControladorListaExpedientesArchivados primeraInstancia = null;
	
	public ControladorListaExpedientesArchivados(){
		
	}
	
	public void iniciar(){
		modelo = ModeloListaExpedientesArchivados.obtenerInstancia();
		InterfazListaExpedientesArchivados = new VistaListaExpedientesArchivados();
		
	}
	
	public static ControladorListaExpedientesArchivados obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorListaExpedientesArchivados();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(InterfazListaExpedientesArchivados);
	}
	
	public SQLContainer consultarDatos(String hileraConsulta){
		return modelo.consultarDatos(hileraConsulta);
	}
}
