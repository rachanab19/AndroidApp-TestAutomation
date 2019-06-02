package com.mytaxi.android_demo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * This is suite class which triggers all the test classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTests.class,
        DriverSearchTests.class
})
public class MyTaxiDemoTestsuite {
}
