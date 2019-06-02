package com.mytaxi.android_demo.screens.actions;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;

/**
 * This is generic screen level helper class having common code to locate elements and perform actions
 */
public class ElementActor {

    protected ViewInteraction findElementWithId(int elementId) {
        return onView(withId(elementId));
    }

    protected ViewInteraction findElementWithText(String text) {

        return onView(withText(text));
    }

    protected ViewInteraction findElementWithContentDescription(String withDescription) {
        return onView(withContentDescription(withDescription));
    }

    protected ViewInteraction findInRoot(ViewInteraction element) {
        return element.inRoot(RootMatchers.isPlatformPopup());
    }

    protected ViewInteraction typeTextIntoElement(ViewInteraction element, String text) {
        try {
            element = element.perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard());

        }catch (Exception ex) {
            Log.e("Test_Error:" , "PageActions->typeTextIntoElement() failed -->" +ex) ;
            element = null ;
            throw  ex;
        }
        return element;
    }

    protected ViewInteraction clickOnElement(ViewInteraction element) {
        try {
            element =  element.perform(ViewActions.click());

        } catch( Exception ex) {
            Log.e("Test_Error:", "PageActions->clickOnElement() failed -->" +ex);
            element = null ;
            throw ex;
        }
        return element;
    }

   /* protected   void waitOnPage() {
        try {
            Thread.sleep(2000);
        }catch(Exception ex) {
            Log.e("Error:" , "ElementActor.waitOnPage failed");
        }
    }*/




}
