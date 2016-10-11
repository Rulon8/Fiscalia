package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			datos.put("Estado", rs.getString("estado"));
			datos.put("Clasificacion", rs.getString("clasificacion"));
			datos.put("Responsable", rs.getString("responsable"));
			datos.put("Fecha de Denuncia", rs.getString("fechadenuncia"));
			datos.put("Parte Denunciada", rs.getString("partedenunciada"));
			datos.put("Carnet", rs.getString("carnet"));
			datos.put("Parte Denunciante", rs.getString("partedenunciante"));
			datos.put("Identificacion", rs.getString("identificacion"));
			datos.put("Causa", rs.getString("causa"));
			datos.put("Articulos Aplicables", rs.getString("articulosaplicables"));
			datos.put("Fecha de Audiencia", rs.getString("fechaaudiencia"));
			datos.put("Hora de Audiencia", rs.getString("horaaudiencia"));
			datos.put("Plazo Meta", rs.getString("plazometa"));
			datos.put("Tiempo Transcurrido", rs.getString("tiempotranscurrido"));
			datos.put("Condicion Plazo", rs.getString("condplazo"));
			datos.put("Ubicacion", rs.getString("ubicacion"));
			datos.put("Mueble Ubicacion", rs.getString("muebleubicacion"));
			datos.put("Ubicacion de Legajos", rs.getString("ubicacionlegajos"));
			datos.put("Cantidad de Legajos", rs.getString("cantlegajos"));
			datos.put("Fecha de Presentacion", rs.getString("fechapresentacion"));
			datos.put("Fecha de Ingreso", rs.getString("fechaingreso"));
			datos.put("Duracion Presentacion Ingreso", rs.getString("duracpresentacioningreso"));
			datos.put("Fecha Cambio de Estado", rs.getString("fechacambioestado"));
			datos.put("Plazo Ingreso Cambio Estado", rs.getString("plazoingresocambio"));
			datos.put("Duracion Ingreso Cambio Estado", rs.getString("duracingresocambio"));
			datos.put("Fecha de Ultima Ubicacion", rs.getString("fechaultubicacion"));
			datos.put("Plazo Ingreso Ultima Ubicacion", rs.getString("plazoingresoultubicacion"));
			datos.put("Duracion Ingreso Ultima Ubicacion", rs.getString("duracingresoultubicacion"));
			datos.put("Duracion Cambio Estado Ultima Ubicacion", rs.getString("duraccambioultubicacion"));
			
			
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
			s.close();
			c.commit();
			c.close();
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarLogs(String codigo, String numExp, String usuario, String valorViejo, String valorNuevo, String campo) {
		
		Connection c;
		Statement s;
		String consulta = "";
		Modelo m = Modelo.obtenerInstancia();
		int numCambio;
		ResultSet rs;
		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = new Date();
		
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			
			consulta = "SELECT numcambios FROM expediente WHERE codigo = '" + codigo + "'";
			
			rs = s.executeQuery(consulta);
			rs.next();
			
			numCambio = rs.getInt(1);
			numCambio++;
			
			consulta = "UPDATE expediente SET numcambios = " + numCambio + "WHERE codigo = '" + codigo + "'";
			s.executeUpdate(consulta);
			
			consulta = "INSERT INTO logCambios VALUES ('" + codigo + "', '" + numExp + "', " + numCambio + ", '" + usuario + "', '" + campo + "', '" + formato.format(fecha) + "', '" + valorViejo + "', '" + valorNuevo +"')";
			s.executeUpdate(consulta);
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
