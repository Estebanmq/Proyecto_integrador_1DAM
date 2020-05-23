package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaModificacion;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de modificacion de películas y del acceso a la base de datos para consultar y modificar películas
 * @author Esteban Martínez
 * @since 23/05/2020
 * @version 1.0
 */

public class CtrlPeliculaModificacion implements ActionListener{

		/**
		 * Ventana DialogoModificacionPelicula 
		 */
		private DialogoPeliculaModificacion dialogoModificacionPelicula;
		
		/**
		 * Atributo para trabajar con los datos de peliculas
		 */
		private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
		
		/**
		 * Atributo para trabajar con los datos de países  
		 */
		private DaoPaisMantenimiento daoPaisMantenimiento; 
		
		/**
		 * Atributo para trabajar con los datos de directores
		 */
		private DaoDirectorMantenimiento daoDirectorMantenimiento;
		
		/**
		 * Método constructor del control para modificar películas
		 */
		public CtrlPeliculaModificacion() {
			
			//Creación de la ventana de modificacion de películas
			dialogoModificacionPelicula = new DialogoPeliculaModificacion();
		
			this.dialogoModificacionPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
			this.dialogoModificacionPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
			this.dialogoModificacionPelicula.getBtnBuscar().addActionListener(this);
			
			dialogoModificacionPelicula.setVisible(true);
			
		}

		/** 
		 * Método para capturar las acciones del usuario en la interfaz gráfica de modificacion de películas
		 * @param ActionEvent
		 * @see java.awt.event.ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			daoPeliculaMantenimiento = new DaoPeliculaMantenimiento();
			daoPaisMantenimiento = new DaoPaisMantenimiento();
			daoDirectorMantenimiento = new DaoDirectorMantenimiento();
			String lblCod;
			Pelicula p;
			switch (e.getActionCommand()) {			
			case "btnBuscar":
				try {
					lblCod = dialogoModificacionPelicula.getTextFieldBuscarCodigo().getText();
					if (lblCod.equals("")) //Si el codigo de la pelicula esta vacio muestro un mensaje de error
						dialogoModificacionPelicula.getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El código no puede estar en blanco");
					else {
						dialogoModificacionPelicula.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(""); //Vacio el mensaje de error
						p = new Pelicula(daoPeliculaMantenimiento.buscarPeli(Integer.parseInt(lblCod))); //Creo un objeto pelicula para poder mostrar posteriormente los datos. Si no se encuentra le da el titulo de Pelicula no existe
						if (p.getTitulo().equals("Pelicula no existe"))  { 
							JOptionPane.showMessageDialog(null, "La pelicula no se ha podido encontrar en la base de datos.", "Error", JOptionPane.PLAIN_MESSAGE);
							dialogoModificacionPelicula.getPanelResultado().setVisible(false); //Oculta el panel de los datos
						} else {
							dialogoModificacionPelicula.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises()); //Recuperación de todos los países almacenados en la BBDD
							dialogoModificacionPelicula.mostrarDirectores(daoDirectorMantenimiento.obtenerNombreDirectores()); //Recuperación del nombre de todos los directores almacenados en la BBDD
							dialogoModificacionPelicula.getPanelResultado().setVisible(true); //Muestra el panel de los datos
							dialogoModificacionPelicula.mostrarPelicula(p); //Llamo al metodo con la pelicula que me devuelve la select
						}
					}
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
		            i.printStackTrace();
		        }
				break;
			}
			
		}
		
		
		//GETTERS Y SETTERS
		public DialogoPeliculaModificacion getdialogoModificacionPelicula() {
			return dialogoModificacionPelicula;
		}

		public void setdialogoModificacionPelicula(DialogoPeliculaModificacion dialogoModificacionPelicula) {
			this.dialogoModificacionPelicula = dialogoModificacionPelicula;
		}


	}
