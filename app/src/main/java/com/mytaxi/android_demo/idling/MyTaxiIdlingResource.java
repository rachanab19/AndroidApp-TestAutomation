package com.mytaxi.android_demo.idling;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

public class MyTaxiIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static MyTaxiCustomIdlingResource myTaxiCustomIdlingResource = null;

    public static void increment() {
        ((MyTaxiCustomIdlingResource) getIdlingResource()).increment();
    }

    public static void decrement() {
        ((MyTaxiCustomIdlingResource) getIdlingResource()).decrement();
    }

    public static IdlingResource getIdlingResource() {
        Log.e("RACHANA ","Resource  >>" + myTaxiCustomIdlingResource);
        if(myTaxiCustomIdlingResource==null) {
            myTaxiCustomIdlingResource = new MyTaxiCustomIdlingResource(RESOURCE);
        }
        return myTaxiCustomIdlingResource;

    }
}