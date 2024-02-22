package com.example.laba31.UI;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.laba31.adapters.MyRecyclerViewAdapter;
import com.example.laba31.data.Constants;
import com.example.laba31.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private ArrayList<ActivityManager.RunningServiceInfo> recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.leaveButton.setOnClickListener(view -> {
            this.finish();
            System.exit(0);
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfoList = manager.getRunningServices(50);
        if (serviceInfoList.size() == 0) {
            Log.d("APP_DEBUGGER", "Error! No services were found.");
        }

        recyclerViewList = (ArrayList<ActivityManager.RunningServiceInfo>) serviceInfoList;
        ((MyRecyclerViewAdapter)binding.recyclerView.getAdapter()).differ.submitList(recyclerViewList);

        Timer event_timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    //Log.d("APP_DEBUGGER", new Date(Calendar.getInstance().getTimeInMillis() - serviceInfoList.get(0).activeSince).toGMTString());
                    updateRecyclerView();
                });
            }
        };
        event_timer.schedule(timerTask, 300L);
    }
}