package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoInterpreteMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Interprete;
import vista.DialogoInterpreteConsulta;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de interpretes y del acceso a la base de datos para borrar directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */
public class CtrlInterpreteConsulta implements ActionListener{

	/**
	 * Ventana DialogoInterprete
	 */
	private DialogoInterpreteConsulta dialogoInterpreteConsulta;
	/**
	 * Atributo para trabajar con los datos de interpretes
	 */
	private DaoInterpreteMantenimiento daoInterpreteMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para dar de baja interpretes
	 */
	public CtrlInterpreteConsulta() {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoInterpreteMantenimiento = new DaoInterpreteMantenimiento();
		
		dialogoInterpreteConsulta = (new DialogoInterpreteConsulta());
		dialogoInterpreteConsulta.getPanelBtnOk().getBtnOk().addActionListener(this);
		dialogoInterpreteConsulta.getBtnBuscar().addActionListener(this);
		dialogoInterpreteConsulta.setVisible(true);
	}
	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de consultas de directores
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "btnBuscar":
			int cod = Integer.parseInt(dialogoInterpreteConsulta.getTextFieldCodigo().getText());
			try {
				Interprete in = daoInterpreteMantenimiento.obtenerInterprete(cod);
				dialogoInterpreteConsulta.mostrarInterprete(in);
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnOK":
			dialogoInterpreteConsulta.dispose();
			break;
		}
	}
}