import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Product extends JFrame {

	private JPanel contentPane;
	private JTextField textField_product_name;
	private JTextField textField_product_detail;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Add_Product frame = new Add_Product("500", "111");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Add_Product(String store_number,String date) {
		setTitle("Add Product");
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\duan\\Desktop\\pic\\mcu.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 287, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductnumber = new JLabel("product_name :");
		lblProductnumber.setBounds(27, 50, 114, 24);
		contentPane.add(lblProductnumber);
		
		JLabel lblProductdetail = new JLabel("product_detail :");
		lblProductdetail.setBounds(27, 84, 114, 24);
		contentPane.add(lblProductdetail);
		
		textField_product_name = new JTextField();
		textField_product_name.setBounds(124, 52, 96, 21);
		contentPane.add(textField_product_name);
		textField_product_name.setColumns(10);
		
		textField_product_detail = new JTextField();
		textField_product_detail.setColumns(10);
		textField_product_detail.setBounds(124, 84, 96, 21);
		contentPane.add(textField_product_detail);
		
		JButton btnNewButton_Check = new JButton("Check");
		btnNewButton_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mysql_connect.Insertdb_Product(textField_product_name.getText(), textField_product_detail.getText());
				String maxproductnumber = Mysql_connect.Insertdb_Product(textField_product_name.getText(), textField_product_detail.getText());
				Mysql_connect.Insert_Pstorestock(store_number, maxproductnumber, date);
				setVisible(false);
			}
		});
		btnNewButton_Check.setEnabled(false);
		btnNewButton_Check.setBounds(84, 172, 87, 23);
		contentPane.add(btnNewButton_Check);
		
		textField_product_detail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(!textField_product_detail.getText().equals("") && !textField_product_name.getText().equals("") )
					btnNewButton_Check.setEnabled(true);
				else {
					btnNewButton_Check.setEnabled(false);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(!textField_product_detail.getText().equals("") && !textField_product_name.getText().equals("") )
					btnNewButton_Check.setEnabled(true);
				else {
					btnNewButton_Check.setEnabled(false);
				}
			}
		});
		
	}
}
