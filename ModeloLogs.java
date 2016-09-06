package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeloLogs {
	
	private static ModeloLogs primeraInstancia;
	ArrayList<String[]> resultados;
	
	public static ModeloLogs obtenerInstancia() {
		if(primeraInstancia == null)
			primeraInstancia = new ModeloLogs();
		return primeraInstancia;
	}
	
	public ArrayList<String[]> pedirDatos(String codExp){
		try {
			resultados = new ArrayList<String[]>();
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			System.out.println("SELECT numcambio, cedusuario, fecha, campo, estadoAnterior, estadoActual FROM logcambios WHERE codigo = '" + codExp + "'");
			String consulta = "SELECT numcambio, cedusuario, fecha, campo, estadoAnterior, estadoActual FROM logcambios WHERE codigo = '" + codExp + "'";
			ResultSet rs = s.executeQuery(consulta);
			while(rs.next() != false) {
				String[] tupla = {rs.getString("numcambio"), rs.getString("cedusuario"), rs.getString("fecha"), rs.getString("campo"), rs.getString("estadoAnterior"), rs.getString("estadoActual")};
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
	
	public String getNumExp(String codExp){
		String numExp = null;
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			System.out.println("SELECT numexpediente FROM logcambios WHERE codigo = '" + codExp + "'");
			String consulta = "SELECT numexpediente FROM logcambios WHERE codigo = '" + codExp + "'";
			ResultSet rs = s.executeQuery(consulta);
			rs.next();
			numExp = rs.getString("numexpediente");	
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return numExp;
	}
}
