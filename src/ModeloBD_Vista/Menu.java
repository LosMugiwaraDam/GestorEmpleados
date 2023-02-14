package ModeloBD_Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloBD_DAO.TablaEmpleados;
import ModeloBD_DAO.TablaJornada;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.UIManager;

/**
 * 
 * JFrame principal desde el cual redirigiremos al usuario a los demas apartados
 * de la aplicacion
 *
 */
public class Menu extends JFrame {
	static int id;
	private JPanel contentPane;
	Calendar calendario = Calendar.getInstance();
	private static TablaJornada j1 = new TablaJornada();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creaciondel JFrame
	 */
	public Menu() {
		setUndecorated(true);
		setTitle("Menu");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(240, 170, 146));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar eliminar;
				try {
					eliminar = new Borrar();
					eliminar.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnEliminar.setBounds(123, 251, 314, 59);
		contentPane.add(btnEliminar);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setForeground(new Color(255, 255, 255));
		btnMostrar.setBackground(new Color(240, 170, 146));
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar mostrar;
				try {
					mostrar = new Mostrar();
					mostrar.llenarTabla();
					mostrar.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnMostrar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnMostrar.setBounds(123, 331, 630, 59);
		contentPane.add(btnMostrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(240, 170, 146));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar editar;
				try {
					editar = new Editar();
					editar.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnModificar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnModificar.setBounds(447, 173, 306, 59);
		contentPane.add(btnModificar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(240, 170, 146));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar buscar;
				try {
					buscar = new Buscar();
					buscar.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnBuscar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnBuscar.setBounds(447, 251, 306, 59);
		contentPane.add(btnBuscar);

		JButton btnSalir = new JButton("Cerrar Sesion");
		btnSalir.setBackground(new Color(240, 170, 146));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int salir = JOptionPane.showConfirmDialog(null, "Desea Cerrar Sesion", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				if (salir == JOptionPane.YES_OPTION) {
					try {
						String hora = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE);
						j1.actualizar(hora, id);
						InicioSesion inicio = new InicioSesion();
						inicio.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnSalir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnSalir.setBounds(123, 411, 630, 59);
		contentPane.add(btnSalir);

		JButton btnAlta = new JButton("Alta");
		btnAlta.setBackground(new Color(240, 170, 146));
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alta alta;
				try {
					alta = new Alta();
					alta.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnAlta.setForeground(new Color(255, 255, 255));
		btnAlta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnAlta.setBounds(123, 173, 314, 59);
		contentPane.add(btnAlta);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-22, 0, 933, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menú");
		lblNewLabel.setBounds(375, 5, 158, 81);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(54, 5, 94, 70);
		lblNewLabel_1.setIcon(new ImageIcon("img/profile.png"));
		panel.add(lblNewLabel_1);
		
		JButton btnModificar_1 = new JButton("Cº");
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clima c1 = new Clima();
				c1.setVisible(true);
			}
		});
		btnModificar_1.setBounds(824, 10, 68, 62);
		panel.add(btnModificar_1);
		btnModificar_1.setForeground(new Color(0, 0, 0));
		btnModificar_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnModificar_1.setBackground(new Color(255, 245, 234));
		setLocationRelativeTo(null);

		setLocationRelativeTo(null);

	}
}
