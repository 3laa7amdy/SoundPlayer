package com.alaa7amdy.soundplayer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Akshay Raj on 7/28/2016.
 * Snow Corporation Inc.
 * www.snowcorp.org
 */
public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME[] = {"Slider","Auth"};



    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_Log_IN = "IsLogIn";

    private static final String ID = "ID";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String ADDRESS = "ADDRESS";
    private static final String PHONE = "PHONE";
    private static final String IMAGE = "IMAGE";
    private static final String EMAIL = "EMAIL";

    public String getID() {

        return pref.getString(ID, ID);

    }

    public String getUSERNAME() {
        return pref.getString(USERNAME, USERNAME);    }

    public String getPASSWORD() {
        return pref.getString(PASSWORD, PASSWORD);    }

    public String getADDRESS() {
        return pref.getString(ADDRESS, ADDRESS);    }

    public String getPHONE() {
        return pref.getString(PHONE, PHONE);
    }

    public String getIMAGE() {
        return pref.getString(IMAGE, IMAGE);    }

    public String getEMAIL() {
        return pref.getString(EMAIL, EMAIL);
    }

    public Session(Context context , int index) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME[index], PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setUserData(String id,String username,String password,String email,String phone,String address,String image) {
        editor.putString(ID,id );
        editor.putString(USERNAME,username );
        editor.putString(PASSWORD,password );
        editor.putString(EMAIL,email );
        editor.putString(PHONE,phone );
        editor.putString(ADDRESS,address );
        editor.putString(IMAGE,image );

        editor.commit();
    }




    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLogIn() {
        return pref.getBoolean(IS_Log_IN, true);
    }

    public void setLogIn(boolean isLogIn) {
        editor.putBoolean(IS_Log_IN, isLogIn);
        editor.commit();
    }

    public void clearUserData() {
       editor.clear();
       editor.commit();
    }

}