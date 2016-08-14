package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class ControladorListaExpedientes {
	ModeloListaExpedientes modelo;
	VistaListaExpedientes InterfazListaExpedientes;
	private static ControladorListaExpedientes primeraInstancia = null;
	
	public ControladorListaExpedientes(){
		
	}
	
	public void iniciar(){
		modelo = ModeloListaExpedientes.obtenerInstancia();
		InterfazListaExpedientes = new VistaListaExpedientes();
		
	}
	
	public static ControladorListaExpedientes obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorListaExpedientes();
		}
		return primeraInstancia;
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(InterfazListaExpedientes);
	}
}
