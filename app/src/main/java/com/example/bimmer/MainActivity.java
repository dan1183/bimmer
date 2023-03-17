package com.example.bimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Bimmer";

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

    public void onClick(View view) {
        if (view.getId() == R.id.buttonMakeAppointment){
            Log.d(TAG, "Произошло нажатие на кнопку 'Записаться на сервис'");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Context info = getApplicationContext();
        CharSequence message = "Start activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Context info = getApplicationContext();
        CharSequence message = "Resume activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Context info = getApplicationContext();
        CharSequence message = "Pause activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Context info = getApplicationContext();
        CharSequence message = "Stop activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Context info = getApplicationContext();
        CharSequence message = "Restart activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Context info = getApplicationContext();
        CharSequence message = "Destroy activity";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(info, message, duration);
        toast.show();
    }
}