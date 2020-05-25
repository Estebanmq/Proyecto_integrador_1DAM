package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import vista.DialogoPeliculaAlta;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de alta de películas y del acceso a la base de datos para añadir películas
 * @author Esteban Martínez
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class CtrlPeliculaAlta implements ActionListener {
	
	/**
	 * Ventana DialogoAltaPelicula 
	 */
	private DialogoPeliculaAlta dialogoAltaPelicula;
	
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Atributo para trabajar con los datos de directores
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	
	/**
	 * Atributo para trabajar con los datos de pelicula
	 */
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	/**
	 * Método constructor del control para dar de alta películas
	 */
	public CtrlPeliculaAlta() {
		daoPaisMantenimiento = new DaoPaisMantenimiento(); //Inicialización para poder trabajar con los datos de países 
		daoDirectorMantenimiento = new DaoDirectorMantenimiento(); //Inicialización para poder trabajar con los datos de directores
		
		//Creación de la ventana de alta de películas 
		dialogoAltaPelicula = new DialogoPeliculaAlta();
		
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		try {
			dialogoAltaPelicula.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises()); //Recuperación de todos los países almacenados en la BBDD
			dialogoAltaPelicula.mostrarDirectores(daoDirectorMantenimiento.obtenerNombreDirectores()); //Recuperación del nombre de todos los directores almacenados en la BBDD
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE); //Creación de una ventana de error
            e.printStackTrace();
        }
		dialogoAltaPelicula.setVisible(true); 
	}

	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de alta de películas
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		daoPeliculaMantenimiento = new DaoPeliculaMantenimiento(); //Inicialización para poder trabajar con los datos de películas
		switch (e.getActionCommand()) {
		case "btnAceptar" :
			if(comprobarDatos()) { //Compruebo todos los datos introducidos por el usuario
				try {
					//Con esta condición llamó al dao de pelicula para insertar todos los datos introducidos por el usuario recuperando campo por campo
					if(daoPeliculaMantenimiento.insertarPelicula(dialogoAltaPelicula.getFieldTitulo().getText(),(Integer) dialogoAltaPelicula.getSpinnerAnyo().getValue(),
							dialogoAltaPelicula.getComboBoxDirector().getSelectedItem().toString(), dialogoAltaPelicula.getComboBoxPais().getSelectedItem().toString(),
						 	dialogoAltaPelicula.getTextAreaSinopsis().getText(), dialogoAltaPelicula.getComboBoxGenero().getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Pelicula insertada correctamente"); //Creación ventana informativa
						dialogoAltaPelicula.dispose();
					}
					
				} catch (DerbySQLIntegrityConstraintViolationException a) {
					JOptionPane.showMessageDialog(null, "La película ya existe.", "Error", JOptionPane.PLAIN_MESSAGE); //Creación de una ventana de error
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE); //Creación de una ventana de error
		            i.printStackTrace();
		        }
			}
			break;
		case "btnCancelar" :
			dialogoAltaPelicula.dispose(); //Cierro la ventana si pulsa el boton de cancelar
			break;
		}
		
	}
	
	
	/**
	 * Método que comprueba los datos de todos los campos para evitar que estén vacíos
	 * @return true si todos los datos son correctos y false si alguno está vacío
	 */
	public boolean comprobarDatos() {
		String mensaje="";
		if (dialogoAltaPelicula.getFieldTitulo().getText().equals("")) {
			mensaje = "Titulo no puede estar vacio";
		} else if (dialogoAltaPelicula.getComboBoxPais().getSelectedItem( ).equals("--Seleccionar País--")) {
			mensaje = "Tiene que seleccionar un pais valido";
		} else if (dialogoAltaPelicula.getComboBoxDirector().getSelectedItem().equals("--Seleccionar Director--")) {
			mensaje = "Tiene que seleccionar un director valido";
		} else if (dialogoAltaPelicula.getComboBoxGenero().getSelectedItem().equals("--Seleccionar Género--")) {
			mensaje = "Tiene que seleccionar un genero valido";
		} else {
			dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(""); // Si todos los campos son correctos vacío el label con el mensaje de error y retorno true
			return true;
		}
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(mensaje); //Si algun campo está mal muestro un label con el correspondiente mensaje en función del error 
		return false;
	}

	//GETTERS Y SETTERS
	public DialogoPeliculaAlta getDialogoAltaPelicula() {
		return dialogoAltaPelicula;
	}

	public void setDialogoAltaPelicula(DialogoPeliculaAlta dialogoAltaPelicula) {
		this.dialogoAltaPelicula = dialogoAltaPelicula;
	}
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
