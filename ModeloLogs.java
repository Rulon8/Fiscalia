package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
	
	public void generarReporte(String codExp) {
		String path = "C:\\Users\\b33799\\Documents\\reporteLogs.pdf";
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT numcambio, cedusuario, fecha, campo, estadoanterior, estadoactual FROM logcambios WHERE codigo = '" + codExp + "'";
			ResultSet rs = s.executeQuery(consulta);
			DateFormat formatoSimple = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = new Date();
			
			try {
				JasperDesign design= JRXmlLoader.load("C:\\Users\\b33799\\Downloads\\Logs.jrxml");
				JasperReport reporte = JasperCompileManager.compileReport(design);
				JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Titulo", "Reporte de cambios del expediente " + getNumExp(codExp));
				parametros.put("Fecha", formatoSimple.format(fecha));
				JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\b33799\\Downloads\\Logs.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, jrRS);
				JasperExportManager.exportReportToPdfFile(jasperPrint, path);
			}
			catch (JRException e) {
				System.out.println(e.getMessage());
			}
			
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
