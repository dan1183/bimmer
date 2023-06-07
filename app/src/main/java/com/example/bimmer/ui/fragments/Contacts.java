package com.example.bimmer.ui.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class Contacts extends Fragment {

    public Contacts() {}

    private MapView mapview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_page, container, false);
    }


    @Override
    public void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }

    private ImageView imgContacts;
    private ImageView imgBlog;
    private ImageView imgHome;
    private ImageView imgWhatsApp;
    private ImageView imgPhone;
    private ImageView imgEmail;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapview = view.findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(55.798272, 37.752755), 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        imgHome = view.findViewById(R.id.homePageLogo);
        imgBlog = view.findViewById(R.id.blogLogo);
        imgContacts = view.findViewById(R.id.userProfileLogo);
        imgWhatsApp = view.findViewById(R.id.whatsappIcon);
        imgPhone = view.findViewById(R.id.phoneIcon);
        imgEmail = view.findViewById(R.id.mailIcon);

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_contactsPageFragment_to_homePageFragment);
            }
        });

        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_contactsPageFragment_to_articlesFragment);
            }
        });

        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_contactsPageFragment_to_loginPageFragment);
            }
        });

        imgWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "79295270667";
                Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "WhatsApp не установлен на телефоне", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+79295270667";
                Uri uri = Uri.parse("tel:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = "bimmermotors.ru@gmail.com";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + emailAddress));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Не удалось найти приложение для отправки почты", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}