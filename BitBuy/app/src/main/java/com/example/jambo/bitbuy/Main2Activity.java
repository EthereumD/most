package com.example.jambo.bitbuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
public class Main2Activity extends AppCompatActivity  {
    EditText edt_id,edt_pwd;
    int answer=3;
    CheckBox chb1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edt_id = (EditText)findViewById(R.id.editText);
        edt_pwd = (EditText)findViewById(R.id.editText3);
        chb1 = (CheckBox)findViewById(R.id.checkBox);
//------
        Button btn3 = (Button) findViewById(R.id.button3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)  {
            String driver = "com.mysql.jdbc.Driver";
                //String url = "jdbc:mysql://120.125.85.30:3306/test";
                // String user = "user01";
                //String password = "master3421";
                String url = "jdbc:mysql://120.125.85.30:3306/test?"
                        + "user=user01&password=master3421&useUnicode=true&characterEncoding=UTF8";
                try {
                    System.out.println("answer = "+0);
                    Class.forName(driver);
                    System.out.println("answer = "+1);
                    Connection conn = DriverManager.getConnection(url);
                    System.out.println("answer = "+2);
                    if (!conn.isClosed())
                        System.out.println("Succeeded connecting to the Database!");
                    System.out.println("answer = "+3);
                    Statement stmt = conn.createStatement();
                    System.out.println("answer = "+4);
                    answer = pass_check(edt_id.toString(),edt_pwd.toString(),stmt);
                    System.out.println("answer = "+5);


//---------------------------------------------------------—
//      String in_name = "hahahsa",in_passwd="123213123";
//      in_user(in_name,in_passwd,stmt);
//---------------------------------------------------------—
//      String in_name = "davidfish";
//      ask_pass(in_name,stmt);
//---------------------------------------------------------—
//      String in_name = "davidfish",in_passwd="1312312f3";
//      System.out.println(pass_check(in_name,in_passwd,stmt));
//---------------------------------------------------------—
                    conn.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("Sorry,can`t find the Driver!");
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                                        //
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this, Main3Activity.class);
                startActivity(intent);

                //



            }


            //新增使用者帳號
            public void in_user(String in_name,String in_passwd,Statement stmt) throws SQLException {
                String sql = "insert into user(name,passwd) values('"+in_name+"','"+in_passwd+"' )";
                stmt.executeUpdate(sql);

            }
            public  String ask_pass(String in_name,Statement stmt) throws SQLException {
                String sql = "select passwd from user where name = '"+in_name+"' ";
                ResultSet rs = stmt.executeQuery(sql);
                String passwd = null;
                while(rs.next()){

                    String passwd0  = rs.getString("passwd");
//           System.out.print("passwd: " + passwd0);
                    passwd=passwd0;
                }
                rs.close();
                return passwd;
            }
            public  int pass_check(String in_name,String in_passwd,Statement stmt) throws SQLException  {
                String right_passwd=ask_pass(in_name,stmt);
                int check = 0;
                if(right_passwd.equals(in_passwd))check=1;

                return check;
            }







        });
        //----
    }
}