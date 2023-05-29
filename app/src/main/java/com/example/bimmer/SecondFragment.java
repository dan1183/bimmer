package com.example.bimmer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class SecondFragment extends Fragment {
    public SecondFragment(){
        super(R.layout.fragment_second);
    }
    public ImageView imgHomePage;
    public ImageView imgBlog;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Log.d("Message", bundle.getString("key", ""));
        }
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_firstFragment);
                Log.d("111", "Clicked home page button");
            }
        });
        imgBlog = view.findViewById(R.id.blogLogo);
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_thirdFragment);
                Log.d("111", "Clicked user blog button");
            }
        });
    }
}