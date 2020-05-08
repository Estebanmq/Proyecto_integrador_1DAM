package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.DaoPaisMantenimiento;
import modelo.Pais;
import vista.DialogoPaisMantenimiento;

public class CtrlPaisMantenimiento implements ActionListener  {
	
	private Pais pais;
	private DialogoPaisMantenimiento dialogoMantPais;
	private DaoPaisMantenimiento daoPaisMantenimiento;

	public CtrlPaisMantenimiento() {
		
		ArrayList<Pais> arrayPais;
		
											// Obtiene el array de países
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		arrayPais = this.getDaoPaisMantenimiento().obtenerListaPaises();

		this.getDialogoMantPais().crearFilas(arrayPais);
		
		this.setDialogoMantPais(new DialogoPaisMantenimiento());
		
		this.getDialogoMantPais().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoMantPais().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		this.getDialogoMantPais().setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.format("Ha pulsado algo: %s\n", e.getActionCommand());
		
	}

	// GETTERS & SETTERS
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public DialogoPaisMantenimiento getDialogoMantPais() {
		return dialogoMantPais;
	}

	public void setDialogoMantPais(DialogoPaisMantenimiento dialogoMantPais) {
		this.dialogoMantPais = dialogoMantPais;
	}
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
