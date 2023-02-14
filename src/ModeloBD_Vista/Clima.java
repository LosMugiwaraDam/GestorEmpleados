package ModeloBD_Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Array;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Clima extends JDialog {
	static String climaStr;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clima dialog = new Clima();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clima() {
		setUndecorated(true);
		almorrana();
		setBounds(100, 100, 526, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(255, 250, 240));
		
		String[] list = climaStr.split(",");
		
		JLabel lblNewLabel = new JLabel(list[0]+" Cº");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(31, 187, 161, 48);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(list[1]);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(31, 118, 161, 48);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(list[2]+" Km/h");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_2.setBounds(284, 187, 161, 48);
		contentPanel.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(0, 0, 526, 82);
		contentPanel.add(panel);
		
		JLabel lblClima = new JLabel("Clima");
		lblClima.setHorizontalAlignment(SwingConstants.CENTER);
		lblClima.setForeground(Color.WHITE);
		lblClima.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		panel.add(lblClima);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 235, 205));
		panel_1.setBounds(0, 102, 526, 168);
		contentPanel.add(panel_1);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		btnNewButton.setBackground(new Color(240, 170, 146));
		btnNewButton.setBounds(120, 280, 268, 41);
		contentPanel.add(btnNewButton);
		
		setLocationRelativeTo(null);
	}
	
	public static void almorrana() {
		 try {
	            ClimaInterface clima = (ClimaInterface) Naming.lookup("//25.56.25.5/getClima");

	            climaStr = clima.getClima();

	            System.out.println(climaStr);
	        } catch (MalformedURLException | RemoteException | NotBoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
}
