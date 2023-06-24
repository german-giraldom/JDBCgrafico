package pack1_crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Interfaz extends JFrame implements ActionListener {
	
	private JFrame ventana = new JFrame();
	private Dimension dimension = new Dimension(700, 500);
	
	private JPanel panelprincipal = new JPanel();
	private JPanel panelsuperior = new JPanel();
	private JPanel panelcentral = new JPanel();
	
	private final String nombresbotones[] = {"añadir","consultar"};
	private JButton boton[] = new JButton[nombresbotones.length];
	
	private Ventana_añadir ventana_agg;
	
	private final String[] nombrescampos = {"id","nombres","cedula","programa","modificar","eliminar"};
	private Object[][] datos;
	
	// se crea una Class para indicarle los tipos que va a soportar
	private Class[] tipoColumnas = {String.class, String.class, String.class, String.class, JButton.class, JButton.class};
	
	private JTable tabla;
	protected static DefaultTableModel dtm;
	private TableColumn columna;
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Interfaz() {
		Iniciar_ventana();
		Iniciar_botones();
		Iniciar_tabla();
		ventana.setVisible(true);
	}
	
	private void Iniciar_tabla() {
		// se le indica al dtm las tipos que seran soportados
		dtm = new DefaultTableModel(datos, nombrescampos) {
			public Class getColumnClass(int indiceColumnas) {
				return tipoColumnas[indiceColumnas];
			}
		};
		
		tabla = new JTable();
		tabla.setModel(dtm);
		tabla.setEnabled(false);
		
		// para indicarle que convierta los objetos a componentes
		tabla.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
				return (Component)value;
			}
		});
		
		// cambiar el ancho de las columnas
		columna = tabla.getColumnModel().getColumn(0);
		columna.setPreferredWidth(columna.getMinWidth());
		columna = tabla.getColumnModel().getColumn(1);
		columna.setPreferredWidth(120);
		
		// centrar el texto
		//renderizar.setHorizontalAlignment(SwingConstants.CENTER);
		//for (int i = 0; i < nombrescampos.length; i++) {
			//tabla.getColumnModel().getColumn(i).setCellRenderer(renderizar);
		//}
		
		tabla.setGridColor(Color.red);
		panelcentral.add(new JScrollPane(tabla)); // para agregar desplazamiento 
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void Iniciar_botones () {
		for (byte i=0; i<nombresbotones.length; i++) {
				boton[i]= new JButton(nombresbotones[i]);
				panelsuperior.add(boton[i]);
				boton[i].addActionListener(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("añadir")) {
			ventana_agg = new Ventana_añadir();
			
		}else
			if(e.getActionCommand().equals("consultar")) {
				IniciarJDBC iniciar = new IniciarJDBC();
				iniciar.Arranque();
				dtm.setRowCount(0);
				iniciar.Consultar();
				iniciar.Cerrar();
			}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void Iniciar_ventana () {
		ventana.setTitle("registro de estudiantes");
		ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ventana.setSize(dimension);
		ventana.setLocationRelativeTo(null);
		
		panelprincipal.setBackground(Color.DARK_GRAY);
		ventana.add(panelprincipal);
		panelprincipal.setLayout(new BorderLayout());
		
		panelsuperior.setBackground(Color.blue);
		panelsuperior.setLayout(new FlowLayout());
		panelprincipal.add(panelsuperior, BorderLayout.NORTH);
		
		panelcentral.setBackground(Color.red);
		panelcentral.setLayout(new BoxLayout(panelcentral, BoxLayout.LINE_AXIS)); // esto permite que la tabla se visualice en todo el panel
		panelprincipal.add(panelcentral, BorderLayout.CENTER);
	}
}

class Add implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			if (value instanceof JButton) {
				JButton boton = (JButton)value;
				return boton;
			}
			return null;
	}
}