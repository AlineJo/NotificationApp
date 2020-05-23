package com.yousuf.notificationapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yousuf.notificationapp.R;
import com.yousuf.notificationapp.models.MyConstants;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvMsg = findViewById(R.id.tv_msg);

        if (getIntent().getExtras() != null) {
            tvMsg.append(getIntent().getStringExtra(MyConstants.KEY_MASSAGE));
        }

    }
}
