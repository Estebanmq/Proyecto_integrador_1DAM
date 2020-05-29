package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoInterpreteMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Interprete;
import vista.DialogoInterpreteBaja;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de interpretes y del acceso a la base de datos para borrar directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */
public class CtrlInterpreteBaja implements ActionListener{
	
	/**
	 * Ventana DialogoInterprete
	 */
	private DialogoInterpreteBaja dialogoInterpreteBaja;
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
	public CtrlInterpreteBaja() {
		
		setDaoPaisMantenimiento(new DaoPaisMantenimiento());
		daoInterpreteMantenimiento = new DaoInterpreteMantenimiento();
		
		dialogoInterpreteBaja = (new DialogoInterpreteBaja());
		dialogoInterpreteBaja.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoInterpreteBaja.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoInterpreteBaja.getBtnBuscar().addActionListener(this);
		dialogoInterpreteBaja.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "btnBuscar":
			int cod = Integer.parseInt(dialogoInterpreteBaja.getTextFieldCodigo().getText());
			try {
				Interprete in = daoInterpreteMantenimiento.obtenerInterprete(cod);
				dialogoInterpreteBaja.mostrarInterprete(in);
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnAceptar":
			//si todo ok borrar director bbdd
			try {
				daoInterpreteMantenimiento.darBajaInterprete(dialogoInterpreteBaja);
			}catch (ClassNotFoundException | SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
			dialogoInterpreteBaja.dispose();
			break;
		case "btnCancelar" :
			dialogoInterpreteBaja.dispose();
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
