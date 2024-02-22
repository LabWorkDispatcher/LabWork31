package com.example.laba31.utils;

import static com.example.laba31.data.Constants.APP_KEY_SERVICE_ACTIVE_LAST;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_ACTIVE_SINCE;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_AMOUNT_CLIENTS;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_AMOUNT_CRASHES;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_CLIENT_LABEL;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_NAME;
import static com.example.laba31.data.Constants.APP_KEY_SERVICE_PROCESS;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba31.UI.ServiceDescriptionActivity;

import java.util.Calendar;
import java.util.TimeZone;

public class Utils {

    public static void moveToActivity(AppCompatActivity currActivity, Intent intent) {
        currActivity.startActivity(intent);
        currActivity.finish();
    }
    public static void moveToServiceDescriptionActivity(Context context, ActivityManager.RunningServiceInfo serviceInfo) {
        Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+3"));
        long currTime = mCalendar.getTimeInMillis();

        Intent intent = new Intent(context, ServiceDescriptionActivity.class);
        intent.putExtra(APP_KEY_SERVICE_NAME, serviceInfo.service.getClassName());
        intent.putExtra(APP_KEY_SERVICE_PROCESS, serviceInfo.process);
        intent.putExtra(APP_KEY_SERVICE_ACTIVE_SINCE, currTime - serviceInfo.lastActivityTime);
        intent.putExtra(APP_KEY_SERVICE_ACTIVE_LAST, currTime - (serviceInfo.lastActivityTime - serviceInfo.activeSince));
        intent.putExtra(APP_KEY_SERVICE_AMOUNT_CLIENTS, serviceInfo.clientCount);
        intent.putExtra(APP_KEY_SERVICE_AMOUNT_CRASHES, serviceInfo.crashCount);
        intent.putExtra(APP_KEY_SERVICE_CLIENT_LABEL, serviceInfo.clientLabel);
        context.startActivity(intent);
        ((AppCompatActivity)context).finish();
    }
}
