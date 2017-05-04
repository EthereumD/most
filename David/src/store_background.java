import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class store_background {

	private JFrame frame;
	private JTable table;
	JLabel labelStore_btc = new JLabel("Store btc :");
	JLabel labelStore_Name = new JLabel("Store Name : ");
	JLabel lblStoreNo = new JLabel("Store No :");
	public static String pname = null;
	public static String pdetail = null;
	/**
	 * Launch the application.
	 */
	 public static void main(String[] args) {
	 EventQueue.invokeLater(new Runnable() {
	 public void run() {
	 try {
	 store_background window = new store_background();
	 window.frame.setVisible(true);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	 });
	 }

	Object[][] cellData = { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
			{ null, null, null }, };
	String[] columnNames = { "name", "detail", "quantity" };
	DefaultTableModel model = new DefaultTableModel(cellData, columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField textQuantity;

	/**
	 * Create the application.
	 */
	public store_background() {
		initialize();
		reflashtable(100);
	}

	static int lblStoreNovar;
	
	public static String store_no;
	public void reflashtable(int no) {
		store_no = Integer.toString(no);
		// TODO Auto-generated method stub
		Mysql_connect mysql_connect = new Mysql_connect();
		mysql_connect.search_name_btc(no).substring(0, mysql_connect.search_name_btc(no).indexOf(" "));

		lblStoreNovar = no;
		lblStoreNo.setText("Store No : " + no);
		labelStore_Name.setText("Store Name : "
				+ mysql_connect.search_name_btc(no).substring(0, mysql_connect.search_name_btc(no).indexOf(" ")));
		labelStore_btc.setText("Store btc :" + mysql_connect.search_name_btc(no)
				.substring(mysql_connect.search_name_btc(no).indexOf(" "), mysql_connect.search_name_btc(no).length()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 517, 357);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Background");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\duan\\Desktop\\pic\\mcu.png"));
		frame.setTitle("Store_Background");
		labelStore_btc.setBounds(293, 26, 186, 20);
		frame.getContentPane().add(labelStore_btc);

		labelStore_Name.setBounds(104, 26, 168, 20);
		frame.getContentPane().add(labelStore_Name);

		lblStoreNo.setBounds(10, 26, 95, 20);
		frame.getContentPane().add(lblStoreNo);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBounds(0, 0, 225, 320);
		frame.getContentPane().add(table);
		table.setModel(model);
		// scrollPane.setViewportView(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 56, 301, 166);
		frame.getContentPane().add(scrollPane);

		JLabel lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(321, 116, 72, 20);
		frame.getContentPane().add(lblName);

		JLabel lblDetail = new JLabel("Detail :");
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetail.setBounds(321, 150, 72, 20);
		frame.getContentPane().add(lblDetail);

		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setBounds(321, 178, 72, 20);
		frame.getContentPane().add(lblQuantity);

		JButton btnUpdat = new JButton("Update");
		btnUpdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gettabledata();
			}
		});
		btnUpdat.setBounds(264, 233, 87, 23);
		frame.getContentPane().add(btnUpdat);
		
		//Ã¸»s­I´º¹Ï
		JPanel panelpic = new JPanel();
		panelpic.setBounds(10, 211, 124, 76);
		frame.getContentPane().add(panelpic);
		panelpic.setOpaque(false);
		((JPanel) frame.getContentPane()).setOpaque(false);
		
		JLabel label_gettablename = new JLabel("");
		label_gettablename.setBounds(392, 116, 80, 28);
		frame.getContentPane().add(label_gettablename);
		
		JLabel label_gettabledetail = new JLabel("");
		label_gettabledetail.setBounds(392, 150, 80, 28);
		frame.getContentPane().add(label_gettabledetail);
		// scrollPane.setViewportView(table);

		JButton btninsert = new JButton("insert");
		btninsert.setEnabled(false);
		btninsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mysql_connect ms = new Mysql_connect();
				
				
//				int i = Integer.parseInt(label_gettablename.getText());
//				int d = Integer.parseInt(label_gettabledetail.getText());
//				int q = Integer.parseInt(textQuantity.getText());
//				System.out.println("i = " + i + 1);
				ms.search_product_number(pname, pdetail);
				ms.Insertdata(lblStoreNovar, ms.search_product_number(pname, pdetail), textQuantity.getText(), showdate());
				// ms.Insertdata(100, 1, 10);
			}
		});
		btninsert.setBounds(392, 233, 87, 23);
		frame.getContentPane().add(btninsert);
		
				textQuantity = new JTextField();
				textQuantity.setColumns(10);
				textQuantity.setBounds(392, 178, 96, 21);
				frame.getContentPane().add(textQuantity);
				
				JButton btnNewButton = new JButton("Add new product");
				btnNewButton.addActionListener(new ActionListener() {
					//Add_Product ap = new Add_Product();
					public void actionPerformed(ActionEvent arg0) {
						Add_Product ap = new Add_Product(store_no, showdate());
						System.out.println("click");
					}
				});
				btnNewButton.setBounds(321, 274, 158, 23);
				frame.getContentPane().add(btnNewButton);
				textQuantity.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						if (!textQuantity.getText().trim().equals("")){
							btninsert.setEnabled(true);
						}
						else {
							btninsert.setEnabled(false);
						}
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						if (!textQuantity.getText().trim().equals("")){
							btninsert.setEnabled(true);
						}
						else {
							btninsert.setEnabled(false);
						}
					}
				});
		ImageIcon img = new ImageIcon("C://Users//duan//Desktop//david//mcu.png");
		JLabel background = new JLabel(img);
		frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(10, 220, img.getIconWidth(), img.getIconHeight());
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		            label_gettablename.setText(""+table.getValueAt(row, col));
		            label_gettabledetail.setText(""+table.getValueAt(row, ((col)+1)));
		            pname = table.getValueAt(row, col).toString();
		            pdetail = table.getValueAt(row, ((col)+1)).toString();

		        }
		    }
		});
	}

	public void gettabledata() {
		Mysql_connect mysql_connect = new Mysql_connect();
//		mysql_connect.update_table(model, lblStoreNovar);
		mysql_connect.Update_TableAll(model,lblStoreNovar);
	}
	public String showdate() {
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd-");   
			Calendar cal = Calendar.getInstance();
			String time = date.toString().substring(11,date.toString().length()-9);
			String year = ft.format(cal.getTime());
			
			System.out.println(year+time);
			//System.out.println("time:"+time);
			return year+time;
		}
}
