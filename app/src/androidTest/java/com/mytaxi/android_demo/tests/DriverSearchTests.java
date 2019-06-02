package com.mytaxi.android_demo.tests;

import com.mytaxi.android_demo.idling.MyTaxiIdlingResource;
import com.mytaxi.android_demo.model.Constants;
import com.mytaxi.android_demo.model.Driver;
import com.mytaxi.android_demo.model.User;
import com.mytaxi.android_demo.screens.DriverProfileScreen;
import com.mytaxi.android_demo.screens.DriverSearchScreen;
import com.mytaxi.android_demo.screens.LoginScreen;

import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.intent.Intents.intended;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

/**
 * This is class for Large testcases related to Driver Search and Call functionality
 */
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverSearchTests extends BaseTest{

    public LoginScreen loginScreen;
    public DriverSearchScreen driverSearchScreen;
    public DriverProfileScreen driverProfileScreen;

    @Before
    public void beforeTest(){
        driverSearchScreen = new DriverSearchScreen();
        driverProfileScreen = new DriverProfileScreen();
        loginScreen = new LoginScreen();

        //Check the current active screen
        if (loginScreen.isScreenActivelyVisible()){
            User user = User.getUser(Constants.VALID);
            loginScreen.login(user);
        }
         Intents.init();
    }

    @Test
    public void test1_verifySearchForUnIntendedDriver(){

        Driver driver = Driver.getDriver(Constants.INVALID);
        driverSearchScreen.searchDriver(driver);

        //This is unintended Driver, hence getting back to Search screen by pressing Back button
        driverProfileScreen.getBackToSearchScreen();
        //Verifying whether we have navigated back to Search screen and Map is displayed
        driverSearchScreen.getMap().check(matches(isDisplayed()));
        }


    @Test
    public void test2_verifySearchAndCallDriver(){
        Driver driver = Driver.getDriver(Constants.VALID);
        driverSearchScreen.searchDriver(driver);
        driverProfileScreen.getDriverName().check(matches(withText(driver.getFullName())));
        //Verifying the navigation to dialer screen by Stubbing the dialer activity
        driverProfileScreen.callDriver();
        intended(toPackage("com.google.android.dialer"));
        Intents.release();
    }

}
