package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ModeloExpediente {
	
	private static ModeloExpediente primeraInstancia;
	
	public static ModeloExpediente obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloExpediente();
		}
		return primeraInstancia;
	}
	
	public HashMap<String, String> traerDatosExpediente(String codExpediente) {
		Connection c;
		Statement s;
		String consulta = "";
		Modelo m = Modelo.obtenerInstancia();
		ResultSet rs;
		HashMap<String, String> datos;
		datos = new HashMap<String, String>();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT * FROM expediente WHERE codigo = '" + codExpediente + "'";
			System.out.println(consulta);
			rs = s.executeQuery(consulta);
			
			rs.next();
			
			datos.put("Archivado", rs.getString("archivado"));
			datos.put("Codigo", rs.getString("codigo"));
			datos.put("Numero Expediente", rs.getString("numexpediente"));
			datos.put("Instructor Asignado", rs.getString("instructorasig"));
			datos.put("Fecha de Ingreso", rs.getString("fechaingreso"));
			datos.put("Clasificacion", rs.getString("clasificacion"));
			
			
			s.close();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return datos;
	}
}
