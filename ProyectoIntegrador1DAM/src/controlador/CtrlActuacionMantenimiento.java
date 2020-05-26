package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DaoActuacionMantenimiento;
import dao.DaoInterpreteMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.ListaInterprete;
import modelo.ListaPersonaje;
import modelo.Pelicula;
import vista.DialogoActuacionMantenimiento;

public class CtrlActuacionMantenimiento implements ActionListener, ListSelectionListener {

											// Pantalla de visualización
	private DialogoActuacionMantenimiento dialogoActuacionMantenimiento;
	
											// Clases de acceso a datos
	
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	private DaoInterpreteMantenimiento daoInterpreteMantenimiento;
	private DaoActuacionMantenimiento daoActuacionMantenimiento;
	
											// Variables de clase
	private Integer codigoPelicula;
	private Pelicula pelicula;
	
	/**
	 * Método constructor del controlador del mantenimiento de actuaciones
	 */
	public CtrlActuacionMantenimiento() {
		
		this.setDialogoActuacionMantenimiento(new DialogoActuacionMantenimiento());
		
//		this.getDialogoActuacionMantenimiento().getTablaInterprete().getSelectionModel().addListSelectionListener(this);
		this.getDialogoActuacionMantenimiento().getTablaPersonaje().getSelectionModel().addListSelectionListener(this);

		this.getDialogoActuacionMantenimiento().getBtnBuscar().addActionListener(this);
		this.getDialogoActuacionMantenimiento().getBtnAdd().addActionListener(this);
		this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		this.getDialogoActuacionMantenimiento().setVisible(true);
	}

	/**
	 * Captura los eventos producidos en el diálogo
	 * 
	 * @param event tipo de evento producido  
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
		
		switch (event.getActionCommand()) {
			case "btnAceptar" :
				this.guardarDatos();
				break;
			case "btnCancelar" :
				this.getDialogoActuacionMantenimiento().dispose();
				break;
			case "btnBuscar" :
				this.validarCodigo();
				break;
			case "btnAdd" :
				this.validarNombrePersonaje();
				break;
			default :
				JOptionPane.showMessageDialog(null, "Opción no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	/**
	 * Método validador del código de película informado en pantalla  
	 */
	private void validarCodigo() {
		
		try {
			this.getDialogoActuacionMantenimiento().getFieldCodigoPelicula().setText(this.getDialogoActuacionMantenimiento().getFieldCodigoPelicula().getText().trim());
			if (this.getDialogoActuacionMantenimiento().getFieldCodigoPelicula().getText().equals("")) {
				this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El código de película es obligatorio.");			
			} else { 
				this.setCodigoPelicula(Integer.parseInt(this.getDialogoActuacionMantenimiento().getFieldCodigoPelicula().getText()));
			}
			
			this.setDaoPeliculaMantenimiento(new DaoPeliculaMantenimiento());
			
			this.setPelicula(this.getDaoPeliculaMantenimiento().buscarPeli(this.getCodigoPelicula()));
//			if (this.getPelicula()!=null) {
			if (!this.getPelicula().getTitulo().equals("Pelicula no existe")) {
				this.congigurarActuacion();
			} else {
				this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El código de película informado no existe.");				
			}
			
		} catch (NumberFormatException e) {
			this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El código de película informado no es numérico.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Método que pasa la pantalla al modo de petición de intérpretes.
	 * Obtiene todos los intérpretes de la base de datos y los carga en la tabla correspondiente.
	 * Obtiene todos los intérpretes de la película, si los hubiera. y los carga en la tabla correspondiente
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void congigurarActuacion() throws ClassNotFoundException, SQLException {
		
		Iterator<ListaPersonaje> iterador;
		
													// Muestra el titulo de la pelicula en la ventana 
		this.getDialogoActuacionMantenimiento().getFieldTitulo().setText(this.getPelicula().getTitulo());
				
													// Obtiene todos los interpretes de BD y los carga en el diálogo
		this.setDaoInterpreteMantenimiento(new DaoInterpreteMantenimiento());
		this.getDialogoActuacionMantenimiento().setListaInterpretes(new HashSet<ListaInterprete>(this.getDaoInterpreteMantenimiento().obtenerListaInterpretes()));
		
													// Obtiene los interpretes de la película y los carga en el diálogo
		this.setDaoActuacionMantenimiento(new DaoActuacionMantenimiento());
		this.getDialogoActuacionMantenimiento().setListaPersonajes(new HashSet<ListaPersonaje>(this.getDaoActuacionMantenimiento().obtenerActuaciones(this.getPelicula().getCodigo())));
		
		if (!this.getDialogoActuacionMantenimiento().getListaPersonajes().isEmpty()) {
													// Quita de la lista de interpretes los interpretes ya asignados a la pelicula
			iterador = this.getDialogoActuacionMantenimiento().getListaPersonajes().iterator();
			
			while (iterador.hasNext()) {
				Integer codigo = iterador.next().getCodigo();
				this.getDialogoActuacionMantenimiento().getListaInterpretes().removeIf(elemento -> elemento.getCodigo().equals(codigo));
			}
		}
		
		if (!this.getDialogoActuacionMantenimiento().getListaInterpretes().isEmpty()) { 
			this.getDialogoActuacionMantenimiento().cargarFilasInterpretes();
		}
		
		if (!this.getDialogoActuacionMantenimiento().getListaPersonajes().isEmpty()) { 
			this.getDialogoActuacionMantenimiento().cargarFilasPersonajes();
		}

		
													// Bloquea y desbloquea los componentes de la ventana
		this.getDialogoActuacionMantenimiento().getFieldCodigoPelicula().setEnabled(false);
		this.getDialogoActuacionMantenimiento().getBtnBuscar().setEnabled(false);
		
		this.getDialogoActuacionMantenimiento().getFieldInterpreteSelected().setEnabled(true);
		this.getDialogoActuacionMantenimiento().getFieldNombrePersonaje().setEnabled(true);
		this.getDialogoActuacionMantenimiento().getBtnAdd().setEnabled(true);
		this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getBtnAceptar().setEnabled(true);

		
	}

	/**
	 * Método que captura el evento de seleccion de fila de la tabla de 
	 * 
	 * @param event Evento se selección de fila  
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {
		
		this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");

		if (event.getValueIsAdjusting()) {
			this.eliminarPersonaje(this.getDialogoActuacionMantenimiento().getTablaPersonaje().getSelectedRow());
		}		
	}
	
	/**
	 * Método que valida el nombre de personaje informado en la ventana
	 */
	public void validarNombrePersonaje() {

		if (this.getDialogoActuacionMantenimiento().getFieldInterpreteSelected().getText().isEmpty()) {
			this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Se debe seleccionar un interprete.");
		} else if (this.getDialogoActuacionMantenimiento().getFieldNombrePersonaje().getText().isEmpty()) {
			this.getDialogoActuacionMantenimiento().getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El nombre de personaje es obligatorio.");			
		} else { 
			this.addInterpreteAPersonaje();			
		}
	}

	/**
	 * Método que añade un personaje a la tabla
	 */
	public void addInterpreteAPersonaje() {

		this.getDialogoActuacionMantenimiento().getListaPersonajes().add(new ListaPersonaje(this.getDialogoActuacionMantenimiento().getListaInterprete().getCodigo(),
				this.getDialogoActuacionMantenimiento().getListaInterprete().getNombre(),
				this.getDialogoActuacionMantenimiento().getFieldNombrePersonaje().getText()));

		this.getDialogoActuacionMantenimiento().cargarFilasPersonajes();
		
		this.getDialogoActuacionMantenimiento().getDtmModeloTablaInterprete().removeRow(this.getDialogoActuacionMantenimiento().getTablaInterprete().getSelectedRow());
		this.getDialogoActuacionMantenimiento().getFieldInterpreteSelected().setText("");
		this.getDialogoActuacionMantenimiento().getFieldNombrePersonaje().setText("");
		
	}

	/**
	 * Método que elimina el personaje seleccionado en la tabla
	 * 
	 * @param row número de fila a eliminar
	 */
	public void eliminarPersonaje(int row) {
		
		ListaInterprete listaInterprete;
		ListaPersonaje listaPersonaje;
		
		if (this.getDialogoActuacionMantenimiento().getTablaPersonaje().getSelectedRow() >= 0) {
			listaInterprete = new ListaInterprete((Integer)getDialogoActuacionMantenimiento().getTablaPersonaje().getValueAt(row, 0),
												  getDialogoActuacionMantenimiento().getTablaPersonaje().getValueAt(row, 1).toString());

			listaPersonaje = new ListaPersonaje((Integer)getDialogoActuacionMantenimiento().getTablaPersonaje().getValueAt(row, 0),
					  getDialogoActuacionMantenimiento().getTablaPersonaje().getValueAt(row, 1).toString(),
					  getDialogoActuacionMantenimiento().getTablaPersonaje().getValueAt(row, 2).toString());
			
			this.getDialogoActuacionMantenimiento().getListaInterpretes().add(listaInterprete);
			this.getDialogoActuacionMantenimiento().getListaPersonajes().removeIf(x -> listaPersonaje.getCodigo().equals(x.getCodigo()));
			this.getDialogoActuacionMantenimiento().cargarFilasInterpretes();
			this.getDialogoActuacionMantenimiento().cargarFilasPersonajes();
		}
		
		
	}
	
	/**
	 * Método para almacenar en BD los datos de Actuacion 
	 */
	public void guardarDatos() {
		
		
		this.setDaoActuacionMantenimiento(new DaoActuacionMantenimiento());
				
		try {
			
			this.getDaoActuacionMantenimiento().guardarActuaciones(this.getCodigoPelicula(), this.getDialogoActuacionMantenimiento().getListaPersonajes());
			
            JOptionPane.showMessageDialog(null, "Los datos han sido guardados.", "Transacción correcta", JOptionPane.INFORMATION_MESSAGE);
            this.getDialogoActuacionMantenimiento().dispose();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}


	// GETTERS & SETTERS
	public DialogoActuacionMantenimiento getDialogoActuacionMantenimiento() {
		return dialogoActuacionMantenimiento;
	}


	public void setDialogoActuacionMantenimiento(DialogoActuacionMantenimiento dialogoActuacionMantenimiento) {
		this.dialogoActuacionMantenimiento = dialogoActuacionMantenimiento;
	}

	public Integer getCodigoPelicula() {
		return codigoPelicula;
	}

	public void setCodigoPelicula(Integer codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}

	public DaoPeliculaMantenimiento getDaoPeliculaMantenimiento() {
		return daoPeliculaMantenimiento;
	}

	public void setDaoPeliculaMantenimiento(DaoPeliculaMantenimiento daoPeliculaMantenimiento) {
		this.daoPeliculaMantenimiento = daoPeliculaMantenimiento;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public DaoInterpreteMantenimiento getDaoInterpreteMantenimiento() {
		return daoInterpreteMantenimiento;
	}

	public void setDaoInterpreteMantenimiento(DaoInterpreteMantenimiento daoInterpreteMantenimiento) {
		this.daoInterpreteMantenimiento = daoInterpreteMantenimiento;
	}

	public DaoActuacionMantenimiento getDaoActuacionMantenimiento() {
		return daoActuacionMantenimiento;
	}

	public void setDaoActuacionMantenimiento(DaoActuacionMantenimiento daoActuacionMantenimiento) {
		this.daoActuacionMantenimiento = daoActuacionMantenimiento;
	}

}
