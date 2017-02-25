package test;
//簡單的空白視窗
import javax.swing.JFrame;

public class Gui {

	static int width =1000;

	static int height =1000;

	public static void main(String[] args){
		
		JFrame jf= new JFrame("helloswingBWF");

		jf.setSize( width , height );//設置窗口大小

		jf.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE );//使窗口上的最大化、最小化以及關閉健發揮作用

		jf.setVisible( true );//設置窗體是否可見
		}

}
