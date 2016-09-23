package ucr.casoUso;

import java.util.ArrayList;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class VistaUsuarios extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label labelUsuario;
	@AutoGenerated
	private Label labelInstructor;
	@AutoGenerated
	private Label labelInstructorConf;
	@AutoGenerated
	private Button botonInstructorCancel;
	@AutoGenerated
	private Button botonInstructorConf;
	@AutoGenerated
	private Button botonElimCancel;
	@AutoGenerated
	private Button botonElimConf;
	@AutoGenerated
	private Label labelElimConf2;
	@AutoGenerated
	private Label labelElimConf1;
	@AutoGenerated
	private Button botonCancelar;
	@AutoGenerated
	private Button botonEliminar;
	@AutoGenerated
	private Button botonInstructor;
	@AutoGenerated
	private Table tablaInstructor;
	@AutoGenerated
	private Table tablaUsuarios;
	@AutoGenerated
	private Label labelTitulo;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	Controlador control;
	ControladorUsuarios controlador;
	
	public VistaUsuarios() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		control = Controlador.obtenerInstancia();
		controlador = ControladorUsuarios.obtenerInstancia();
		
		// TODO add user code here
		
		labelUsuario.setVisible(false);
		labelInstructor.setVisible(false);
		labelElimConf1.setVisible(false);
		labelElimConf2.setVisible(false);
		labelInstructorConf.setVisible(false);
		botonElimConf.setVisible(false);
		botonElimCancel.setVisible(false);
		botonInstructorConf.setVisible(false);
		botonInstructorCancel.setVisible(false);
		tablaInstructor.setVisible(false);
		
		tablaInstructor.setSelectable(true);
		tablaUsuarios.setSelectable(true);
		llenarTablaUsuarios();
		
		tablaUsuarios.addItemClickListener(new ItemClickEvent.ItemClickListener() {
		    @Override
		    public void itemClick(ItemClickEvent itemClickEvent) {
		    	Item fila =  itemClickEvent.getItem();
		        System.out.println(   fila.getItemProperty("NombreColumna").getValue()   );
		    	
		    }
		});
		
		botonEliminar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Object linea = tablaUsuarios.getValue();
				Item item = tablaUsuarios.getItem(linea);
				String cedUsuario = item.getItemProperty("Cédula").getValue().toString();
				System.out.println(cedUsuario);
			}
		});
		
		tablaUsuarios.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			  @Override
			  public void itemClick(ItemClickEvent event) {
			      //Manage collection and manually fetch property of table
			      Object value = event.getItem().getItemProperty("property").getValue();
			  }
			});
		
		botonCancelar.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				control.cambiarVista("Menu Principal");
			}
		});
	}
	
	public void llenarTablaUsuarios(){
		ArrayList<String[]> resultados = controlador.pedirDatosUsuarios();
		tablaUsuarios.addContainerProperty("Cédula", String.class, null);
		tablaUsuarios.addContainerProperty("Nombre", String.class, null);
		tablaUsuarios.addContainerProperty("Apellido", String.class, null);
		tablaUsuarios.addContainerProperty("Tipo de usuario", String.class, null);
		for(int i = 0; i < resultados.size(); i++){
			String[] tupla = resultados.get(i);
			System.out.println(tupla[0] + tupla[1] + tupla[2] + tupla[3]);
			tablaUsuarios.addItem(new Object[]{tupla[0], tupla[1], tupla[2], tupla[3]}, (i +1));
		}
	}
	
	public void llenarTablaInstructores(String cedInst){
		ArrayList<String[]> resultados = controlador.pedirDatosInstructores(cedInst);
		tablaUsuarios.addContainerProperty("Cédula", String.class, null);
		tablaUsuarios.addContainerProperty("Nombre", String.class, null);
		tablaUsuarios.addContainerProperty("Apellido", String.class, null);
		for(int i = 0; i < resultados.size(); i++){
			String[] tupla = resultados.get(i);
			System.out.println(tupla[0] + tupla[1] + tupla[2]);
			tablaUsuarios.addItem(new Object[]{tupla[0], tupla[1], tupla[2]}, (i +1));
		}
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
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setStyleName("titulo");
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Usuarios del sistema");
		mainLayout.addComponent(labelTitulo, "top:20.0px;left:49.0px;");
		
		// tablaUsuarios
		tablaUsuarios = new Table();
		tablaUsuarios.setCaption("Usuarios");
		tablaUsuarios.setImmediate(false);
		tablaUsuarios.setWidth("-1px");
		tablaUsuarios.setHeight("-1px");
		mainLayout.addComponent(tablaUsuarios, "top:80.0px;left:49.0px;");
		
		// tablaInstructor
		tablaInstructor = new Table();
		tablaInstructor.setCaption("Instructores");
		tablaInstructor.setImmediate(false);
		tablaInstructor.setWidth("-1px");
		tablaInstructor.setHeight("-1px");
		mainLayout.addComponent(tablaInstructor, "top:80.0px;left:442.0px;");
		
		// botonInstructor
		botonInstructor = new Button();
		botonInstructor.setCaption("Elegir");
		botonInstructor.setImmediate(true);
		botonInstructor.setWidth("-1px");
		botonInstructor.setHeight("-1px");
		mainLayout.addComponent(botonInstructor, "top:454.0px;left:439.0px;");
		
		// botonEliminar
		botonEliminar = new Button();
		botonEliminar.setCaption("Eliminar");
		botonEliminar.setImmediate(true);
		botonEliminar.setWidth("-1px");
		botonEliminar.setHeight("-1px");
		mainLayout.addComponent(botonEliminar, "top:454.0px;left:49.0px;");
		
		// botonCancelar
		botonCancelar = new Button();
		botonCancelar.setCaption("Cancelar");
		botonCancelar.setImmediate(true);
		botonCancelar.setWidth("-1px");
		botonCancelar.setHeight("-1px");
		mainLayout.addComponent(botonCancelar, "top:454.0px;left:135.0px;");
		
		// labelElimConf1
		labelElimConf1 = new Label();
		labelElimConf1.setImmediate(false);
		labelElimConf1.setWidth("260px");
		labelElimConf1.setHeight("18px");
		labelElimConf1.setValue("Esta seguro que desea eliminar a este usuario?");
		mainLayout.addComponent(labelElimConf1, "top:162.0px;left:180.0px;");
		
		// labelElimConf2
		labelElimConf2 = new Label();
		labelElimConf2.setImmediate(false);
		labelElimConf2.setWidth("-1px");
		labelElimConf2.setHeight("18px");
		labelElimConf2.setValue("Una vez eliminado, los datos no se pueden recuperar.");
		mainLayout.addComponent(labelElimConf2, "top:180.0px;left:160.0px;");
		
		// botonElimConf
		botonElimConf = new Button();
		botonElimConf.setCaption("Si");
		botonElimConf.setImmediate(true);
		botonElimConf.setWidth("-1px");
		botonElimConf.setHeight("-1px");
		mainLayout.addComponent(botonElimConf, "top:220.0px;left:235.0px;");
		
		// botonElimCancel
		botonElimCancel = new Button();
		botonElimCancel.setCaption("No");
		botonElimCancel.setImmediate(true);
		botonElimCancel.setWidth("-1px");
		botonElimCancel.setHeight("-1px");
		mainLayout.addComponent(botonElimCancel, "top:220.0px;left:330.0px;");
		
		// botonInstructorConf
		botonInstructorConf = new Button();
		botonInstructorConf.setCaption("SI");
		botonInstructorConf.setImmediate(true);
		botonInstructorConf.setWidth("-1px");
		botonInstructorConf.setHeight("-1px");
		mainLayout.addComponent(botonInstructorConf, "top:254.0px;left:235.0px;");
		
		// botonInstructorCancel
		botonInstructorCancel = new Button();
		botonInstructorCancel.setCaption("No");
		botonInstructorCancel.setImmediate(true);
		botonInstructorCancel.setWidth("-1px");
		botonInstructorCancel.setHeight("-1px");
		mainLayout.addComponent(botonInstructorCancel, "top:260.0px;left:330.0px;");
		
		// labelInstructorConf
		labelInstructorConf = new Label();
		labelInstructorConf.setImmediate(false);
		labelInstructorConf.setWidth("-1px");
		labelInstructorConf.setHeight("-1px");
		labelInstructorConf.setValue("Los expedientes asociados a éste usuario se le asignarán a:");
		mainLayout.addComponent(labelInstructorConf, "top:198.0px;left:140.0px;");
		
		// labelInstructor
		labelInstructor = new Label();
		labelInstructor.setImmediate(false);
		labelInstructor.setWidth("-1px");
		labelInstructor.setHeight("-1px");
		labelInstructor.setValue("Instructor nuevo");
		mainLayout.addComponent(labelInstructor, "top:220.0px;left:254.0px;");
		
		// labelUsuario
		labelUsuario = new Label();
		labelUsuario.setImmediate(false);
		labelUsuario.setWidth("-1px");
		labelUsuario.setHeight("-1px");
		labelUsuario.setValue("Usuario a eliminar");
		mainLayout.addComponent(labelUsuario, "top:120.0px;left:254.0px;");
		
		return mainLayout;
	}
}
