package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaBaja;
/**
 * Esta clase está dedicada al control de la interfaz gráfica de baja de películas y del acceso a la base de datos para consultar y eliminar películas
 * @author Esteban Martínez
 * @since 16/05/2020
 * @version 1.0
 */
public class CtrlPeliculaBaja implements ActionListener{
	
	/**
	 * Ventana DialogoBajaPelicula 
	 */
	private DialogoPeliculaBaja dialogoBajaPelicula;
	
	/**
	 * Atributo para trabajar con los datos de peliculas
	 */
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	/**
	 * Método constructor del control para dar de baja películas
	 */
	public CtrlPeliculaBaja() {
		
		//Creación de la ventana de baja de películas
		dialogoBajaPelicula = new DialogoPeliculaBaja();
		
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoBajaPelicula.getBtnBuscar().addActionListener(this);
		dialogoBajaPelicula.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de baja de películas
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoPeliculaMantenimiento daoPeliculaMantenimiento = new DaoPeliculaMantenimiento();
		int cod=0;
		switch (e.getActionCommand()) {
		case "btnBuscar":
			try {
				Pelicula p = new Pelicula(daoPeliculaMantenimiento.buscarPeli(Integer.parseInt(dialogoBajaPelicula.getTextFieldBuscarCodigo().getText()))); //Creo un objeto pelicula para poder mostrar posteriormente los datos
				dialogoBajaPelicula.mostrarPelicula(p); //Llamo al metodo con la pelicula que me devuelve la select
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnAceptar":
			try {
				if (daoPeliculaMantenimiento.borrarPelicula(Integer.parseInt(dialogoBajaPelicula.getTextFieldBuscarCodigo().getText())) != 0)
					JOptionPane.showMessageDialog(null, "Pelicula borrada correctamente"); //Creación ventana informativa
				else 
					JOptionPane.showMessageDialog(null, "Error al borrar la pelicula", "Error", JOptionPane.PLAIN_MESSAGE);
				dialogoBajaPelicula.dispose();
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			
			break;
		case "btnCancelar":
			dialogoBajaPelicula.dispose();
			break;
		}
		
	}
	
	public DialogoPeliculaBaja getDialogoBajaPelicula() {
		return dialogoBajaPelicula;
	}

	public void setDialogoBajaPelicula(DialogoPeliculaBaja dialogoBajaPelicula) {
		this.dialogoBajaPelicula = dialogoBajaPelicula;
	}


}
