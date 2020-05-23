package crearDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Conexion;

public class CargarParticipantes {

	public static void main(String[] args) {

		File ficheroParticipantes = new File("ficheros/participantes.csv");
		File ficheroDirectores = new File("ficheros/directores.csv");
		File ficheroInterpretes = new File("ficheros/interpretes.csv");

		borrarDatos();
		cargarParticipantes(ficheroParticipantes);
		cargarDirectores(ficheroDirectores);
		cargarInterpretes(ficheroInterpretes);
	}
	
	public static void borrarDatos() {

		Connection conexion;			
		Statement st;
		PreparedStatement ps;
		int resultado;
		String query;

		try  {

			conexion = Conexion.getConexion();

			query = "delete from INTERPRETE";
			ps= conexion.prepareStatement(query);
			ps.execute();

			query = "delete from DIRECTOR";
			ps= conexion.prepareStatement(query);
			ps.execute();

			query = "delete from PARTICIPANTE";
			ps= conexion.prepareStatement(query);
			ps.execute();

			Conexion.cerrar();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void cargarParticipantes(File fichero) {

		Connection conexion;			
		Statement st;
		PreparedStatement ps;
		int resultado;
		String query;

		String linea;
		String valores[] = new String[5];

		try (BufferedReader bR = new BufferedReader(new FileReader(fichero))) {

			conexion = Conexion.getConexion();
			conexion.setAutoCommit(false);
			
			query = "insert into PARTICIPANTE values (?, ?, ?, ?, ?)";
			ps= conexion.prepareStatement(query);

			
			while ((linea = bR.readLine())!=null) {

				valores = linea.split(";");

				ps.setInt(1, Integer.parseInt(valores[0]));
				ps.setString(2, valores[1]);
				ps.setString(3, valores[2]);
				ps.setString(4, valores[3]);
				ps.setInt(5, Integer.parseInt(valores[4]));
				ps.execute();
				
			}

			conexion.commit();
			Conexion.cerrar();

		} catch (FileNotFoundException e) {
			System.out.format("Fichero no existe!\n");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void cargarDirectores(File fichero) {

		Connection conexion;			
		Statement st;
		PreparedStatement ps;
		int resultado;
		String query;

		String linea;
		String valores[] = new String[2];

		try (BufferedReader bR = new BufferedReader(new FileReader(fichero))) {

			conexion = Conexion.getConexion();
			conexion.setAutoCommit(false);
			
			query = "insert into DIRECTOR values (?, ?)";
			ps= conexion.prepareStatement(query);

			
			while ((linea = bR.readLine())!=null) {

				valores = linea.split(";");

				ps.setInt(1, Integer.parseInt(valores[0]));
				ps.setString(2, valores[1]);
				ps.execute();
				
			}

			conexion.commit();
			Conexion.cerrar();

		} catch (FileNotFoundException e) {
			System.out.format("Fichero no existe!\n");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void cargarInterpretes(File fichero) {

		Connection conexion;			
		Statement st;
		PreparedStatement ps;
		int resultado;
		String query;

		String linea;
		String valores[] = new String[2];

		try (BufferedReader bR = new BufferedReader(new FileReader(fichero))) {

			conexion = Conexion.getConexion();
			conexion.setAutoCommit(false);
			
			query = "insert into INTERPRETE values (?, ?)";
			ps= conexion.prepareStatement(query);

			
			while ((linea = bR.readLine())!=null) {

				valores = linea.split(";");

				ps.setInt(1, Integer.parseInt(valores[0]));
				ps.setDouble(2, Double.parseDouble(valores[1]));
				ps.execute();
				
			}

			conexion.commit();
			Conexion.cerrar();

		} catch (FileNotFoundException e) {
			System.out.format("Fichero no existe!\n");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
