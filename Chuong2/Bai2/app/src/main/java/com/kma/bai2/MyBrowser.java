package com.kma.bai2;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowser extends WebViewClient {

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon){
        super.onPageStarted(view,url,favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
