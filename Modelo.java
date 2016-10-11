package ucr.casoUso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class Modelo {
	
	private static Modelo primeraInstancia = null;
	private SimpleJDBCConnectionPool cp;
	Connection conn = null;
	
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
			cp = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver",
					"jdbc:mysql://127.0.0.1:3306/fiscalia", "mysql", "ECCImysql2016", 1, 5);
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
