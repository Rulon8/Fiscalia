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
	
	public ArrayList<String[]> pedirUsuarios(){
		try {
			resultados = new ArrayList<String[]>();
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT cedula, nombre, apellido, tipodeusuario FROM usuario";
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
			String consulta = "SELECT cedula, nombre, apellido FROM usuario where tipodeusuario = 'instructor' EXCEPT SELECT cedula, nombre, apellido FROM usuario where cedula = '" + cedInst + "'";
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
}
