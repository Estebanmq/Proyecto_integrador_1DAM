package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.Conexion;
import dao.DaoListadoParticipantes;
import dao.DaoPaisMantenimiento;
import modelo.FiltroListadoParticipantes;
import modelo.ListaParticipante;
import modelo.Pais;
import vista.DialogoListadoParticipantes;

public class CtrlListadoParticipantes implements ActionListener, ListSelectionListener {

	private DialogoListadoParticipantes dialogoListadoPart;

	private DaoListadoParticipantes daoListadoPart;
	private DaoPaisMantenimiento daoPaisMant;
	
	private FiltroListadoParticipantes filtro;

	private ArrayList<ListaParticipante> arrayParticipantes;

	public CtrlListadoParticipantes() {
		
		setArrayParticipantes(new ArrayList<ListaParticipante>());
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>();
		
		try {
			
			this.daoListadoPart = new DaoListadoParticipantes(Conexion.getConexion());
			this.daoPaisMant = new DaoPaisMantenimiento();
			
			arrayPaises = this.daoPaisMant.obtenerListaPaises();

			setArrayParticipantes(this.daoListadoPart.obtenerListaParticipantes());
			
			this.dialogoListadoPart = new DialogoListadoParticipantes();
			this.dialogoListadoPart.crearFilas(getArrayParticipantes());
			
			this.dialogoListadoPart.getTablaParticipantes().getSelectionModel().addListSelectionListener(this);
			
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
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		System.out.println(this.dialogoListadoPart.getTablaParticipantes().getValueAt(this.dialogoListadoPart.getTablaParticipantes().getSelectedRow(), 0));
		
		
		
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

	public ArrayList<ListaParticipante> getArrayParticipantes() {
		return arrayParticipantes;
	}

	public void setArrayParticipantes(ArrayList<ListaParticipante> arrayParticipantes) {
		this.arrayParticipantes = arrayParticipantes;
	}

}
