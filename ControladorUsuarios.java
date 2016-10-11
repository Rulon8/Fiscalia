package ucr.casoUso;

import java.util.ArrayList;

public class ControladorUsuarios {
	VistaUsuarios interfazUsuarios;
	ModeloUsuarios modeloUsuarios;
	private static ControladorUsuarios primeraInstancia = null;
	
	public ControladorUsuarios() {
	}
	
	public void iniciar(){
		modeloUsuarios = ModeloUsuarios.obtenerInstancia();
		interfazUsuarios = new VistaUsuarios();
	}
	
	public static ControladorUsuarios obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorUsuarios();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		//interfazUsuarios.setVista();
		MyUI.getCurrent().setContent(interfazUsuarios);
	}
	
	public ArrayList<String[]> pedirDatosUsuarios(String ced){
		return modeloUsuarios.pedirUsuarios(ced);
	}
	
	public ArrayList<String[]> pedirDatosInstructores(String cedInst){
		return modeloUsuarios.pedirInstructores(cedInst);
	}
	
	public boolean eliminarUsuario(String ced){
		return modeloUsuarios.eliminarUsuario(ced);
	}
	
	public boolean asignarExpedientes(String viejo, String nuevo){
		return modeloUsuarios.asignarExpedientes(viejo, nuevo);
	}
}
