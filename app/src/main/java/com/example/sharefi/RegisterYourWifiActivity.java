package com.example.sharefi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharefi.databinding.ActivityRegisterYourWifiBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterYourWifiActivity extends DrawerBaseActivity {
    EditText editTextWifiSSID;
    EditText editTextWifiPassword;

    ActivityRegisterYourWifiBinding activityRegisterYourWifiBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterYourWifiBinding = ActivityRegisterYourWifiBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterYourWifiBinding.getRoot());
        allocateActivityTitle("Register Your Wifi");

        editTextWifiSSID = (EditText) findViewById(R.id.editTextWifiSSID);
        editTextWifiPassword = (EditText) findViewById(R.id.editTextWifiPassword);

    }


//    public void registerWifiBtnClicked(View v){
//        String SSID = editTextWifiSSID.getText().toString().trim();
//        String password = editTextWifiPassword.getText().toString().trim();
//
//        Wifi wifi = new Wifi(SSID, password);
//
//        FirebaseDatabase.getInstance().getReference("WIFI").child(FirebaseAuth.getInstance().getUid())
//                .setValue(wifi).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(RegisterYourWifiActivity.this, "Wifi successfully registered", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(RegisterYourWifiActivity.this, "Could not register your WIFI", Toast.LENGTH_LONG).show();
//                }
//                finish();
//            }
//        });
//    }
    public void registerWifiBtnClicked(View v){
        String SSID = editTextWifiSSID.getText().toString().trim();
        String password = editTextWifiPassword.getText().toString().trim();

        Map<String, String> wifi = new HashMap<>();

        wifi.put(SSID, password);
        FirebaseDatabase.getInstance().getReference("WIFI").setValue(wifi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterYourWifiActivity.this, "Wifi successfully registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterYourWifiActivity.this, DashboardActivity.class));
                }
                else {
                    Toast.makeText(RegisterYourWifiActivity.this, "Could not register your WIFI", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

//Toast.makeText(RegisterYourWifiActivity.this, "Wifi successfully registered", Toast.LENGTH_LONG).show();