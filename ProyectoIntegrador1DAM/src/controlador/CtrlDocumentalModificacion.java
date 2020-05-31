package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoDirectorMantenimiento;
import dao.DaoPaisMantenimiento;
import dao.DaoDocumentalMantenimiento;
import modelo.Director;
import modelo.GeneroDocumental;
import modelo.Pais;
import vista.DialogoDocumentalModificacion;
import modelo.Documental;


/**
 * Esta clase está dedicada al control de la interfaz gráfica de modificacion de documentales y del acceso a la base de datos para consultar y modificar documentales
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 */

public class CtrlDocumentalModificacion implements ActionListener{

		/**
		 * Ventana DialogoModificacionDocumental 
		 */
		private DialogoDocumentalModificacion dialogoModificacionDocumental;
		
		/**
		 * Atributo para trabajar con los datos de documentales
		 */
		private DaoDocumentalMantenimiento daoDocumentalMantenimiento;
		
		/**
		 * Atributo para trabajar con los datos de países  
		 */
		private DaoPaisMantenimiento daoPaisMantenimiento; 
		
		/**
		 * Atributo para trabajar con los datos de directores
		 */
		private DaoDirectorMantenimiento daoDirectorMantenimiento;
		
		/**
		 * Método constructor del control para modificar documentales
		 */
		public CtrlDocumentalModificacion() {
			
			//Creación de la ventana de modificacion de documentales
			dialogoModificacionDocumental = new DialogoDocumentalModificacion();
		
			dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getBtnAceptar().addActionListener(this);
			dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getBtnCancelar().addActionListener(this);
			dialogoModificacionDocumental.getBtnBuscar().addActionListener(this);
			
			dialogoModificacionDocumental.setVisible(true);
			
		}

		/** 
		 * Método para capturar las acciones del usuario en la interfaz gráfica de modificacion de documentales
		 * @param e ActionEvent
		 * @see java.awt.event.ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			daoDocumentalMantenimiento = new DaoDocumentalMantenimiento();
			daoPaisMantenimiento = new DaoPaisMantenimiento();
			daoDirectorMantenimiento = new DaoDirectorMantenimiento();
			
			int codDirec;
			int codPais;
			String lblCod = dialogoModificacionDocumental.getTextFieldBuscarCodigo().getText();
			Documental p;
			switch (e.getActionCommand()) {			
				case "btnBuscar":
					try {
						
						if (lblCod.equals("")) //Si el codigo del documental esta vacio muestro un mensaje de error
							dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El código no puede estar en blanco");
						else {
							dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText(""); //Vacio el mensaje de error
							p = new Documental(daoDocumentalMantenimiento.buscarDocu(Integer.parseInt(lblCod))); //Creo un objeto documental para poder mostrar posteriormente los datos. Si no se encuentra le da el titulo de documental no existe
							if (p.getTitulo().equalsIgnoreCase("Documental no existe"))  { 
								JOptionPane.showMessageDialog(null, "El documental no se ha podido encontrar en la base de datos.", "Error", JOptionPane.PLAIN_MESSAGE);
								dialogoModificacionDocumental.getPanelResultado().setVisible(false); //Oculta el panel de los datos
							} else {
								dialogoModificacionDocumental.mostrarPaises(daoPaisMantenimiento.obtenerListaPaises()); //Recuperación de todos los países almacenados en la BBDD
								dialogoModificacionDocumental.mostrarDirectores(daoDirectorMantenimiento.obtenerNombreDirectores()); //Recuperación del nombre de todos los directores almacenados en la BBDD
								dialogoModificacionDocumental.getPanelResultado().setVisible(true); //Muestra el panel de los datos
								dialogoModificacionDocumental.mostrardocumental(p); //Llamo al metodo con la documental que me devuelve la select
							}
						}
					} catch (ClassNotFoundException | SQLException i) {
			            JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.PLAIN_MESSAGE);
			            i.printStackTrace();
			        }
					break;
				case "btnAceptar":
					dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
					try {
						codDirec = daoDirectorMantenimiento.buscarCodDirector(dialogoModificacionDocumental.getComboBoxDirector().getSelectedItem().toString());
						codPais = daoPaisMantenimiento.obtenerCodPais(dialogoModificacionDocumental.getComboBoxPais().getSelectedItem().toString());
						if (!dialogoModificacionDocumental.getTextFieldTitResul().getText().equalsIgnoreCase("")) {
							if (daoDocumentalMantenimiento.actualizarDocumental(new Documental(Integer.parseInt(dialogoModificacionDocumental.getTextFieldBuscarCodigo().getText()),
									dialogoModificacionDocumental.getTextFieldTitResul().getText(),Integer.parseInt(dialogoModificacionDocumental.getTextFieldAnyoResul().getText()),
									new Director(codDirec,null,null,null,null,null),dialogoModificacionDocumental.getTextAreaSinopsisResul().getText(),new Pais(codPais,null),
									GeneroDocumental.valueOfDescripcion(dialogoModificacionDocumental.getComboBoxGenero().getSelectedItem().toString()))) != 0)
								JOptionPane.showMessageDialog(null, "Documental actualizado correctamente");
							else
								JOptionPane.showMessageDialog(null, "El documental no se ha podido actualizar", "Error", JOptionPane.PLAIN_MESSAGE);
						} else {
							dialogoModificacionDocumental.getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El título no puede estar vacío");
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					break;
				case "btnCancelar" :
					dialogoModificacionDocumental.dispose(); //Cierro la ventana si pulsa el boton de cancelar
					break;
				
				
			}
			
		}
		
		
		//GETTERS Y SETTERS
		public DialogoDocumentalModificacion getdialogoModificacionDocumental() {
			return dialogoModificacionDocumental;
		}

		public void setdialogoModificacionDocumental(DialogoDocumentalModificacion dialogoModificacionDocumental) {
			this.dialogoModificacionDocumental = dialogoModificacionDocumental;
		}


	}
