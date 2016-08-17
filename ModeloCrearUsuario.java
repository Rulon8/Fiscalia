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
	
	public List<String> getInfoInstructores() {
		
		List<String> instructores = new ArrayList<String>();
		
		//FALTA: CAMBIAR POR CONSULTA
		instructores.add("instructor 1");
		instructores.add("instructor 2");
		instructores.add("Instructor 3");
		
		return instructores;
		
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

			System.out.println(consulta);
			
			s.executeUpdate(consulta);
			
			if (usuario[5] == "Instructor" || usuario[5] == "Asistente") {
				consulta = "INSERT INTO instasist VALUES ('" + usuario[2] + "', " + usuario[7] + ")";
				s.executeUpdate(consulta);
			}
			else if (usuario[5] == "Notificador") {
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
			//FALTA: Controlar errores de SQL
			e.printStackTrace();
		}
		return exito;
	}

	public Container getInfoInstructores2() {
		Container cont = new IndexedContainer();
		Connection c;
		Statement s;
		String consulta;
		Modelo m = Modelo.obtenerInstancia();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT DISTINCT codigo FROM InstAsist";					

			System.out.println(consulta);
			
			cont = (Container) s.executeQuery(consulta);
			
			s.close();
			c.commit();
			c.close();
			
			m.getConnectionPool().releaseConnection(c);
			
		}
		catch (SQLException e) {
			//FALTA: Controlar errores de SQL
			e.printStackTrace();
		}
		
		return cont;
		
	}

	
	
}
