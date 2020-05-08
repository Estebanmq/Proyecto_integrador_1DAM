package crearDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Conexion;

public class cargarPaises {

	public static void main(String[] args) {

		File fichero = new File("ficheros/paises.csv");
		String linea;

		try (BufferedReader bR = new BufferedReader(new FileReader(fichero))) {
			
			while ((linea = bR.readLine())!=null) {
				cargarPais(linea);
			}
			
		} catch (FileNotFoundException e) {
			System.out.format("Fichero no existe!\n");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cargarPais(String linea) throws ClassNotFoundException, SQLException {
		
		Connection conexion;
		Statement st;
		int resultado;
		String query;
		
		String valores[] = new String[4];
		valores = linea.split(";");
		
		query = String.format("insert into pais values (%d, '%s')", Integer.parseInt(valores[2]), valores[1]);

		conexion = Conexion.conectar();
		st = conexion.createStatement();
		resultado = st.executeUpdate(query);
		System.out.format("%d filas actualizadas\n", resultado);
		Conexion.cerrar();
		
	}

}
