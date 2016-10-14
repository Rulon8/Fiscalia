package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ControladorListaExpedientesActivos {
	ModeloListaExpedientesActivos modelo;
	VistaListaExpedientesActivos InterfazListaExpedientes;
	private static ControladorListaExpedientesActivos primeraInstancia = null;
	
	public ControladorListaExpedientesActivos(){
		
	}
	
	public void iniciar(){
		modelo = ModeloListaExpedientesActivos.obtenerInstancia();
		InterfazListaExpedientes = new VistaListaExpedientesActivos();
		
	}
	
	public static ControladorListaExpedientesActivos obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorListaExpedientesActivos();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		InterfazListaExpedientes.setVista();
	}
	
	public SQLContainer consultarDatos(String hileraConsulta){
		return modelo.consultarDatos(hileraConsulta);
	}
}
