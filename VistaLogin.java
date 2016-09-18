package ucr.casoUso;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.ColOffsetMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.Row;
//import com.sebworks.vaadstrap.Col;
//import com.sebworks.vaadstrap.ColMod;
//import com.sebworks.vaadstrap.ColOffsetMod;
//import com.sebworks.vaadstrap.Container;
//import com.sebworks.vaadstrap.Row;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class VistaLogin extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private PasswordField campoPassword;
	@AutoGenerated
	private Label labelErrAuth;
	@AutoGenerated
	private Label labelErrPass;
	@AutoGenerated
	private Label labelErrUser;
	@AutoGenerated
	private Label labelTitulo;
	@AutoGenerated
	private Button botonEntrar;
	@AutoGenerated
	private Button botonCambiar;
	@AutoGenerated
	private TextField campoCedula;
	private ControladorLogin controladorLogin;
	private Controlador control;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public VistaLogin() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		
		
		
		
		
		
		controladorLogin = ControladorLogin.obtenerInstancia();
		control = Controlador.obtenerInstancia();
		
		//Ocultar mensajes de error
		labelErrUser.setVisible(false);
		labelErrPass.setVisible(false);
		labelErrAuth.setVisible(false);
		
		mainLayout.setResponsive(true);
		campoCedula.setResponsive(true);
		labelTitulo.setResponsive(true);
		
		botonEntrar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String [] credenciales = new String[2];
				boolean usuarioValido;
				labelErrAuth.setVisible(false);
				labelErrPass.setVisible(false);
				labelErrUser.setVisible(false);
				
				if (validarCampos()) {
					credenciales[0] = campoCedula.getValue();
					credenciales[1] = campoPassword.getValue();
					usuarioValido = controladorLogin.enviarDatos(credenciales);
					
					//Mostrar mensaje si autenticacion falla
					if (!usuarioValido) {
						labelErrAuth.setVisible(true);
					} else {
						control.cambiarVista("Menu Principal");
					}
				}
			}
		});
		
		
		botonCambiar.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				control.cambiarVista("Nuevo Password");
			}
			
		});
	}
	
	public void setVista() {
		
		this.setSizeFull();
		mainLayout.removeAllComponents();
		
		
		Container container = new Container();
		
		Row row = container.addRow();
		Col col1 = row.addCol(ColMod.XS_12, ColOffsetMod.MD_OFFSET_2);
		col1.addComponent(labelTitulo);
		
		
		Row row2 = container.addRow();
		Col col2 = row2.addCol(ColMod.XS_12, ColMod.MD_8, ColOffsetMod.MD_OFFSET_2);
		col2.addComponent(campoCedula);
		Col col3 = row2.addCol(ColMod.XS_12, ColMod.MD_8, ColOffsetMod.MD_OFFSET_2);
		col3.addComponent(labelErrUser);
		Col col4 = row2.addCol(ColMod.XS_12, ColMod.MD_8, ColOffsetMod.MD_OFFSET_2);
		col4.addComponent(campoPassword);
		Col col5 = row2.addCol(ColMod.XS_12, ColMod.MD_8, ColOffsetMod.MD_OFFSET_2);
		col5.addComponent(labelErrPass);
		
		
		Row row3 = container.addRow();
		Col col6 = row3.addCol(ColMod.XS_12, ColMod.MD_3, ColOffsetMod.MD_OFFSET_2);
		col6.addComponent(botonCambiar);
		Col col7 = row3.addCol(ColMod.XS_12, ColMod.MD_3, ColOffsetMod.MD_OFFSET_2);
		col7.addComponent(botonEntrar);
		Col col8 = row3.addCol(ColMod.XS_12, ColOffsetMod.MD_OFFSET_2);
		col8.addComponent(labelErrAuth);
		
		mainLayout.addComponent(container);
		
		MyUI.getCurrent().setContent(this);
	} 

	private boolean validarCampos() {
		boolean resultado = true;
		
		if (campoCedula.isEmpty()) {
			resultado = false;
			labelErrUser.setVisible(true);
		}
		
		if (campoPassword.isEmpty() || campoPassword.getValue().length() < 8) {
			resultado = false;
			labelErrPass.setVisible(true);
		}
		
		return resultado;
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setStyleName("layout-centrado");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// campoCedula
		campoCedula = new TextField();
		campoCedula.setStyleName("campotexto");
		campoCedula.setImmediate(false);
		campoCedula.setWidth("394px");
		campoCedula.setHeight("-1px");
		campoCedula.setTabIndex(1);
		campoCedula.setInputPrompt("Cédula");
		mainLayout.addComponent(campoCedula, "top:184.0px;left:238.0px;");
		
		// botonCambiar
		botonCambiar = new Button();
		botonCambiar.setStyleName("boton");
		botonCambiar.setCaption("¿Olvidó su contraseña?");
		botonCambiar.setImmediate(true);
		botonCambiar.setWidth("-1px");
		botonCambiar.setHeight("-1px");
		botonCambiar.setTabIndex(4);
		mainLayout.addComponent(botonCambiar, "top:308.0px;left:238.0px;");
		
		// botonEntrar
		botonEntrar = new Button();
		botonEntrar.setStyleName("boton");
		botonEntrar.setCaption("Entrar");
		botonEntrar.setImmediate(true);
		botonEntrar.setWidth("-1px");
		botonEntrar.setHeight("-1px");
		botonEntrar.setTabIndex(3);
		mainLayout.addComponent(botonEntrar, "top:308.0px;left:548.0px;");
		
		// labelTitulo
		labelTitulo = new Label();
		labelTitulo.setStyleName("titulo");
		labelTitulo.setImmediate(false);
		labelTitulo.setWidth("-1px");
		labelTitulo.setHeight("-1px");
		labelTitulo.setValue("Ingreso al sistema");
		mainLayout.addComponent(labelTitulo, "top:80.0px;left:80.0px;");
		
		// labelErrUser
		labelErrUser = new Label();
		labelErrUser.setStyleName("error");
		labelErrUser.setImmediate(false);
		labelErrUser.setWidth("-1px");
		labelErrUser.setHeight("-1px");
		labelErrUser.setValue("Valor no válido");
		mainLayout.addComponent(labelErrUser, "top:196.0px;left:785.0px;");
		
		// labelErrPass
		labelErrPass = new Label();
		labelErrPass.setStyleName("error");
		labelErrPass.setImmediate(false);
		labelErrPass.setWidth("-1px");
		labelErrPass.setHeight("-1px");
		labelErrPass.setValue("Contraseña debe tener al menos 8 dígitos");
		mainLayout.addComponent(labelErrPass, "top:262.0px;left:785.0px;");
		
		// labelErrAuth
		labelErrAuth = new Label();
		labelErrAuth.setStyleName("error");
		labelErrAuth.setImmediate(false);
		labelErrAuth.setWidth("-1px");
		labelErrAuth.setHeight("-1px");
		labelErrAuth.setValue("Cédula o contraseña inválida");
		mainLayout.addComponent(labelErrAuth, "top:370.0px;left:473.0px;");
		
		// campoPassword
		campoPassword = new PasswordField();
		campoPassword.setStyleName("campotexto");
		campoPassword.setImmediate(false);
		campoPassword.setWidth("394px");
		campoPassword.setHeight("-1px");
		campoPassword.setTabIndex(2);
		campoPassword.setInputPrompt("Contraseña");
		mainLayout.addComponent(campoPassword, "top:252.0px;left:238.0px;");
		
		return mainLayout;
	}

}
