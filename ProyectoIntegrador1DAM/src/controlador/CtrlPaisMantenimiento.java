package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Pais;

public class CtrlPaisMantenimiento implements ActionListener  {
	
	private Pais pais;
//	private DialogoPais dialogoPais;
	
	public CtrlPaisMantenimiento() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	// GETTERS & SETTERS
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
