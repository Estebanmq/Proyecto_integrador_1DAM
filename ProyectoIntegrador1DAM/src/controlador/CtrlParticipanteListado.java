package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.Conexion;
import dao.DaoDirectorMantenimiento;
import dao.DaoInterpreteMantenimiento;
import dao.DaoParticipanteListado;
import dao.DaoPaisMantenimiento;
import dao.DaoParticipanteMantenimiento;
import modelo.Director;
import modelo.FiltroParticipanteListado;
import modelo.Interprete;
import modelo.ListaParticipante;
import modelo.Pais;
import vista.DialogoParticipanteListado;
/**
 * Clase perteneciente a la capa "controlador" que maneja los objetos involucrados en el listado de participantes
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 */
public class CtrlParticipanteListado implements ActionListener, ListSelectionListener {

	/**
	 * Clase que contiene la pantalla de visualización
	 * 
	 * @see DialogoParticipanteListado
	 */
	private DialogoParticipanteListado dialogoListadoPart;

	/**
	 * Clase que contiene el acceso a datos de participantes
	 * 
	 * @see DaoParticipanteListado
	 */
	private DaoParticipanteListado daoListadoPart;
	
	/**
	 * Clase que contiene el acceso a datos de países
	 * 
	 * @see DaoPaisMantenimiento
	 */
	private DaoPaisMantenimiento daoPaisMant;
	
	/**
	 * Clase utilizada para guardar el filtro aplicado en la pantalla
	 * 
	 * @see FiltroParticipanteListado
	 */
	private FiltroParticipanteListado filtro;

	/**
	 * Relación de participantes obtenidos de BD y que tienen que ser mostrados en la pantalla
	 * 
	 * @see ListaParticipante
	 */
	private ArrayList<ListaParticipante> arrayParticipantes;
	
	/**
	 * Clase para obtener los datos de un participante 
	 */
	private DaoParticipanteMantenimiento daoParticipanteMantenimiento;

	/**
	 * Clase para obtener los datos de un Director
	 */
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	
	/**
	 * Clase para obtener los datos de un Interprete
	 */
	private DaoInterpreteMantenimiento daoInterpreteMantenimiento;

	/**
	 * Método constructor para conectar modelo-controlador-vista
	 */
	public CtrlParticipanteListado() {
		
		setArrayParticipantes(new ArrayList<ListaParticipante>());
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>();
		
		try {
			
			this.daoListadoPart = new DaoParticipanteListado(Conexion.getConexion());
			this.daoPaisMant = new DaoPaisMantenimiento();
			
			arrayPaises = this.daoPaisMant.obtenerListaPaises();

			this.setArrayParticipantes(this.daoListadoPart.obtenerListaParticipantes());
			
			this.dialogoListadoPart = new DialogoParticipanteListado();
			this.dialogoListadoPart.setArrayDatos(this.getArrayParticipantes());
			this.dialogoListadoPart.crearFilas();
			
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

	/**
	 * Método que captura los eventos ocurridos en pantalla 
	 * 
	 * @param ActionEvent e
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		switch (event.getActionCommand()) {
		
			case "btnOk" :
				this.getDialogoListadoPart().dispose();
				break;
				
			case "btnAplicarFiltros" :
				if (this.obtenerFiltro()) {
					this.obtenerParticipantes();
				}
				break;
				
			case "btnExportar" :
//				if this.getDialogoListadoPart().
				break;
				
			default :
				JOptionPane.showMessageDialog(null, "Opción no implementada.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método que captura los eventos ocurridos en la tabla mostrada en pantalla 
	 * 
	 * @param ListSelectionEvent e
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {
		
		Integer codigo = null;
		Director director = null;
		Interprete interprete = null;
		
		if (event.getValueIsAdjusting()) {			
			this.setDaoParticipanteMantenimiento(new DaoParticipanteMantenimiento());
			codigo = (Integer)this.dialogoListadoPart.getTablaParticipantes().getValueAt(this.dialogoListadoPart.getTablaParticipantes().getSelectedRow(), 0);
			
			try {
				if (this.getDaoParticipanteMantenimiento().obtenerTipoParticipante(codigo).equalsIgnoreCase("D")) {
					this.setDaoDirectorMantenimiento(new DaoDirectorMantenimiento());
					director = new Director(this.getDaoDirectorMantenimiento().obtenerDirector(codigo));
					this.getDialogoListadoPart().mostrarDirector(director);
				} else {
					this.setDaoInterpreteMantenimiento(new DaoInterpreteMantenimiento());
					interprete = new Interprete(this.getDaoInterpreteMantenimiento().obtenerInterprete(codigo));					
					this.getDialogoListadoPart().mostrarInterprete(interprete);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Método que obtiene el filtro a aplicar en la consulta, siempre y cuando pase la validación
	 * 
	 * @see FiltroParticipanteListado
	 * 
	 * @return boolean indicando si la validación de datos es correcta
	 */
	public boolean obtenerFiltro() {
		
		String validacion;
		
		setFiltro(new FiltroParticipanteListado());
		getFiltro().setDirector(this.dialogoListadoPart.getChkDirectores().isSelected());
		getFiltro().setInterprete(this.dialogoListadoPart.getChkInterpretes().isSelected());
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

	/**
	 * Método que obtiene los participantes sin aplicar ningún tipo de filtro.
	 * Éstos son cargados en la pantalla de visualización
	 * 
	 * @see ListaParticipante
	 */
	public void obtenerParticipantes() {

		try {
			this.setArrayParticipantes(this.daoListadoPart.obtenerListaParticipantes(this.filtro));
			this.dialogoListadoPart.setArrayDatos(this.getArrayParticipantes());
			this.dialogoListadoPart.crearFilas();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	// GETTERS & SETTERS
	public DialogoParticipanteListado getDialogoListadoPart() {
		return dialogoListadoPart;
	}


	public void setDialogoListadoPart(DialogoParticipanteListado dialogoListadoPart) {
		this.dialogoListadoPart = dialogoListadoPart;
	}


	public DaoParticipanteListado getDaoListadoPart() {
		return daoListadoPart;
	}


	public void setDaoListadoPart(DaoParticipanteListado daoListadoPart) {
		this.daoListadoPart = daoListadoPart;
	}

	public DaoPaisMantenimiento getDaoPaisMant() {
		return daoPaisMant;
	}

	public void setDaoPaisMant(DaoPaisMantenimiento daoPaisMant) {
		this.daoPaisMant = daoPaisMant;
	}

	public FiltroParticipanteListado getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroParticipanteListado filtro) {
		this.filtro = filtro;
	}

	public ArrayList<ListaParticipante> getArrayParticipantes() {
		return arrayParticipantes;
	}

	public void setArrayParticipantes(ArrayList<ListaParticipante> arrayParticipantes) {
		this.arrayParticipantes = arrayParticipantes;
	}

	public DaoParticipanteMantenimiento getDaoParticipanteMantenimiento() {
		return daoParticipanteMantenimiento;
	}

	public void setDaoParticipanteMantenimiento(DaoParticipanteMantenimiento daoParticipanteMantenimiento) {
		this.daoParticipanteMantenimiento = daoParticipanteMantenimiento;
	}

	public DaoDirectorMantenimiento getDaoDirectorMantenimiento() {
		return daoDirectorMantenimiento;
	}

	public void setDaoDirectorMantenimiento(DaoDirectorMantenimiento daoDirectorMantenimiento) {
		this.daoDirectorMantenimiento = daoDirectorMantenimiento;
	}

	public DaoInterpreteMantenimiento getDaoInterpreteMantenimiento() {
		return daoInterpreteMantenimiento;
	}

	public void setDaoInterpreteMantenimiento(DaoInterpreteMantenimiento daoInterpreteMantenimiento) {
		this.daoInterpreteMantenimiento = daoInterpreteMantenimiento;
	}

}
