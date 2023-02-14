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
import ModeloBD_DAO.TablaJornada;
import ModeloBD_DTO.Empleado;
import ModeloBD_DTO.Historial;
import ModeloBD_DTO.Jornada;

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
 * JDialog donde mostramos la actividad de cada usuario en el chat
 *
 */
public class MostrarHistorial extends JFrame {
	static DefaultTableModel dtm;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private static TablaJornada d1 = new TablaJornada();
	private static TablaEmpleados e1 = new TablaEmpleados();
	static ArrayList<Jornada> Jornadas;
	static ArrayList<Empleado> empleados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarHistorial frame = new MostrarHistorial();
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
	public MostrarHistorial() throws SQLException {
		setUndecorated(true);
		Jornadas = d1.listarTodos();
		empleados = e1.listarTodos();
		setTitle("Historial del Chat");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		dtm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBackground(new Color(240, 240, 240));
		scrollPane.setBounds(155, 150, 558, 263);
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
		dtm.addColumn("Empleado");
		dtm.addColumn("Fecha");
		dtm.addColumn("Hora Entrada");
		dtm.addColumn("Hora Salida");

		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(289, 450, 290, 43);
		getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		comboBox.setBounds(485, 113, 232, 27);
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-25, 0, 930, 80);
		contentPane.add(panel);
		
		JLabel lblHistorialDeRegistros = new JLabel("Historial de Registros");
		lblHistorialDeRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDeRegistros.setForeground(Color.WHITE);
		lblHistorialDeRegistros.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblHistorialDeRegistros);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-45, 100, 985, 333);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnListarTodo = new JButton("Listar Todo");
		btnListarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboBox.setSelectedIndex(0);
					Jornadas = d1.listarTodos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				llenarTabla();
			}
		});
		btnListarTodo.setBounds(202, 10, 170, 33);
		btnListarTodo.setForeground(Color.WHITE);
		btnListarTodo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnListarTodo.setBackground(new Color(240, 170, 146));
		panel_1.add(btnListarTodo);
		
		for (int i = 0; i < empleados.size(); i++) {
			comboBox.addItem(empleados.get(i).getNombre());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = comboBox.getSelectedIndex();
				try {
					Jornadas.clear();
					Jornadas = d1.buscarTodos(empleados.get(pos).getId());
					llenarTabla();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		setLocationRelativeTo(null);
		llenarTabla();

	}

	/*
	 * Usando la funcion de Empresa llemos el txt mandado por el chat y llenamos la
	 * tabla con esa informacion
	 */
	public static void llenarTabla() {

		dtm.getDataVector().removeAllElements();

		for (Jornada p1 : Jornadas) {
			Object[] fila = new Object[4];
			int pos=0;
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getId() == p1.getIdEmpleadoJornada()) {
					pos=i;
				}
			}
			fila[0] = empleados.get(pos).getNombre();
			fila[1] = p1.getFecha();
			fila[2] = p1.getHora_entrada();
			fila[3] = p1.getHora_salida();

			dtm.addRow(fila);
		}
	}
}
