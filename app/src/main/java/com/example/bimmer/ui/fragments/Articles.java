package com.example.bimmer.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bimmer.R;

public class Articles extends Fragment {

    private WebView mWebView;

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
        return inflater.inflate(R.layout.acrticles_page, container, false);
    }


    private ImageView imgHomePage;
    private ImageView imgProfile;
    private ImageView imgContacts;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.ArticlesWebView);
        mWebView = (WebView) view.findViewById(R.id.ArticlesWebView);
        mWebView.loadUrl("https://bimmer.ru/blog");
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        imgContacts = view.findViewById(R.id.companyContactsLogo);
        imgProfile = view.findViewById(R.id.userProfileLogo);
        imgHomePage = view.findViewById(R.id.homePageLogo);
        imgHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_articlesFragment_to_homePageFragment);
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_articlesFragment_to_loginPageFragment);
            }
        });
        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_articlesFragment_to_contactsPageFragment);
            }
        });
    }
}