package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloCrearExpediente {
	private static ModeloCrearExpediente primeraInstancia;
	public ModeloCrearExpediente(){
		
	}
	
	public static ModeloCrearExpediente obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloCrearExpediente();
		}
		return primeraInstancia;
	}
	
	public boolean comprobarNumExp(String valorNumExp){
		String numExp = null;
		boolean esVacia = false;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT numexpediente FROM expediente WHERE numexpediente = '" + valorNumExp + "'";
			ResultSet rs = s.executeQuery(consulta);
			if (!rs.next()) {
				esVacia = true;
			}
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return esVacia;
	}
	

	public boolean comprobarInstructor(String valorInstructor){
		String numExp = null;
		boolean esVacia = false;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT codigo FROM instasist WHERE codigo = '" + valorInstructor + "'";
			ResultSet rs = s.executeQuery(consulta);
			if (!rs.next()) {
				esVacia = true;
			}
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return esVacia;
	}
	
	public void ingresarExpediente(String valorConsulta){
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			s.executeUpdate(valorConsulta);
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int consultarCodigo(){
		int cantidadCodigo = 0;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta =  "SELECT COUNT(codigo)  AS conteo FROM expediente";
			ResultSet rs = s.executeQuery(consulta);
			rs.next();
			cantidadCodigo = rs.getInt("conteo");
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidadCodigo;
	}

}
