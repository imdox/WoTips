package com.imdox.wotips.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imdox.wotips.support.AppController;
import com.imdox.wotips.tips.AddTipsActivity;
import com.imdox.wotips.R;
import com.imdox.wotips.tips.TipsActivity;
import com.imdox.wotips.support.AboutActivity;
import com.imdox.wotips.tips.UserTipsActivity;


public class MainActivity extends AppCompatActivity {

    private int index = 0;
    private InterstitialAd mInterstitialAd;
    //cardEMGInfo,cardGraphics

    @Override
    protected void onResume() {
        super.onResume();
        getNodeCount();
 /*       if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            index = 0;
        } else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            loadData();
        }*/
    }

    private AdView adView;
    private  DatabaseReference databaseRefObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseRefObject = FirebaseDatabase.getInstance().getReference(getString(R.string.tagTipsNode));


        (findViewById(R.id.tipsHealth)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(0);
            }
        });

        (findViewById(R.id.tipsSafety)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(1);
            }
        });

        (findViewById(R.id.tipsPregnancy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(2);
            }
        });

        (findViewById(R.id.tipsPeriod)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(3);
            }
        });

        (findViewById(R.id.tipsFashion)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(4);
            }
        });

        (findViewById(R.id.tipsBeauty)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(5);
            }
        });

        (findViewById(R.id.tipsOthers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(6);
            }
        });

        (findViewById(R.id.cardAddTips)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTipsActivity.class));
            }
        });

        (findViewById(R.id.cardTipsList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserTipsActivity.class));
            }
        });


        ((TextView)findViewById(R.id.txtEmG)).setTypeface(AppController.getDefaultBoldFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtEmGValue)).setTypeface(AppController.getDefaultFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtImages)).setTypeface(AppController.getDefaultBoldFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtImagesValue)).setTypeface(AppController.getDefaultFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtTipsCount)).setTypeface(AppController.getDefaultBoldFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtTipsValue)).setTypeface(AppController.getDefaultFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtAddTips)).setTypeface(AppController.getDefaultBoldFont(MainActivity.this));
        ((TextView)findViewById(R.id.txtAddTipsValue)).setTypeface(AppController.getDefaultFont(MainActivity.this));

        //((TextView)findViewById(R.id.txtTipsCount)).setText("User Tips ("+getNodeCount()+")");



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

    private long totalCount = 0;
    private void getNodeCount(){
        try{
            databaseRefObject.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            totalCount = dataSnapshot.getChildrenCount();
                        try{
                            ((TextView)findViewById(R.id.txtTipsCount)).setText("User Tips ("+totalCount+")");
                        }catch (Exception e){
                        }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }
                    });
        }catch (Exception e){
        }
    }

    private void redirectActivity(int tipsIndex){
        Intent intent = new Intent(MainActivity.this,TipsActivity.class);
        intent.putExtra("tipsIndex", tipsIndex);
        startActivity(intent);
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
