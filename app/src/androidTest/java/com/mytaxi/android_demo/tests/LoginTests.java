package com.mytaxi.android_demo.tests;


import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.SmallTest;
import android.widget.AlphabetIndexer;

import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

import com.mytaxi.android_demo.idling.MyTaxiCustomIdlingResource;
import com.mytaxi.android_demo.idling.MyTaxiIdlingResource;
import com.mytaxi.android_demo.model.Constants;
import com.mytaxi.android_demo.model.User;
import com.mytaxi.android_demo.screens.LoginScreen;
import com.mytaxi.android_demo.screens.DriverProfileScreen;
import com.mytaxi.android_demo.screens.DriverSearchScreen;
import com.mytaxi.android_demo.screens.NavigationBarScreen;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

/**
 * This is class for small testcases related to Login functionality
 */
@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTests extends BaseTest {

    public static DriverSearchScreen driverSearchScreen;
    public static DriverProfileScreen driverProfileScreen;
    public static LoginScreen loginScreen;
    public static NavigationBarScreen navigationBarScreen;


    @Before
    public void beforeTest() {
        loginScreen = new LoginScreen();
        driverSearchScreen = new DriverSearchScreen();
        navigationBarScreen = new NavigationBarScreen();

        if (driverSearchScreen.isScreenActivelyVisible()){
            navigationBarScreen.logout();
        }
    }

    @Test
    public void test1_verifyLoginFailForInvalidUser(){

        User user = User.getUser(Constants.INVALID);
        loginScreen.login(user);
        //Validating that we remain on the Login screen for invalid user
        loginScreen.getLoginButton().check(matches(isDisplayed()));
    }

    @Test
    public void test2_verifyLoginForInvalidUser(){

        User user = User.getUser(Constants.INVALID);
        loginScreen.login(user);
        //Validating that negative case that the user does not get login with the invalid credentials
        driverSearchScreen.getLoggedInUser().check(matches(withText(user.getUserId())));
    }

    @Test
    public void test3_verifyLoginForValidUser(){


        User user = User.getUser(Constants.VALID);
        loginScreen.login(user);
        //Validating the expected username is displayed in the navigation bar
        driverSearchScreen.getLoggedInUser().check(matches(withText(user.getUserId())));
    }

    @Test
    public void test4_verifyLogout(){

        navigationBarScreen.logout();
        //Validating that we are taken back to Login screen post logout action
        loginScreen.getLoginButton().check(matches(isDisplayed()));
    }

 }
