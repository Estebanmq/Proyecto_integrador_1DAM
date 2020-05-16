package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import modelo.Director;
import vista.DialogoDirectorAlta;

public class CtrlDirectorAlta implements ActionListener {
	
	private DialogoDirectorAlta dialogoDirectorAlta;
	private Director Director;
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	
	public CtrlDirectorAlta() {
		
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		this.setDialogoDirectorAlta(new DialogoDirectorAlta());
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		this.getDialogoDirectorAlta().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "btnAceptar" :
				//valida que no esten vacios los campos
				if(this.getDialogoDirectorAlta().getTextNombre().getText().isEmpty()) {
					this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Nombre es un campo obligatorio");
					break;
				}
				if(this.getDialogoDirectorAlta().getTextFecha().getText().isEmpty()) {
					this.getDialogoDirectorAlta().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Fecha es un campo obligatorio");
					break;
				}
				//crear codigo director
				try {
					daoDirectorMantenimiento.darAltaDirector(dialogoDirectorAlta);
				}catch (ClassNotFoundException | SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
				//si todo ok meter director bbdd
				System.out.format("Ha pulsado: %s\n", e.getActionCommand());
				break;
			case "btnCancelar" :
				this.getDialogoDirectorAlta().dispose();
				break;
		}
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
