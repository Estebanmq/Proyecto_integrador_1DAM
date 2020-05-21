package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoPeliculaMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaConsulta;
/**
 * Esta clase está dedicada al control de la interfaz gráfica de consulta de películas y del acceso a la base de datos para consultar películas
 * @author Esteban Martínez
 * @since 21/05/2020
 * @version 1.0
 */
public class CtrlPeliculaConsulta implements ActionListener{
	
	/**
	 * Ventana DialogoConsultaPelicula 
	 */
	private DialogoPeliculaConsulta dialogoConsultaPelicula;
	
	/**
	 * Atributo para trabajar con los datos de peliculas
	 */
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	/**
	 * Método constructor del control para dar de consulta películas
	 */
	public CtrlPeliculaConsulta() {
		
		//Creación de la ventana de consulta de películas
		dialogoConsultaPelicula = new DialogoPeliculaConsulta();
		
		dialogoConsultaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoConsultaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		
		dialogoConsultaPelicula.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de Consulta de películas
	 * @param ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoPeliculaMantenimiento daoPeliculaMantenimiento = new DaoPeliculaMantenimiento();
		switch (e.getActionCommand()) {			
		case "btnAceptar":
			System.out.format("%s\n", "Boton de aceptar");
			try {
				Pelicula p = new Pelicula(daoPeliculaMantenimiento.buscarPeli(Integer.parseInt(dialogoConsultaPelicula.getTextFieldBuscarCodigo().getText()))); //Creo un objeto pelicula para poder mostrar posteriormente los datos
				dialogoConsultaPelicula.mostrarPelicula(p); //Llamo al metodo con la pelicula que me devuelve la select
			} catch (ClassNotFoundException | SQLException i) {
	            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
	            i.printStackTrace();
	        }
			break;
		case "btnCancelar": 
			System.out.format("%s\n", "Boton de cancelar");
			dialogoConsultaPelicula.dispose();
			break;
		}
		
	}
	
	public DialogoPeliculaConsulta getDialogoConsultaPelicula() {
		return dialogoConsultaPelicula;
	}

	public void setDialogoConsultaPelicula(DialogoPeliculaConsulta dialogoConsultaPelicula) {
		this.dialogoConsultaPelicula = dialogoConsultaPelicula;
	}


}
