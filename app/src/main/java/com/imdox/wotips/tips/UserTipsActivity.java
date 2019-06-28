package com.imdox.wotips.tips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imdox.wotips.R;
import com.imdox.wotips.support.AboutActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserTipsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static ArrayList<UserTips> userTips;
    private List<Object> adapterList;
    private InterstitialAd mInterstitialAd;
    private int index = 0;
    private AdView adView;
    private DatabaseReference databaseRefObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseRefObject = FirebaseDatabase.getInstance().getReference(getString(R.string.tagTipsNode));


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
                    startActivity(new Intent(UserTipsActivity.this,AboutActivity.class));
                }else if(index==2){
                    loadData();
                }

            }
        });
    }

    private void loadData(){
        try{
            userTips = new ArrayList<UserTips>();
            try{
                databaseRefObject.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try{
                                    Map<String,Object> userFbTips = (Map<String,Object>) dataSnapshot.getValue();
                                    for (Map.Entry<String, Object> entry : userFbTips.entrySet()){
                                        Map tipsData = (Map) entry.getValue();
                                        userTips.add(new UserTips(tipsData.get("userMobile").toString(),tipsData.get("userEmail").toString(),
                                                tipsData.get("userName").toString(),tipsData.get("userDisplayName").toString(),
                                                tipsData.get("tipCategory").toString(),tipsData.get("tipTitle").toString(),
                                                tipsData.get("tipContent").toString(),tipsData.get("tipStatus").toString(),tipsData.get("strCreatedDate").toString()));
                                    }
                                    adapterList = new ArrayList<>();
                                    adapterList.addAll(userTips);
                                    // Initiating Adapter

                                    recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                                    // Set Layout Manager
                                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(UserTipsActivity.this,1);
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    TipsAdapter tipsAdapter = new TipsAdapter(UserTipsActivity.this);
                                    recyclerView.setAdapter(tipsAdapter);
                                    tipsAdapter.setAdapterData(adapterList);
                                    tipsAdapter.notifyDataSetChanged();
                                } catch (Exception e){
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //handle databaseError
                            }
                        });
            }catch (Exception e){
            }
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
