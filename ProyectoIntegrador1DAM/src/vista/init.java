package vista;

import java.awt.EventQueue;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal principal = new Principal();
					principal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
