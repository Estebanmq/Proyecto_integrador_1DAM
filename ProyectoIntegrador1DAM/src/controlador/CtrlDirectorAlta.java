package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Director;
import vista.DialogoDirectorAlta;

public class CtrlDirectorAlta implements ActionListener {
	
	private DialogoDirectorAlta dialogoDirectorAlta;
	private Director Director;
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
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
