package ucr.casoUso;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class Modelo {
	
	private static Modelo primeraInstancia = null;
	private SimpleJDBCConnectionPool cp;
	
	public Modelo() {
		if (!conectarBD()) {
			System.out.println("Base no conectada");
		}
	}
	
	public static Modelo obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new Modelo();
		}
		return primeraInstancia;
	}
	
	private boolean conectarBD() {
		boolean exito = false;
		try {
			cp = new SimpleJDBCConnectionPool("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/Fiscalia", "postgres", "scott", 1, 5);
			exito = true;
			return exito;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}
	
	public SimpleJDBCConnectionPool getConnectionPool() {
		return cp;
	}
	
}
