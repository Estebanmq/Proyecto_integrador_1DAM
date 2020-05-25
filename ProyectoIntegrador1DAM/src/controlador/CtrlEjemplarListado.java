package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

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
import modelo.FileChooser;
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
	 * @param event ActionEvent
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
				if (!this.getDialogoEjemplarListado().getArrayDatos().isEmpty()) {
					this.exportarFichero();
				} else {					
					JOptionPane.showMessageDialog(null, "No existen datos a exportar.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			default :
				JOptionPane.showMessageDialog(null, "Opción no implementada.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método que captura los eventos ocurridos en la tabla mostrada en pantalla 
	 * 
	 * @param event ListSelectionEvent
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
	
	
	/**
	 * Realiza la exportación de los datos visualizados en la tabla a un fichero csv
	 * 
	 * @return boolean indicando si se ha podido ejecutar la exportación del fichero
	 */
	public boolean exportarFichero() {
		
		File fichero;
		Iterator<ListaEjemplar> iterador ;
		Integer codigo = 0;
		Pelicula pelicula = null;
		Documental documental = null;
		
		this.setDaoEjemplarMantenimiento(new DaoEjemplarMantenimiento());
		
		fichero = FileChooser.escogerFichero();
		
		if (fichero!=null) {
			try (BufferedWriter bW = new BufferedWriter(new FileWriter(fichero, false))) {
					
				if (fichero.exists()) {
					fichero.delete();
				}
				fichero.createNewFile();
				
				if (fichero.isFile()) {
					this.grabarCabecera(bW);
					
					iterador = this.getArrayEjemplares().iterator();
					while (iterador.hasNext()) {
						codigo = iterador.next().getCodigo();
						
						if (this.getDaoEjemplarMantenimiento().obtenerTipoEjemplar(codigo).equalsIgnoreCase("P")) {
							this.setDaoPeliculaMantenimiento(new DaoPeliculaMantenimiento());
							pelicula = new Pelicula(this.getDaoPeliculaMantenimiento().buscarPeli(codigo));
							this.grabarFicheroPelicula(bW, pelicula);
						} else {
							this.setDaoDocumentalMantenimiento(new DaoDocumentalMantenimiento());
							documental = new Documental(this.getDaoDocumentalMantenimiento().buscarDocu(codigo));					
							this.grabarFicheroDocumental(bW, documental);
						}
					}
					
					JOptionPane.showMessageDialog(null, "Fichero generado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(null, "No es posible generar el fichero.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Se ha producido un error al grabar el fichero.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return false;
		
	}
	
	/**
	 * Graba la cabecera de las columnas en el fichero csv a exportar  
	 * 
	 * @param bW BufferedWriter con el fichero de salida donde debe grabar la cabecera de datos 
	 * @throws IOException
	 */
	public void grabarCabecera(BufferedWriter bW) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		bW.write("\ufeff");

		joiner.add("Código");
		joiner.add("Título");
		joiner.add("Año");
		joiner.add("Director");
		joiner.add("Género");
		joiner.add("Nacionalidad");
		
		bW.write(joiner.toString());
		bW.newLine();
	}

	/**
	 * Graba un registro de tipo Director en el csv de salida
	 * 
	 * @param bW BufferedWriter con el fichero de salida
	 * @param pelicula Pelicula a grabar
	 * @throws IOException
	 */
	public void grabarFicheroPelicula (BufferedWriter bW, Pelicula pelicula) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		joiner.add(pelicula.getCodigo().toString());
		joiner.add(pelicula.getTitulo());
		joiner.add(Integer.toString(pelicula.getAnyo()));
		joiner.add(pelicula.getDirector().getNombre());
		if (pelicula.getGenero()!=null) {
			joiner.add(pelicula.getGenero().getDescripcion());			
		} else {
			joiner.add("");						
		}
		joiner.add(pelicula.getNacionalidad().getDescripcion());
		
		bW.write(joiner.toString());
		bW.newLine();
	}
	
	/**
	 * Graba un registro de tipo Interprete en el csv de salida
	 * 
	 * @param bW BufferedWriter donde debe grabar el intérprete recibido también por parámetros
	 * @param documental Documental a grabar en el fichero de salida
	 * @throws IOException
	 */
	public void grabarFicheroDocumental (BufferedWriter bW, Documental documental) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		joiner.add(documental.getCodigo().toString());
		joiner.add(documental.getTitulo());
		joiner.add(Integer.toString(documental.getAnyo()));
		joiner.add(documental.getDirector().getNombre());
		if (documental.getGenero()!=null) {
			joiner.add(documental.getGenero().getDescripcion());			
		} else {
			joiner.add("");						
		}
		joiner.add(documental.getNacionalidad().getDescripcion());
		
		bW.write(joiner.toString());
		bW.newLine();
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
