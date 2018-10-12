package com.imdox.wotips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static ArrayList<TipsData> tipsData;

    private List<Object> adapterList;
    private InterstitialAd mInterstitialAd;
    private int index = 0;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        adView = findViewById(R.id.adView);
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
                    startActivity(new Intent(TipsActivity.this,AboutActivity.class));
                }else if(index==2){
                    loadData();
                }

            }
        });
    }

    private void loadData(){
        try{
            String[] str_heading = getResources().getStringArray(R.array.strHeading);
            String[] str_value = getResources().getStringArray(R.array.strValue);
            tipsData = new ArrayList<TipsData>();
            for (int i=0;i<str_heading.length;i++) {
                tipsData.add(new TipsData(str_heading[i],str_value[i]));
            }

            adapterList = new ArrayList<>();
            adapterList.addAll(tipsData);
            // Initiating Adapter

            recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
            // Set Layout Manager
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(TipsActivity.this,1);
            recyclerView.setLayoutManager(mLayoutManager);
            TipsAdapter modelListAdapter = new TipsAdapter(TipsActivity.this);
            recyclerView.setAdapter(modelListAdapter);
            modelListAdapter.setAdapterData(adapterList);
            modelListAdapter.notifyDataSetChanged();
        }catch (Exception e){
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            index = 0;
        } else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            loadData();
        }
    }
}
