package com.example.sharefi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharefi.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends DrawerBaseActivity {

    ActivityFeedbackBinding activityFeedbackBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFeedbackBinding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(activityFeedbackBinding.getRoot());
        allocateActivityTitle("Feedback");
    }
}