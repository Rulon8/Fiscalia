package ucr.casoUso;

import java.sql.SQLException;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.ColOffsetMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.MarginMod;
import com.sebworks.vaadstrap.Row;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class VistaListaExpedientesArchivados extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private ComboBox comboBoxOpciones;
	@AutoGenerated
	private TextField textFieldFiltro;
	@AutoGenerated
	private Table tableExpArc;
	@AutoGenerated
	private Button buttonVolver;
	@AutoGenerated
	private Label labelTitulo;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	private String nomColum = null;
	private String valorText = null;
	private Controlador control;
	private ControladorListaExpedientesArchivados controlador;
	private final String consultaInicial = "SELECT codigo, numexpediente, instructorasig,fechaingreso, clasificacion "
			+ "FROM expediente "
			+ "WHERE archivado = true";
	
	public VistaListaExpedientesArchivados() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		llenarTabla(consultaInicial);
		llenarComboBoxOpciones();
		control = Controlador.obtenerInstancia();
		
		//Metodo para el filtro de la tabla
		textFieldFiltro.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
            	String tabla = "";
            	if (comboBoxOpciones.getValue() != null){
            	switch(comboBoxOpciones.getValue().toString()) {
            		case "Código":
            			tabla = "codigo";
            			break;
            		case "Número de Expediente":
            			tabla = "numexpediente";
            			break;
            		case "Instructor Asignado":
            			tabla = "instructorasig";
            			break;
            		case "Fecha de Ingreso":
            			tabla = "fechaingreso";
            			break;
            		case "Clasificación":
            			tabla = "clasificacion";
            			break;
            	}
            	String filtro = event.getText();
                String consulta;
                	tableExpArc.removeAllItems();
        			if (event.getText().isEmpty()) {
        				llenarTabla(consultaInicial);
        			}
        			else {
        				consulta = consultaInicial + " AND " + tabla + " LIKE " + "'%" + filtro + "%'";
        				llenarTabla(consulta);
        			}
            }
            }
        });

		buttonVolver.addClickListener(new ClickListener(){
			@Override
			public void buttonClick (ClickEvent evento){
				control.cambiarVista("Menu Principal");
			}
		});
	}
	
	public void setVista() {
		
		this.setSizeFull();
		mainLayout.removeAllComponents();
		
		Container container = new Container();
		
		Row row1 = container.addRow();
		Col col1 = row1.addCol(ColMod.XS_12, ColMod.MD_10, ColOffsetMod.MD_OFFSET_1);
		col1.addComponent(labelTitulo);
		
		Row row2 = container.addRow();
		Col col2 = row2.addCol(ColMod.XS_12, ColMod.MD_10, ColOffsetMod.MD_OFFSET_1, MarginMod.TOP30);
		col2.addComponent(tableExpArc);
		
		Row row3 = container.addRow();
		Col col3 = row3.addCol(ColMod.XS_12, ColMod.SM_4, ColMod.MD_3, ColOffsetMod.MD_OFFSET_1, MarginMod.TOP30);
		col3.addComponent(textFieldFiltro);
		Col col4 = row3.addCol(ColMod.XS_12, ColMod.SM_5, ColOffsetMod.SM_OFFSET_2, ColMod.MD_4, ColOffsetMod.MD_OFFSET_2, MarginMod.TOP30);
		col4.addComponent(comboBoxOpciones);
		
		Row row4 = container.addRow();
		row4.addCol(MarginMod.TOP30);
		Col col5 = row4.addCol(ColMod.XS_12, ColMod.SM_3, ColOffsetMod.MD_OFFSET_1, MarginMod.TOP5);
		col5.addComponent(buttonVolver);
		
		mainLayout.addComponent(container);
		
		MyUI.getCurrent().setContent(this);
	}
	
	public void llenarTabla(String hileraConsulta){
		try{
			SimpleJDBCConnectionPool cp;
			cp = new SimpleJDBCConnectionPool("org.postgresql.Driver",
				"jdbc:postgresql://127.0.0.1:5432/Fiscalia", "postgres", "ECCIpgsql2016", 1, 5);
			//TableQuery tq = new TableQuery("expediente", cp);
			//SQLContainer containerTabla = new SQLContainer(tq);
			FreeformQuery productFreeFormQuery = new FreeformQuery(hileraConsulta, cp);
			SQLContainer containerTabla = new SQLContainer(productFreeFormQuery);
			tableExpArc.setContainerDataSource(containerTabla);
			tableExpArc.setSelectable(true);
			tableExpArc.setImmediate(true);
			tableExpArc.setVisibleColumns(new Object[] {"codigo","numexpediente","instructorasig","fechaingreso","clasificacion"});
			tableExpArc.setColumnHeaders(new String [] {"Código", "Número de Expediente", "Instructor Asignado", "Fecha de Ingreso", "Clasificación"});
			tableExpArc.setColumnReorderingAllowed(true);
			tableExpArc.setColumnCollapsingAllowed(true);	
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	private void llenarComboBoxOpciones() {
		comboBoxOpciones.addItem("Código");
		comboBoxOpciones.addItem("Número de Expediente");
		comboBoxOpciones.addItem("Instructor Asignado");
		comboBoxOpciones.addItem("Fecha de Ingreso");
		comboBoxOpciones.addItem("Clasificación");
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setStyleName("layout-archivados");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setStyleName("titulo");
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("200px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Lista de Expedientes Archivados");
		mainLayout.addComponent(labelTitulo, "top:100.0px;left:120.0px;");
		
		// buttonVolver
		buttonVolver = new Button();
		buttonVolver.setCaption("Volver");
		buttonVolver.setImmediate(true);
		buttonVolver.setWidth("-1px");
		buttonVolver.setHeight("-1px");
		mainLayout.addComponent(buttonVolver, "top:460.0px;left:140.0px;");
		
		// tableExpArc
		tableExpArc = new Table();
		tableExpArc.setStyleName("tabla");
		tableExpArc.setImmediate(false);
		tableExpArc.setWidth("100.0%");
		tableExpArc.setHeight("240px");
		mainLayout.addComponent(tableExpArc, "top:120.0px;right:509.0px;left:120.0px;");
		
		// textFieldFiltro
		textFieldFiltro = new TextField();
		textFieldFiltro.setImmediate(false);
		textFieldFiltro.setWidth("140px");
		textFieldFiltro.setHeight("-1px");
		mainLayout.addComponent(textFieldFiltro, "top:400.0px;left:120.0px;");
		
		// comboBoxOpciones
		comboBoxOpciones = new ComboBox();
		comboBoxOpciones.setImmediate(false);
		comboBoxOpciones.setWidth("220px");
		comboBoxOpciones.setHeight("-1px");
		mainLayout.addComponent(comboBoxOpciones, "top:400.0px;left:300.0px;");
		
		return mainLayout;
	}

}
