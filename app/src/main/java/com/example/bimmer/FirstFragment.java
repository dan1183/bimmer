package com.example.bimmer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class FirstFragment extends Fragment {
    private final String TAG = "ApplicationMessage";
    public FirstFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public ImageView imgProfile;
    public ImageView imgBlog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgProfile = view.findViewById(R.id.userProfileLogo);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked user profile button");
                Bundle bundle = new Bundle();
                bundle.putString("key", "hello world");
                Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment, bundle);
            }
        });
        imgBlog = view.findViewById(R.id.blogLogo);
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked user blog button");
                Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_thirdFragment);
            }
        });
    }
}