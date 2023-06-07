package com.example.bimmer.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.R;
import com.example.bimmer.data.models.Users;
import com.example.bimmer.ui.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginPage extends Fragment {
    public LoginPage() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_page, container, false);
    }

    private ImageView imgHomePage;
    private ImageView imgBlog;
    private ImageView imgContacts;
    private EditText phone;
    private EditText password;
    private Button btn_login;
    private Button btn_register;
    private String parentDbName = "Users";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        phone = view.findViewById(R.id.editTextPhone);
        password = view.findViewById(R.id.editTextPassword);
        btn_login = view.findViewById(R.id.loginButton);
        btn_register = view.findViewById(R.id.registerButtonLoginPage);
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgBlog = view.findViewById(R.id.blogLogo);
        imgContacts = view.findViewById(R.id.companyContactsLogo);
        Paper.init(getContext());
        String userPhoneKey = Paper.book().read(Prevalent.userPhoneKey);
        String userPasswordKey = Paper.book().read(Prevalent.userPasswordKey);
        if (userPhoneKey != "" && userPasswordKey != ""){
            if (!TextUtils.isEmpty(userPhoneKey) && !TextUtils.isEmpty(userPasswordKey)){
                ValidateUser(userPhoneKey, userPasswordKey, view);
            }
        }
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v).navigate(R.id.action_loginPageFragment_to_registrationFragment, bundle);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(phone.getText().toString(), password.getText().toString(), v);
            }
        });
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginPageFragment_to_homePageFragment);
            }
        });
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginPageFragment_to_articlesFragment);
            }
        });
        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginPageFragment_to_contactsPageFragment);
            }
        });
    }

    private void loginUser(String phone, String password, View v) {
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(getContext(), "Укажите телефон", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(getContext(), "Укажите пароль", Toast.LENGTH_SHORT).show();
        } else {
            ValidateUser(phone, password, v);
        }
    }

    private void ValidateUser(String phone, String password, View v) {
        Paper.book().write(Prevalent.userPhoneKey, phone);
        Paper.book().write(Prevalent.userPasswordKey, password);
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance("https://bimmer-motors-android-app-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbName).child(phone).exists()){
                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone)){
                        if (usersData.getPassword().equals(password)){
                            Navigation.findNavController(v).navigate(R.id.action_loginPageFragment_to_profileFragment);
                        } else {
                            Toast.makeText(getContext(), "Неверный пароль", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Аккаунт не существует", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}