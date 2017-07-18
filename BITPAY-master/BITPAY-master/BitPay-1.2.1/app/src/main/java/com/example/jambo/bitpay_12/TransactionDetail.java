package com.example.jambo.bitpay_12;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.jambo.bitpay_12.Transaction.TransactionListType;
import static com.example.jambo.bitpay_12.Transaction.username;

public class TransactionDetail extends AppCompatActivity {
    static String Time,Seller_Address;
    static double TotalPrice;
    ListView TransactionDetail_List;
    TextView txv,TextSellerName,TextCustomerName,TextTime,TextTotalPrice;
    TranDetailAdapter  tranDetailAdapter;
    ArrayList name =new ArrayList();
    ArrayList price =new ArrayList();
    ArrayList seller_address =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        TextSellerName = (TextView)findViewById(R.id.Text_SellerName);
        TextCustomerName = (TextView)findViewById(R.id.Text_CustomerName);
        TextTime = (TextView)findViewById(R.id.Text_Time);
        TextTotalPrice = (TextView)findViewById(R.id.Text_TotalPrice);
        TransactionDetail_List = (ListView)findViewById(R.id.TransactionDetail_List);
        tranDetailAdapter = new TranDetailAdapter();
        TransactionDetail_List.setAdapter(tranDetailAdapter);

        //接取bundle資料
        try {
            Time = getIntent().getExtras().getString("Time");
            TotalPrice = getIntent().getExtras().getDouble("TotalPrice");
        }catch(Exception e){}
        //txv = (TextView)findViewById(R.id.txv);        txv.setText(Time+"\n"+TotalPrice+"\n"+username);
        //填入基本資料
        TextTime.setText(Time);
        TextTotalPrice.setText(String .valueOf(TotalPrice)+"mBTC ");

        //取得資料庫
        GetTransactionDetail(username);

        TextTotalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),Seller_Address  , Toast.LENGTH_SHORT).show();
                Intent intentUri = new Intent(Intent.ACTION_VIEW);
                intentUri.setData(Uri.parse("https://testnet.blockexplorer.com/address/"+Seller_Address));
                startActivity(intentUri);
            }
        });
    }



    private void GetTransactionDetail(String username) {
        try {

            String result = DBgetTransactionDetail.executeQuery(username, Time);
                /*
                    SQL 結果有多筆資料時使用JSONArray
                    只有一筆資料時直接建立JSONObject物件
                    JSONObject jsonData = new JSONObject(result);
                */
            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                price.add(jsonData.getDouble("price"));
                name.add ( jsonData.getString("name"));
                seller_address.add(jsonData.getString("seller_address"));
                tranDetailAdapter.addItem(i);
                tranDetailAdapter.notifyDataSetChanged();
                TextSellerName.setText(jsonData.getString("Seller"));
                TextCustomerName.setText(jsonData.getString("Customer"));

                if(username.equals(jsonData.getString("Seller"))){
                    TextSellerName.setTextColor(0xffff4081);
                }
                else TextCustomerName.setTextColor(0xffff4081);
            }
            Seller_Address = (String) seller_address.get(0);


        } catch(Exception e) {
            Log.e("log_tag", e.toString());
        }
    }



    private class TranDetailAdapter extends BaseAdapter {
        private ArrayList<Integer> tList;
        public TranDetailAdapter(){
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
            Holder trandetailholder;
            if(v == null){
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.trandetail_list, null);
                trandetailholder = new Holder();
                trandetailholder.TranId = (TextView)v.findViewById(R.id.TranId);
                trandetailholder.TranName = (TextView) v.findViewById(R.id.TranName);
                trandetailholder.TranPrice = (TextView) v.findViewById(R.id.TranPrice);
                v.setTag(trandetailholder);
            } else{
                trandetailholder = (Holder) v.getTag();
            }
            int itemNumber = position+1;

            trandetailholder.TranId.setText("   " + itemNumber + "         ");
            trandetailholder.TranName.setText("   " + name.get(position) + "  ");
            trandetailholder.TranPrice.setText( String.valueOf(price.get(position)) + " ");

            return v;
        }
        class Holder{
            TextView TranId,TranName,TranPrice;
        }
    }



}
