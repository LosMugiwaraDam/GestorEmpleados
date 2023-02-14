package ModeloBD_Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;

/**
 * 
 * JDialog en el que realizamos las Altas de Empleados
 *
 */
public class Alta extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static TablaPuestos p1 = new TablaPuestos();
	private static TablaEmpleados e1 = new TablaEmpleados();
	static ArrayList<puesto> puestos;

	public static void main(String[] args) {
		try {
			Alta dialog = new Alta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor del JDialog
	 * 
	 * @throws SQLException
	 */
	public Alta() throws SQLException {
		setUndecorated(true);
		puestos = p1.listarTodos();
		getContentPane().setBackground(new Color(255, 250, 240));
		setTitle("Insertar");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		setBounds(100, 100, 880, 522);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(468, 404, 268, 59);
		getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setOpaque(false);
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(285, 206, 181, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setOpaque(false);
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(285, 150, 181, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setOpaque(false);
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.setColumns(10);
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
		textField_2.setBounds(285, 262, 181, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		comboBox.setBounds(515, 150, 246, 46);
		getContentPane().add(comboBox);
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(612, 262, 49, 46);
		checkAdmin.setOpaque(false);
		getContentPane().add(checkAdmin);

		JButton btnInsertar = new JButton("Alta");
		btnInsertar.setForeground(new Color(255, 255, 255));
		btnInsertar.setBackground(new Color(240, 170, 146));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idEmpleado = 0;
					String nombre = textField_1.getText();
					String contraseña = textField.getText();
					float sueldo = (float) Integer.parseInt(textField_2.getText());
					int puesto = puestos.get(comboBox.getSelectedIndex()).getIdPuesto();
					int admin;
					if (checkAdmin.isSelected()) {
						admin = 1;
					} else {
						admin = 0;
					}
					Empleado x1 = new Empleado(idEmpleado, nombre, contraseña, sueldo, puesto, admin);
					try {
						e1.insertar(x1);
						JOptionPane.showMessageDialog(null, "Empleado Añadida con Exito", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						limpiar();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Faltan campos por rellenar", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}

			private void limpiar() {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				checkAdmin.setSelected(false);
			}

		});
		btnInsertar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnInsertar.setBounds(155, 404, 268, 59);
		getContentPane().add(btnInsertar);

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(124, 206, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(124, 150, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(124, 262, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(515, 259, 151, 46);
		getContentPane().add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-27, 0, 946, 82);
		getContentPane().add(panel);
		
				JLabel lblAadirEmpleado = new JLabel("Añadir Empleado");
				lblAadirEmpleado.setForeground(new Color(255, 255, 255));
				panel.add(lblAadirEmpleado);
				lblAadirEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
				lblAadirEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
				
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBackground(new Color(255, 235, 205));
				panel_1.setBounds(-16, 125, 985, 241);
				getContentPane().add(panel_1);

		setLocationRelativeTo(null);
	}
}
