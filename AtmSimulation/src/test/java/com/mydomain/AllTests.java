package com.mydomain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mydomain.model.AtmTest;
import com.mydomain.model.BanknoteTest;

@RunWith(Suite.class)
@SuiteClasses({ BanknoteTest.class, AtmTest.class })
public class AllTests {

}
