package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Director;
import vista.DialogoDirectorConsulta;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de consulta de directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */
public class CtrlDirectorConsulta implements ActionListener{
	
	/**
	 * Ventana DialogoDirector
	 */
	private DialogoDirectorConsulta dialogoDirectorConsulta;
	/**
	 * Atributo para trabajar con los datos de directores
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para la consulta de directores
	 */

	public CtrlDirectorConsulta() {
		
		setDaoPaisMantenimiento(new DaoPaisMantenimiento());
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		dialogoDirectorConsulta = (new DialogoDirectorConsulta());
		dialogoDirectorConsulta.getPanelBtnOk().getBtnOk().addActionListener(this);
		dialogoDirectorConsulta.getBtnBuscar().addActionListener(this);
		dialogoDirectorConsulta.setVisible(true);
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
				int cod = Integer.parseInt(dialogoDirectorConsulta.getTextFieldCodigo().getText());
				try {
					Director d = daoDirectorMantenimiento.obtenerDirector(cod);
					dialogoDirectorConsulta.mostrarDirector(d);
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
		            i.printStackTrace();
		        }
				break;
			case "btnOK":
				dialogoDirectorConsulta.dispose();
				break;
		}
	}

	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}
}
