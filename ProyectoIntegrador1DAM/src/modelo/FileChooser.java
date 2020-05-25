package modelo;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase para obtener la ventana de selección de ficheros
 * Utilizada en la exportación de datos a fichero
 * 
 * @author Jose Manuel de Dios
 * @since 25/05/2020
 * @version 1.0
 */
public class FileChooser {

	/**
	 * Muestra la ventana de selección de fichero desde el sistema de archivos
	 * 
	 * @return Devuelve el fichero seleccionado por el usuario o null en el caso de que pulse cancelar
	 */
	public static File escogerFichero() {

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");

		fileChooser.setFileFilter(filtro);

		if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){	 
		    return fileChooser.getSelectedFile();
		}
		
		return null;

	}

}