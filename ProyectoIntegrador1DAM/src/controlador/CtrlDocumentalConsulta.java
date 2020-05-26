package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDocumentalMantenimiento;
import modelo.Documental;
import vista.DialogoDocumentalConsulta;
/**
 * Esta clase está dedicada al control de la interfaz gráfica de consulta de documentales y del acceso a la base de datos para consultar documentales
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 */
public class CtrlDocumentalConsulta implements ActionListener{
	
	/**
	 * Ventana DialogoConsultadocumental 
	 */
	private DialogoDocumentalConsulta dialogoConsultaDocumental;
	
	/**
	 * Atributo para trabajar con los datos de documentales
	 */
	private DaoDocumentalMantenimiento daoDocumentalMantenimiento;
	
	/**
	 * Método constructor del control para dar de consulta documentales
	 */
	public CtrlDocumentalConsulta() {
		
		//Creación de la ventana de consulta de documentales
		dialogoConsultaDocumental = new DialogoDocumentalConsulta();
	
		this.dialogoConsultaDocumental.getPanelBtnOk().getBtnOk().addActionListener(this);
		
		dialogoConsultaDocumental.setVisible(true);
		
	}

	/** 
	 * Método para capturar las acciones del usuario en la interfaz gráfica de Consulta de documentales
	 * @param e ActionEvent
	 * @see java.awt.event.ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DaoDocumentalMantenimiento daoDocumentalMantenimiento = new DaoDocumentalMantenimiento();
		String lblCod;
		Documental p;
		switch (e.getActionCommand()) {			
			case "btnOk":
				try {
					lblCod = dialogoConsultaDocumental.getTextFieldBuscarCodigo().getText();
					if (lblCod.equals(""))
						dialogoConsultaDocumental.getPanelBtnOk().getLabelTextoError().setText("El código no puede estar en blanco");
					else {
						dialogoConsultaDocumental.getPanelBtnOk().getLabelTextoError().setText("");
						p = new Documental(daoDocumentalMantenimiento.buscarDocu(Integer.parseInt(lblCod))); //Creo un objeto documental para poder mostrar posteriormente los datos
						if (p.getTitulo().equalsIgnoreCase("Documental no existe"))  {
							JOptionPane.showMessageDialog(null, "La documental no se ha podido encontrar en la base de datos.", "Error", JOptionPane.PLAIN_MESSAGE);
							dialogoConsultaDocumental.getPanelResultado().setVisible(false);
						} else {
							dialogoConsultaDocumental.getPanelResultado().setVisible(true);
							dialogoConsultaDocumental.mostrardocumental(p); //Llamo al metodo con la documental que me devuelve la select
						}
					}
				} catch (ClassNotFoundException | SQLException i) {
		            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
		            i.printStackTrace();
		        }
				break;
		}
		
	}
	
	public DialogoDocumentalConsulta getDialogoConsultadocumental() {
		return dialogoConsultaDocumental;
	}

	public void setDialogoConsultaDocumental(DialogoDocumentalConsulta dialogoConsultaDocumental) {
		this.dialogoConsultaDocumental = dialogoConsultaDocumental;
	}


}
