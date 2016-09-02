package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class ModeloLogs {
	
	private static ModeloLogs primeraInstancia;
	ArrayList<String[]> resultados;
	
	public static ModeloLogs obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloLogs();
		}
		return primeraInstancia;
	}
	
	public ArrayList<String[]> pedirDatos(String numExpediente){
		try {
			resultados = new ArrayList<String[]>();
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			System.out.println("SELECT numcambio, cedusuario, fecha, descripcion FROM logCambios WHERE codigo = '" + numExpediente + "'");
			String consulta = "SELECT numcambio, cedusuario, fecha, descripcion FROM logCambios WHERE codigo = '" + numExpediente + "'";
			ResultSet rs = s.executeQuery(consulta);
			rs.next();
			while(rs.next() != false) {
				String[] tupla = {rs.getString("numcambio"), rs.getString("cedusuario"), rs.getString("fecha"), rs.getString("descripcion")};
				resultados.add(tupla);
				rs.next();
			}	
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;
	}
}
