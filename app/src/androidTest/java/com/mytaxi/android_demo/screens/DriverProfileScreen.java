package com.mytaxi.android_demo.screens;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.screens.actions.ElementActor;
import android.util.Log;

/**
 * This class is to perform functionality available on the Driver PROFILE Screen
 */
public class DriverProfileScreen extends ElementActor {

    public ViewInteraction getDriverName(){

        return findElementWithId(R.id.textViewDriverName);
    }

    public void callDriver () {
        try {
            clickOnElement(findElementWithId(R.id.fab));
        } catch (Exception ex) {
            Log.e("Test_Error:", "DriverProfileScreen.callDriver()-->" +ex);
        }
    }

    public void getBackToSearchScreen(){
        Espresso.pressBack();
    }



}
