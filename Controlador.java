package ucr.casoUso;

public class Controlador {
	
	private static Controlador primeraInstancia = null;
	private Modelo modelo;
	private ControladorCrearUsuario controladorCrearUsuario;
	private ControladorLogin controladorLogin;
	private ControladorMenuPrincipal controladorMenuPrincipal;
	private ControladorNuevoPassword controladorNuevoPassword;
	private ControladorListaExpedientes controladorListaExpedientes;
	private String tipoUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String numInstructor = "-1";
	
	private Controlador() {
		
	}
			
	public static Controlador obtenerInstancia() {
		
		if (primeraInstancia == null) {
			primeraInstancia = new Controlador();
		}
		
		return primeraInstancia;
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
		//NOTA: HACER SINGLETONS
		modelo = Modelo.obtenerInstancia();
		controladorCrearUsuario = ControladorCrearUsuario.obtenerInstancia();
		controladorLogin = ControladorLogin.obtenerInstancia();
		controladorMenuPrincipal = ControladorMenuPrincipal.obtenerInstancia();
		controladorNuevoPassword = ControladorNuevoPassword.obtenerInstancia();
		controladorListaExpedientes =  ControladorListaExpedientes.obtenerInstancia();		
		//controladorListaExpedientes.iniciar();
		//controladorListaExpedientes.setVista();
		
		controladorLogin.iniciar();
		controladorLogin.setVista();
		
		//controladorCrearUsuario.iniciar();
		//controladorCrearUsuario.setVista();
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
			controladorListaExpedientes.iniciar();
			controladorListaExpedientes.setVista();
			break;
		}
		
	}

}
