import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Error {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Error window = new Error();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Error() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Error messige");
		frame.setBounds(100, 100, 332, 237);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\duan\\Desktop\\pic\\mcu.png"));
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(109, 147, 87, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblPleaseRetry = new JLabel("Please Retry!");
		lblPleaseRetry.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseRetry.setFont(new Font("·L³n¥¿¶ÂÅé", Font.PLAIN, 28));
		lblPleaseRetry.setForeground(Color.RED);
		lblPleaseRetry.setBounds(44, 44, 236, 85);
		frame.getContentPane().add(lblPleaseRetry);
	}
}
