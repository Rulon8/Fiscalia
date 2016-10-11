package ucr.casoUso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloCambioPassword {
	
	private static ModeloCambioPassword primeraInstancia;
		
		public static ModeloCambioPassword obtenerInstancia() {
			if(primeraInstancia == null)
				primeraInstancia = new ModeloCambioPassword();
			return primeraInstancia;
		}
	
	public boolean comparar(String pass, String conf) {
		if(pass.compareTo(conf) == 0)
			return true;
		else
			return false;
	}
		
	public boolean autenticar(String usuario, String pass){
		boolean valido = false;
		Connection c;
		Statement s;
		String consulta;
		Modelo m = Modelo.obtenerInstancia();
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT pass from usuario where cedula = '" + usuario + "'";
			ResultSet rs = s.executeQuery(consulta);
			rs.next();
			if(rs.getString("pass").compareTo(hash(pass)) == 0)
				valido = true;
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return valido;
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
			consulta = "UPDATE usuario SET pass = '" + hash(pass) + "' WHERE cedula = '" + user + "'";
			s.executeUpdate(consulta);
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
	
	public String hash(String pass){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(pass.getBytes());
			pass = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return pass;
	}
}
