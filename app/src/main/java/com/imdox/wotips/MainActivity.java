package com.imdox.wotips;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int index = 0;
    private CardView cardHealth;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onResume() {
        super.onResume();
 /*       if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            index = 0;
        } else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            loadData();
        }*/
    }

    private AdView adView;
    public static final int TYPE_TIPS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardHealth = findViewById(R.id.cardHealth);
        cardHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TipsActivity.class));
            }
        });

      /*  adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intAdd));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.


            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("Error Code : ",String.valueOf(errorCode));
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                if(index==0){
                    loadData();
                }else if(index==1){
                    startActivity(new Intent(MainActivity.this,AboutActivity.class));
                }else if(index==2){
                    rateApp();
                }else {
                    loadData();
                }

            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.idAbout:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    index = 1;
                } else {
                    startActivity(new Intent(this,AboutActivity.class));
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
                return(true);
            case R.id.idRate:

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    index = 2;
                } else {
                    rateApp();
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
              return(true);

         }
        return(super.onOptionsItemSelected(item));
    }

    private void rateApp(){
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

}
