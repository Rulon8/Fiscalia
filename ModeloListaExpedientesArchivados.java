package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class ModeloListaExpedientesArchivados {
	private static ModeloListaExpedientesArchivados primeraInstancia;
	public ModeloListaExpedientesArchivados(){
		conectarBD();
	}
	
	public static ModeloListaExpedientesArchivados obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloListaExpedientesArchivados();
		}
		return primeraInstancia;
	}
		
	public String conectarBD(){
		SimpleJDBCConnectionPool cp;
		Connection conexion = null;
		try{
			cp = new SimpleJDBCConnectionPool("org.postgresql.Driver",
					"jdbc:postgresql://127.0.0.1:5432/Fiscalia", "postgres", "123456", 1, 5);
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
