package com.example.bimmer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_third, container, false);
    }


    public ImageView imgHomePage;
    public ImageView imgProfile;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView bmwRecyclerView = view.findViewById(R.id.bmwRecyclerView);
        List<Item> list = new ArrayList<>();
        for (String l:autos) {
            list.add(new Item(R.drawable.bmw3g20, l));
        }
        BMWAutoAdapter.OnStateClickListener stateClickListener = new BMWAutoAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Item item, int position) {
                Log.d(TAG, item.getName());
                Toast.makeText(requireContext(), item.getName(), Toast.LENGTH_SHORT).show();
            }
        };
        BMWAutoAdapter bmwAutoAdapter = new BMWAutoAdapter(stateClickListener, requireContext(), list);
        bmwRecyclerView.setAdapter(bmwAutoAdapter);

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
        imgProfile = view.findViewById(R.id.userProfileLogo);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new SecondFragment())
                        .commit();
                Log.d("111", "Clicked user profile button");
            }
        });
    }
}