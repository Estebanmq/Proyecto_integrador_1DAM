package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.DaoPaisMantenimiento;
import modelo.Pais;
import vista.DialogoPaisMantenimiento;

public class CtrlPaisMantenimiento implements ActionListener  {
	
	private Pais pais;
	private DialogoPaisMantenimiento dialogoPaisMant;

	private DaoPaisMantenimiento daoPaisMantenimiento;

	public CtrlPaisMantenimiento() {
		
		ArrayList<Pais> arrayPais;
		
											// Obtiene el array de países
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		arrayPais = this.getDaoPaisMantenimiento().obtenerListaPaises();
		
		
		this.setDialogoPaisMant(new DialogoPaisMantenimiento());
		this.getDialogoPaisMant().crearFilas(arrayPais);
		
		this.getDialogoPaisMant().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoPaisMant().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		this.getDialogoPaisMant().setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.format("Se ha pulsado: %s\n", e.getActionCommand());
		if (e.getActionCommand().equals("Ok")) {
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
