package ucr.casoUso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.vaadin.data.Container;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import com.vaadin.data.Container;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ControladorCrearUsuario {
	
	VistaCrearUsuario interfazCrearUsuario;
	ModeloCrearUsuario modelo;
	private static ControladorCrearUsuario primeraInstancia = null;
	Correo correo;
	
	public static ControladorCrearUsuario obtenerInstancia() {
		if(primeraInstancia == null){
			primeraInstancia = new ControladorCrearUsuario();
		}
		return primeraInstancia;
	}
	
	public void iniciar() {
		interfazCrearUsuario = new VistaCrearUsuario();
		modelo = ModeloCrearUsuario.obtenerInstancia();
		correo = new Correo();
		actualizar();
	}
	
	public void actualizar() {
		List<String> instructores = modelo.getInfoInstructores2();
		interfazCrearUsuario.addVariosComboBoxInstructor2(instructores);
	}
	
	public boolean enviarDatos(String [] user){
		boolean exito;
		String pass[] = crearPassword();
		
		user[6] = pass[1];
		
		exito = modelo.setNewUsuario(user);
		//correo.enviar(user[3], pass[0]);
		//crearReporteUsuarios();
		actualizar();
		return exito;
	}
	
	private String[] crearPassword() {
		String alfabeto = "abcdefghijklmnopqrstuvwxyz";
		String pass = "";
		String passHash = "";
		String[] passwords = new String[2];
		Random r = new Random();
		/*
		for (int i = 0; i < 8; i++) {
			pass += alfabeto.charAt(r.nextInt(26));
		}
		*/
		pass = "password1";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(pass.getBytes());
			passHash = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error de hash");
		}
		
		passwords[0] = pass;
		passwords[1] = passHash;
		
		return passwords;
	}
	
	public void crearReporteUsuarios() {
		
		Connection c;
		Statement s;
		String consulta;
		ResultSet rs;
		Modelo m = Modelo.obtenerInstancia();
				
		try {
			c = m.getConnectionPool().reserveConnection();
			s = c.createStatement();
			consulta = "SELECT nombre, apellido, tipodeusuario FROM usuario";					

			rs = s.executeQuery(consulta);
			
			try {
				JasperDesign design= JRXmlLoader.load("C:\\Users\\Admin\\JaspersoftWorkspace\\MyReports\\usuarios.jrxml");
				JasperReport reporte = JasperCompileManager.compileReport(design);
				JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
				Map<String, Object> parametros = new HashMap();
				parametros.put("empresa", "Colegio de Abogados");
				JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Admin\\JaspersoftWorkspace\\MyReports\\usuarios.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, jrRS);
				JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Giancarlo\\Documents\\UCR\\Ingenería I\\reporteUsuarios.pdf");
			}
			catch (JRException e) {
				
			}
			
			s.close();
			c.commit();
			c.close();
			
			m.getConnectionPool().releaseConnection(c);
		}
		catch (SQLException e) {
			//FALTA: Controlar errores de SQL
			e.printStackTrace();
		}
		
	}
	
	public void setVista() {
		MyUI.getCurrent().setContent(interfazCrearUsuario);
	}
	
}
