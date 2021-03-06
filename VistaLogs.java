package ucr.casoUso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.ColOffsetMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.MarginMod;
import com.sebworks.vaadstrap.Row;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class VistaLogs extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button botonGenerarReporte;
	@AutoGenerated
	private Button botonVolver;
	@AutoGenerated
	private Label labelNumero;
	@AutoGenerated
	private Label labelTitulo;
	@AutoGenerated
	private Table table_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	Controlador control;
	ControladorLogs controlador;
	
	public VistaLogs() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		controlador = ControladorLogs.obtenerInstancia();
		control = Controlador.obtenerInstancia();
		labelTitulo.setValue(labelTitulo.getValue() + " " + controlador.getNumExp());
		llenarTabla();
		botonVolver.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				control.cambiarVista("Expediente", controlador.getCodExp());
			}
		});
		
		botonGenerarReporte.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.generarReporte();
				pdf(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().replace("\\", "/") + "/WEB-INF/reporte/Logs.pdf");
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
		col2.addComponent(table_1);
		
		Row row3 = container.addRow();
		Col col3 = row3.addCol(ColMod.XS_4, ColMod.SM_3, ColMod.MD_3, ColOffsetMod.MD_OFFSET_1, MarginMod.TOP30);
		col3.addComponent(botonVolver);
		Col col4 = row3.addCol(ColMod.XS_7, ColOffsetMod.XS_OFFSET_1, ColMod.SM_4, ColOffsetMod.SM_OFFSET_2, MarginMod.TOP30);
		col4.addComponent(botonGenerarReporte);
		
		mainLayout.addComponent(container);
		
		MyUI.getCurrent().setContent(this);
	}
	
	public void llenarTabla(){
		ArrayList<String[]> resultados = controlador.pedirDatos();
		table_1.addContainerProperty("#", String.class, null);
		table_1.addContainerProperty("Usuario", String.class, null);
		table_1.addContainerProperty("Fecha", String.class, null);
		table_1.addContainerProperty("Campo", String.class, null);
		table_1.addContainerProperty("Estado Anterior", String.class, null);
		table_1.addContainerProperty("Estado Actual", String.class, null);
		for(int i = 0; i < resultados.size(); i++){
			String[] tupla = resultados.get(i);
			table_1.addItem(new Object[]{tupla[0], tupla[1], tupla[2], tupla[3], tupla[4], tupla[5]}, (i +1));
		}
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
		mainLayout.setStyleName("layout-logs");
		mainLayout.setCaption("asds");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// table_1
		table_1 = new Table();
		table_1.setStyleName("tabla");
		table_1.setImmediate(false);
		table_1.setWidth("-1px");
		table_1.setHeight("223px");
		mainLayout.addComponent(table_1, "top:57.0px;left:40.0px;");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setStyleName("titulo");
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("340px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Registro de cambios de expediente #");
		mainLayout.addComponent(labelTitulo, "top:20.0px;left:40.0px;");
		
		// labelNumero
		labelNumero = new Label();
		labelNumero.setImmediate(false);
		labelNumero.setWidth("-1px");
		labelNumero.setHeight("-1px");
		labelNumero.setValue("(Numero)");
		mainLayout.addComponent(labelNumero, "top:20.0px;left:309.0px;");
		
		// botonVolver
		botonVolver = new Button();
		botonVolver.setCaption("Volver");
		botonVolver.setImmediate(true);
		botonVolver.setWidth("-1px");
		botonVolver.setHeight("-1px");
		mainLayout.addComponent(botonVolver, "top:300.0px;left:40.0px;");
		
		// botonGenerarReporte
		botonGenerarReporte = new Button();
		botonGenerarReporte.setCaption("Generar Reporte");
		botonGenerarReporte.setImmediate(true);
		botonGenerarReporte.setWidth("-1px");
		botonGenerarReporte.setHeight("-1px");
		mainLayout.addComponent(botonGenerarReporte, "top:300.0px;left:203.0px;");
		
		return mainLayout;
	}

}
