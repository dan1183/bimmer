package com.example.bimmer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.Navigation;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bimmer.R;
import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {

    public static String message = "Exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        MapKitFactory.setApiKey("7ca9ccc4-2932-492b-b2eb-7d02e7905d06");
    }
}