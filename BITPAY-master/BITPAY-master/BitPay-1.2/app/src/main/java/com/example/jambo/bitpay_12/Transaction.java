package com.example.jambo.bitpay_12;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {
    static String username,selleraddress,getpoeple,ReceivingAddress,customer;
    static  double amount ;
    TextView textView;
    static  int msg ,TransactionListType = 0;
    static String Nowtime;
    ListView TransactionList;
    TranAdapter tranAdapter;
    Thread insertSellerAddress,CancelTransaction,updateCustomer;
    Button getTransaction;

    ArrayList TotalPrice =new ArrayList();
    ArrayList Time =new ArrayList();
    ArrayList konwhow =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        textView = (TextView)findViewById(R.id.textView);
        getTransaction = (Button)findViewById(R.id.getTransaction);
        TransactionList = (ListView)findViewById(R.id.TransactionList);
        tranAdapter = new TranAdapter();
        TransactionList.setAdapter(tranAdapter);

        //接收參數
        try {
            username = getIntent().getExtras().getString("username");
            customer = getIntent().getExtras().getString("customer");
            selleraddress = getIntent().getExtras().getString("SellerAddress");
            getpoeple = getIntent().getExtras().getString("GetPeople");
            ReceivingAddress = getIntent().getExtras().getString("ReceivingAddress");
            amount = getIntent().getExtras().getDouble("amount");
            Nowtime = getIntent().getExtras().getString("NowTime");
            msg = getIntent().getExtras().getInt("Msg");

        }catch(Exception e){}
/*
        //從Login 進來交易紀錄頁面
        if(msg ==4){
            getTranList(username,TransactionListType);


        }

        //從mainactivity 進來交易紀錄頁面
        if(msg==0){

        }*/
        //從請款進來交易紀錄頁面
        if(msg ==1){
            //textView.setText("Hi : "+username+"\nyou will request "+amount +" by : \n"+selleraddress+"\nat :"+Nowtime);
            ConfrimList();
        }
        //從付款進來交易紀錄頁面
        if(msg ==2){
            //textView.setText("\nyou will pay "+amount +" to : \n"+selleraddress);
            PayConfrimList();
            getTranList(customer,TransactionListType);
        }
        //取得交易資訊
        getTranList(username,TransactionListType);


        TransactionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Bundle bundle = new Bundle();
                bundle.putDouble("TotalPrice", (Double) TotalPrice.get(position));
                bundle.putString("Time",String.valueOf(Time.get(position)));
                bundle.putString("username",username);
                Intent i = new Intent(Transaction.this, TransactionDetail.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        //進入詳細資訊
        TransactionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(getApplicationContext(),
                        "you are "+ position,
                        Toast.LENGTH_SHORT).show();
                return false;

            }
        });

    }


    private void getTranList(String username ,int TransactionListType) {
        try {
            //清空交易紀錄
            int length = Time.size();
            for(int i =0;i <length;i++) {
                tranAdapter.removeItem(0);
                TotalPrice.remove(0);
                Time.remove(0);
            }
            tranAdapter.notifyDataSetChanged();



            String result = DBgetTransaction.executeQuery(username, TransactionListType);
                /*
                    SQL 結果有多筆資料時使用JSONArray
                    只有一筆資料時直接建立JSONObject物件
                    JSONObject jsonData = new JSONObject(result);
                */
            JSONArray jsonArray = new JSONArray(result);
            //if (jsonData.getString("name").equals(null))textname.setText("無商品資訊 !");
            for(int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonData = jsonArray.getJSONObject(i);
                //textname.setText("商品名稱 : "+jsonData.getString("name") +"\n商品價格 : " +jsonData.getString("price")+" BTC");
                TotalPrice.add(jsonData.getDouble("TotalPrice"));
                Time.add ( jsonData.getString("time"));
                //確定是買或賣
                if(username.equals( jsonData.getString("Seller"))) {
                    konwhow.add("1");
                }
                if(username.equals( jsonData.getString("Customer"))) {
                    konwhow.add("0");
                }
                tranAdapter.addItem(i);
                tranAdapter.notifyDataSetChanged();
            }



        } catch(Exception e) {
            Log.e("log_tag", e.toString());
        }
    }

    //交易清單(ListView) 製作
    private class TranAdapter extends BaseAdapter {
        private ArrayList<Integer> tList;
        public TranAdapter(){
            tList = new ArrayList<>();
        }

        public void addItem(Integer i){
            tList.add(i);
        }

        public void removeItem(int index){
            tList.remove(index);
        }

        @Override
        public int getCount() {
            return tList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            Transaction.TranAdapter.Holder tranholder;
            if(v == null){
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tran_list, null);
                tranholder = new Holder();
                tranholder.TranId = (TextView)v.findViewById(R.id.TranId);
                tranholder.TranTime = (TextView) v.findViewById(R.id.TranTime);
                tranholder.TranPrice = (TextView) v.findViewById(R.id.TranPrice);
                v.setTag(tranholder);
            } else{
                tranholder = (Holder) v.getTag();
            }
            //totalPrice += (double)price.get(position) ;
            //TextTotal.setText(totalPrice + " mBTC");
            int itemNumber = position+1;

            tranholder.TranId.setText("   " + itemNumber + "         ");
            tranholder.TranTime.setText(Time.get(position).toString());
            tranholder.TranPrice.setText(TotalPrice.get(position).toString() + "mBTC  ");

            if(TransactionListType == 0) {
                if (konwhow.get(position) == "1") {
                    v.setBackgroundColor(0xFFEF6D75);
                }
                if (konwhow.get(position) == "0") {
                    v.setBackgroundColor(0xFF7AE6A2);
                }
            }
            if(TransactionListType == 1 ){
                v.setBackgroundColor(0xFFEF6D75);
            }
            if(TransactionListType == 2 ){
                v.setBackgroundColor(0xFF7AE6A2);
            }

            return v;
        }
        class Holder{
            TextView TranId,TranTime,TranPrice;
        }
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
                                //tranAdapter.notifyDataSetChanged();
                                getTranList(username,TransactionListType);


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
                                tranAdapter.notifyDataSetChanged();

                            }
                        }).show();
    }

    //付款後確認交易
    private void PayConfrimList() {
        new AlertDialog.Builder(Transaction.this)
                .setTitle("交易清單確認")
                .setMessage("是否要新增此筆交易清單\n"+"\n購買人 : "+ username + "\n總金額 : "+ amount + "mBTC\n銷售員 : "+getpoeple )
                .setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                updateCustomer = new updateCustomer();
                                updateCustomer.start();
                                Toast.makeText(Transaction.this, "已建立交易清單", Toast.LENGTH_LONG).show();
                                //tranAdapter.notifyDataSetChanged();
                                finish();




                            }
                        }).show();
    }
    //---返回鍵退出應用 確認
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK&&(msg ==1||msg == 4)) { // 攔截返回鍵
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            Intent i = new Intent(Transaction.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (keyCode == KeyEvent.KEYCODE_BACK&&(msg==0||msg ==2)){
            new AlertDialog.Builder(Transaction.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Log.v("TAG","msg : " +msg);
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
                            }).show();        }
        return true;
    }


    //新增銷售員錢包地址
    class insertSellerAddress extends Thread {
        @Override
        public void run() {
            //建立HttpClient用以跟伺服器溝通
            HttpClient client = new DefaultHttpClient();

            try {
                //HttpPost post = new HttpPost("http://120.125.85.162/test/InsertSellerAddress.php");
                HttpPost post = new HttpPost("http://mcdf.asuscomm.com/test/InsertSellerAddress.php");
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
                //Toast.makeText(Transaction.this, response, Toast.LENGTH_LONG).show();
                Looper.prepare();

                Looper.loop();
                getTranList(username,TransactionListType);


            } catch (Exception ex) {
                //若伺服器無法與PHP檔連接時的動作
                Toast.makeText(Transaction.this, "新增失敗", Toast.LENGTH_LONG).show();
                Looper.prepare();

                Looper.loop();
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
                //HttpPost post = new HttpPost("http://120.125.85.162/test/CancelTransaction.php");
                HttpPost post = new HttpPost("http://mcdf.asuscomm.com/test/CancelTransaction.php");
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
                getTranList(username,TransactionListType);


            } catch (Exception ex) {
                //若伺服器無法與PHP檔連接時的動作
                Toast.makeText(Transaction.this, "刪除失敗", Toast.LENGTH_LONG).show();
            }
        }
    }



    //更新買家資訊
    class updateCustomer extends Thread{
        @Override
        public void run() {
            //建立HttpClient用以跟伺服器溝通
            HttpClient client = new DefaultHttpClient();

            try {
                //HttpPost post = new HttpPost("http://120.125.85.162/test/updateCustomer.php");
                HttpPost post = new HttpPost("http://mcdf.asuscomm.com/test/updateCustomer.php");
                //建立POST的變數

                List<NameValuePair> vars = new ArrayList<NameValuePair>();
                vars.clear();
                //將商品名稱、價格、銷售員及總金額加入vars 的list中
                vars.add(new BasicNameValuePair("Customer", username));
                vars.add(new BasicNameValuePair("Seller_Address", ReceivingAddress));
                vars.add(new BasicNameValuePair("Seller", getpoeple));


                //發出POST要求
                post.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));

                //建立ResponseHandler,以接收伺服器回傳的訊息
                ResponseHandler<String> h = new BasicResponseHandler();
                //將回傳的訊息轉為String
                String response = new String(client.execute(post, h).getBytes(), HTTP.UTF_8);
                //Toast.makeText(Transaction.this, response, Toast.LENGTH_LONG).show();
                Looper.prepare();
                Looper.loop();
                getTranList(username,TransactionListType);


            } catch (Exception ex) {
                //若伺服器無法與PHP檔連接時的動作
                Toast.makeText(Transaction.this, "刪除失敗", Toast.LENGTH_LONG).show();
            }
        }
    }


    //新增右上選單按鈕
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, Menu.FIRST , Menu.NONE, "Hi "+username);
        menu.add(2, Menu.NONE , Menu.NONE, "Sell & Buy");
        menu.add(3, Menu.NONE , Menu.NONE, "Sell");
        menu.add(4, Menu.NONE , Menu.NONE, "Buy");
        menu.add(5, Menu.NONE , Menu.NONE, "Log out");

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Hi user
        if (item.getGroupId() == 1) {
            Toast.makeText(getApplicationContext(),
                    item.getTitle(),
                    Toast.LENGTH_SHORT).show();
        }

        //Sell & Buy
        if (item.getGroupId() == 2) {
            TransactionListType = 0;
            getTranList(username,TransactionListType);
        }

        //Sell
        if (item.getGroupId() == 3) {
            TransactionListType = 1;
            getTranList(username,TransactionListType);
        }

        //Buy
        if (item.getGroupId() == 4) {
            TransactionListType = 2;
            getTranList(username,TransactionListType);
        }
        //Logout
        if(item.getGroupId() == 5) {
            Toast.makeText(getApplicationContext(),
                    item.getTitle(),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Transaction.this, LoginPage.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}
