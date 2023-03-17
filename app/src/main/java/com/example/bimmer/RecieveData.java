package com.example.bimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecieveData extends AppCompatActivity {
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_data);
        Bundle arguments = getIntent().getExtras();
        TextView textView = (TextView) findViewById(R.id.editPhone);
        textView.setText(arguments.getString("Phone"));
    }

    public void OnClickLogout(View view){
        Intent data = new Intent();
        data.putExtra(MainActivity.message,"Вы вышли из аккаунта");
        finish();
    }
}