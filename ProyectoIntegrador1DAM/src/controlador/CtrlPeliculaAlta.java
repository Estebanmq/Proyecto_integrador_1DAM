package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaAlta;

public class CtrlPeliculaAlta implements ActionListener {
	
	private Pelicula pelicula;
	
	private DialogoPeliculaAlta dialogoAltaPelicula;
	
	private DaoPaisMantenimiento daoPaisMantenimiento;
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	
	public CtrlPeliculaAlta() {
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		dialogoAltaPelicula = new DialogoPeliculaAlta();
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		try {
			dialogoAltaPelicula.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises());
			dialogoAltaPelicula.mostrarDirectores(daoDirectorMantenimiento.obtenerNombreDirectores());
		} catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
		String mensaje="";
		System.out.format("%s\n", dialogoAltaPelicula.getFieldTitulo().getText());
		if (dialogoAltaPelicula.getFieldTitulo().getText().equals("")) {
			mensaje = "Titulo no puede estar vacio";
		} else if (dialogoAltaPelicula.getComboBoxPais().getSelectedItem().equals("--Seleccionar País--")) {
			mensaje = "Tiene que seleccionar un pais valido";
		} else if (dialogoAltaPelicula.getComboBoxDirector().getSelectedItem().equals("--Seleccionar Director--")) {
			mensaje = "Tiene que seleccionar un director valido";
		} else if (dialogoAltaPelicula.getComboBoxGenero().getSelectedItem().equals("--Seleccionar Género--")) {
			mensaje = "Tiene que seleccionar un genero valido";
		}
		dialogoAltaPelicula.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(mensaje);
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
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
