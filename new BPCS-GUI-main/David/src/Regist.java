import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class Regist extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField_acount;
	private JPasswordField passwordField_Password;
	private JPasswordField passwordField_checkPassword;
	private JLabel lblStorename;
	private JTextField textField_store_name;
	private JLabel lblStoreaddress;
	private JTextField textField_store_address;
	private JLabel lblStorebtcaddress;
	private JTextField textField_store_btc_address;
	private JButton btnConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regist frame = new Regist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Regist() {
		// TextFeild_init();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\duan\\Desktop\\pic\\mcu.png"));
		setTitle("Regist");
		setVisible(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAccount = new JLabel("Input Your account : ");
		lblAccount.setBounds(10, 10, 121, 24);
		contentPane.add(lblAccount);

		JLabel lblInputYourPassword = new JLabel("Input Your Password : ");
		lblInputYourPassword.setBounds(10, 45, 121, 24);
		contentPane.add(lblInputYourPassword);

		JLabel lblCheckPassword = new JLabel("Check Password : ");
		lblCheckPassword.setBounds(10, 77, 121, 24);
		contentPane.add(lblCheckPassword);

		textField_acount = new JTextField();
		textField_acount.setBounds(164, 12, 138, 22);
		contentPane.add(textField_acount);
		textField_acount.setColumns(10);

		passwordField_Password = new JPasswordField();
		passwordField_Password.setBounds(164, 46, 138, 24);
		contentPane.add(passwordField_Password);

		passwordField_checkPassword = new JPasswordField();
		passwordField_checkPassword.setBounds(164, 79, 138, 24);
		contentPane.add(passwordField_checkPassword);

//		lblStorename = new JLabel("store_name : ");
//		lblStorename.setBounds(10, 111, 121, 24);
//		contentPane.add(lblStorename);

//		textField_store_name = new JTextField();
//		textField_store_name.setColumns(10);
//		textField_store_name.setBounds(164, 113, 138, 22);
//		contentPane.add(textField_store_name);

//		lblStoreaddress = new JLabel("store_address : ");
//		lblStoreaddress.setBounds(10, 145, 121, 24);
//		contentPane.add(lblStoreaddress);

//		textField_store_address = new JTextField();
//		textField_store_address.setColumns(10);
//		textField_store_address.setBounds(164, 147, 138, 22);
//		contentPane.add(textField_store_address);

//		lblStorebtcaddress = new JLabel("store_btc_address : ");
//		lblStorebtcaddress.setBounds(10, 179, 121, 24);
//		contentPane.add(lblStorebtcaddress);

//		textField_store_btc_address = new JTextField();
//		textField_store_btc_address.setColumns(10);
//		textField_store_btc_address.setBounds(164, 181, 138, 22);
//		contentPane.add(textField_store_btc_address);

//		textField_acount.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//		textField_store_address.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//		textField_store_btc_address.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//		textField_store_name.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//		passwordField_Password.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//		passwordField_checkPassword.addFocusListener(new FocusListener() {
//			public void focusGained(FocusEvent e) {
//				Check_textfield();
//			}
//
//			public void focusLost(FocusEvent e) {
//				Check_textfield();
//
//			}
//		});
//
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CheckPassword()) {
					Mysql_connect mysql_connect = new Mysql_connect();
					mysql_connect.inserent_login(textField_acount.getText(), passwordField_checkPassword.getText()
//							,textField_store_name.getText(), textField_store_address.getText()
//							,textField_store_btc_address.getText()
							);
					
					

				} else {
					Error er = new Error();
				}
			}
		});
		btnConfirm.setEnabled(true);
		btnConfirm.setBounds(325, 228, 87, 23);
		contentPane.add(btnConfirm);

		TextFeild_init();
	}

//	public void Check_textfield() {
//		if (!textField_acount.getText().trim().equals("") & !passwordField_Password.getText().trim().equals("")
//				& !passwordField_checkPassword.getText().trim().equals("")
//				& !textField_store_name.getText().trim().equals("")
//				& !textField_store_address.getText().trim().equals("")
//				& !textField_store_btc_address.getText().trim().equals("")) {
//			btnConfirm.setEnabled(true);
//		} else {
//			btnConfirm.setEnabled(false);
//		}

//	}

	public void TextFeild_init() {
		textField_acount.setText("hello");
		passwordField_Password.setText("123");
		passwordField_checkPassword.setText("123");
//		textField_store_name.setText("A_America");
//		textField_store_address.setText("America");
//		textField_store_btc_address.setText("Generate 10");
//
	}

	public boolean CheckPassword() {
		if (passwordField_Password.getText().equals(passwordField_checkPassword.getText())) {
			// System.out.println(passwordField_Password.getText());<---- use
			// string.equal(string)
			System.out.println("Successful");
			return true;
		} else {
			return false;
		}

	}
}
