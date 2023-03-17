package com.example.bimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView header = (TextView) findViewById(R.id.bimmerHeader);
        header.setText("BIMMER");
        ImageView callBimmer = findViewById(R.id.imageCall);
        callBimmer.setImageResource(R.drawable.icon_call);
        ImageView profile = findViewById(R.id.iconAccount);
        profile.setImageResource(R.drawable.icon_account);
        Button makeAppointment = findViewById(R.id.buttonMakeAppointment);
        makeAppointment.setText("Записаться на сервис");
    }


}