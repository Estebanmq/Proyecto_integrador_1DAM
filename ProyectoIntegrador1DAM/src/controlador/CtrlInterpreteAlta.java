package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoInterpreteMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Interprete;
import vista.DialogoInterpreteAlta;
import vista.DialogoInterpreteAlta;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de alta de directores y del acceso a la base de datos para añadir directores
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */
public class CtrlInterpreteAlta implements ActionListener{
	
	/**
	 * Ventana DialogoInterprete
	 */
	private DialogoInterpreteAlta dialogoInterpreteAlta;
	/**
	 * Objeto para director
	 */
	private Interprete interprete;
	/**
	 * Atributo para trabajar con los datos de los interpretes
	 */
	private DaoInterpreteMantenimiento daoInterpreteMantenimiento;
	/**
	 * Atributo para trabajar con los datos de países  
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Método constructor del control para dar de alta directores
	 */
	public CtrlInterpreteAlta() {
		
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoInterpreteMantenimiento = new DaoInterpreteMantenimiento();
		
		this.setDialogoInterpreteAlta(new DialogoInterpreteAlta());
		this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		try {
			this.getDialogoInterpreteAlta().mostrarPaises(daoPaisMantenimiento.obtenerListaPaises());
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		this.getDialogoInterpreteAlta().setVisible(true);
	}
	
	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de alta de los interpretes
	 * @param ActionEvent
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
				daoInterpreteMantenimiento.darAltaInterprete(dialogoInterpreteAlta);
			}catch (ClassNotFoundException | SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
			System.out.format("Ha pulsado: %s\n", e.getActionCommand());
			this.getDialogoInterpreteAlta().dispose();
			break;
		case "btnCancelar" :
			this.getDialogoInterpreteAlta().dispose();
			break;
		}	
	}
	
	/**
	 * Método para validar los campos y que no esten vacios
	 */
	private boolean validarCampos() {
		
		boolean validar = true;
		
		if(this.getDialogoInterpreteAlta().getTextNombre().getText().isEmpty()) {
			this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Nombre es un campo obligatorio");
			validar = false;
		}else if(this.getDialogoInterpreteAlta().getTextFecha().getText().isEmpty()) {
			this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Fecha es un campo obligatorio");
			validar = false;
		}else if (this.getDialogoInterpreteAlta().getComboBoxPais().getSelectedItem().equals("--Seleccionar País--")) {
			this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Seleccione un país");
			validar = false;
		}else {
			if(!dialogoInterpreteAlta.getTextCache().getText().isEmpty()) {
				try {
					Double.parseDouble(dialogoInterpreteAlta.getTextCache().getText());
				}catch(NumberFormatException e) {
					this.getDialogoInterpreteAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Para los decimales hay que usar punto");
					validar = false;
				}
			}
		}
		return validar;
	}
	
	//Gettes and Setters
	public DialogoInterpreteAlta getDialogoInterpreteAlta() {
		return dialogoInterpreteAlta;
	}
	public void setDialogoInterpreteAlta(DialogoInterpreteAlta dialogoInterpreteAlta) {
		this.dialogoInterpreteAlta = dialogoInterpreteAlta;
	}
	public Interprete getInterprete() {
		return interprete;
	}
	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}
	public DaoInterpreteMantenimiento getDaoInterpreteMantenimiento() {
		return daoInterpreteMantenimiento;
	}
	public void setDaoInterpreteMantenimiento(DaoInterpreteMantenimiento daoInterpreteMantenimiento) {
		this.daoInterpreteMantenimiento = daoInterpreteMantenimiento;
	}
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}
	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}
}

