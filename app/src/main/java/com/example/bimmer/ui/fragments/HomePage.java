package com.example.bimmer.ui.fragments;

import static com.example.bimmer.data.protocols.EmailHelper.sendEmail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.R;
import com.example.bimmer.ui.DiscountDialog;

public class HomePage extends Fragment {
    private final String TAG = "ApplicationMessage";
    public HomePage() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String botToken = "6257010618:AAG7UbqCJIF_CjXAZWtCJVrXPcEZVMCOj6o";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page, container, false);
    }

    private ImageView imgProfile;
    private ImageView imgBlog;
    private ImageView imgContacts;
    private Button openDiscountDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openDiscountDialog = view.findViewById(R.id.discountButton);
        imgProfile = view.findViewById(R.id.userProfileLogo);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homePageFragment_to_loginPageFragment);
            }
        });
        imgBlog = view.findViewById(R.id.blogLogo);
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked user blog button");
                Navigation.findNavController(v).navigate(R.id.action_homePageFragment_to_articlesFragment);
            }
        });
        imgContacts = view.findViewById(R.id.companyContactsLogo);
        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homePageFragment_to_contactsPageFragment);
            }
        });
        openDiscountDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountDialog dialog = new DiscountDialog();
                dialog.setDialogListener(new DiscountDialog.DialogListener() {
                    @Override
                    public void onDialogSubmit(String name, String phone) {
                        Context context = getContext();
                        if (context != null) {
                            sendEmail(context, name, phone);
                        }
                    }
                });
                dialog.show(getFragmentManager(), "dialog");
            }
        });
    }

}