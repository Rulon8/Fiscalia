package ucr.casoUso;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;

public class VistaCambioPassword extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label labelErrorPassConf;
	@AutoGenerated
	private Label labelErrorPassViejo;
	@AutoGenerated
	private Label labelPassConf;
	@AutoGenerated
	private Label labelPassNuevo;
	@AutoGenerated
	private Label labelPassViejo;
	@AutoGenerated
	private Label labelTitulo;
	@AutoGenerated
	private Button buttonVolverCancelar;
	@AutoGenerated
	private Button botonCambiar;
	@AutoGenerated
	private PasswordField passFieldConf;
	@AutoGenerated
	private PasswordField passFieldNuevo;
	@AutoGenerated
	private PasswordField passFieldViejo;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public VistaCambioPassword() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		
		// passFieldViejo
		passFieldViejo = new PasswordField();
		passFieldViejo.setImmediate(false);
		passFieldViejo.setWidth("-1px");
		passFieldViejo.setHeight("-1px");
		mainLayout.addComponent(passFieldViejo, "top:140.0px;left:40.0px;");
		
		// passFieldNuevo
		passFieldNuevo = new PasswordField();
		passFieldNuevo.setImmediate(false);
		passFieldNuevo.setWidth("-1px");
		passFieldNuevo.setHeight("-1px");
		mainLayout.addComponent(passFieldNuevo, "top:216.0px;left:40.0px;");
		
		// passFieldConf
		passFieldConf = new PasswordField();
		passFieldConf.setImmediate(false);
		passFieldConf.setWidth("-1px");
		passFieldConf.setHeight("-1px");
		mainLayout.addComponent(passFieldConf, "top:296.0px;left:40.0px;");
		
		// botonCambiar
		botonCambiar = new Button();
		botonCambiar.setCaption("Cambiar");
		botonCambiar.setImmediate(true);
		botonCambiar.setDescription("Cambiar");
		botonCambiar.setWidth("-1px");
		botonCambiar.setHeight("-1px");
		mainLayout.addComponent(botonCambiar, "top:360.0px;left:40.0px;");
		
		// buttonVolverCancelar
		buttonVolverCancelar = new Button();
		buttonVolverCancelar.setCaption("Cancelar");
		buttonVolverCancelar.setImmediate(true);
		buttonVolverCancelar.setWidth("-1px");
		buttonVolverCancelar.setHeight("-1px");
		mainLayout.addComponent(buttonVolverCancelar, "top:360.0px;left:140.0px;");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Cambio de contraseña");
		mainLayout.addComponent(labelTitulo, "top:20.0px;left:40.0px;");
		
		// labelPassViejo
		labelPassViejo = new Label();
		labelPassViejo.setImmediate(false);
		labelPassViejo.setWidth("-1px");
		labelPassViejo.setHeight("-1px");
		labelPassViejo.setValue("Contraseña actual:");
		mainLayout.addComponent(labelPassViejo, "top:102.0px;left:40.0px;");
		
		// labelPassNuevo
		labelPassNuevo = new Label();
		labelPassNuevo.setImmediate(false);
		labelPassNuevo.setWidth("-1px");
		labelPassNuevo.setHeight("-1px");
		labelPassNuevo.setValue("Contraseña nueva:");
		mainLayout.addComponent(labelPassNuevo, "top:182.0px;left:40.0px;");
		
		// labelPassConf
		labelPassConf = new Label();
		labelPassConf.setImmediate(false);
		labelPassConf.setWidth("-1px");
		labelPassConf.setHeight("-1px");
		labelPassConf.setValue("Confirmar contraseña nueva:");
		mainLayout.addComponent(labelPassConf, "top:262.0px;left:40.0px;");
		
		// labelErrorPassViejo
		labelErrorPassViejo = new Label();
		labelErrorPassViejo.setImmediate(false);
		labelErrorPassViejo.setWidth("-1px");
		labelErrorPassViejo.setHeight("-1px");
		labelErrorPassViejo.setValue("Contraseña actual invalida!");
		mainLayout.addComponent(labelErrorPassViejo, "top:402.0px;left:40.0px;");
		
		// labelErrorPassConf
		labelErrorPassConf = new Label();
		labelErrorPassConf.setImmediate(false);
		labelErrorPassConf.setWidth("-1px");
		labelErrorPassConf.setHeight("-1px");
		labelErrorPassConf.setValue("Contraseñas nuevas no coinciden!");
		mainLayout.addComponent(labelErrorPassConf, "top:402.0px;left:330.0px;");
		
		return mainLayout;
	}

}