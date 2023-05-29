package com.example.bimmer;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class SecondFragment extends Fragment {
    public SecondFragment(){
        super(R.layout.fragment_second);
    }
    public ImageView imgHomePage;
    public ImageView imgBlog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        createNotificationChannel();
        showNotification();
        Intent intent = new Intent(requireContext(), CarService.class);
        requireActivity().startService(intent);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Log.d("Message", bundle.getString("key", ""));
        }
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("111", "Clicked home page button");
                Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_firstFragment);
            }
        });
        imgBlog = view.findViewById(R.id.blogLogo);
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("111", "Clicked user blog button");
                Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_thirdFragment);
            }
        });
    }
    private static final String CHANNEL_ID = "TestChannel";

    private void showNotification() {
        Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.bmw_m_logo)
                .setContentText("Вы записались на ремонт")
                .setContentTitle("Успешно")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "calendar",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager notificationManager =
                    (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void checkPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions((Activity) requireContext(),  new String[] { permission }, requestCode);
        } else {
            Toast.makeText(getContext(), "Разрешение уже получено", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionResult(int requestCode,
                                          @NonNull String[] permissions,
                                          @NonNull int[] grantResults){
        if (requestCode == 100 && grantResults.length == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Notification", "Permission granted");
            }
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults
        );
    }
}