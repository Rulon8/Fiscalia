package ucr.casoUso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.ColOffsetMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.MarginMod;
import com.sebworks.vaadstrap.Row;
import com.vaadin.addon.tableexport.CsvExport;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class VistaListaExpedientesArchivados extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button buttonCsv;
	@AutoGenerated
	private Button buttonExcel;
	@AutoGenerated
	private Button buttonReporte;
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
	
	@SuppressWarnings("deprecation")
	public VistaListaExpedientesArchivados() {
		control = Controlador.obtenerInstancia();
		controlador = ControladorListaExpedientesArchivados.obtenerInstancia();
		buildMainLayout();
		setCompositionRoot(mainLayout);
		llenarTabla(consultaInicial);
		llenarComboBoxOpciones();
		textFieldFiltro.setImmediate(true);
		comboBoxOpciones.setImmediate(true);
		
		//Metodo para el filtro de la tabla
		textFieldFiltro.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
            	String columna = "";
            	if (comboBoxOpciones.getValue() != null){
            	switch(comboBoxOpciones.getValue().toString()) {
            		case "Código":
            			columna = "codigo";
            			break;
            		case "Número de Expediente":
            			columna = "numexpediente";
            			break;
            		case "Instructor Asignado":
            			columna = "instructorasig";
            			break;
            		case "Fecha de Ingreso":
            			columna = "fechaingreso";
            			break;
            		case "Clasificación":
            			columna = "clasificacion";
            			break;
            	}
            	String filtro = event.getText();
                String consulta;
                	tableExpArc.removeAllItems();
        			if (event.getText().isEmpty()) {
        				llenarTabla(consultaInicial);
        			}
        			else {
        				consulta = consultaInicial + " AND " + columna + " LIKE " + "'%" + filtro + "%'";
        				llenarTabla(consulta);
        			}
            	}
            	else {
            		llenarTabla(consultaInicial);
            	}
            }
        });
		
		comboBoxOpciones.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				
				String columna = "";
            	if (comboBoxOpciones.getValue() != null){
            	switch(comboBoxOpciones.getValue().toString()) {
            		case "Código":
            			columna = "codigo";
            			break;
            		case "Número de Expediente":
            			columna = "numexpediente";
            			break;
            		case "Instructor Asignado":
            			columna = "instructorasig";
            			break;
            		case "Fecha de Ingreso":
            			columna = "fechaingreso";
            			break;
            		case "Clasificación":
            			columna = "clasificacion";
            			break;
            	}
            	String filtro = textFieldFiltro.getValue();
                String consulta;
                	tableExpArc.removeAllItems();
        			if (filtro.isEmpty()) {
        				llenarTabla(consultaInicial);
        			}
        			else {
        				consulta = consultaInicial + " AND " + columna + " LIKE " + "'%" + filtro + "%'";
        				llenarTabla(consulta);
        			}
            	} 
            	else {
            		llenarTabla(consultaInicial);
            	}
			}
			
		});

		buttonVolver.addClickListener(new ClickListener(){
			@Override
			public void buttonClick (ClickEvent evento){
				control.cambiarVista("Menu Principal");
			}
		});
		
		buttonReporte.addClickListener(new ClickListener(){
			@Override
			public void buttonClick (ClickEvent evento){
				controlador.generarReporte();
				pdf(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().replace("\\", "/") + "/WEB-INF/reporte/ExpedientesArchivados.pdf");
			}
		});
		
		buttonExcel.addClickListener(new ClickListener(){
			@Override
			public void buttonClick (ClickEvent evento){
				excel();
			}
		});
		
		buttonCsv.addClickListener(new ClickListener(){
			@Override
			public void buttonClick (ClickEvent evento){
				csv();
			}
		});
		
		tableExpArc.addListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 2068314108919135281L;
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                	Property itemProperty = event.getItem().getItemProperty("codigo");
                    control.cambiarVista("Expediente", itemProperty.getValue().toString());
                }
            }
        });
		
		
	}
	
	private void excel() {
		tableExpArc.setImmediate(true);
		ExcelExport excelExport;
		excelExport = new ExcelExport(tableExpArc);
		excelExport.excludeCollapsedColumns();
		excelExport.setReportTitle("Expedientes Archivados");
		excelExport.setExportFileName("ExpedientesArchivados.xls");
		excelExport.setDisplayTotals(false);
		excelExport.export();
	}
	
	private void csv() {
		tableExpArc.setImmediate(true);
		CsvExport csvExport;
		csvExport = new CsvExport(tableExpArc);
		csvExport.excludeCollapsedColumns();
		csvExport.setReportTitle("Expedientes Archivados");
		csvExport.setExportFileName("ExpedientesArchivados.csv");
		csvExport.setDisplayTotals(false);
		csvExport.export();
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
		Col col7 = row4.addCol(ColMod.XS_12, ColMod.SM_3, ColMod.MD_2, ColOffsetMod.MD_OFFSET_1, MarginMod.TOP5);
		col7.addComponent(buttonReporte);
		Col col8 = row4.addCol(ColMod.XS_12, ColMod.SM_3, ColMod.MD_2, MarginMod.TOP5);
		col8.addComponent(buttonCsv);
		Col col9 = row4.addCol(ColMod.XS_12, ColMod.SM_3, ColMod.MD_2, MarginMod.TOP5);
		col9.addComponent(buttonExcel);
		Col col5 = row4.addCol(ColMod.XS_12, ColMod.SM_3, ColMod.MD_2, ColOffsetMod.MD_OFFSET_2, MarginMod.TOP5);
		col5.addComponent(buttonVolver);
		
		mainLayout.addComponent(container);
		
		MyUI.getCurrent().setContent(this);
	}
	
	public void llenarTabla(String hileraConsulta){
			tableExpArc.setContainerDataSource(controlador.consultarDatos(hileraConsulta));
			tableExpArc.setSelectable(true);
			tableExpArc.setImmediate(true);
			tableExpArc.setVisibleColumns(new Object[] {"codigo","numexpediente","instructorasig","fechaingreso","clasificacion"});
			tableExpArc.setColumnHeaders(new String [] {"Código", "Número de Expediente", "Instructor Asignado", "Fecha de Ingreso", "Clasificación"});
			tableExpArc.setColumnReorderingAllowed(true);
			tableExpArc.setColumnCollapsingAllowed(true);	
	}
	
	private void llenarComboBoxOpciones() {
		comboBoxOpciones.addItem("Código");
		comboBoxOpciones.addItem("Número de Expediente");
		comboBoxOpciones.addItem("Instructor Asignado");
		comboBoxOpciones.addItem("Fecha de Ingreso");
		comboBoxOpciones.addItem("Clasificación");
	}
	
	public void pdf(final String nombreArchivo){
		try {
			Window w = new Window();
			w.setResizable(true);
			w.setWidth("900");
			w.setHeight("800");
			w.center();

			Embedded e = new Embedded();
			e.setSizeFull();
			e.setType(Embedded.TYPE_BROWSER);
		
			StreamResource resource = new StreamResource( 
					new StreamSource(){     
			            public InputStream getStream() {
			                InputStream mipdf = null;
							try {
								mipdf = new FileInputStream(nombreArchivo);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
			            	return mipdf;
			            }
			         }
					, nombreArchivo);

			resource.setMIMEType("application/pdf");

			e.setSource(resource);
			w.setContent(e);
			UI.getCurrent().addWindow(w);
		} catch (Exception e) {
			Notification.show("Se produjo un error en el sistema", Notification.Type.WARNING_MESSAGE);
			e.getStackTrace();
		}
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
		
		// buttonReporte
		buttonReporte = new Button();
		buttonReporte.setCaption("Generar PDF");
		buttonReporte.setImmediate(true);
		buttonReporte.setWidth("-1px");
		buttonReporte.setHeight("-1px");
		mainLayout.addComponent(buttonReporte, "top:460.0px;left:243.0px;");
		
		// buttonExcel
		buttonExcel = new Button();
		buttonExcel.setCaption("Generar XLS");
		buttonExcel.setImmediate(true);
		buttonExcel.setWidth("-1px");
		buttonExcel.setHeight("-1px");
		mainLayout.addComponent(buttonExcel, "top:460.0px;left:400.0px;");
		
		// buttonCsv
		buttonCsv = new Button();
		buttonCsv.setCaption("Generar CSV");
		buttonCsv.setImmediate(false);
		buttonCsv.setWidth("-1px");
		buttonCsv.setHeight("-1px");
		mainLayout.addComponent(buttonCsv, "top:460.0px;left:540.0px;");
		
		return mainLayout;
	}

}
