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
			String consulta = "SELECT instructorasig FROM expediente WHERE instructorasig = '" + valorInstructor + "'";
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

/*
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
 */
