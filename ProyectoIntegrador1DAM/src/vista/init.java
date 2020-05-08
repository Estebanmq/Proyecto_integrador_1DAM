package vista;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import controlador.CtrlPrincipal;

/**
 * <h1>Clase iniciadora de la aplicación</h1>
 * 
 * @author Jose Manuel
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
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			
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
