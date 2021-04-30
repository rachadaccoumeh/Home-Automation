package com.accoumeh.rachad.homeautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
    WebView webView;
    String version;
    String versionName;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        textView = findViewById(R.id.textView3);
        webView = findViewById(R.id.webview);
        button=findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

    }
    public void start(){
        textView.setText(R.string.loading);
        button.setOnClickListener(v -> myintent());
        if (connected()) {
            textView.setText(R.string.checkinternet);
            if (isOnline()) {
                webView.loadUrl("https://s0sa.000webhostapp.com/Home_Automation/homeautomationcheckforupdate.html");
                check_for_update();
            } else {
                textView.setText(R.string.no_internet_connection);
            }
        } else {
            textView.setText(R.string.no_internet_connection);
        }
    }

    public boolean connected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        // not connected to network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    public void timer(int fulltime, int intervaltime) {
        new CountDownTimer(fulltime, intervaltime) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

            }
        }.start();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void check_internet() {
        check_for_update();
    }

    public void check_for_update() {
        textView.setText(R.string.checking_for_update);
        versionName = BuildConfig.VERSION_NAME;
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                version = title;
                if (!version.equals(versionName)) {
                    //update are available
                    new CountDownTimer(4000, 1000) {
                        int i1 = 0;

                        public void onTick(long millisUntilFinished) {
                            button.setVisibility(View.VISIBLE);
                            i1++;
                            if (i1 == 1) {
                                textView.setText(getString(R.string.redirecting) + " 3 ");
                            } else if (i1 == 2) {
                                textView.setText(getString(R.string.redirecting) + " 2 ");
                            } else if (i1 == 3) {
                                textView.setText(getString(R.string.redirecting) + " 1 ");
                            } else if (i1 == 4) {
                                textView.setText(getString(R.string.redirecting) + " 0 ");
                                Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.accoumeh.rachad.homeautomation"));
                                startActivity(Getintent);
                            }
                        }

                        public void onFinish() {
                        }
                    }.start();
                } else {
                    // no update available
                    myintent();
                }
            }
        });


    }


    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }


    public void myintent() {
        Intent i = new Intent(Splash.this, MainActivity2.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        Splash.super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }
}