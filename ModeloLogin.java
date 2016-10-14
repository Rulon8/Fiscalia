package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloLogin {
	
	private static ModeloLogin primeraInstancia;
	
	public static ModeloLogin obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloLogin();
		}
		return primeraInstancia;
	}
	
	public String[] autenticar(String [] credenciales) {
		
		Connection c;
		Statement s;
		String consulta, pass = "";
		Modelo m = Modelo.obtenerInstancia();
		ResultSet rs;
		String[] datosUsuario = {"","","","", ""};
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT pass FROM usuario WHERE cedula = '" + credenciales[0] + "'";
			rs = s.executeQuery(consulta);
			
			if (rs.next() != false) {
				pass = rs.getString(1);
			}
			
			if (pass.equals(credenciales[1])) {
						
				consulta = "SELECT nombre, apellido, tipodeusuario, cedula FROM usuario WHERE cedula = '" + credenciales[0] + "'";
				rs = s.executeQuery(consulta);
				rs.next();
				datosUsuario[0] = rs.getString(1);
				datosUsuario[1] = rs.getString(2);
				datosUsuario[2] = rs.getString(3);
				datosUsuario[4] = rs.getString(4);
				
				if (datosUsuario[2].compareTo("Instructor") == 0 || datosUsuario[2].compareTo("Asistente") == 0 || datosUsuario[2].compareTo("Instructor Jefe") == 0) {
					consulta = "SELECT codigo FROM instasist WHERE cedula = '" + credenciales[0] + "'";
					rs = s.executeQuery(consulta);
					rs.next();
					datosUsuario[3] = rs.getString(1);
				}
			}
			
			s.close();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return datosUsuario;
		
	}

}
