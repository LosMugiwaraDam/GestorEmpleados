package ModeloBD_Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloBD_DAO.TablaEmpleados;
import ModeloBD_DAO.TablaPuestos;
import ModeloBD_DTO.Empleado;
import ModeloBD_DTO.puesto;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * 
 * JDialog en el que realizamos las Bajas de Empleados
 *
 */
public class Borrar extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static TablaPuestos p1 = new TablaPuestos();
	private static TablaEmpleados e1 = new TablaEmpleados();
	Empleado x1;
	static ArrayList<puesto> puestos;

	public static void main(String[] args) {
		try {
			Borrar dialog = new Borrar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Primer constructor al cual llamamos desde el Menu y aparecen las opciones de
	 * busca Empleado
	 * 
	 * @throws SQLException
	 */
	public Borrar() throws SQLException {
		setUndecorated(true);
		puestos = p1.listarTodos();
		getContentPane().setBackground(new Color(255, 250, 240));
		setTitle("Eliminar");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		setBounds(100, 100, 880, 522);

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		textField = new JTextField();
		textField.setEditable(false);
		textField.setOpaque(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(203, 252, 175, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setOpaque(false);
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(203, 183, 175, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setOpaque(false);
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(203, 314, 175, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		comboBox.setBounds(509, 183, 246, 46);
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}
		getContentPane().add(comboBox);

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setEnabled(false);
		checkAdmin.setOpaque(false);
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(611, 314, 49, 46);
		getContentPane().add(checkAdmin);

		JButton btnInsertar = new JButton("Eliminar");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x1 == null) {
					JOptionPane.showMessageDialog(null, "Seleccione un Empleado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int borrar = JOptionPane.showConfirmDialog(null, "Desea Eliminar el Empleado", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					if (borrar == JOptionPane.YES_OPTION) {
						try {
							JOptionPane.showMessageDialog(null, "Empleado Eliminado con Exito", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							e1.borrar(x1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}

			}

		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(157, 405, 268, 59);
		getContentPane().add(btnInsertar);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(469, 405, 268, 59);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(240, 170, 146));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textField_3.getText());
				try {
					x1 = e1.buscar(id);
					textField_1.setText(x1.getNombre());
					textField.setText(x1.getContraseña());
					textField_2.setText("" + x1.getSueldo());
					comboBox.setSelectedIndex(x1.getPuesto() - 1);
					if (x1.getAdmin() == 1) {
						checkAdmin.setSelected(true);
					} else {
						checkAdmin.setSelected(false);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "No existe ningun empleado con ese codigo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btnNewButton_1.setBounds(753, 124, 103, 46);
		getContentPane().add(btnNewButton_1);

		textField_3 = new JTextField();
		textField_3.setForeground(new Color(0, 0, 0));
		textField_3.setOpaque(false);
		textField_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (!numeros) {
					e.consume();
				}
			}
		});
		textField_3.setBounds(509, 124, 246, 46);
		getContentPane().add(textField_3);

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(57, 249, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(57, 180, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(57, 314, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(512, 314, 151, 46);
		getContentPane().add(lblNewLabel_1_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(0, 0, 930, 82);
		getContentPane().add(panel);

		JLabel lblEliminarEmpleado = new JLabel("Eliminar Empleado");
		lblEliminarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarEmpleado.setForeground(Color.WHITE);
		lblEliminarEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblEliminarEmpleado);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-55, 112, 985, 274);
		getContentPane().add(panel_1);
	}

	public Borrar(Empleado i1) throws SQLException {
		puestos = p1.listarTodos();
		setUndecorated(true);
		getContentPane().setBackground(new Color(255, 250, 240));
		getContentPane().setForeground(new Color(255, 255, 255));
		setTitle("Eliminar");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		setBounds(100, 100, 880, 522);

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar mostrar;
				try {
					mostrar = new Mostrar();
					mostrar.llenarTabla();
					mostrar.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		textField = new JTextField();
		textField.setEditable(false);
		textField.setOpaque(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(203, 252, 175, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setOpaque(false);
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(203, 183, 175, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setOpaque(false);
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(203, 314, 175, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		comboBox.setBounds(509, 183, 246, 46);
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}
		getContentPane().add(comboBox);

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setEnabled(false);
		checkAdmin.setOpaque(false);
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(611, 314, 49, 46);
		getContentPane().add(checkAdmin);

		JButton btnInsertar = new JButton("Eliminar");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					e1.borrar(i1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(157, 405, 268, 59);
		getContentPane().add(btnInsertar);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(469, 405, 268, 59);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btnNewButton_1.setBounds(228, 128, 103, 46);
		getContentPane().add(btnNewButton_1);

		textField_1.setText(i1.getNombre());
		textField.setText(i1.getContraseña());
		textField_2.setText("" + i1.getSueldo());
		comboBox.setSelectedIndex(i1.getPuesto() - 1);
		if (i1.getAdmin() == 1) {
			checkAdmin.setSelected(true);
		} else {
			checkAdmin.setSelected(false);
		}

		textField_3 = new JTextField();
		textField_3.setForeground(new Color(0, 0, 0));
		textField_3.setOpaque(false);
		textField_3.setVisible(false);
		textField_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (!numeros) {
					e.consume();
				}
			}
		});
		textField_3.setBounds(28, 128, 202, 46);
		getContentPane().add(textField_3);

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(57, 249, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(57, 180, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(57, 314, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(512, 314, 151, 46);
		getContentPane().add(lblNewLabel_1_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(0, 0, 930, 82);
		getContentPane().add(panel);

		JLabel lblEliminarEmpleado = new JLabel("Eliminar Empleado");
		lblEliminarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarEmpleado.setForeground(Color.WHITE);
		lblEliminarEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblEliminarEmpleado);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-55, 112, 985, 274);
		getContentPane().add(panel_1);
	}

}
