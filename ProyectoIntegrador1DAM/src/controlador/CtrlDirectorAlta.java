package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Director;
import vista.DialogoDirectorAlta;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de alta de directores y del acceso a la base de datos para añadir directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */

public class CtrlDirectorAlta implements ActionListener {
	
	/**
	 * Ventana DialogoDirector
	 */
	private DialogoDirectorAlta dialogoDirectorAlta;
	/**
	 * Objeto para director
	 */
	private Director Director;
	/**
	 * Atributo para trabajar con los datos de directores
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para dar de alta directores
	 */
	public CtrlDirectorAlta() {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		this.setDialogoDirectorAlta(new DialogoDirectorAlta());
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		try {
			this.getDialogoDirectorAlta().mostrarPaises(daoPaisMantenimiento.obtenerListaPaises());
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		this.getDialogoDirectorAlta().setVisible(true);
	}

	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de alta de directores
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "btnAceptar" :
				//valida que no esten vacios los campos
				boolean validar = validarCampos();
				if(!validar) {
					break;
				}
				//si todo ok meter director bbdd
				try {
					daoDirectorMantenimiento.darAltaDirector(dialogoDirectorAlta);
				}catch (ClassNotFoundException | SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
				System.out.format("Ha pulsado: %s\n", e.getActionCommand());
				this.getDialogoDirectorAlta().dispose();
				break;
			case "btnCancelar" :
				this.getDialogoDirectorAlta().dispose();
				break;
		}
	}
	
	/**
	 * Método para validar los campos y que no esten vacios
	 */
	private boolean validarCampos() {
		
		boolean validar = true;
		
		if(this.getDialogoDirectorAlta().getTextNombre().getText().isEmpty()) {
			this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Nombre es un campo obligatorio");
			validar = false;
		}else if(this.getDialogoDirectorAlta().getTextFecha().getText().isEmpty()) {
			this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Fecha es un campo obligatorio");
			validar = false;
		}else if (this.getDialogoDirectorAlta().getComboBoxPais().getSelectedItem().equals("--Seleccionar País--")) {
			this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Seleccione un país");
			validar = false;
		}
		return validar;
	}

	//GETTERS Y SETTERS
	public DialogoDirectorAlta getDialogoDirectorAlta() {
		return dialogoDirectorAlta;
	}

	public void setDialogoDirectorAlta(DialogoDirectorAlta dialogoDirectorAlta) {
		this.dialogoDirectorAlta = dialogoDirectorAlta;
	}

	public Director getDirector() {
		return Director;
	}

	public void setDirector(Director director) {
		Director = director;
	}

}
