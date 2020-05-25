package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

import dao.DaoDirectorMantenimiento;
import dao.DaoDocumentalMantenimiento;
import dao.DaoPaisMantenimiento;
import vista.DialogoDocumentalAlta;


/**
 * Esta clase está dedicada al control de la interfaz gráfica de alta de documentales y del acceso a la base de datos para añadir documentales
 * @author Esteban Martínez
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class CtrlDocumentalAlta implements ActionListener {
	
	/**
	 * Ventana dialogoAltaDocumental 
	 */
	private DialogoDocumentalAlta dialogoAltaDocumental;
	
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Atributo para trabajar con los datos de directores
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	
	/**
	 * Atributo para trabajar con los datos de Documental
	 */
	private DaoDocumentalMantenimiento daoDocumentalMantenimiento;
	
	/**
	 * Método constructor del control para dar de alta documentales
	 */
	public CtrlDocumentalAlta() {
		daoPaisMantenimiento = new DaoPaisMantenimiento(); //Inicialización para poder trabajar con los datos de países 
		daoDirectorMantenimiento = new DaoDirectorMantenimiento(); //Inicialización para poder trabajar con los datos de directores
		
		//Creación de la ventana de alta de películas 
		dialogoAltaDocumental = new DialogoDocumentalAlta();
		
		dialogoAltaDocumental.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoAltaDocumental.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		try {
			dialogoAltaDocumental.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises()); //Recuperación de todos los países almacenados en la BBDD
			dialogoAltaDocumental.mostrarDirectores(daoDirectorMantenimiento.obtenerNombreDirectores()); //Recuperación del nombre de todos los directores almacenados en la BBDD
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE); //Creación de una ventana de error
            e.printStackTrace();
        }
		dialogoAltaDocumental.setVisible(true); 
	}

	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de alta de documentales
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		daoDocumentalMantenimiento = new DaoDocumentalMantenimiento(); //Inicialización para poder trabajar con los datos de documentales
		switch (e.getActionCommand()) {
		case "btnAceptar" :
			if(comprobarDatos()) { //Compruebo todos los datos introducidos por el usuario
				try {
					//Con esta condición llamo al dao de Documental para insertar todos los datos introducidos por el usuario recuperando campo por campo
					if(daoDocumentalMantenimiento.insertarDocumental(dialogoAltaDocumental.getFieldTitulo().getText(),(Integer) dialogoAltaDocumental.getSpinnerAnyo().getValue(),
							dialogoAltaDocumental.getComboBoxDirector().getSelectedItem().toString(), dialogoAltaDocumental.getComboBoxPais().getSelectedItem().toString(),
						 	dialogoAltaDocumental.getTextAreaSinopsis().getText(), dialogoAltaDocumental.getComboBoxGenero().getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Documental insertado correctamente"); //Creación ventana informativa
						dialogoAltaDocumental.dispose();
					}
					
				} catch (DerbySQLIntegrityConstraintViolationException a) {
					JOptionPane.showMessageDialog(null, "El documental ya existe.", "Error", JOptionPane.PLAIN_MESSAGE); //Creación de una ventana de error
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE); //Creación de una ventana de error
		            i.printStackTrace();
		        }
			}
			break;
		case "btnCancelar" :
			dialogoAltaDocumental.dispose(); //Cierro la ventana si pulsa el boton de cancelar
			break;
		}
		
	}
	
	
	/**
	 * Método que comprueba los datos de todos los campos para evitar que estén vacíos
	 * @return true si todos los datos son correctos y false si alguno está vacío
	 */
	public boolean comprobarDatos() {
		String mensaje="";
		if (dialogoAltaDocumental.getFieldTitulo().getText().equals("")) {
			mensaje = "Titulo no puede estar vacio";
		} else if (dialogoAltaDocumental.getComboBoxPais().getSelectedItem( ).equals("--Seleccionar País--")) {
			mensaje = "Tiene que seleccionar un pais valido";
		} else if (dialogoAltaDocumental.getComboBoxDirector().getSelectedItem().equals("--Seleccionar Director--")) {
			mensaje = "Tiene que seleccionar un director valido";
		} else if (dialogoAltaDocumental.getComboBoxGenero().getSelectedItem().equals("--Seleccionar Género--")) {
			mensaje = "Tiene que seleccionar un genero valido";
		} else {
			dialogoAltaDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(""); // Si todos los campos son correctos vacío el label con el mensaje de error y retorno true
			return true;
		}
		dialogoAltaDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(mensaje); //Si algun campo está mal muestro un label con el correspondiente mensaje en función del error 
		return false;
	}

	//GETTERS Y SETTERS
	public DialogoDocumentalAlta getdialogoAltaDocumental() {
		return dialogoAltaDocumental;
	}

	public void setdialogoAltaDocumental(DialogoDocumentalAlta dialogoAltaDocumental) {
		this.dialogoAltaDocumental = dialogoAltaDocumental;
	}
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
