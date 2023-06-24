package pruebaArranqueBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArranqueBBDD {
	public static void main(String[] args) {
		
		Extraer ex = new Extraer();
		
		String url = "jdbc:postgresql://localhost/postgres";
		String user ="postgres", password ="1212";
		
		try {
			
			// al hacer cualquier CRUD por separado, siempre hay que conectar y desconectar la BBDD,
			// y abrir y cerrar la declaracion
			
			// coneccion a BBDD
			Connection coneccion = DriverManager.getConnection(url, user, password);
			
			// crear declaracion
			Statement st = coneccion.createStatement();
			
			//////////////////////////////////////////inserccion/////////////////////////////////////////////
			
			//st.execute("insert into persona(idpersona, nombre, cedula) values('2','german elcapo','1010')");
			
			///////////////////////////////////////////consulta//////////////////////////////////////////////
			
			// hacer la peticion
			//ResultSet rs = st.executeQuery("SELECT * FROM persona");
			
			// extraer toda la tabla
			/*
			 * while (rs.next()) { 
			 * ex.setId(rs.getString("idpersona"));
			 * ex.setNombre(rs.getString("nombre")); 
			 * ex.setCc(rs.getString("cedula"));
			 * 
			 * System.out.println(ex.toString()); }
			 */
			
			/////////////////////////////////////////actualizacion/////////////////////////////////////////
			
			//st.executeUpdate("update persona set nombre='german giraldo', idpersona='3' where cedula='1010'");
			
			/////////////////////////////////////////borrar////////////////////////////////////////////////
			
			st.executeUpdate("delete from persona where idpersona='2'");
			
			//////////////////////////////////////////////////////////////////////////////////////////////
			
			// cerrar declarador
			st.close();
			
			// cerrar conexion con BBDD
			coneccion.close();
			
			System.out.println("conexion exitosa");
		} catch (Exception e) {
			System.out.println("conexion fallida");
		}
	}
}

class Extraer {
	static String id,nombre,cc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "datos [id=" + id + ", nombre=" + nombre + ", cc=" + cc + "]";
	}
}