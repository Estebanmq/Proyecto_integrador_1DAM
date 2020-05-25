package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Director;
import modelo.Pelicula;
import vista.DialogoDirectorBaja;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de directores y del acceso a la base de datos para borrar directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */

public class CtrlDirectorBaja implements ActionListener{
	
	/**
	 * Ventana DialogoDirector
	 */
	private DialogoDirectorBaja dialogoDirectorBaja;
	/**
	 * Atributo para trabajar con los datos de directores
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para dar de baja directores
	 */
	public CtrlDirectorBaja () {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		dialogoDirectorBaja = (new DialogoDirectorBaja());
		dialogoDirectorBaja.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoDirectorBaja.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoDirectorBaja.getBtnBuscar().addActionListener(this);
		dialogoDirectorBaja.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de baja de directores
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "btnBuscar":
				int cod = Integer.parseInt(dialogoDirectorBaja.getTextFieldCodigo().getText());
				try {
					Director d = daoDirectorMantenimiento.obtenerDirector(cod);
					System.out.format("%s\n", d.toString());
					dialogoDirectorBaja.mostrarDirector(d);
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
		            i.printStackTrace();
		        }
				break;
			case "btnAceptar":
				//si todo ok borrar director bbdd
				try {
					daoDirectorMantenimiento.darBajaDirector(dialogoDirectorBaja);
				}catch (ClassNotFoundException | SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
				System.out.format("Ha pulsado: %s\n", e.getActionCommand());
				dialogoDirectorBaja.dispose();
				break;
			case "btnCancelar" :
				dialogoDirectorBaja.dispose();
				break;
		}

	}

}
