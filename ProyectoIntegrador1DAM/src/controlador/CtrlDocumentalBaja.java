package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import dao.DaoDocumentalMantenimiento;
import modelo.Documental;
import vista.DialogoDocumentalBaja;
/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de documentales y del acceso a la base de datos para consultar y eliminar documentales
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 */
public class CtrlDocumentalBaja implements ActionListener{
	
	/**
	 * Ventana DialogoBajaDocumental 
	 */
	private DialogoDocumentalBaja dialogoBajaDocumental;

	
	/**
	 * Método constructor del control para dar de baja documentales
	 */
	public CtrlDocumentalBaja() {
		
		//Creación de la ventana de baja de documentals
		dialogoBajaDocumental = new DialogoDocumentalBaja();
		
		dialogoBajaDocumental.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoBajaDocumental.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoBajaDocumental.getBtnBuscar().addActionListener(this);
		dialogoBajaDocumental.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de baja de documentales
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoDocumentalMantenimiento daoDocumentalMantenimiento = new DaoDocumentalMantenimiento();
		switch (e.getActionCommand()) {
		case "btnBuscar":
			try {
				Documental d = new Documental(daoDocumentalMantenimiento.buscarDocu(Integer.parseInt(dialogoBajaDocumental.getTextFieldBuscarCodigo().getText()))); //Creo un objeto Documental para poder mostrar posteriormente los datos
				dialogoBajaDocumental.mostrarDocumental(d); //Llamo al metodo con el Documental que me devuelve la select
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnAceptar":
			try {
				if (daoDocumentalMantenimiento.borrarDocumental(Integer.parseInt(dialogoBajaDocumental.getTextFieldBuscarCodigo().getText())) == 2)
					JOptionPane.showMessageDialog(null, "Documental borrado correctamente"); //Creación ventana informativa
				else 
					JOptionPane.showMessageDialog(null, "Error al borrar el documental", "Error", JOptionPane.PLAIN_MESSAGE);
				dialogoBajaDocumental.dispose();
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			
			break;
		case "btnCancelar":
			dialogoBajaDocumental.dispose();
			break;
		}
		
	}
	
	public DialogoDocumentalBaja getDialogoBajaDocumental() {
		return dialogoBajaDocumental;
	}

	public void setDialogoBajaDocumental(DialogoDocumentalBaja dialogoBajaDocumental) {
		this.dialogoBajaDocumental = dialogoBajaDocumental;
	}


}
