import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.security.Principal;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\duan\\Desktop\\pic\\mcu.png"));
		textField = new JTextField();
		textField.setBounds(156, 84, 118, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblAcount = new JLabel("Acount :");
		lblAcount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcount.setBounds(59, 78, 87, 34);
		frame.getContentPane().add(lblAcount);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(60, 111, 87, 34);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(156, 117, 118, 23);
		frame.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			Mysql_connect mc = new Mysql_connect();

			public void actionPerformed(ActionEvent arg0) {
				if (mc.SelectTable_account(textField.getText(), passwordField.getPassword())) {
					System.out.println("Login Successful!!");

					store_background sb = new store_background();
					
					System.out.println(mc.getstorenumber(textField.getText()));
					
					sb.reflashtable(mc.getstorenumber(textField.getText()));
					System.out.println("storenumber : " + mc.getstorenumber(textField.getText()));
					System.out.println("Good!");
					sb.gettabledata();
				} else {
					System.out.println("Login Faild!!");
					Error error = new Error();
				}
			}
		});
		btnLogin.setBounds(173, 207, 87, 23);
		frame.getContentPane().add(btnLogin);

		JButton btnRegist = new JButton("Regist");
		btnRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Regist regist = new Regist();
			}
		});
		btnRegist.setBounds(319, 207, 87, 23);
		frame.getContentPane().add(btnRegist);
	}
}
