package com.example.jambo.bitpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class Regiest extends AppCompatActivity {
    EditText edtregiestid,edtpwd1,edtpwd2;
    TextView txtpwd1 , txtpwd2;
    Button btnregiest;
    public static  File file_id,file_pwd;
    String strid=null , strpwd = null;
    public SharedPreferences setid,setpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiest);
        //--宣告
        edtregiestid = (EditText)findViewById(R.id.edtregiest);   edtpwd1 = (EditText)findViewById(R.id.edtpwd1);  edtpwd2 = (EditText)findViewById(R.id.edtpwd2);
        btnregiest = (Button)findViewById(R.id.btnregiest);       btnregiest.setOnClickListener(new Clickregiest());
        txtpwd1 = (TextView)findViewById(R.id.txtpwd1);         txtpwd2 = (TextView) findViewById(R.id.txtpwd2);
        file_id = new File("/data/data/com.example.user.loginandout/shared_prefs","UserInfo.xml");



    }
    //---註冊按鈕
    class Clickregiest implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(passwdcompare()==1 ){
                strid = edtregiestid.getEditableText().toString();
                strpwd = edtpwd1.getEditableText().toString();
                setid = getSharedPreferences("UserInfo",0);
                setid.edit().putString("STRID",strid).commit();
                setpwd = getSharedPreferences("UserInfo",0);
                setpwd.edit().putString("STRPWD",strpwd).commit();
                //--
                Intent intent = new Intent();
                intent.setClass(Regiest.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        }
    }


    //---密碼比對
    public int passwdcompare(){
        String pwd1 = edtpwd1.getText().toString(),pwd2 = edtpwd2.getText().toString();
        if("".equals(edtregiestid.getText().toString())){
            new AlertDialog.Builder(Regiest.this)
                    .setTitle("確認視窗")
                    .setMessage("請輸入帳號!")
                    .setIcon(R.drawable.ic_home_black_24dp)
                    .setNegativeButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
            return 0 ;
        }
        if("".equals(edtpwd1.getText().toString())){
            new AlertDialog.Builder(Regiest.this)
                    .setTitle("確認視窗")
                    .setMessage("請輸入密碼!")
                    .setIcon(R.drawable.ic_home_black_24dp)
                    .setNegativeButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
            return 0 ;
        }
        else if (pwd1.equals(pwd2)){
            return 1;
        }
        else {
            new AlertDialog.Builder(Regiest.this)
                    .setTitle("確認視窗")
                    .setMessage("兩次密碼不符!")
                    .setIcon(R.drawable.ic_home_black_24dp)
                    .setNegativeButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
            return 0 ;
        }

    }


    //---返回鍵退出應用 確認
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(Regiest.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
                    .setIcon(R.drawable.ic_home_black_24dp)
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;
    }


}
