package ModeloBD_Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ModeloBD_DAO.TablaEmpleados;
import ModeloBD_DTO.Empleado;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.SwingConstants;

/**
 * 
 * JDialog donde mostramos todos los datos de los Empleados almacenados en
 * nuestra base de datos
 *
 */
public class Mostrar extends JFrame {
	static DefaultTableModel dtm;
	private JPanel contentPane;
	private JTable table;
	private JButton btnConexionesChat;
	private static TablaEmpleados d1 = new TablaEmpleados();
	static ArrayList<Empleado> empleados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mostrar frame = new Mostrar();
					frame.setVisible(true);
					llenarTabla();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creacion del JFrame
	 * 
	 * @throws SQLException
	 */
	public Mostrar() throws SQLException {
		setUndecorated(true);
		setTitle("Registro de Empleados");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		ImageIcon img = new ImageIcon("img/Fondo.jpg");

		dtm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		empleados = d1.listarTodos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBackground(new Color(212, 187, 155));
		scrollPane.setBounds(59, 165, 753, 210);
		contentPane.add(scrollPane);

		table = new JTable();
		JTableHeader header = table.getTableHeader();
		Color marron = new Color(190, 156, 112);
		Color marron2 = new Color(212, 187, 155);
        header.setBackground(marron);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setOpaque(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBackground(marron2);
	    table.setForeground(Color.BLACK);
		table.setModel(dtm);
		dtm.addColumn("Id");
		dtm.addColumn("Nombre");
		dtm.addColumn("Contraseña");
		dtm.addColumn("Sueldo");
		dtm.addColumn("Admin");
		dtm.addColumn("Puesto");

		scrollPane.setViewportView(table);
		btnConexionesChat = new JButton("Historial Registros");
		btnConexionesChat.setForeground(new Color(255, 255, 255));
		btnConexionesChat.setBackground(new Color(240, 170, 146));
		btnConexionesChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarHistorial historial;
				try {
					historial = new MostrarHistorial();
					historial.llenarTabla();
					historial.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConexionesChat.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnConexionesChat.setBounds(59, 399, 243, 38);
		contentPane.add(btnConexionesChat);
		table.getTableHeader().setReorderingAllowed(false);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(561, 446, 251, 41);
		getContentPane().add(btnNewButton);

		/*
		 * Boton que llama a la ventana Eliminar pero con el segundo constructor
		 */
		JButton btnInsertar = new JButton("Eliminar");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = table.getSelectedRow();
					table.clearSelection();
					if (pos != -1) {
						Borrar eliminar = new Borrar(empleados.get(pos));
						eliminar.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IndexOutOfBoundsException i) {
					JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(308, 399, 243, 38);
		getContentPane().add(btnInsertar);

		/*
		 * Boton que llama a la ventana Modificar pero con el segudni contructor
		 */
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(240, 170, 146));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = table.getSelectedRow();
					table.clearSelection();
					if (pos != -1) {
						Editar modificar = new Editar(empleados.get(pos));
						modificar.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IndexOutOfBoundsException i) {
					JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnModificar.setBounds(561, 399, 251, 38);
		getContentPane().add(btnModificar);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione Opcion", "Sueldo Ascendente", "Sueldo Descendente", "Alfabeticamente" }));
		comboBox.setBounds(580, 128, 232, 27);
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-41, 0, 930, 82);
		contentPane.add(panel);
		
		JLabel lblListaDeEmpleados = new JLabel("Lista de Empleados");
		lblListaDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeEmpleados.setForeground(Color.WHITE);
		lblListaDeEmpleados.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblListaDeEmpleados);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-69, 115, 985, 274);
		contentPane.add(panel_1);
		
		JButton btnInsertar_1 = new JButton("Informe Emple.");
		btnInsertar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				JasperPrint jasperPrintWindow = null;
				try {
					jasperPrintWindow = JasperFillManager.fillReport("src/modeloBD_Vista/InformeEmpleados.jrxml", null,
							ConexionSGL.getInstancia().getCon());
				} catch (JRException e) {
					// TODO Auto-generated catch block e.printStackTrace();
					e.printStackTrace();
				}
				System.out.println();
				JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
				jasperViewer.setVisible(true);
				*/
			}
		});
		btnInsertar_1.setForeground(Color.WHITE);
		btnInsertar_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar_1.setBackground(new Color(240, 170, 146));
		btnInsertar_1.setBounds(59, 447, 243, 38);
		contentPane.add(btnInsertar_1);
		
		JButton btnConexionesChat_1 = new JButton("Informe Horario");
		btnConexionesChat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				try {
					int pos = table.getSelectedRow();
					int cod = empleados.get(pos).getId();
					JasperPrint jasperPrintWindow = null;
					try {
						jasperPrintWindow = JasperFillManager.fillReport("src/modeloBD_Vista/InicioSesion.jrxml", cod,
								ConexionSGL.getInstancia().getCon());
					} catch (JRException e) {
						// TODO Auto-generated catch block e.printStackTrace();
						e.printStackTrace();
					}
					System.out.println();
					JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
					jasperViewer.setVisible(true);
				} catch (IndexOutOfBoundsException i) {
					JOptionPane.showMessageDialog(null, "Seleccione un registro", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
				*/
			}
		});
		btnConexionesChat_1.setForeground(Color.WHITE);
		btnConexionesChat_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnConexionesChat_1.setBackground(new Color(240, 170, 146));
		btnConexionesChat_1.setBounds(309, 447, 243, 38);
		contentPane.add(btnConexionesChat_1);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = comboBox.getSelectedIndex(); 
				if (pos == 1) {
					try {
						empleados = d1.ordenarSueldoDesc();
						llenarTabla();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if (pos == 2) {
					try {
						empleados = d1.ordenarSueldoAsc();
						llenarTabla();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if (pos == 3) {
					try {
						empleados = d1.ordenarNombre();
						llenarTabla();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					try {
						empleados = d1.listarTodos();
						llenarTabla();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		setLocationRelativeTo(null);

	}

	/*
	 * Funcion para actualizar/llenar la tabla de empleados
	 */
	public static void llenarTabla() {
		dtm.getDataVector().removeAllElements();

		for (Empleado p1 : empleados) {
			Object[] fila = new Object[6];
			fila[0] = p1.getId();
			fila[1] = p1.getNombre();
			fila[2] = p1.getContraseña();
			fila[3] = p1.getSueldo();
			fila[4] = p1.getAdmin();
			if (p1.getAdmin() == 0) {
				fila[4] = "";
			} else {
				fila[4] = "Si";
			}
			fila[5] = p1.getPuesto();

			dtm.addRow(fila);
		}
	}

}
