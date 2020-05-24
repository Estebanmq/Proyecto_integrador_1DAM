package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Director;
import vista.DialogoDirectorBaja;
import vista.DialogoDirectorModificacion;

public class CtrlDirectorModificacion implements ActionListener{
	
	/**
	 * Ventana DialogoDirector
	 */
	private DialogoDirectorModificacion dialogoDirectorModificacion;
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
	public CtrlDirectorModificacion() {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		dialogoDirectorModificacion = (new DialogoDirectorModificacion());
		dialogoDirectorModificacion.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoDirectorModificacion.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoDirectorModificacion.getBtnBuscar().addActionListener(this);
		try {
			dialogoDirectorModificacion.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises());
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		dialogoDirectorModificacion.mostrarGeneros();
		dialogoDirectorModificacion.setVisible(true);
	}
	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de modificaicon de directores
	 * @param ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "btnBuscar":
			int cod = Integer.parseInt(dialogoDirectorModificacion.getTextFieldCodigo().getText());
			try {
				Director d = daoDirectorMantenimiento.obtenerDirector(cod);
				System.out.format("%s\n", d.toString());
				dialogoDirectorModificacion.mostrarDirector(d);
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnAceptar":
			//si todo ok modifica el director bbdd
			try {
				daoDirectorMantenimiento.modificarDirector(dialogoDirectorModificacion);
			}catch (ClassNotFoundException | SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
			System.out.format("Ha pulsado: %s\n", e.getActionCommand());
			dialogoDirectorModificacion.dispose();
			break;
		case "btnCancelar" :
			dialogoDirectorModificacion.dispose();
			break;
	}
		
	}

}
