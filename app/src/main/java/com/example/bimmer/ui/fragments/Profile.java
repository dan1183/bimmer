package com.example.bimmer.ui.fragments;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.CarService;
import com.example.bimmer.R;
import com.example.bimmer.ui.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class Profile extends Fragment {
    public Profile(){
        super(R.layout.account_page);
    }
    private ImageView imgHomePage;
    private ImageView imgBlog;
    private ImageView imgContacts;
    private Button logoutButton;
    private TextView emailTextView;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView phoneTextView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        logoutButton = view.findViewById(R.id.buttonLogout);
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgBlog = view.findViewById(R.id.blogLogo);
        imgContacts = view.findViewById(R.id.companyContactsLogo);
        Paper.init(getContext());
        String userEmailKey = Paper.book().read(Prevalent.userPhoneKey);
        displayUserData(view, userEmailKey);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_loginPageFragment);
            }
        });
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_homePageFragment);
            }
        });
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_articlesFragment);
            }
        });
        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_contactsPageFragment);
            }
        });
    }

    private void displayUserData(View v, String userEmailKey) {
        emailTextView = v.findViewById(R.id.emailTextView);
        nameTextView = v.findViewById(R.id.nameTextView);
        surnameTextView = v.findViewById(R.id.surnameTextView);
        phoneTextView = v.findViewById(R.id.phoneTextView);
        DatabaseReference userRef = FirebaseDatabase.getInstance("https://bimmer-motors-android-app-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("Users").child(userEmailKey);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userEmail = snapshot.child("email").getValue().toString();
                    String userName = snapshot.child("name").getValue().toString();
                    String userSurname = snapshot.child("surname").getValue().toString();
                    String userPhone = snapshot.child("phone").getValue().toString();

                    // Отобразить данные на соответствующих элементах пользовательского интерфейса
                    emailTextView.setText(userEmail);
                    nameTextView.setText(userName);
                    surnameTextView.setText(userSurname);
                    phoneTextView.setText(userPhone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибки чтения из базы данных
            }
        });
    }
}