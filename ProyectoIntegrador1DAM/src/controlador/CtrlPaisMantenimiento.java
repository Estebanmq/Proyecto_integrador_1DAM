package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.Conexion;
import dao.DaoPaisMantenimiento;
import modelo.Pais;
import vista.DialogoPaisMantenimiento;

public class CtrlPaisMantenimiento implements ActionListener  {
	
	private Pais pais;
	private DialogoPaisMantenimiento dialogoPaisMant;

	private DaoPaisMantenimiento daoPaisMantenimiento;

	public CtrlPaisMantenimiento() {
		
		ArrayList<Pais> arrayPais;
		
		try {

			// Instancia al DAO y obtiene el array de países
			daoPaisMantenimiento = new DaoPaisMantenimiento();

			
			arrayPais = this.getDaoPaisMantenimiento().obtenerListaPaises();
			
						// Instancia al diálogo y le pasa el array de países 
			this.setDialogoPaisMant(new DialogoPaisMantenimiento());
			this.getDialogoPaisMant().crearFilas(arrayPais);
			
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
        	try {
				Conexion.cerrar();
			} catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
		
											// Añade el listener a los botones del dialogo
		this.getDialogoPaisMant().getPanelBtnOk().getBtnOk().addActionListener(this);

											// Hace visible el dialogo
		this.getDialogoPaisMant().setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.format("Se ha pulsado: %s\n", e.getActionCommand());
		if (e.getActionCommand().equals("btnOk")) {
			this.getDialogoPaisMant().dispose();			
		}
		
	}

	// GETTERS & SETTERS
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public DialogoPaisMantenimiento getDialogoPaisMant() {
		return dialogoPaisMant;
	}

	public void setDialogoPaisMant(DialogoPaisMantenimiento dialogoPaisMant) {
		this.dialogoPaisMant = dialogoPaisMant;
	}
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
