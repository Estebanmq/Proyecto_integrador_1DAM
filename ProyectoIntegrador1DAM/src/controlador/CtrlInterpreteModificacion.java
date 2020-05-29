package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoInterpreteMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Interprete;
import vista.DialogoInterpreteModificacion;

public class CtrlInterpreteModificacion implements ActionListener {

	/**
	 * Ventana DialogoInterprete
	 */
	private DialogoInterpreteModificacion dialogoInterpreteModificacion;

	/**
	 * Atributo para trabajar con los datos de interprete
	 */
	private DaoInterpreteMantenimiento daoInterpreteMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para dar de alta directores
	 */
	public CtrlInterpreteModificacion() {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoInterpreteMantenimiento = new DaoInterpreteMantenimiento();
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoInterpreteMantenimiento = new DaoInterpreteMantenimiento();
		
		dialogoInterpreteModificacion = (new DialogoInterpreteModificacion());
		dialogoInterpreteModificacion.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoInterpreteModificacion.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoInterpreteModificacion.getBtnBuscar().addActionListener(this);
		try {
			dialogoInterpreteModificacion.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises());
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		dialogoInterpreteModificacion.setVisible(true);
	}
	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de modificaicon de interpretes
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "btnBuscar":
			int cod = Integer.parseInt(dialogoInterpreteModificacion.getTextFieldCodigo().getText());
			try {
				Interprete interprete = daoInterpreteMantenimiento.obtenerInterprete(cod);
				dialogoInterpreteModificacion.mostrarInterprete(interprete);
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnAceptar":
			//si todo ok modifica el director bbdd
			try {
				daoInterpreteMantenimiento.modificarInterprete(dialogoInterpreteModificacion);
			}catch (ClassNotFoundException | SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
			dialogoInterpreteModificacion.dispose();
			break;
		case "btnCancelar" :
			dialogoInterpreteModificacion.dispose();
			break;
	}
		
	}

}
