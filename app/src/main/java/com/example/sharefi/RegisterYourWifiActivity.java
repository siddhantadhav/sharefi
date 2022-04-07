package com.example.sharefi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharefi.databinding.ActivityRegisterYourWifiBinding;

public class RegisterYourWifiActivity extends DrawerBaseActivity {

    ActivityRegisterYourWifiBinding activityRegisterYourWifiBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterYourWifiBinding = ActivityRegisterYourWifiBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterYourWifiBinding.getRoot());
        allocateActivityTitle("Register Your Wifi");
    }
}