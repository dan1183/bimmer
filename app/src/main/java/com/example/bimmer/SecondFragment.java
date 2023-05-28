package com.example.bimmer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {
    public String TAG = "list";
    String autos[] = new String[]{
            "BMW 1",
            "BMW 2",
            "BMW 3",
            "BMW 4",
            "BMW 5",
            "BMW 6",
            "BMW 7",
            "BMW X3",
            "BMW X5",
            "BMW X6",
            "BMW X7"
    };
    public SecondFragment(){
        super(R.layout.fragment_second);
    }

    public ImageView imgHomePage;
    public ImageView imgBlog;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new FirstFragment())
                        .commit();
                Log.d("111", "Clicked home page button");
            }
        });
        imgBlog = view.findViewById(R.id.blogLogo);
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new ThirdFragment())
                        .commit();
                Log.d("111", "Clicked user blog button");
            }
        });
    }

    public void OnClickLogout(View view){
        Intent data = new Intent();
        data.putExtra(MainActivity.message, "Вы успешно вышли из аккаунта");
    }
}