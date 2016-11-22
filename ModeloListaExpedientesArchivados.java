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

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.server.VaadinService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ModeloListaExpedientesArchivados {
	private static ModeloListaExpedientesArchivados primeraInstancia;
	public static ModeloListaExpedientesArchivados obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ModeloListaExpedientesArchivados();
		}
		return primeraInstancia;
	}
		
	public SQLContainer consultarDatos(String hileraConsulta){
		SQLContainer containerTabla = null;
		try {
				Modelo m = Modelo.obtenerInstancia();
				SimpleJDBCConnectionPool c = m.getConnectionPool();
				FreeformQuery productFreeFormQuery = new FreeformQuery(hileraConsulta, c);
				containerTabla = new SQLContainer(productFreeFormQuery);
				}
		catch (SQLException e) {
				e.printStackTrace();
			}
		return containerTabla;
	}
	
	public void generarReporte() {
		String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "\\WEB-INF\\reporte\\ExpedientesArchivados.pdf";
		try {
			Modelo m = Modelo.obtenerInstancia();
			Connection c = m.getConnectionPool().reserveConnection();
			Statement s = c.createStatement();
			String consulta = "SELECT codigo, numexpediente, instructorasig, fechaingreso, clasificacion FROM expediente WHERE archivado = true";
			ResultSet rs = s.executeQuery(consulta);
			DateFormat formatoSimple = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = new Date();
			
			try {
				JasperDesign design= JRXmlLoader.load(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "\\WEB-INF\\reporte\\Expedientes.jrxml");
				JasperReport reporte = JasperCompileManager.compileReport(design);
				JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Titulo", "Reporte de expedientes archivados");
				parametros.put("Fecha", formatoSimple.format(fecha));
				JasperReport jasperReport = JasperCompileManager.compileReport(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "\\WEB-INF\\reporte\\Expedientes.jrxml");
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
