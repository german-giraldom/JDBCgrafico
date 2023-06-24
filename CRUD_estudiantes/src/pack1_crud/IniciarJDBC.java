package pack1_crud;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class IniciarJDBC {
	
	private final String url = "jdbc:postgresql://localhost/postgres";
	private final String user ="postgres", password ="1212";
	
	private Connection coneccion;
	private Statement st;
	
	private JButton modificar = new JButton("modificar");
	private JButton borrar = new JButton("eliminar");
	
	public void Arranque () {
		try {
			coneccion = DriverManager.getConnection(url, user, password);
			st = coneccion.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "algo salio mal al arranque", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void Cerrar () {
		try {
			st.close();
			coneccion.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "algo salio mal al cerrar", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void Consultar () {
		
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM persona");
			// es mejor insertar los datos en la tabla aqui, por si hay demasiados
			while (rs.next()) {
				modificar.setBackground(Color.yellow);
				borrar.setBackground(Color.red);
				Object[] temp = {rs.getString(1),rs.getString(2),rs.getString(3),"programa", modificar,borrar};
				Interfaz.dtm.addRow(temp);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "algo salio mal en consultar", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.print(e.toString());
			Cerrar();
		}
	}
	
	
	public void Insertar (int id, String nombre, String cc) {
		try {
			st.execute("insert into persona(idpersona,nombre,cedula) values('"+id+"','"+nombre+"','"+cc+"')");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "algo salio mal en insertar", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.print(e.toString());
			Cerrar();
		}
	}
	
	public void Eliminar () {
		
	}
}