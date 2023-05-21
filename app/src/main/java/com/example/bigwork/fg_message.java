package com.example.bigwork;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class fg_message extends Fragment {
    private WebView webView;
    private String musicName = "演员";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_fg_message, container, false);
        webView = view.findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        String userAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(userAgent);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        String encodedMusicName;
        try {
            encodedMusicName = URLEncoder.encode(musicName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encodedMusicName = musicName;
            e.printStackTrace();
        }
        // 加载网易云音乐的歌曲评论页面
//        String url = "https://music.163.com/?s=#/search/m/?s=" + encodedMusicName + "&type=1";
//       String url = "https://music.163.com/m/song?id=1463165983";
        String url = "https://music.163.com/#/song?id=32507038";
        System.out.println(url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;

    }
}