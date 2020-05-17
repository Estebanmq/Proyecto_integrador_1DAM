package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.DaoListadoParticipantes;
import vista.DialogoListadoParticipantes;

public class CtrlListadoParticipantes implements ActionListener {

	private DialogoListadoParticipantes dialogoListadoPart;

	private DaoListadoParticipantes daoListadoPart;

	public CtrlListadoParticipantes() {
		
		

		this.dialogoListadoPart = new DialogoListadoParticipantes();
		this.dialogoListadoPart.getPanelBtnOk().getBtnOk().addActionListener(this);
		
		
		
		this.dialogoListadoPart.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.format("Se ha pulsado: %s\n", e.getActionCommand());
		
		switch (e.getActionCommand()) {
		
			case "btnOk" :
				this.getDialogoListadoPart().dispose();
				break;
				
			case "btnAplicarFiltros" :
				break;
				
			case "btnExportar" :
				break;
		
		}
		
	}

	// GETTERS & SETTERS
	public DialogoListadoParticipantes getDialogoListadoPart() {
		return dialogoListadoPart;
	}


	public void setDialogoListadoPart(DialogoListadoParticipantes dialogoListadoPart) {
		this.dialogoListadoPart = dialogoListadoPart;
	}


	public DaoListadoParticipantes getDaoListadoPart() {
		return daoListadoPart;
	}


	public void setDaoListadoPart(DaoListadoParticipantes daoListadoPart) {
		this.daoListadoPart = daoListadoPart;
	}

}
