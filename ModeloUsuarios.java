package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloUsuarios {
	
	private static ModeloUsuarios primeraInstancia;
	ArrayList<String[]> resultados;
	
	public static ModeloUsuarios obtenerInstancia() {
		if(primeraInstancia == null)
			primeraInstancia = new ModeloUsuarios();
		return primeraInstancia;
	}
	
	public ArrayList<String[]> pedirUsuarios(String ced){
		try {
			resultados = new ArrayList<String[]>();
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT cedula, nombre, apellido, tipodeusuario FROM usuario EXCEPT SELECT cedula, nombre, apellido, tipodeusuario FROM usuario where cedula = '" + ced + "'";
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			while(rs.next() != false) {
				String[] tupla = {rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("tipodeusuario")};
				resultados.add(tupla);
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
	
	public ArrayList<String[]> pedirInstructores(String cedInst){
		try {
			resultados = new ArrayList<String[]>();
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT cedula, nombre, apellido FROM usuario where tipodeusuario = 'Instructor' EXCEPT SELECT cedula, nombre, apellido FROM usuario where cedula = '" + cedInst + "'";
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			while(rs.next() != false) {
				String[] tupla = {rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido")};
				resultados.add(tupla);
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
	
	public boolean eliminarUsuario(String ced){
		boolean exito;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "DELETE FROM usuario where cedula = '" + ced + "'";
			System.out.println(consulta);
			s.executeUpdate(consulta);
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
			exito = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;
	}
	
	public boolean asignarExpedientes(String viejo, String nuevo){
		boolean exito = false;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "UPDATE expedientes SET instructorasig = '" + nuevo + "' where instructorasig = '" + viejo + "'";
			System.out.println(consulta);
			s.executeUpdate(consulta);
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
			exito = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;
	}
}
