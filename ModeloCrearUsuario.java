package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class ModeloCrearUsuario {
	
	List<String[]> usuarios = new ArrayList<String[]>();
	private static ModeloCrearUsuario primeraInstancia;
	
	public static ModeloCrearUsuario obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloCrearUsuario();
		}
		return primeraInstancia;
	}
	
	public boolean setNewUsuario(String[] usuario){
		
		Connection c;
		Statement s;
		String consulta;
		boolean exito = false;
		Modelo m = Modelo.obtenerInstancia();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "INSERT INTO usuario VALUES ('" + usuario[0] + "', '" + usuario[1] + "', '" + usuario[2] + "', '" + usuario[3] + "', '" + usuario[4] + "', '" + usuario[5] + "', '" + usuario[6] + "')";					
			s.executeUpdate(consulta);
			
			if (usuario[5].compareTo("Instructor") == 0 || usuario[5].compareTo("Asistente") == 0 || usuario[5].compareTo("Instructor Jefe") == 0) {
				consulta = "INSERT INTO instasist VALUES ('" + usuario[2] + "', " + usuario[7] + ")";
				s.executeUpdate(consulta);
			}
			else if (usuario[5].compareTo("Notificador") == 0) {
				consulta = "INSERT INTO notificador VALUES ('" + usuario[2] + "')";
				s.executeUpdate(consulta);
			}
			
			s.close();
			c.commit();
			c.close();
			
			m.getConnectionPool().releaseConnection(c);
			
			exito = true;
			return exito;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}

	public List<String> getInfoInstructores2() {
		List<String> codigos = new ArrayList<String>();
		ResultSet r;
		Connection c;
		Statement s;
		String consulta;
		Modelo m = Modelo.obtenerInstancia();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT DISTINCT codigo FROM InstAsist";					

			r = s.executeQuery(consulta);
			
			while (r.next()) {
				codigos.add(r.getString(1));
			}
			
			s.close();
			c.commit();
			c.close();
			
			m.getConnectionPool().releaseConnection(c);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return codigos;
		
	}

	
	
}
