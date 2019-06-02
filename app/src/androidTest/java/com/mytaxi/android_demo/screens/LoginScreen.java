package com.mytaxi.android_demo.screens;

import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.ViewInteraction;

import android.support.test.espresso.matcher.ViewMatchers;
import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.model.User;
import com.mytaxi.android_demo.screens.actions.ElementActor;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This class is to perform functionality available on the My Taxi LOGIN Screen
 */
public class LoginScreen extends ElementActor {

    public void login (User user) {
        try {
            typeTextIntoElement( findElementWithId(R.id.edt_username), user.getUserId() );
            typeTextIntoElement( findElementWithId(R.id.edt_password), user.getPassword() );
            clickOnElement(findElementWithId(R.id.btn_login));
            //waitOnPage();
        } catch (Exception ex) {
            Log.e("Error:", "Login Error "+ex);
        }
    }

    public ViewInteraction getLoginButton(){
        return findElementWithId(R.id.btn_login);
    }

    public boolean isScreenActivelyVisible (){

        final boolean[] isDisplayed = {true};
        onView(withId(R.id.btn_login)).withFailureHandler(new FailureHandler()
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
