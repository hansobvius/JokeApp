package com.example.android.jokedisplay;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class JokeActivity extends AppCompatActivity {

    public static final String KEY = "key";
    public static final String BOOLEAN_KEY = "boolean_key";
    public static final String SAVED_STRING = "saved_string";

    private TextView mTextView;
    private AdView mAdView;

    private String joke;
    private boolean freeVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        joke = intent.getStringExtra(JokeActivity.KEY);
        freeVersion = intent.getBooleanExtra(JokeActivity.BOOLEAN_KEY, true);

        mTextView = (TextView) findViewById(R.id.text_joke);
        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        displayJoke(joke);

        displayAd(freeVersion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    public void backHome(View view){
        finish();
    }

    public void displayJoke(String t){
        mTextView.setText(t);
    }

    public void displayAd(boolean b){
        if(b){
            mAdView.setVisibility(View.VISIBLE);
        }else{
            mAdView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(SAVED_STRING, joke);
        super.onSaveInstanceState(savedInstanceState);
    }
}
