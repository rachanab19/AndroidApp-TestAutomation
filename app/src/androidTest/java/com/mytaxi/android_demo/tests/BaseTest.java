package com.mytaxi.android_demo.tests;

import android.Manifest;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.idling.MyTaxiIdlingResource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.util.concurrent.TimeUnit;

/**
 * This is the parent class for the Test classes containing common functions across all the test cases
 */
public class BaseTest {

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void init(){
        IdlingRegistry.getInstance().register(MyTaxiIdlingResource.getIdlingResource());
        IdlingPolicies.setMasterPolicyTimeout(1, TimeUnit.MINUTES);
        IdlingPolicies.setIdlingResourceTimeout(1, TimeUnit.MINUTES);
    }

    @AfterClass
    public static void destroy(){
        IdlingRegistry.getInstance().unregister(MyTaxiIdlingResource.getIdlingResource());
    }

}
