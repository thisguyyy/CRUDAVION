package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.VuelosController;
import models.Vuelos;

public class ConexionBD {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/adat_vuelos";
	private static final String USUARIO = "root";
	private static final String PASS = "";

	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public Connection get_conexion() {

		Connection conexion = null;

		try {

			conexion = (Connection) DriverManager.getConnection(URL, USUARIO, PASS);
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;

	}

	public static void main(String[] args) {

		VuelosController lista = new VuelosController();

		System.out.println("Seleccione el método a ejecutar");
		System.out.println("1- Listar Vuelos");
		System.out.println("2- Mostrar Información de Vuelo");
		System.out.println("3- Insertar Vuelo");
		System.out.println("4- Eliminar Vuelo");
		System.out.println("5- Modificar Vuelo");
		System.out.println("6- Leer fichero");
		System.out.println("7- Exportar BD a Fichero");
		System.out.println("8- Exportar de fichero a BD");

		System.out.println("Entre el Número de Método a Ejecutar");
		Scanner scan = new Scanner(System.in);
		String next_scan = scan.next();
		int metodo = Integer.parseInt(next_scan);

		ConexionBD.seleccionar_metodo(metodo);

	}

	public static void seleccionar_metodo(int metodo) {

		switch (metodo) {
		case 1: {
			ConexionBD.listar_vuelos();
			break;
		}
		case 2: {
			ConexionBD.mostrar_info_vuelo();
			break;
		}
		case 3: {
			ConexionBD.insertar_vuelos();
			break;
		}
		case 4: {
			ConexionBD.eliminar_vuelo();
			break;
		}
		case 5: {
			ConexionBD.editar_vuelo();
			break;
		}
		case 6: {
			ConexionBD.leer_fichero();
			break;
		}
		case 7: {
			ConexionBD.bd_a_fichero();
			break;
		}
		case 8: {
			ConexionBD.fichero_a_bd();
			break;
		}
		default:
			throw new IllegalArgumentException("No existe método para ese valor");
		}
	}

	public static void listar_vuelos() {

		VuelosController lista = new VuelosController();

		List<Vuelos> lista_vuelos = lista.listarVuelos();

		Iterator<Vuelos> iterador = lista_vuelos.iterator();

		while (iterador.hasNext()) {
			Vuelos vuelos = new Vuelos();
			vuelos = iterador.next();

			System.out.println("ID:" + vuelos.getId());
			System.out.println("CÓDIGO: " + vuelos.getCodigo_vuelo());
			System.out.println("ORIGEN: " + vuelos.getOrigen());
			System.out.println("DESTINO: " + vuelos.getDestino());
			System.out.println("FECHA: " + vuelos.getFecha());
			System.out.println("PLAZAS TOTALES: " + vuelos.getPlazas_totales());
			System.out.println("PLAZAS DISPONIBLES:" + vuelos.getPlazas_disponibles());

			System.out.println(" **************************** ");

		}
	}

	public static void mostrar_info_vuelo() {

		VuelosController lista = new VuelosController();

		System.out.println("Entre el Código del Vuelo");

		Scanner entradaEscaner = new Scanner(System.in);

		List<Vuelos> lista_vuelos = lista.info_vuelo(entradaEscaner.nextLine());

		Iterator<Vuelos> iterador = lista_vuelos.iterator();

		while (iterador.hasNext()) {
			Vuelos vuelos = new Vuelos();
			vuelos = iterador.next();

			System.out.println("ID:" + vuelos.getId());
			System.out.println("CÓDIGO: " + vuelos.getCodigo_vuelo());
			System.out.println("ORIGEN: " + vuelos.getOrigen());
			System.out.println("DESTINO: " + vuelos.getDestino());
			System.out.println("FECHA: " + vuelos.getFecha());
			System.out.println("PLAZAS TOTALES: " + vuelos.getPlazas_totales());
			System.out.println("PLAZAS DISPONIBLES:" + vuelos.getPlazas_disponibles());

			System.out.println(" **************************** ");

		}
	}

	public static void insertar_vuelos() {

		VuelosController lista = new VuelosController();

		String cod_vuelo = "";
		String org_vuelo = "";
		String des_vuelo = "";
		String fec_vuelo = "";
		int pzt_vuelo = 0;
		int pzd_vuelo = 0;

		System.out.println("Entre el Código del Vuelo");
		Scanner scanner = new Scanner(System.in);

		cod_vuelo = scanner.nextLine();

		if (cod_vuelo.length() == 5) {

			List<Vuelos> lista_tamano = lista.info_vuelo(cod_vuelo);

			if (lista_tamano.size() == 0) {

				System.out.println("Entre el Origen del Vuelo");

				org_vuelo = scanner.nextLine();

				System.out.println("Entre el Destino del Vuelo");
				des_vuelo = scanner.nextLine();

				System.out.println("Entre la Fecha del Vuelo");

				fec_vuelo = scanner.nextLine();

				System.out.println("Entre la cantidad de plazas totales del Vuelo");
				String next_scan_plazas_totales = scanner.next();
				int plazas_totales = Integer.parseInt(next_scan_plazas_totales);
				pzt_vuelo = plazas_totales;

				System.out.println("Entre la cantidad de plazas disponibles del Vuelo");
				String next_scan_plazas_disponibles = scanner.next();
				int plazas_disponibles = Integer.parseInt(next_scan_plazas_disponibles);
				pzd_vuelo = plazas_disponibles;

				boolean insercion = lista.insertar_vuelo(cod_vuelo, org_vuelo, des_vuelo, fec_vuelo, pzt_vuelo,
						pzd_vuelo);

				if (insercion == true) {
					System.out.println("Vuelo insertado correctamente");
				} else {
					System.out.println("Error al insertar el vuelo. Revise sus datos por favor");
				}

			} else {
				System.out.println(
						"El vuelo ya existe, no se puede crear un vuelo con el mismo código. Revise sus datos por favor !!!");
			}
		} else {
			System.out.println("El código de vuelo debe de tener 5 Caracteres obligatoriamente !!!");
		}

	}

	public static void eliminar_vuelo() {

		System.out.println("Entre el ID del Vuelo");
		Scanner scan = new Scanner(System.in);
		String next_scan = scan.next();
		int id = Integer.parseInt(next_scan);

		VuelosController lista = new VuelosController();
		boolean eliminar = lista.eliminar_vuelo(id);

		if (eliminar == true) {
			System.out.println("Vuelo eliminado correctamente");
		} else {
			System.out.println("Error al eliminado el vuelo. Revise sus datos por favor");
		}

	}

	public static void editar_vuelo() {

		System.out.println("Entre el ID del Vuelo");
		Scanner scanner = new Scanner(System.in);
		int id_vuelo = Integer.parseInt(scanner.nextLine());

		VuelosController lista = new VuelosController();
		List<Vuelos> lista_tamano = lista.info_vuelo_by_ID(id_vuelo);

		if (lista_tamano.size() == 1) {

			System.out.println("Entre el Código del Vuelo a modificar");
			String cod_vuelo = scanner.nextLine();

			System.out.println("Entre el Origen del Vuelo a modificar");
			String org_vuelo = scanner.nextLine();

			System.out.println("Entre el Destino del Vuelo a modificar");
			String des_vuelo = scanner.nextLine();

			System.out.println("Entre la Fecha del Vuelo a modificar");
			String fec_vuelo = scanner.nextLine();

			System.out.println("Entre la cantidad de plazas totales del Vuelo a modificar");
			String next_scan_plazas_totales = scanner.next();
			int plazas_totales = Integer.parseInt(next_scan_plazas_totales);
			int pzt_vuelo = plazas_totales;

			System.out.println("Entre la cantidad de plazas disponibles del Vuelo a modificar");
			String next_scan_plazas_disponibles = scanner.next();
			int plazas_disponibles = Integer.parseInt(next_scan_plazas_disponibles);
			int pzd_vuelo = plazas_disponibles;

			boolean editar = lista.editar_vuelo(id_vuelo, cod_vuelo, org_vuelo, des_vuelo, fec_vuelo, pzt_vuelo,
					pzd_vuelo);

			if (editar == true) {
				System.out.println("Vuelo editado correctamente");
			} else {
				System.out.println("Error al editar el vuelo. Revise sus datos por favor");
			}

		} else {
			System.out.println("No existe vuelo asociado para el ID");
		}

	}

	public static void leer_fichero() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File("./src/archivo.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null)
				System.out.println(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void bd_a_fichero() {

		VuelosController lista = new VuelosController();
		List<Vuelos> lista_vuelos = lista.listarVuelos();

		Iterator<Vuelos> iterador = lista_vuelos.iterator();

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter("./src/archivo.txt");
			pw = new PrintWriter(fichero);

			while (iterador.hasNext()) {
				Vuelos vuelos = new Vuelos();
				vuelos = iterador.next();
				pw.println(vuelos.getId() + "/" + vuelos.getCodigo_vuelo() + "/" + vuelos.getOrigen() + "/"
						+ vuelos.getDestino() + "/" + vuelos.getFecha() + "/" + vuelos.getPlazas_totales() + "/"
						+ vuelos.getPlazas_disponibles());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
					System.out.println("Base de datos exportada correctamente");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
	public static void fichero_a_bd(){
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		ArrayList<String> lista_vuelos_fichero  = new ArrayList();
		
		try {
			archivo = new File("./src/archivo.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			while ((linea = br.readLine()) != null)
				lista_vuelos_fichero.add(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		for(int i = 0; i < lista_vuelos_fichero.size(); i++) {
			
			String[] parts = lista_vuelos_fichero.get(i).split("/");
			String id_vuelo = parts[0]; //id
			String codigo_vuelo = parts[1]; //cod_vuelo
			String origen_vuelo = parts[2]; //origen
			String destino_vuelo = parts[3]; //destino
			String fecha_vuelo = parts[4]; //fecha
			int plazas_totales = Integer.parseInt(parts[5]); //plazas totales
			int plazas_disponibles = Integer.parseInt(parts[6]); // plazas disponibles
			
			VuelosController insertarVuelo = new VuelosController();
			insertarVuelo.insertar_vuelo(codigo_vuelo, origen_vuelo, destino_vuelo, fecha_vuelo, plazas_totales, plazas_disponibles);
			
		}
		System.out.println("Nuevos vuelos insertados en la BD");
	}
}
