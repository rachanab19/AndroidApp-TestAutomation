package com.mytaxi.android_demo.screens;

import android.view.View;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import org.hamcrest.Matcher;
import android.util.Log;
import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.model.Constants;
import com.mytaxi.android_demo.model.Driver;

/**
 * This class is to perform functionality available on the Driver SEARCH Screen
 */
public class DriverSearchScreen extends NavigationBarScreen {


    public String searchDriver(Driver driver) {

        try {
            clickOnElement(findElementWithId(R.id.textSearch));
            //waitOnPage();

            //Entering the initials of the driver
            typeTextIntoElement(findElementWithId(R.id.textSearch),driver.getInitials());

            //Selecting the driver from combobox
            clickOnElement(findInRoot(findElementWithText(driver.getFullName()) ));
            //waitOnPage();

        } catch (Exception ex) {
            Log.e("Error:", "Main_Bar_Screen.searchDriver failed "+ex);
        }
        return null;
    }

    public ViewInteraction getMap(){
        return findElementWithId(R.id.map);
    }

    public boolean isScreenActivelyVisible (){

        final boolean[] isDisplayed = {true};
        onView(withText(Constants.APPNAME)).withFailureHandler(new FailureHandler()
        {
            @Override
            public void handle(Throwable error, Matcher<View> viewMatcher)
            {
                isDisplayed[0] = false;
            }
        }).check(matches(ViewMatchers.isDisplayed()));
        return isDisplayed[0];

    }


}
