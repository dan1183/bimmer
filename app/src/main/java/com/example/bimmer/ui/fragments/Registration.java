package com.example.bimmer.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration extends Fragment {

    public Registration() {}

    public static Registration newInstance(String param1, String param2) {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.registration_page, container, false);
    }

    private EditText email;
    private EditText password;
    private EditText name;
    private EditText surname;
    private EditText phone;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        email = view.findViewById(R.id.editTextEmailAddressRegistration);
        password = view.findViewById(R.id.editTextPasswordRegistration);
        name = view.findViewById(R.id.editTextNameRegistration);
        surname = view.findViewById(R.id.editTextSurnameRegistration);
        phone = view.findViewById(R.id.editTextPhoneRegistration);
        Button btn_register = view.findViewById(R.id.registerButton);
        Button btn_cancel = view.findViewById(R.id.backToLoginPageButton);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginPageFragment);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getContext(), "Укажите E-mail", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(getContext(), "Укажите пароль", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getContext(), "Введите свое имя", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(surname.getText().toString())){
                    Toast.makeText(getContext(), "Введите свою фамилию", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(getContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show();
                } else {
                    ValidateEmail(email.getText().toString(), password.getText().toString(),
                            name.getText().toString(), surname.getText().toString(), phone.getText().toString(), v);
                }
            }
        });


}

    private void ValidateEmail(String email, String password, String name,
                               String surname, String phone, View v) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance("https://bimmer-motors-android-app-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("email", email);
                    userDataMap.put("password", password);
                    userDataMap.put("name", name);
                    userDataMap.put("surname", surname);
                    userDataMap.put("phone", phone);
                    RootRef.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Bundle bundle = new Bundle();
                                        Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_loginPageFragment, bundle);
                                        Toast.makeText(getContext(), "Регистрация завершена", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getContext(), "Телефон " + phone + " уже зарегистрирован", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}