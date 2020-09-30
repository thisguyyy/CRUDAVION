package controller;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import config.ConexionBD;
import models.Vuelos;

public class VuelosController {
	
	// Método que devuelve todos los vuelos
	public List<Vuelos> listarVuelos(){
		
		// Variable conexion que accede al método de nuetsra clase Conexión
		ConexionBD conexion = new ConexionBD();
		
		// Variable que llama al método get_conexion()
		Connection con = conexion.get_conexion();
		
		//Variable que se inicializa para guardar la preparación de la consulta o query
		PreparedStatement stm = null;
		
		//Variable que se inicializa para guradra la ejecución dela consulta o query
		ResultSet rs = null;		
		
		// Lista donde se guardará el listaod de vuelos de la tabla Vuelos en la BD
		List<Vuelos> lista_vuelos = new ArrayList<Vuelos>();
		
		try {
			// Consulta a la BD para listar los vuelos [Listar Vuelos]
			String query = "SELECT * from vuelos ORDER BY id ASC";
			
			// Hace la llamada al métododo prepareStatement y este prepara la ejecución de la consulta
			stm = con.prepareStatement(query);
			
			// Hace la llamda al método executeQuery ejecuta la consulta y la guardamos en una variable iteradora 
			rs = stm.executeQuery();
			
			while (rs.next()) {
				
				Vuelos vuelos = new Vuelos();
								
				vuelos.setId(rs.getInt("ID"));
				vuelos.setCodigo_vuelo(rs.getString("CODIGO_VUELO"));
				vuelos.setOrigen(rs.getString("ORIGEN"));
				vuelos.setDestino(rs.getString("DESTINO"));
				vuelos.setFecha(rs.getString("FECHA"));
				vuelos.setPlazas_totales(rs.getInt("PLAZAS_TOTALES"));
				vuelos.setPlazas_disponibles(rs.getInt("PLAZAS_DISPONIBLES"));
				
				lista_vuelos.add(vuelos);	
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al listar los vuelos");
		}
		
		return lista_vuelos;
	}

	public List<Vuelos> info_vuelo(String codigo_vuelo) {

		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.get_conexion();
		PreparedStatement stm = null;
		ResultSet rs = null;	
		
		List<Vuelos> lista_vuelos = new ArrayList<Vuelos>();
		
		try {
			String query = "SELECT * from vuelos WHERE CODIGO_VUELO = " + "'" + codigo_vuelo + "'";
			
			stm = con.prepareStatement(query); 
			rs = stm.executeQuery();
			
			while (rs.next()) {
				
				Vuelos vuelos = new Vuelos();
								
				vuelos.setId(rs.getInt("ID"));
				vuelos.setCodigo_vuelo(rs.getString("CODIGO_VUELO"));
				vuelos.setOrigen(rs.getString("ORIGEN"));
				vuelos.setDestino(rs.getString("DESTINO"));
				vuelos.setFecha(rs.getString("FECHA"));
				vuelos.setPlazas_totales(rs.getInt("PLAZAS_TOTALES"));
				vuelos.setPlazas_disponibles(rs.getInt("PLAZAS_DISPONIBLES"));
				
				lista_vuelos.add(vuelos);	
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al listar los vuelos");
		}
		
		return lista_vuelos;
	}
	
public List<Vuelos> info_vuelo_by_ID(int id_vuelo) {

		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.get_conexion();
		PreparedStatement stm = null;
		ResultSet rs = null;	
		
		List<Vuelos> lista_vuelos = new ArrayList<Vuelos>();
		
		try {
			String query = "SELECT * from vuelos WHERE ID = " + "'" + id_vuelo + "'";
			
			stm = con.prepareStatement(query); 
			rs = stm.executeQuery();
			
			while (rs.next()) {
				
				Vuelos vuelos = new Vuelos();
								
				vuelos.setId(rs.getInt("ID"));
				vuelos.setCodigo_vuelo(rs.getString("CODIGO_VUELO"));
				vuelos.setOrigen(rs.getString("ORIGEN"));
				vuelos.setDestino(rs.getString("DESTINO"));
				vuelos.setFecha(rs.getString("FECHA"));
				vuelos.setPlazas_totales(rs.getInt("PLAZAS_TOTALES"));
				vuelos.setPlazas_disponibles(rs.getInt("PLAZAS_DISPONIBLES"));
				
				lista_vuelos.add(vuelos);	
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al listar los vuelos");
		}
		
		return lista_vuelos;
	}
	
	public boolean insertar_vuelo(String codigo_vuelo, String origen, String destino, String fecha, int plazas_totales, int plazas_disponibles) {
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.get_conexion();
		
		PreparedStatement stm = null;
		
		boolean insercion;
		 
		try {
			String query = "INSERT into vuelos (CODIGO_VUELO, ORIGEN, DESTINO, FECHA, PLAZAS_TOTALES, PLAZAS_DISPONIBLES) values (?,?,?,?,?,?)";
			stm = con.prepareStatement(query);
			
			stm.setString(1, codigo_vuelo);
			stm.setString(2, origen);
			stm.setString(3, destino);
			stm.setString(4, fecha);
			stm.setInt(5, plazas_totales);
			stm.setInt(6, plazas_disponibles);
			
			stm.executeUpdate();
			
			insercion = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar el libro");
			insercion = false;
		}
		
		return insercion;
		
	}
	
	public boolean eliminar_vuelo(int id) {
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.get_conexion();
		
		PreparedStatement stm = null;
		
		boolean eliminar = false;
		
		try {
			String query = "DELETE FROM vuelos WHERE ID = " + "'" + id + "'";
			stm = con.prepareStatement(query);
			stm.executeUpdate();			
			eliminar = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return eliminar;
	}
	
	
public boolean editar_vuelo(int id,String codigo_vuelo, String origen, String destino, String fecha, int plazas_totales, int plazas_disponibles) {
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.get_conexion();
		
		PreparedStatement stm = null;
		
		boolean editar;
		 
		try {
			String query = "UPDATE vuelos SET CODIGO_VUELO=?,ORIGEN=?,DESTINO=?,FECHA=?,PLAZAS_TOTALES=?,PLAZAS_DISPONIBLES=? WHERE ID = ?";
			
			stm = con.prepareStatement(query);
			
			stm.setString(1, codigo_vuelo);
			stm.setString(2, origen);
			stm.setString(3, destino);
			stm.setString(4, fecha);
			stm.setInt(5, plazas_totales);
			stm.setInt(6, plazas_disponibles);
			stm.setInt(7, id);
			
			stm.executeUpdate();
			
			editar = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al editar el vuelo");
			editar = false;
		}
		
		return editar;
		
	}
	
	
	
	
	
}
