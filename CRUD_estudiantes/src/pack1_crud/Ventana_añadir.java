package pack1_crud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ventana_añadir extends JFrame implements FocusListener {

	private JFrame ventana = new JFrame();
	private Dimension dimension = new Dimension(350,500);
	
	
	private final String nombrescampos[] = {"id","nombres","cedula","programa"};
	private JTextField txtcampo[] = new JTextField[nombrescampos.length];
	
	
	private JButton guardar= new JButton("guardar");
	private JButton cancelar= new JButton("cancelar");
	
	private Object[] datos=new Object[nombrescampos.length];

	private short y= 10; // para darle el espacio entre campos
	
	public Ventana_añadir() {
		Iniciar_ventana();
		Iniciar_field();
		iniciar_botones ();
		ventana.setVisible(true);
		ventana.requestFocusInWindow();
	}
	
	///////////////////////////////////iniciar botones////////////////////////////////////////
	
	private void iniciar_botones () {
		
		/////////boton guardar//////////
		guardar.setBounds(50, y, 100, 50);
		guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IniciarJDBC iniciar = new IniciarJDBC();
				iniciar.Arranque();
				iniciar.Insertar(Integer.parseInt(txtcampo[0].getText()), txtcampo[1].getText(), txtcampo[2].getText());
				iniciar.Cerrar();
				ventana.setVisible(false);
			}
		});
		ventana.add(guardar);
		
		/////////boton cancelar///////////////
		cancelar.setBounds(200, y, 100, 50);
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false); //para cerrar la ventana sin finalizar todo
			}
		});
		ventana.add(cancelar);
	}
	
	///////////////////////////////////////iniciar campos////////////////////////////////////////////
	
	private void Iniciar_field () {
		for (byte i=0; i<nombrescampos.length;i++) {
			txtcampo[i] = new JTextField();
			txtcampo[i].setFont(new Font(getName(), Font.BOLD, 20));
			txtcampo[i].setText(nombrescampos[i]);
			txtcampo[i].setForeground(Color.LIGHT_GRAY);
			txtcampo[i].setBounds(50, y, 250, 50);
			txtcampo[i].addFocusListener(this); // al enfocar o hacer click en el campo ejecuta 
			ventana.add(txtcampo[i]);
			this.y+=60;
		}
	}

	@Override	// cuando esta enfocato en un campo
	public void focusGained(FocusEvent e) {
		for (byte i=0; i<nombrescampos.length;i++) {
			// solo entra si encuentra el campo donde se enfocó
			if (e.getSource()==txtcampo[i]) {
				if (txtcampo[i].getText().equals(nombrescampos[i])) {
					txtcampo[i].setText("");
				}else {
					txtcampo[i].setText(nombrescampos[i]);
				}
				txtcampo[i].setBackground(Color.LIGHT_GRAY);
				txtcampo[i].setForeground(Color.black);
				break;
			}
		}
	}

	@Override	// cuando se desenfoca del campo enfocado
	public void focusLost(FocusEvent e) {
		for (byte i=0; i<nombrescampos.length;i++) {
			//solo entra si encuentra el campo donde se desenfocó
			if (e.getSource()==txtcampo[i]) {
				txtcampo[i].setBackground(Color.white);
				break;
			}
		}
	}
	
	////////////////////////////////////iniciar ventana//////////////////////////////////////////////
	
	private void Iniciar_ventana () {
		ventana.setTitle("añadir estudiantes");
		ventana.setSize(dimension);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setLayout(null);
	}
}
