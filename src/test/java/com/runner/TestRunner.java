package com.runner; 

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/Features"}, glue = {"com.stepdef"}, dryRun = false, monochrome = true)
public class TestRunner {

	
}
