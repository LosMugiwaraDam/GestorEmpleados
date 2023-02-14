package ModeloBD_Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloBD_DAO.TablaEmpleados;
import ModeloBD_DAO.TablaJornada;
import ModeloBD_DTO.Empleado;
import ModeloBD_DTO.Jornada;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.awt.event.ActionEvent;
/**
 * 
 * JFrame mediante el cual podemos iniciar sesion en nuestra aplicacion
 *
 */
public class InicioSesion extends JFrame {
	private static TablaEmpleados d1 = new TablaEmpleados();
	static ArrayList<Empleado> empleados;
	Calendar calendario = Calendar.getInstance();
	int code;
	private JPanel contentPane;
	private JTextField tfusuario;
	private JTextField tfcontra;
	private static TablaJornada j1 = new TablaJornada();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creacion del JFrame
	 * @throws SQLException 
	 */
	public InicioSesion() throws SQLException {
		setUndecorated(true);
		empleados = d1.listarTodos();
		setTitle("Inicio de Sesion");
		setBackground(new Color(255, 250, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 672);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfusuario = new JTextField();
		tfusuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		tfusuario.setBounds(139, 217, 211, 41);
		contentPane.add(tfusuario);
		tfusuario.setColumns(10);

		tfcontra = new JTextField();
		tfcontra.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		tfcontra.setColumns(10);
		tfcontra.setBounds(138, 324, 211, 41);
		contentPane.add(tfcontra);
		
		
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);

		JButton btnNewButton = new JButton("Inicio Sesion");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean entrar = false;
				for (Empleado p1 : empleados) {
					if(p1.getNombre().equals(tfusuario.getText()) && p1.getContraseña().equals(tfcontra.getText())){
						code = p1.getId();
						entrar=true;
					}
				}
				if(entrar==true) {
					JOptionPane.showMessageDialog(null, "Inicio de Sesion Correcto", "Bienvenido",
							JOptionPane.INFORMATION_MESSAGE);
					Menu menu = new Menu();
					String hora = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE);
					String fecha = calendario.get(Calendar.DATE) + "/" + (calendario.get(Calendar.MONTH)+1) + "/" + calendario.get(Calendar.YEAR);
					Jornada j = new Jornada(0,code,fecha,hora,"0");
					try {
						j1.insertar(j);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						Menu.id = j1.buscarId();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					menu.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(115, 479, 268, 59);
		contentPane.add(btnNewButton);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(new Color(240, 170, 146));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int salir = JOptionPane.showConfirmDialog(null, "Desea salir de la aplicacion", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				if (salir == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnSalir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnSalir.setBounds(115, 550, 268, 59);
		contentPane.add(btnSalir);

		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(87, 173, 308, 28);
		contentPane.add(lblNewLabel);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblContrasea.setBounds(87, 286, 308, 28);
		contentPane.add(lblContrasea);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-34, 0, 585, 82);
		contentPane.add(panel);
		
		JLabel lblListaDeEmpleados = new JLabel("Inicio Sesion");
		lblListaDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeEmpleados.setForeground(Color.WHITE);
		lblListaDeEmpleados.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblListaDeEmpleados);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-368, 141, 985, 274);
		contentPane.add(panel_1);

		setLocationRelativeTo(null);
	}
}
