package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Pelicula;
import vista.DialogoPeliculaAlta;

public class CtrlPeliculaAlta implements ActionListener {
	
	private Pelicula pelicula;
	
	private DialogoPeliculaAlta dialogoAltaPelicula;
	
	public CtrlPeliculaAlta() {
		
		dialogoAltaPelicula = new DialogoPeliculaAlta();
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoAltaPelicula.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "btnAceptar" :
			comprobarTitulo(); //Llamada a comprobarTitulo
			System.out.format("Ha pulsado: %s\n", e.getActionCommand());
			//pelicula.setTitulo(dialogoAltaPelicula.getFieldTitulo().getText());
			break;
		case "btnCancelar" :
			dialogoAltaPelicula.dispose();
			break;
		}
		
	}
	
	public void comprobarTitulo() {
		System.out.format("%s\n", dialogoAltaPelicula.getFieldTitulo().getText());
		if (dialogoAltaPelicula.getFieldTitulo().getText().equals("")) {
			dialogoAltaPelicula.mensajeError("El titulo no debe de estar vacio");
		} 
//		if (dialogoAltaPelicula.get) {
//			
//		}
	}

	public DialogoPeliculaAlta getDialogoAltaPelicula() {
		return dialogoAltaPelicula;
	}

	public void setDialogoAltaPelicula(DialogoPeliculaAlta dialogoAltaPelicula) {
		this.dialogoAltaPelicula = dialogoAltaPelicula;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

}
