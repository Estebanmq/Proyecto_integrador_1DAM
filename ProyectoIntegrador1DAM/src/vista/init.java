package vista;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlador.CtrlPrincipal;

/**
 * <h1>Clase iniciadora de la aplicación</h1>
 * 
 * @author Jose Manuel
 * @version 1.0
 * @since 01/05/2020
 *
 */
public class init {

	/**
	 * Lanza la aplicación
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new CtrlPrincipal();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
