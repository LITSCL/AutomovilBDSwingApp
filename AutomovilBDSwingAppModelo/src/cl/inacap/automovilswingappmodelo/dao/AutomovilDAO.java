package cl.inacap.automovilswingappmodelo.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cl.inacap.automovilswingappmodelo.dto.Automovil;
import cl.inacap.automovilswingappmodelo.util.BDUtil;

import java.util.ArrayList;

public class AutomovilDAO {
	private BDUtil bdUtil = new BDUtil();
	
	//Método que añade objetos de tipo Automovil a la tabla automoviles que se encuentra en la base de datos.
	public boolean save(Automovil au) {
		// Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia sql (INSERT).
			String sql = "INSERT INTO automovil" + "(patente, kilometraje, nombre_de_contacto,tipo_de_atencion, tipo_de_motor)" + " VALUES(?, ?, ?, ?, ?)"; //Los ID Autoincrementales no van aca, ya que el dbms asigna su valor.
			Connection co = bdUtil.getConexion(); //Esta instrucción retorna la conexión activa.
			PreparedStatement st = co.prepareStatement(sql); //Aca se prepara el statement.
			st.setString(1, au.getPatente());
			st.setInt(2, au.getKilometraje());
			st.setString(3, au.getNombreDeContacto());
			st.setString(4, au.getTipoDeAtencion());
			st.setString(5, au.getTipoDeMotor());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtil.desconectar(); //Envia la petición de desconexión al dbms.
		}
		return resultado;
	}
	
	//Método que trae todos los objetos de la tabla llamada automoviles que se encuentra en la base de datos.
	public List<Automovil> getAll() {
		// Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		List<Automovil> automoviles = new ArrayList<Automovil>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia sql (SELECT).
			String sql = "SELECT id, patente, kilometraje, nombre_de_contacto, tipo_de_atencion, tipo_de_motor" + " FROM automovil";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			//3. Ejecutar el SQL.
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Automovil au = new Automovil();
				au.setId(rs.getInt(1));
				au.setPatente(rs.getString(2));
				au.setKilometraje(rs.getInt(3));
				au.setNombreDeContacto(rs.getString(4));
				au.setTipoDeAtencion(rs.getString(5));
				au.setTipoDeMotor(rs.getString(6));
				automoviles.add(au); //Añade el automovil a la lista (Dicha lista es la que se muestra en la interfaz).
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			automoviles = null;
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtil.desconectar(); //Envia la petición de desconexión al dbms.
		}
		
		return automoviles;
	}
	
	//Método que trae objetos según un filtro de la tabla automoviles que se encuentra en la base de datos.
	public List<Automovil> filtrarAutomovil(String filtro) {
		
		// Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		List<Automovil> automoviles = new ArrayList<Automovil>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia sql (SELECT).
			String sql = "SELECT id, patente, kilometraje, nombre_de_contacto, tipo_de_atencion, tipo_de_motor" + " FROM automovil";
			sql+=" WHERE tipo_de_motor=" + "'" + filtro + "'";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			//3. Ejecutar el SQL.
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Automovil au=new Automovil();
				au.setId(rs.getInt(1));
				au.setPatente(rs.getString(2));
				au.setKilometraje(rs.getInt(3));
				au.setNombreDeContacto(rs.getString(4));
				au.setTipoDeAtencion(rs.getString(5));
				au.setTipoDeMotor(rs.getString(6));
				automoviles.add(au); //Añade el automovil a la lista (Dicha lista es la que se muestra en la interfaz).
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			automoviles = null;
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta se caiga o no el programa.
			bdUtil.desconectar(); //Envia la petición de desconexión al dbms.
		}
		return automoviles;
	}
	
	public void update(Automovil a) {
		// Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia sql (UPDATE).
			String sql = "UPDATE automovil SET patente = ?"+", kilometraje = ?" + ", nombre_de_contacto = ?" + ", tipo_de_atencion = ?" + ", tipo_de_motor = ?" + " WHERE id = ?"; //Lo que tiene un ? es lo que se va a modificar, todo lo demas no se toca.
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			st.setString(1, a.getPatente());
			st.setInt(2, a.getKilometraje());
			st.setString(3, a.getNombreDeContacto());
			st.setString(4, a.getTipoDeAtencion());
			st.setString(5, a.getTipoDeMotor());
			st.setInt(6, a.getId());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} catch (Exception ex) {
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta, caiga o no el programa.
			bdUtil.desconectar(); //Envia la petición de desconexión al dbms.
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
		}
		
	}
	
	//Método que Elimina objetos de la tabla automoviles que se encuentra en la base de datos.
	public void delete(Automovil a) {
		//Todos los métodos que se conecten a una base de datos deben seguir los 4 siguientes pasos:
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			this.bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia sql (INSERT).
			String sql = "DELETE FROM automoviles" + " WHERE id = ?";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); //Aca se trae la conexión y se prepara el statement.
			st.setInt(1, a.getId());
			//3. Ejecutar el SQL.
			st.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//4. Desconectarse.
		} finally { //Esta instrucción se ejecuta, caiga o no el programa.
			bdUtil.desconectar(); //Envia la petición de desconexión al dbms.
		}
	}
}
