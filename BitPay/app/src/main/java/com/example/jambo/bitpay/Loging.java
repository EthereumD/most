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

public class Loging extends AppCompatActivity {
    Button btnlogin,btnregiest;
    EditText edtid,edtpwd;
    String valuestring = null,useid,usepwd;
    TextView txtid,txtpwd;
    public SharedPreferences setting,UserInfo;
    public static File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        //---宣告
        btnlogin = (Button)findViewById(R.id.BtnLogin);        btnlogin.setOnClickListener(new Clicklogin());
        btnregiest = (Button)findViewById(R.id.BtnRegiest);     btnregiest.setOnClickListener(new Clickregiest());
        edtid = (EditText)findViewById(R.id.edtregiest);             edtpwd = (EditText)findViewById(R.id.edtpwd);
        txtid = (TextView)findViewById(R.id.textView);          txtpwd = (TextView)findViewById(R.id.textView2);
        //---檔案位置宣告
        file = new File("/data/data/com.example.user.loginandout/shared_prefs","Idinfo.xml");
        //file = new File("/data/data/com.example.user.loginandout/shared_prefs","Pwdinfo.xml");




    }

    //--按鈕物件 Login
    class Clicklogin implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view == btnlogin){
                UserInfo = getSharedPreferences("UserInfo",0);
                String UserId = UserInfo.getString("STRID","") , UserPwd = UserInfo.getString("STRPWD","");
                String checkid =edtid.getText().toString() , checkpwd = edtpwd.getText().toString();
                if(checkid.equals(UserId) && checkpwd.equals(UserPwd) )   SendIntentLog();
                else {
                    new AlertDialog.Builder(Loging.this)
                            .setTitle("確認視窗")
                            .setMessage("帳號或密碼有誤")
                            .setIcon(R.drawable.ic_home_black_24dp)
                            .setNegativeButton("好的",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            // TODO Auto-generated method stub
                                        }
                                    }).show();
                }
            }
        }
    }
    //--按鈕物件 regiest
    class Clickregiest implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            SendIntentReg();
        }
    }


    //--傳送資料 login
    public void SendIntentLog(){
        Intent intent = new Intent();
        intent.setClass(Loging.this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("VALUE",valuestring);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Loging.this.finish();
    }
    //--跳轉到註冊頁面 regiest
    public void SendIntentReg(){
        Intent intent = new Intent();
        intent.setClass(Loging.this,Regiest.class);
        startActivity(intent);
        Loging.this.finish();
    }


    //---讀取資料
    public void ReadValue(){
        setting = getSharedPreferences("Idinfo",0);
        valuestring = setting.getString("VALUESTRING","");
        edtid.setText(valuestring);
    }


    //---返回鍵退出應用 確認
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(Loging.this)
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

