package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.Conexion;
import dao.DaoListadoParticipantes;
import dao.DaoPaisMantenimiento;
import modelo.FiltroListadoParticipantes;
import modelo.ListaParticipante;
import modelo.Pais;
import vista.DialogoListadoParticipantes;

public class CtrlListadoParticipantes implements ActionListener {

	private DialogoListadoParticipantes dialogoListadoPart;

	private DaoListadoParticipantes daoListadoPart;
	private DaoPaisMantenimiento daoPaisMant;
	
	private FiltroListadoParticipantes filtro;

	public CtrlListadoParticipantes() {
		
		ArrayList<ListaParticipante> arrayParticipantes = new ArrayList<ListaParticipante>();
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>();
		
		try {
			
			this.daoListadoPart = new DaoListadoParticipantes(Conexion.getConexion());
			this.daoPaisMant = new DaoPaisMantenimiento();
			
			arrayPaises = this.daoPaisMant.obtenerListaPaises();

			arrayParticipantes = this.daoListadoPart.obtenerListaParticipantes();
			
			this.dialogoListadoPart = new DialogoListadoParticipantes();
			this.dialogoListadoPart.crearFilas(arrayParticipantes);
			
			
			this.dialogoListadoPart.getBtnAplicar().addActionListener(this);
			this.dialogoListadoPart.getBtnExportar().addActionListener(this);
			this.dialogoListadoPart.getPanelBtnOk().getBtnOk().addActionListener(this);
			
			this.dialogoListadoPart.cargarPaises(arrayPaises);
			
			this.dialogoListadoPart.setVisible(true);

			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				Conexion.cerrar();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.format("Se ha pulsado: %s\n", e.getActionCommand());
		
		switch (e.getActionCommand()) {
		
			case "btnOk" :
				this.getDialogoListadoPart().dispose();
				break;
				
			case "btnAplicarFiltros" :
				if (this.obtenerFiltro()) {
					this.obtenerParticipantes();
				}
				break;
				
			case "btnExportar" :
				break;
		
		}
		
	}
	
	public boolean obtenerFiltro() {
		
		setFiltro(new FiltroListadoParticipantes());
		String validacion;
		
		getFiltro().setDirector(this.dialogoListadoPart.getChkDirectores().isSelected());
		getFiltro().setParticipipante(this.dialogoListadoPart.getChkParticipantes().isSelected());
//		getFiltro().setEjemplar(this.dialogoListadoPart.getComboEjemplar().getSelectedItem().toString());
		getFiltro().setNombre(this.dialogoListadoPart.getFieldNombre().getText());
		getFiltro().setPais(((Pais)this.dialogoListadoPart.getComboNacionalidad().getSelectedItem()).getCodigo());
		getFiltro().setSexoFemenino(this.dialogoListadoPart.getTglbtnFemenino().isSelected());
		getFiltro().setSexoMasculino(this.dialogoListadoPart.getTglbtnMasculino().isSelected());
		if ((validacion = getFiltro().validarDatos()) != null) {
			this.dialogoListadoPart.getPanelBtnOk().getLabelTextoError().setText(validacion);
			return false;
		} 
		this.dialogoListadoPart.getPanelBtnOk().getLabelTextoError().setText("");			
		return true;
		
	}
	
	public void obtenerParticipantes() {

		ArrayList<ListaParticipante> arrayParticipantes = new ArrayList<ListaParticipante>();

		try {
			arrayParticipantes = this.daoListadoPart.obtenerListaParticipantes(this.filtro);
			
			this.dialogoListadoPart.crearFilas(arrayParticipantes);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
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

	public DaoPaisMantenimiento getDaoPaisMant() {
		return daoPaisMant;
	}

	public void setDaoPaisMant(DaoPaisMantenimiento daoPaisMant) {
		this.daoPaisMant = daoPaisMant;
	}

	public FiltroListadoParticipantes getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroListadoParticipantes filtro) {
		this.filtro = filtro;
	}

}
