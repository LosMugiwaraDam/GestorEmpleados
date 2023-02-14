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
 * JDialog en el que realizamos las Modificaciones de Empleados
 *
 */
public class Editar extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static TablaPuestos p1 = new TablaPuestos();
	private static TablaEmpleados e1 = new TablaEmpleados();
	static ArrayList<puesto> puestos;

	public static void main(String[] args) {
		try {
			Editar dialog = new Editar();
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
	public Editar() throws SQLException {
		setUndecorated(true);
		puestos = p1.listarTodos();
		getContentPane().setBackground(new Color(255, 250, 240));
		getContentPane().setForeground(new Color(255, 255, 255));
		setTitle("Modificar");
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
		textField.setOpaque(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(232, 270, 174, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setOpaque(false);
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(232, 201, 174, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setOpaque(false);
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (!numeros) {
					if (key == 46) {

					} else {
						e.consume();
					}
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(232, 332, 174, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		comboBox.setBounds(479, 198, 246, 46);
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}

		getContentPane().add(comboBox);

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setOpaque(false);
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(578, 332, 49, 46);
		getContentPane().add(checkAdmin);

		JButton btnInsertar = new JButton("Modificar");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int idEmpleado = Integer.parseInt(textField_3.getText());
					String nombre = textField_1.getText();
					String contraseña = textField.getText();
					float sueldo = Float.parseFloat(textField_2.getText());
					int puesto = puestos.get(comboBox.getSelectedIndex()).getIdPuesto();
					int admin;
					if (checkAdmin.isSelected()) {
						admin = 1;
					} else {
						admin = 0;
					}
					Empleado x1 = new Empleado(idEmpleado, nombre, contraseña, sueldo, puesto, admin);
					try {
						int editar = JOptionPane.showConfirmDialog(null, "Desea Editar el Empleado", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						if (editar == JOptionPane.YES_OPTION) {
							try {
								JOptionPane.showMessageDialog(null, "Empleado Editado con Exito", "Aviso",
										JOptionPane.INFORMATION_MESSAGE);
								e1.actualizar(x1);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Faltan campos por rellenar", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(167, 416, 268, 59);
		getContentPane().add(btnInsertar);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(478, 416, 268, 59);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(240, 170, 146));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textField_3.getText());
				try {
					Empleado x1 = e1.buscar(id);
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
		btnNewButton_1.setBounds(723, 128, 103, 46);
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
		textField_3.setBounds(479, 128, 246, 46);
		getContentPane().add(textField_3);

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(85, 267, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(85, 198, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(85, 332, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(479, 332, 151, 46);
		getContentPane().add(lblNewLabel_1_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-22, 0, 930, 82);
		getContentPane().add(panel);

		JLabel lblEditarEmpleado = new JLabel("Editar Empleado");
		lblEditarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarEmpleado.setForeground(Color.WHITE);
		lblEditarEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblEditarEmpleado);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-55, 114, 985, 274);
		getContentPane().add(panel_1);
	}

	public Editar(Empleado i1) throws SQLException {
		puestos = p1.listarTodos();
		setUndecorated(true);
		getContentPane().setBackground(new Color(255, 250, 240));
		getContentPane().setForeground(new Color(255, 255, 255));
		setTitle("Modificar");
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
		textField.setOpaque(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(232, 270, 174, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setOpaque(false);
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(232, 201, 174, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setOpaque(false);
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;
				if (!numeros) {
					if (key == 46) {

					} else {
						e.consume();
					}
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(232, 332, 174, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		comboBox.setBounds(479, 198, 246, 46);
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}

		getContentPane().add(comboBox);

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setOpaque(false);
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(578, 332, 49, 46);
		getContentPane().add(checkAdmin);

		JButton btnInsertar = new JButton("Modificar");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idEmpleado = i1.getId();
					String nombre = textField_1.getText();
					String contraseña = textField.getText();
					float sueldo = Float.parseFloat(textField_2.getText());
					int puesto = puestos.get(comboBox.getSelectedIndex()).getIdPuesto();
					int admin;
					if (checkAdmin.isSelected()) {
						admin = 1;
					} else {
						admin = 0;
					}
					Empleado x1 = new Empleado(idEmpleado, nombre, contraseña, sueldo, puesto, admin);
					try {
						e1.actualizar(x1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Faltan campos por rellenar", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(167, 416, 268, 59);
		getContentPane().add(btnInsertar);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(478, 416, 268, 59);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btnNewButton_1.setBounds(228, 128, 103, 46);
		getContentPane().add(btnNewButton_1);

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
		textField_3.setBounds(10, 128, 220, 46);
		getContentPane().add(textField_3);

		textField_1.setText(i1.getNombre());
		textField.setText(i1.getContraseña());
		textField_2.setText("" + i1.getSueldo());
		comboBox.setSelectedIndex(i1.getPuesto() - 1);
		if (i1.getAdmin() == 1) {
			checkAdmin.setSelected(true);
		} else {
			checkAdmin.setSelected(false);
		}

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(85, 267, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(85, 198, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(85, 332, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(479, 332, 151, 46);
		getContentPane().add(lblNewLabel_1_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-22, 0, 930, 82);
		getContentPane().add(panel);

		JLabel lblEditarEmpleado = new JLabel("Editar Empleado");
		lblEditarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarEmpleado.setForeground(Color.WHITE);
		lblEditarEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblEditarEmpleado);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-55, 114, 985, 274);
		getContentPane().add(panel_1);
	}
}