package com.example.laba31.UI;

import static com.example.laba31.data.Constants.APP_KEY_SERVICE_ACTIVE_LAST;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_ACTIVE_SINCE;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_AMOUNT_CLIENTS;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_AMOUNT_CRASHES;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_CLIENT_LABEL;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_NAME;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_PROCESS;
import static com.example.laba31.utils.Utils.moveToActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba31.databinding.ActivityServiceDescriptionBinding;

import java.util.Date;

public class ServiceDescriptionActivity extends AppCompatActivity {
    private ActivityServiceDescriptionBinding binding;

    private Bundle serviceInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBackButton.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            moveToActivity(this, i);
        });

        serviceInfo = getIntent().getExtras();
        binding.serviceClass.setText(binding.serviceClass.getText() + serviceInfo.getString(APP_KEY_SERVICE_NAME));
        binding.serviceProcess.setText(binding.serviceProcess.getText() + serviceInfo.getString(APP_KEY_SERVICE_PROCESS));
        binding.clientAmount.setText(binding.clientAmount.getText().toString() + serviceInfo.getInt(APP_KEY_SERVICE_AMOUNT_CLIENTS));
        binding.crashCount.setText(binding.crashCount.getText().toString() + serviceInfo.getInt(APP_KEY_SERVICE_AMOUNT_CRASHES));
        binding.activeSince.setText(binding.activeSince.getText() + new Date(serviceInfo.getLong(APP_KEY_SERVICE_ACTIVE_SINCE)).toGMTString());
        binding.activeLast.setText(binding.activeLast.getText() + new Date(serviceInfo.getLong(APP_KEY_SERVICE_ACTIVE_LAST)).toGMTString());

        if (serviceInfo.getInt(APP_KEY_SERVICE_CLIENT_LABEL) != 0) {
            binding.clientLabel.setText(binding.clientLabel.getText().toString() + serviceInfo.getInt(APP_KEY_SERVICE_CLIENT_LABEL));
        } else {
            binding.clientLabel.setText("");
        }
    }
}
