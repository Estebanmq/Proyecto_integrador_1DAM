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
import dao.DaoDirectorMantenimiento;
import dao.DaoInterpreteMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoParticipanteListado;
import dao.DaoParticipanteMantenimiento;
import modelo.Director;
import modelo.FileChooser;
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
	private DialogoParticipanteListado dialogoParticipanteListado;

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
			
			this.dialogoParticipanteListado = new DialogoParticipanteListado();
			this.dialogoParticipanteListado.setArrayDatos(this.getArrayParticipantes());
			this.dialogoParticipanteListado.crearFilas();
			
			this.dialogoParticipanteListado.getTablaParticipantes().getSelectionModel().addListSelectionListener(this);
			
			this.dialogoParticipanteListado.getBtnAplicar().addActionListener(this);
			this.dialogoParticipanteListado.getBtnExportar().addActionListener(this);
			this.dialogoParticipanteListado.getPanelBtnOk().getBtnOk().addActionListener(this);
			
			this.dialogoParticipanteListado.cargarPaises(arrayPaises);
			
			this.dialogoParticipanteListado.setVisible(true);

			
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
				this.getDialogoParticipanteListado().dispose();
				break;
				
			case "btnAplicarFiltros" :
				if (this.obtenerFiltro()) {
					this.obtenerParticipantes();
				}
				break;
				
			case "btnExportar" :
				if (!this.getDialogoParticipanteListado().getArrayDatos().isEmpty()) {
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
		Director director = null;
		Interprete interprete = null;
		
		if (event.getValueIsAdjusting()) {			
			this.setDaoParticipanteMantenimiento(new DaoParticipanteMantenimiento());
			codigo = (Integer)this.dialogoParticipanteListado.getTablaParticipantes().getValueAt(this.dialogoParticipanteListado.getTablaParticipantes().getSelectedRow(), 0);
			
			try {
				if (this.getDaoParticipanteMantenimiento().obtenerTipoParticipante(codigo).equalsIgnoreCase("D")) {
					this.setDaoDirectorMantenimiento(new DaoDirectorMantenimiento());
					director = new Director(this.getDaoDirectorMantenimiento().obtenerDirector(codigo));
					this.getDialogoParticipanteListado().mostrarDirector(director);
				} else {
					this.setDaoInterpreteMantenimiento(new DaoInterpreteMantenimiento());
					interprete = new Interprete(this.getDaoInterpreteMantenimiento().obtenerInterprete(codigo));					
					this.getDialogoParticipanteListado().mostrarInterprete(interprete);
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
		getFiltro().setDirector(this.dialogoParticipanteListado.getChkDirectores().isSelected());
		getFiltro().setInterprete(this.dialogoParticipanteListado.getChkInterpretes().isSelected());
		getFiltro().setNombre(this.dialogoParticipanteListado.getFieldNombre().getText());
		getFiltro().setPais(((Pais)this.dialogoParticipanteListado.getComboNacionalidad().getSelectedItem()).getCodigo());
		getFiltro().setSexoFemenino(this.dialogoParticipanteListado.getTglbtnFemenino().isSelected());
		getFiltro().setSexoMasculino(this.dialogoParticipanteListado.getTglbtnMasculino().isSelected());
		if ((validacion = getFiltro().validarDatos()) != null) {
			this.dialogoParticipanteListado.getPanelBtnOk().getLabelTextoError().setText(validacion);
			return false;
		} 
		this.dialogoParticipanteListado.getPanelBtnOk().getLabelTextoError().setText("");			
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
			this.dialogoParticipanteListado.setArrayDatos(this.getArrayParticipantes());
			this.dialogoParticipanteListado.crearFilas();
			
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
		Iterator<ListaParticipante> iterador ;
		Integer codigo = 0;
		Director director = null;
		Interprete interprete = null;
		
		this.setDaoParticipanteMantenimiento(new DaoParticipanteMantenimiento());
		
		fichero = FileChooser.escogerFichero();
		
		if (fichero!=null) {
			try (BufferedWriter bW = new BufferedWriter(new FileWriter(fichero, false))) {
					
				if (fichero.exists()) {
					fichero.delete();
				}
				fichero.createNewFile();
				
				if (fichero.isFile()) {
					this.grabarCabecera(bW);
					
					iterador = this.getArrayParticipantes().iterator();
					while (iterador.hasNext()) {
						codigo = iterador.next().getCodigo();
						
						if (this.getDaoParticipanteMantenimiento().obtenerTipoParticipante(codigo).equalsIgnoreCase("D")) {
							this.setDaoDirectorMantenimiento(new DaoDirectorMantenimiento());
							director = new Director(this.getDaoDirectorMantenimiento().obtenerDirector(codigo));
							this.grabarFicheroDirector(bW, director);
						} else {
							this.setDaoInterpreteMantenimiento(new DaoInterpreteMantenimiento());
							interprete = new Interprete(this.getDaoInterpreteMantenimiento().obtenerInterprete(codigo));					
							this.grabarFicheroInterprete(bW, interprete);
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
	 * @throws IOException si no se puede grabar el fichero
	 */
	public void grabarCabecera(BufferedWriter bW) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		bW.write("\ufeff");

		joiner.add("Código");
		joiner.add("Nombre");
		joiner.add("FNacimiento");
		joiner.add("Sexo");
		joiner.add("Género/Caché");
		joiner.add("Nacionalidad");
		
		bW.write(joiner.toString());
		bW.newLine();
	}
	
	/**
	 * Graba un registro de tipo Director en el csv de salida
	 * 
	 * @param bW BufferedWriter donde debe grabar el director recibido también por parámetros
	 * @param director Director a grabar en el fichero de salida
	 * @throws IOException si no se puede grabar el fichero
	 */
	public void grabarFicheroDirector (BufferedWriter bW, Director director) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		joiner.add(director.getCodigo().toString());
		joiner.add(director.getNombre());
		joiner.add(director.getFechaNacimiento().toString());
		joiner.add(director.getSexo().getDescripcion());
		if (director.getGeneroPreferido()!=null) {
			joiner.add(director.getGeneroPreferido().getDescripcion());			
		} else {
			joiner.add("");						
		}
		joiner.add(director.getNacionalidad().getDescripcion());
		
		bW.write(joiner.toString());
		bW.newLine();
	}
	
	/**
	 * Graba un registro de tipo Interprete en el csv de salida
	 * 
	 * @param bW BufferedWriter donde debe grabar el intérprete recibido también por parámetros
	 * @param interprete Interprete a grabar en el fichero de salida
	 * @throws IOException si no se puede grabar el fichero
	 */
	public void grabarFicheroInterprete (BufferedWriter bW, Interprete interprete) throws IOException {
		
		StringJoiner joiner = new StringJoiner(";");
		
		joiner.add(interprete.getCodigo().toString());
		joiner.add(interprete.getNombre());
		joiner.add(interprete.getFechaNacimiento().toString());
		joiner.add(interprete.getSexo().getDescripcion());
		joiner.add(Double.toString(interprete.getCache()));
		joiner.add(interprete.getNacionalidad().getDescripcion());
		
		bW.write(joiner.toString());
		bW.newLine();
	}

	// GETTERS & SETTERS
	public DialogoParticipanteListado getDialogoParticipanteListado() {
		return dialogoParticipanteListado;
	}


	public void setDialogoParticipanteListado(DialogoParticipanteListado dialogoParticipanteListado) {
		this.dialogoParticipanteListado = dialogoParticipanteListado;
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
