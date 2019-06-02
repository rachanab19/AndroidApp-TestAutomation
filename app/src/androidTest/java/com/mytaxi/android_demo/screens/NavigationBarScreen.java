package com.mytaxi.android_demo.screens;

import android.support.test.espresso.ViewInteraction;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.model.Constants;
import com.mytaxi.android_demo.screens.actions.ElementActor;

/**
 * This class is to perform functionality available on the NAVIGATION BAR panel
 */

public class NavigationBarScreen extends ElementActor {

   public void openNavigationBar(){
         try{
             clickOnElement(findElementWithContentDescription(Constants.OPEN_NAVIGATION_BAR));
         }
        catch(Exception e){
            Log.e("@openNavigationBar()", "Unable to open Navigation Bar");
        }
    }

    public ViewInteraction getLoggedInUser(){
        openNavigationBar();
        return findElementWithId(R.id.nav_username);
    }

    public void logout(){
        try{
            //waitOnPage();
            openNavigationBar();
            //waitOnPage();
            clickOnElement(findElementWithText(Constants.LOGOUT));
        }
        catch (Exception ex){
            Log.e("@logout()", "Unable to click on logout "+ex);
        }
    }


}
