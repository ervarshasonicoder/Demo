package com.example.myapplication.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.myapplication.activity.LoginActivity;


public class UserSessionManager {
    private static final String SHARED_PREF_NAME = "UserSessionManager";
    private static final String KEY_USERID = "keyuserid";

    private static UserSessionManager mInstance;
    private static Context mCtx;

    public UserSessionManager(Context context) {
        mCtx = context;
    }

    public static synchronized UserSessionManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserSessionManager(context);
        }
        return mInstance;
    }


    //this method will store the user data in shared preferences
    public void setUserid(String userid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, userid);
        editor.apply();
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERID, null) != null;
    }

    //this method will give the logged in user
    public String getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERID, null);
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
