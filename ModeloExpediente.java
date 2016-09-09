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

			rs = s.executeQuery(consulta);
			
			rs.next();
			
			datos.put("Archivado", rs.getString("archivado"));
			datos.put("Codigo", rs.getString("codigo"));
			datos.put("Numero Expediente", rs.getString("numexpediente"));
			datos.put("Instructor Asignado", rs.getString("instructorasig"));
			datos.put("Fecha de Denuncia", rs.getString("fechadenuncia"));
			datos.put("Fecha de Ingreso", rs.getString("fechaingreso"));
			datos.put("Parte Denunciada", rs.getString("partedenunciada"));
			datos.put("Carnet", rs.getString("carnet"));
			datos.put("Parte Denunciante", rs.getString("partedenunciante"));
			datos.put("Identificacion", rs.getString("identificacion"));
			datos.put("Estado", rs.getString("estado"));
			datos.put("Clasificacion", rs.getString("clasificacion"));
			datos.put("Responsable", rs.getString("responsable"));
			datos.put("Fecha Cambio de Estado", rs.getString("fechacambioestado"));
			datos.put("Plazo meta", rs.getString("plazometa"));
			datos.put("Tiempo Transcurrido", rs.getString("tiempotranscurrido"));
			datos.put("Condicion Plazo", rs.getString("condplazo"));
			datos.put("Ubicacion", rs.getString("ubicacion"));
			datos.put("Mueble Ubicacion", rs.getString("muebleubicacion"));
			datos.put("Ubicacion de Legajos", rs.getString("ubicacionlegajos"));
			datos.put("Cantidad de legajos", rs.getString("cantlegajos"));
			datos.put("Fecha de Ultima Ubicacion", rs.getString("fechaultubicacion"));
			datos.put("Causa", rs.getString("causa"));
			datos.put("Articulos Aplicables", rs.getString("articulosaplicables"));
			datos.put("Fecha de Audiencia", rs.getString("fechaaudiencia"));
			datos.put("Hora de Aundiencia", rs.getString("horaaudiencia"));
			
			s.close();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return datos;
	}
	
	public void actualizarDatos(String nuevoDato, String columna, String codigoExpediente) {
		
		Connection c;
		Statement s;
		String consulta = "";
		Modelo m = Modelo.obtenerInstancia();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "UPDATE expediente SET " + columna + " = " + "'" + nuevoDato + "' WHERE codigo = '" + codigoExpediente + "'";
			s.executeUpdate(consulta);
			
			System.out.println(consulta);
			
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarLogs(String codigo, String numExp, String usuario, String campo, String valorViejo, String valorNuevo) {
		
		Connection c;
		Statement s;
		String consulta = "";
		Modelo m = Modelo.obtenerInstancia();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "INSERT INTO logCambios VALUES ()";
			s.executeUpdate(consulta);
			
			System.out.println(consulta);
			
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
