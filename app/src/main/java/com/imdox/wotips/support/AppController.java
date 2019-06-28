package com.imdox.wotips.support;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Locale;


public class AppController extends Application {

    private static AppController mInstance;
    public static final int TYPE_TIPS=0,USER_TIPS_TYPE=1;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    //Setting application font Bold,Regular
    public static Typeface getDefaultBoldFont(Context context) {
        AssetManager am = context.getApplicationContext().getAssets();
        return Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "Montserrat-SemiBold.otf"));//Roboto-Medium.ttf
    }

    public static Typeface getDefaultFont(Context context) {
        AssetManager am = context.getApplicationContext().getAssets();
        return Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "OpenSans-Regular.ttf"));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
