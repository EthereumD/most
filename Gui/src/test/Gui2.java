package test;

public class Gui2 {
	public Swing1 {
	     this.setLayout(null);//设置布局管理器
	    JLabel jl=new JLabel();／／定义一个标签
	    jl.setText("第一个Swing程序"); //设置显示的文字 
	    jl.setBounds(50,50,400,50); //设    置标签的大小和位置 
	    this.add(jl); //将标签放到窗口中     
	    this.setBounds(300,250,500,200); //设置窗口的大小和位置 
	    this.setVisible(true); //设置窗口是可见的
	 }
	 public static void main(String args[]){
	     Swing1 s=new Swing1();
	 }
}
