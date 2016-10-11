package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloNuevoPassword {
	
	private static ModeloNuevoPassword primeraInstancia;
		
		public static ModeloNuevoPassword obtenerInstancia() {
			if(primeraInstancia == null)
				primeraInstancia = new ModeloNuevoPassword();
			return primeraInstancia;
		}
		
	public String autenticar (String usuario) {
		Connection c;
		Statement s;
		String consulta, correo = "";
		Modelo m = Modelo.obtenerInstancia();
		ResultSet rs;
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT email FROM usuario WHERE cedula = '" + usuario + "'";
			rs = s.executeQuery(consulta);
			
			if (rs.next() != false)
				correo = rs.getString(1);		
			System.out.println(correo);
			s.close();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return correo;
	}
	
	public boolean actualizarPass(String user, String pass) {
		Connection c;
		Statement s;
		String consulta;
		boolean exito = false;
		Modelo m = Modelo.obtenerInstancia();
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "UPDATE usuario SET pass = '" + pass + "' WHERE cedula = '" + user + "'";
			s.executeUpdate(consulta);
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
}
