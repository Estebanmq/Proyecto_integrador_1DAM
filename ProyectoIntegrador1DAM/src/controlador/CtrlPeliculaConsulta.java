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
	
		this.dialogoConsultaPelicula.getPanelBtnOk().getBtnOk().addActionListener(this);
		
		dialogoConsultaPelicula.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de Consulta de películas
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoPeliculaMantenimiento daoPeliculaMantenimiento = new DaoPeliculaMantenimiento();
		String lblCod;
		Pelicula p;
		switch (e.getActionCommand()) {			
			case "btnOk":
				try {
					lblCod = dialogoConsultaPelicula.getTextFieldBuscarCodigo().getText();
					if (lblCod.equals(""))
						dialogoConsultaPelicula.getPanelBtnOk().getLabelTextoError().setText("El código no puede estar en blanco");
					else {
						dialogoConsultaPelicula.getPanelBtnOk().getLabelTextoError().setText("");
						p = new Pelicula(daoPeliculaMantenimiento.buscarPeli(Integer.parseInt(lblCod))); //Creo un objeto pelicula para poder mostrar posteriormente los datos
						if (p.getTitulo().equals("Pelicula no existe"))  {
							JOptionPane.showMessageDialog(null, "La pelicula no se ha podido encontrar en la base de datos.", "Error", JOptionPane.PLAIN_MESSAGE);
							dialogoConsultaPelicula.getPanelResultado().setVisible(false);
						} else {
							dialogoConsultaPelicula.getPanelResultado().setVisible(true);
							dialogoConsultaPelicula.mostrarPelicula(p); //Llamo al metodo con la pelicula que me devuelve la select
						}
					}
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
		            i.printStackTrace();
		        }
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
