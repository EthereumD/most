package pictest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class datetest {
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd-");   
		Calendar cal = Calendar.getInstance();
		String time = date.toString().substring(11,date.toString().length()-9);
		String year = ft.format(cal.getTime());
		
		System.out.println("today is:"+year+time);
		//System.out.println("time:"+time);
		
	}

}
