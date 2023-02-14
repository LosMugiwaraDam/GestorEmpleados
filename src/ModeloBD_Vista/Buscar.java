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
 * JDialog en el que realizamos las Busquedas de Empleados
 *
 */
public class Buscar extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static TablaPuestos p1 = new TablaPuestos();
	private static TablaEmpleados e1 = new TablaEmpleados();
	static ArrayList<puesto> puestos;

	public static void main(String[] args) {
		try {
			Buscar dialog = new Buscar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Contructor del JDialog
	 * @throws SQLException 
	 */
	public Buscar() throws SQLException {
		setUndecorated(true);
		puestos = p1.listarTodos();
		getContentPane().setBackground(new Color(255, 250, 240));
		setTitle("Buscar");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		setIconImage(imgIcon);
		setBounds(100, 100, 880, 522);

		ButtonGroup grupo1;
		grupo1 = new ButtonGroup();

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
		textField.setBounds(227, 270, 171, 46);
		getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setOpaque(false);
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(227, 201, 171, 46);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setOpaque(false);
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(227, 332, 171, 46);
		getContentPane().add(textField_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		for (int i = 0; i < puestos.size(); i++) {
			comboBox.addItem(puestos.get(i).getPuesto());
		}
		comboBox.setBounds(483, 198, 246, 46);
		getContentPane().add(comboBox);

		JCheckBox checkAdmin = new JCheckBox("");
		checkAdmin.setEnabled(false);
		checkAdmin.setOpaque(false);
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkAdmin.setBounds(582, 329, 49, 46);
		getContentPane().add(checkAdmin);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBounds(270, 416, 361, 59);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(240, 170, 146));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textField_3.getText());
				try {
					Empleado x1=e1.buscar(id);
					textField_1.setText(x1.getNombre());
					textField.setText(x1.getContraseña());
					textField_2.setText(""+x1.getSueldo());
					comboBox.setSelectedIndex(x1.getPuesto()-1);
					if(x1.getAdmin()==1) {
						checkAdmin.setSelected(true);
					}else {
						checkAdmin.setSelected(false);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "No existe ningun empleado con ese codigo", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btnNewButton_1.setBounds(727, 126, 103, 46);
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
				if (textField_3.getText().trim().length() == 8) {
					e.consume();
				}
			}
		});
		textField_3.setBounds(483, 126, 246, 46);
		getContentPane().add(textField_3);

		JLabel lblNewLabel = new JLabel("Contraseña:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel.setBounds(77, 267, 151, 46);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(77, 198, 151, 46);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(77, 329, 151, 46);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Admin:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(483, 329, 151, 46);
		getContentPane().add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(-18, 0, 930, 82);
		getContentPane().add(panel);
		
		JLabel lblBuscarEmpleado = new JLabel("Buscar Empleado");
		lblBuscarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarEmpleado.setForeground(Color.WHITE);
		lblBuscarEmpleado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblBuscarEmpleado);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(-56, 113, 985, 274);
		getContentPane().add(panel_1);
	}

}
