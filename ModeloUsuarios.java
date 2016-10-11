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
			String consulta = "SELECT cedula, nombre, apellido FROM usuario where tipodeusuario = 'Instructor' OR tipodeusuario = 'Instructor Jefe' EXCEPT SELECT cedula, nombre, apellido FROM usuario where cedula = '" + cedInst + "'";
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
			String consulta3 = "DELETE FROM notificador WHERE cedula = '" + ced + "'";
			String consulta1 = "DELETE FROM instasist WHERE cedula = '" + ced + "'";
			String consulta2 = "DELETE FROM usuario where cedula = '" + ced + "'";
			s.executeUpdate(consulta3);
			s.executeUpdate(consulta1);
			s.executeUpdate(consulta2);
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
			Statement s2 = c.createStatement();
			String consulta1 = "SELECT codigo FROM instasist WHERE cedula = '" + viejo + "'";
			String consulta2 = "SELECT codigo FROM instasist WHERE cedula = '" + nuevo + "'";
			String consulta3 = "UPDATE expediente SET instructorasig = '" + nuevo + "' where instructorasig = '" + viejo + "'";
			ResultSet rs1 = s.executeQuery(consulta1);
			rs1.next();
			ResultSet rs2 = s2.executeQuery(consulta2);
			rs2.next();
			String consulta4 = "UPDATE instasist SET codigo = '" + rs2.getString("codigo") + "' WHERE codigo = '" + rs1.getString("codigo") + "'";
			s.executeUpdate(consulta4);
			s.executeUpdate(consulta3);
			s.close();
			s2.close();
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
