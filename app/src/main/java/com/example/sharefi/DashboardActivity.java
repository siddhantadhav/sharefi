package com.example.sharefi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharefi.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class DashboardActivity extends DrawerBaseActivity {
    WifiManager wifiManager;
    WifiReceiver wifiReceiver;
    ListAdapter listAdapter;
    ListView wifiList;
    List mywifiList;
    ActivityDashboardBinding activityDashboardBinding;

    TextView textViewUsername;
    String setUsername;
    private DatabaseReference mDatabase;

    String SSID;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Home");

        wifiList=(ListView)findViewById(R.id.myListView);
        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }else{
            scanWifiList();
        }
//        new android.widget.AdapterView.OnClickListener()

//        mDatabase.getDatabase().getReference();

        wifiList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                View view = getParent();
                TextView txtSSID =(TextView) view.findViewById(R.id.txtWifiName);
                SSID = txtSSID.getText().toString();
//                password = mDatabase.child("WIFI").child(FirebaseAuth.getInstance().getUid()).child("wifiPassword").toString();

                Toast.makeText(DashboardActivity.this, SSID, Toast.LENGTH_LONG).show();
            }

        });

    };

    private void scanWifiList() {
        wifiManager.startScan();
        mywifiList=wifiManager.getScanResults();
        setAdapter();
    }

    private void setAdapter() {
        listAdapter=new ListAdapter(getApplicationContext(),mywifiList);
        wifiList.setAdapter(listAdapter);
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        wifiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(DashboardActivity.this, "clicked", Toast.LENGTH_LONG).show();
//            }
//        });


    }
}