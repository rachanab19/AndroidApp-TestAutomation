package com.mytaxi.android_demo.utils.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mytaxi.android_demo.idling.MyTaxiIdlingResource;
import com.mytaxi.android_demo.models.User;

public class SharedPrefStorage implements Storage {

    private static final String PREFS_NAME = "MytaxiPrefs";

    private SharedPreferences mSharedPref;

    public SharedPrefStorage(Context context) {
        this.mSharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public User loadUser() {
        Log.e("RACHANA ", "SharedPrefStorage.loadUser()");
        MyTaxiIdlingResource.increment(); //ADDED BY RACHANA - Counter base idling increment
        String username = mSharedPref.getString("username", null);
        String salt = mSharedPref.getString("salt", null);
        String sha256 = mSharedPref.getString("sha256", null);
        if (username != null && salt != null && sha256 != null) {
            MyTaxiIdlingResource.decrement(); //ADDED BY RACHANA - Counter base idling decrement
            return new User(username, salt, sha256);
        } else {
            MyTaxiIdlingResource.decrement(); //ADDED BY RACHANA - Counter base idling decrement
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString("username", user.getUsername());
        editor.putString("salt", user.getSalt());
        editor.putString("sha256", user.getSHA256());
        editor.apply();
    }

    @Override
    public void resetUser() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove("username");
        editor.remove("salt");
        editor.remove("sha256");
        editor.apply();
    }

}
