package ucr.casoUso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;

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
	
}
