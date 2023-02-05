package com.example.u6_ae1_oliverbaidezpatrick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView myWebView = (WebView) findViewById(R.id.wbGPL);
        myWebView.loadUrl("https://www.gnu.org/licenses/gplv3-the-program.es.html");


    }
}


