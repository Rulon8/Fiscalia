package ucr.casoUso;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.ColOffsetMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.Row;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;

public class VistaMenuPrincipal extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button botonCambiarPass;
	@AutoGenerated
	private Button botonSalir;
	@AutoGenerated
	private Label etiquetaTitulo;
	@AutoGenerated
	private Label etiquetaUsuarioLogeado;
	@AutoGenerated
	private Button botonCrearExpediente;
	@AutoGenerated
	private Button botonExpedientesArchivados;
	@AutoGenerated
	private Button botonCrearUsuario;
	@AutoGenerated
	private Button botonExpedientesActivos;
	@AutoGenerated
	private Embedded embedded_2;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	private Controlador controlador;
	
	public void setInfoUsuario() {
		etiquetaUsuarioLogeado.setValue("Usuario: " + controlador.getNombreUsuario() + " " + controlador.getApellidoUsuario());
	}
	
	public void determinarTipoUsuario() {
		if(controlador.getTipoUsuario().equals("Secretaria")) {
			botonCrearExpediente.setVisible(true);
			botonCrearUsuario.setVisible(true);
		}
		else {
			botonCrearExpediente.setVisible(false);
			botonCrearUsuario.setVisible(false);
		}
	}
	
	public void setVista() {
		
		this.setSizeFull();
		mainLayout.removeAllComponents();
		
		Container container = new Container();
		
		Row row1 = container.addRow();
		Col col1 = row1.addCol(ColMod.XS_12, ColMod.MD_4, ColOffsetMod.MD_OFFSET_4);
		col1.addComponent(etiquetaTitulo);
		
		Row row2 = container.addRow();
		Col col2 = row2.addCol(ColMod.XS_12, ColMod.MD_4, ColOffsetMod.MD_OFFSET_4);
		col2.addComponent(embedded_2);
		
		Row row3 = container.addRow();
		Col col3 = row3.addCol(ColMod.XS_12, ColMod.MD_4, ColOffsetMod.MD_OFFSET_4);
		col3.addComponent(etiquetaUsuarioLogeado);
		
		Row row4 = container.addRow();
		Col col4 = row4.addCol(ColMod.XS_12, ColMod.MD_4);
		col4.addComponent(botonExpedientesActivos);
		Col col5 = row4.addCol(ColMod.XS_12, ColMod.MD_4, ColOffsetMod.MD_OFFSET_4);
		col5.addComponent(botonExpedientesArchivados);
	}
	
	public VistaMenuPrincipal() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		
		etiquetaUsuarioLogeado.setImmediate(true);
		controlador = Controlador.obtenerInstancia();
		
		determinarTipoUsuario();
		setInfoUsuario();
		
		botonExpedientesActivos.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.cambiarVista("Expedientes Activos");
			}
		});
		
		botonExpedientesArchivados.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.cambiarVista("Expedientes Archivados");
			}
		});
		
		botonCrearUsuario.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.cambiarVista("Crear Usuario");
			}
		});
		
		botonCrearExpediente.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.cambiarVista("Agregar Expediente");
			}
		});
		
		botonExpedientesActivos.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controlador.cambiarVista("Lista Expedientes");
			}
		});
		
		botonSalir.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				controlador.cambiarVista("Login");
			}
			
		});
		
		botonCambiarPass.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				controlador.cambiarVista("Cambio Pass");
			}
			
		});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// embedded_2
		embedded_2 = new Embedded();
		embedded_2.setImmediate(false);
		embedded_2.setWidth("180px");
		embedded_2.setHeight("160px");
		embedded_2.setSource(new ExternalResource("http://campusvirtualabogados.cr/img/logo.png"));
		embedded_2.setType(1);
		embedded_2.setMimeType("image/png");
		mainLayout.addComponent(embedded_2, "top:60.0px;left:294.0px;");
		
		// botonExpedientesActivos
		botonExpedientesActivos = new Button();
		botonExpedientesActivos.setCaption("Expedientes Activos");
		botonExpedientesActivos.setImmediate(true);
		botonExpedientesActivos.setWidth("250px");
		botonExpedientesActivos.setHeight("30px");
		mainLayout.addComponent(botonExpedientesActivos, "top:340.0px;left:120.0px;");
		
		// botonCrearUsuario
		botonCrearUsuario = new Button();
		botonCrearUsuario.setCaption("Crear Usuario");
		botonCrearUsuario.setImmediate(true);
		botonCrearUsuario.setWidth("250px");
		botonCrearUsuario.setHeight("30px");
		mainLayout.addComponent(botonCrearUsuario, "top:394.0px;left:120.0px;");
		
		// botonExpedientesArchivados
		botonExpedientesArchivados = new Button();
		botonExpedientesArchivados.setCaption("Expedientes Archivados");
		botonExpedientesArchivados.setImmediate(true);
		botonExpedientesArchivados.setWidth("250px");
		botonExpedientesArchivados.setHeight("30px");
		mainLayout.addComponent(botonExpedientesArchivados, "top:340.0px;left:440.0px;");
		
		// botonCrearExpediente
		botonCrearExpediente = new Button();
		botonCrearExpediente.setCaption("Agregar Expediente");
		botonCrearExpediente.setImmediate(true);
		botonCrearExpediente.setWidth("250px");
		botonCrearExpediente.setHeight("30px");
		mainLayout.addComponent(botonCrearExpediente, "top:394.0px;left:440.0px;");
		
		// etiquetaUsuarioLogeado
		etiquetaUsuarioLogeado = new Label();
		etiquetaUsuarioLogeado.setImmediate(false);
		etiquetaUsuarioLogeado.setWidth("-1px");
		etiquetaUsuarioLogeado.setHeight("-1px");
		etiquetaUsuarioLogeado.setValue("Usuario: ");
		mainLayout.addComponent(etiquetaUsuarioLogeado, "top:262.0px;left:318.0px;");
		
		// etiquetaTitulo
		etiquetaTitulo = new Label();
		etiquetaTitulo.setStyleName("titulo");
		etiquetaTitulo.setImmediate(false);
		etiquetaTitulo.setWidth("-1px");
		etiquetaTitulo.setHeight("-1px");
		etiquetaTitulo.setValue("Menu Principal");
		mainLayout.addComponent(etiquetaTitulo, "top:20.0px;left:312.0px;");
		
		// botonSalir
		botonSalir = new Button();
		botonSalir.setCaption("Salir");
		botonSalir.setImmediate(true);
		botonSalir.setWidth("-1px");
		botonSalir.setHeight("-1px");
		mainLayout.addComponent(botonSalir, "top:455.0px;left:370.0px;");
		
		// botonCambiarPass
		botonCambiarPass = new Button();
		botonCambiarPass.setCaption("Cambiar Contraseña");
		botonCambiarPass.setImmediate(true);
		botonCambiarPass.setWidth("-1px");
		botonCambiarPass.setHeight("-1px");
		mainLayout.addComponent(botonCambiarPass, "top:560.0px;left:336.0px;");
		
		return mainLayout;
	}

}
