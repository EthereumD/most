package com.example.jambo.bitpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import static com.example.jambo.bitpay.Loging.file;

public class MainActivity extends AppCompatActivity {
    Button btnlogout;
    public static  File file;
    private TextView mTextMessage;
    public SharedPreferences UserInfo,InfoDelete;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogout = (Button)findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(new Clicklogout());
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        UserInfo = getSharedPreferences("UserInfo",0);
        String UserId = UserInfo.getString("STRID","") , UserPwd = UserInfo.getString("STRPWD","");
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("確認視窗")
                .setMessage("您的帳號是 : "+UserId+"\n您的密碼是 : "+UserPwd)
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

    class Clicklogout implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            file = new File("/data/data/com.example.user.loginandout/shared_prefs","UserInfo.xml");
            //file.delete();
            InfoDelete = getSharedPreferences("UserInfo",0);
            InfoDelete.edit().putString("STRID","").commit();
            Intent reit = new Intent();
            reit.setClass(MainActivity.this,Loging.class);
            startActivity(reit);
            MainActivity.this.finish();
        }
    }

    //---返回鍵退出應用 確認
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(MainActivity.this)
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
