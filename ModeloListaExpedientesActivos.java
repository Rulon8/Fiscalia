package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class ModeloListaExpedientesActivos {
	private static ModeloListaExpedientesActivos primeraInstancia;
	public ModeloListaExpedientesActivos(){
		conectarBD();
	}
	
	public static ModeloListaExpedientesActivos obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloListaExpedientesActivos();
		}
		return primeraInstancia;
	}
		
	public String conectarBD(){
		SimpleJDBCConnectionPool cp;
		Connection conexion = null;
		try{
			cp = new SimpleJDBCConnectionPool("org.postgresql.Driver", 
			"jdbc:postgresql://127.0.0.1:5432/Fiscalia", "postgres", "ECCIpgsql2016", 1, 5);
			Connection conn = cp.reserveConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT current_database()");
			rs.next();
			return rs.getString(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}
