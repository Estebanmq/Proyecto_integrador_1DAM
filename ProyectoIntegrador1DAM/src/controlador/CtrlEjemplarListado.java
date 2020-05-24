package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.Conexion;
import dao.DaoDocumentalMantenimiento;
import dao.DaoEjemplarListado;
import dao.DaoEjemplarMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Documental;
import modelo.FiltroEjemplarListado;
import modelo.ListaEjemplar;
import modelo.ListaParticipante;
import modelo.Pais;
import modelo.Pelicula;
import vista.DialogoEjemplarListado;
/**
 * Clase perteneciente a la capa "controlador" que maneja los objetos involucrados en el listado de ejemplares audiovisuales
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 24/05/2020
 */
public class CtrlEjemplarListado implements ActionListener, ListSelectionListener {

	/**
	 * Clase que contiene la pantalla de visualización
	 * 
	 * @see DialogoEjemplarListado
	 */
	private DialogoEjemplarListado dialogoEjemplarListado;

	/**
	 * Clase que contiene el acceso a datos de participantes
	 * 
	 * @see DaoEjemplarListado
	 */
	private DaoEjemplarListado daoEjemplarListado;
	
	/**
	 * Clase que contiene el acceso a datos de países
	 * 
	 * @see DaoPaisMantenimiento
	 */
	private DaoPaisMantenimiento daoPaisMantenimiento;
	
	/**
	 * Clase utilizada para guardar el filtro aplicado en la pantalla
	 * 
	 * @see FiltroEjemplarListado
	 */
	private FiltroEjemplarListado filtro;

	/**
	 * Relación de participantes obtenidos de BD y que tienen que ser mostrados en la pantalla
	 * 
	 * @see ListaParticipante
	 */
	private ArrayList<ListaEjemplar> arrayEjemplares;
	
	/**
	 * Clase para obtener los datos de un ejemplar audiovisual 
	 */
	private DaoEjemplarMantenimiento daoEjemplarMantenimiento;

	/**
	 * Clase para obtener los datos de una película
	 */
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	/**
	 * Clase para obtener los datos de un Interprete
	 */
	private DaoDocumentalMantenimiento daoDocumentalMantenimiento;

	/**
	 * Método constructor para conectar modelo-controlador-vista
	 */
	public CtrlEjemplarListado() {
		
		this.setArrayEjemplares(new ArrayList<ListaEjemplar>());
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>();
		
		try {
			
			this.daoEjemplarListado = new DaoEjemplarListado(Conexion.getConexion());
			this.daoPaisMantenimiento = new DaoPaisMantenimiento();
			
			arrayPaises = this.daoPaisMantenimiento.obtenerListaPaises();

			this.setArrayEjemplares(this.daoEjemplarListado.obtenerListaEjemplares());
			
			this.setDialogoEjemplarListado(new DialogoEjemplarListado());
			this.getDialogoEjemplarListado().setArrayDatos(this.getArrayEjemplares());
			this.getDialogoEjemplarListado().crearFilas();
			
			this.getDialogoEjemplarListado().getTablaEjemplares().getSelectionModel().addListSelectionListener(this);
			
			this.getDialogoEjemplarListado().getBtnAplicar().addActionListener(this);
			this.getDialogoEjemplarListado().getBtnExportar().addActionListener(this);
			this.getDialogoEjemplarListado().getPanelBtnOk().getBtnOk().addActionListener(this);
			
			this.getDialogoEjemplarListado().cargarPaises(arrayPaises);
			
			this.getDialogoEjemplarListado().setVisible(true);

			
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
				this.getDialogoEjemplarListado().dispose();
				break;
				
			case "btnAplicarFiltros" :
				if (this.obtenerFiltro()) {
					this.obtenerEjemplares();
				}
				break;
				
			case "btnExportar" :
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
		Pelicula pelicula = null;
		Documental documental = null;
		
		if (event.getValueIsAdjusting()) {			
			this.setDaoEjemplarMantenimiento(new DaoEjemplarMantenimiento());
			codigo = (Integer)this.getDialogoEjemplarListado().getTablaEjemplares().getValueAt(this.getDialogoEjemplarListado().getTablaEjemplares().getSelectedRow(), 0);
			
			try {
				if (this.getDaoEjemplarMantenimiento().obtenerTipoEjemplar(codigo).equalsIgnoreCase("P")) {
					this.setDaoPeliculaMantenimiento(new DaoPeliculaMantenimiento());
					pelicula = new Pelicula(this.getDaoPeliculaMantenimiento().buscarPeli(codigo));
					this.getDialogoEjemplarListado().mostrarPelicula(pelicula);
				} else {
					this.setDaoDocumentalMantenimiento(new DaoDocumentalMantenimiento());
					documental = new Documental(this.getDaoDocumentalMantenimiento().buscarDocu(codigo));					
					this.getDialogoEjemplarListado().mostrarDocumental(documental);
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
	 * @see FiltroEjemplarListado
	 * 
	 * @return boolean indicando si la validación de datos es correcta
	 */
	public boolean obtenerFiltro() {
		
		String validacion;
		
		this.setFiltro(new FiltroEjemplarListado());
		this.getFiltro().setPelicula(this.getDialogoEjemplarListado().getChkPeliculas().isSelected());
		this.getFiltro().setDocumental(this.getDialogoEjemplarListado().getChkDocumentales().isSelected());
		this.getFiltro().setTitulo(this.getDialogoEjemplarListado().getFieldTitulo().getText());
		this.getFiltro().setPais(((Pais)this.getDialogoEjemplarListado().getComboNacionalidad().getSelectedItem()).getCodigo());
		this.getFiltro().setAnyo((Integer)this.getDialogoEjemplarListado().getSpinnerAnyo().getValue());
		if ((validacion = getFiltro().validarDatos()) != null) {
			this.getDialogoEjemplarListado().getPanelBtnOk().getLabelTextoError().setText(validacion);
			return false;
		} 
		this.getDialogoEjemplarListado().getPanelBtnOk().getLabelTextoError().setText("");			
		return true;
		
	}

	/**
	 * Método que obtiene los ejemplares sin aplicar ningún tipo de filtro.
	 * Éstos son cargados en la pantalla de visualización
	 * 
	 * @see ListaEjemplar
	 */
	public void obtenerEjemplares() {

		try {
			this.setArrayEjemplares(this.daoEjemplarListado.obtenerListaEjemplares(this.getFiltro()));
			this.getDialogoEjemplarListado().setArrayDatos(this.getArrayEjemplares());
			this.getDialogoEjemplarListado().crearFilas();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	// GETTERS & SETTERS

	public DaoPaisMantenimiento getDaoPaisMant() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMant(DaoPaisMantenimiento daoPaisMant) {
		this.daoPaisMantenimiento = daoPaisMant;
	}

	public FiltroEjemplarListado getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroEjemplarListado filtro) {
		this.filtro = filtro;
	}

	public ArrayList<ListaEjemplar> getArrayEjemplares() {
		return arrayEjemplares;
	}

	public void setArrayEjemplares(ArrayList<ListaEjemplar> arrayEjemplares) {
		this.arrayEjemplares = arrayEjemplares;
	}

	public DaoEjemplarMantenimiento getDaoEjemplarMantenimiento() {
		return daoEjemplarMantenimiento;
	}

	public void setDaoEjemplarMantenimiento(DaoEjemplarMantenimiento daoEjemplarMantenimiento) {
		this.daoEjemplarMantenimiento = daoEjemplarMantenimiento;
	}

	public DaoPeliculaMantenimiento getDaoPeliculaMantenimiento() {
		return daoPeliculaMantenimiento;
	}

	public void setDaoPeliculaMantenimiento(DaoPeliculaMantenimiento daoPeliculaMantenimiento) {
		this.daoPeliculaMantenimiento = daoPeliculaMantenimiento;
	}

	public DaoDocumentalMantenimiento getDaoDocumentalMantenimiento() {
		return daoDocumentalMantenimiento;
	}

	public void setDaoDocumentalMantenimiento(DaoDocumentalMantenimiento daoDocumentalMantenimiento) {
		this.daoDocumentalMantenimiento = daoDocumentalMantenimiento;
	}

	public DialogoEjemplarListado getDialogoEjemplarListado() {
		return dialogoEjemplarListado;
	}

	public void setDialogoEjemplarListado(DialogoEjemplarListado dialogoEjemplarListado) {
		this.dialogoEjemplarListado = dialogoEjemplarListado;
	}

}
