package com.example.jambo.bitpay_12;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {
    static String username,selleraddress;
    static  double amount ;
    TextView textView;
    static  int msg ;
    static String Nowtime;
    Thread insertSellerAddress,CancelTransaction;
    Button getTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        textView = (TextView)findViewById(R.id.textView);
        getTransaction = (Button)findViewById(R.id.getTransaction);
        msg = 0;
        try {
            username = getIntent().getExtras().getString("username");
            selleraddress = getIntent().getExtras().getString("SellerAddress");
            amount = getIntent().getExtras().getDouble("amount");
            Nowtime = getIntent().getExtras().getString("NowTime");
            msg = getIntent().getExtras().getInt("Msg");

        }catch(Exception e){}

        if(msg ==0){
            textView.setText("Hi : "+username);
        }

        if(msg ==1){
            //textView.setText("Hi : "+username+"\nyou will request "+amount +" by : \n"+selleraddress+"\nat :"+Nowtime);
            ConfrimList();
        }

        if(msg ==2){
            textView.setText("\nyou will pay "+amount +" to : \n"+selleraddress);
        }

        getTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    //商家確認訂單
    protected void ConfrimList(){
        new AlertDialog.Builder(Transaction.this)
                .setTitle("交易清單確認")
                .setMessage("是否要新增此筆交易清單\n"+"\n銷售員 : "+ username + "\n總金額 : "+ amount + "mBTC\n清單建立時間 : "+ Nowtime)
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                insertSellerAddress = new insertSellerAddress();
                                insertSellerAddress.start();
                                Toast.makeText(Transaction.this, "已建立交易清單", Toast.LENGTH_LONG).show();

                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                CancelTransaction = new CancelTransaction();
                                CancelTransaction.start();
                                Toast.makeText(Transaction.this, "已取消訂單", Toast.LENGTH_LONG).show();

                            }
                        }).show();
    }


    //---返回鍵退出應用 確認
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK&&msg ==1) { // 攔截返回鍵
            new AlertDialog.Builder(Transaction.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
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
        if (keyCode == KeyEvent.KEYCODE_BACK&&msg==0){
            finish();
        }
        return true;
    }


    //新增銷售員錢包地址
    class insertSellerAddress extends Thread {
        @Override
        public void run() {
            //建立HttpClient用以跟伺服器溝通
            HttpClient client = new DefaultHttpClient();

            try {
                HttpPost post = new HttpPost("http://120.125.85.162/test/InsertSellerAddress.php");
                //建立POST的變數

                List<NameValuePair> vars = new ArrayList<NameValuePair>();
                vars.clear();
                //將商品名稱、價格、銷售員及總金額加入vars 的list中
                vars.add(new BasicNameValuePair("Seller", username));
                vars.add(new BasicNameValuePair("Time", Nowtime));
                vars.add(new BasicNameValuePair("Seller_Address", selleraddress));

                //發出POST要求
                post.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));

                //建立ResponseHandler,以接收伺服器回傳的訊息
                ResponseHandler<String> h = new BasicResponseHandler();
                //將回傳的訊息轉為String
                String response = new String(client.execute(post, h).getBytes(), HTTP.UTF_8);
                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                Looper.prepare();

                Looper.loop();


            } catch (Exception ex) {
                //若伺服器無法與PHP檔連接時的動作
                Toast.makeText(Transaction.this, "新增失敗", Toast.LENGTH_LONG).show();
            }
        }
    }


    //刪除銷售員錢包地址
    class CancelTransaction extends Thread {
        @Override
        public void run() {
            //建立HttpClient用以跟伺服器溝通
            HttpClient client = new DefaultHttpClient();

            try {
                HttpPost post = new HttpPost("http://120.125.85.162/test/CancelTransaction.php");
                //建立POST的變數

                List<NameValuePair> vars = new ArrayList<NameValuePair>();
                vars.clear();
                //將商品名稱、價格、銷售員及總金額加入vars 的list中
                vars.add(new BasicNameValuePair("Seller", username));
                vars.add(new BasicNameValuePair("Time", Nowtime));

                //發出POST要求
                post.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));

                //建立ResponseHandler,以接收伺服器回傳的訊息
                ResponseHandler<String> h = new BasicResponseHandler();
                //將回傳的訊息轉為String
                String response = new String(client.execute(post, h).getBytes(), HTTP.UTF_8);
                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                Looper.prepare();

                Looper.loop();


            } catch (Exception ex) {
                //若伺服器無法與PHP檔連接時的動作
                Toast.makeText(Transaction.this, "刪除失敗", Toast.LENGTH_LONG).show();
            }
        }
    }
}
