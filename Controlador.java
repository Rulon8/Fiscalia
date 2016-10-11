package ucr.casoUso;

public class Controlador {
	
	private static Controlador primeraInstancia = null;
	private Modelo modelo;
	private ControladorCrearExpediente controladorCrearExpediente;
	private ControladorCrearUsuario controladorCrearUsuario;
	private ControladorLogin controladorLogin;
	private ControladorMenuPrincipal controladorMenuPrincipal;
	private ControladorNuevoPassword controladorNuevoPassword;
	private ControladorListaExpedientesActivos controladorListaExpedientesActivos;
	private ControladorListaExpedientesArchivados controladorListaExpedientesArchivados;
	private ControladorLogs controladorLogs;
	private ControladorExpediente controladorExpediente;
	private ControladorCambioPassword controladorCambioPassword;
	private ControladorUsuarios controladorUsarios;
	private String tipoUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String cedulaUsuario;
	private String numInstructor = "-1";
	
	private Controlador() {
		
	}
			
	public static Controlador obtenerInstancia() {
		
		if (primeraInstancia == null) {
			primeraInstancia = new Controlador();
		}
		
		return primeraInstancia;
	}
	
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getNumInstructor() {
		return numInstructor;
	}

	public void setCedulaUsuario(String ced) {
		cedulaUsuario = ced;
	}
	
	public void setNombreUsuario(String nom) {
		nombreUsuario = nom;
	}
	
	public void setApellidoUsuario(String apellido) {
		apellidoUsuario = apellido;
	}
	
	public void setTipoUsuario(String tipo) {
		tipoUsuario = tipo;
	}
	
	public void setNumInstructor(String num) {
		numInstructor = num;
	}
	
	public void iniciar() {
		modelo = Modelo.obtenerInstancia();
		controladorCrearUsuario = ControladorCrearUsuario.obtenerInstancia();
		controladorLogin = ControladorLogin.obtenerInstancia();
		controladorMenuPrincipal = ControladorMenuPrincipal.obtenerInstancia();
		controladorNuevoPassword = ControladorNuevoPassword.obtenerInstancia();
		controladorCrearExpediente =  ControladorCrearExpediente.obtenerInstancia();
		controladorListaExpedientesActivos =  ControladorListaExpedientesActivos.obtenerInstancia();
		controladorListaExpedientesArchivados =  ControladorListaExpedientesArchivados.obtenerInstancia();
		controladorLogs = ControladorLogs.obtenerInstancia();
		controladorExpediente = ControladorExpediente.obtenerInstancia();
		controladorCambioPassword = ControladorCambioPassword.obtenerInstancia();
		controladorUsarios = ControladorUsuarios.obtenerInstancia();
		
		controladorListaExpedientesArchivados.iniciar();
		controladorListaExpedientesArchivados.setVista();
		
		//controladorListaExpedientesActivos.iniciar();
		//controladorListaExpedientesActivos.setVista();
		
		//controladorLogin.iniciar();
		//controladorLogin.setVista();
		
		//controladorLogs.iniciar("33");
		//controladorLogs.setVista();
		
		//controladorExpediente.iniciar();
		//controladorExpediente.setVista();
		
		//controladorCrearUsuario.iniciar();
		//controladorCrearUsuario.setVista();
		
		//controladorCambioPassword.iniciar();
		//controladorCambioPassword.setVista();
		
		//controladorCrearExpediente.iniciar();
		//controladorCrearExpediente.setVista();
	}
	
	public void cambiarVista(String vista) {
		
		switch(vista) {
		case "Login":
			controladorLogin.iniciar();
			controladorLogin.setVista();
			break;
			
		case "Menu Principal":
			controladorMenuPrincipal.iniciar();
			controladorMenuPrincipal.setVista();
			break;
			
		case "Crear Usuario":
			controladorCrearUsuario.iniciar();
			controladorCrearUsuario.setVista();
			break;
			
		case "Nuevo Password":
			controladorNuevoPassword.iniciar();
			controladorNuevoPassword.setVista();
			break;
		
		case "Lista Expedientes":
			controladorListaExpedientesActivos.iniciar();
			controladorListaExpedientesActivos.setVista();
			break;
			
		case "Crear Expediente":
			controladorCrearExpediente.iniciar();
			controladorCrearExpediente.setVista();
			break;
			
		case "Expedientes Archivados":
			controladorListaExpedientesArchivados.iniciar();
			controladorListaExpedientesArchivados.setVista();
			break;
		
		case "Cambio Pass":
			controladorCambioPassword.iniciar();
			controladorCambioPassword.setVista();
			break;
			
		case "Eliminar Usuario":
			controladorUsarios.iniciar();
			controladorUsarios.setVista();
			break;
		}

		}	
	
	
	public void cambiarVista(String vista, String codigo){
		switch(vista) {
		case "Logs":
			controladorLogs.iniciar(codigo);
			controladorLogs.setVista();
			break;
		case "Expediente":
			controladorExpediente.iniciar(codigo);
			controladorExpediente.setVista();
			break;
		}
	}

}
