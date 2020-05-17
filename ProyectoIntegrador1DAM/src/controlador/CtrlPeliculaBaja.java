package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoPeliculaMantenimiento;
import modelo.Pelicula;
import vista.DialogoPeliculaAlta;
import vista.DialogoPeliculaBaja;


public class CtrlPeliculaBaja implements ActionListener{

	private DialogoPeliculaBaja dialogoBajaPelicula;
	
	private DaoPaisMantenimiento daoPaisMantenimiento;
	private DaoDirectorMantenimiento daoDirectorMantenimiento;
	private DaoPeliculaMantenimiento daoPeliculaMantenimiento;
	
	public CtrlPeliculaBaja() {
		daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		
		dialogoBajaPelicula = new DialogoPeliculaBaja();
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
		dialogoBajaPelicula.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
		dialogoBajaPelicula.getBtnBuscar().addActionListener(this);
		dialogoBajaPelicula.getBtnEliminar().addActionListener(this);
//		try {
//			
//		} catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
		dialogoBajaPelicula.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		daoPaisMantenimiento = new DaoPaisMantenimiento();
		switch (e.getActionCommand()) {
		case "btnBuscar":
			daoPeliculaMantenimiento.buscarPeli(dialogoBajaPelicula.getFieldTitulo().getText());
			break;
		case "btnEliminar":
			System.out.format("%s\n", "Boton de eliminar");
			break;
		case "btnAceptar":
			System.out.format("%s\n", "Boton de aceptar");
			break;
		case "btnCancelar":
			dialogoBajaPelicula.dispose();
			break;
		}
		
	}
	
	public DialogoPeliculaBaja getDialogoBajaPelicula() {
		return dialogoBajaPelicula;
	}

	public void setDialogoAltaPelicula(DialogoPeliculaBaja dialogoBajaPelicula) {
		this.dialogoBajaPelicula = dialogoBajaPelicula;
	}
	
	public DaoPaisMantenimiento getDaoPaisMantenimiento() {
		return daoPaisMantenimiento;
	}

	public void setDaoPaisMantenimiento(DaoPaisMantenimiento daoPaisMantenimiento) {
		this.daoPaisMantenimiento = daoPaisMantenimiento;
	}

}
