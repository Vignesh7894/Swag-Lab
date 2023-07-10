package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.utility.JVMReport;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src\\test\\resources\\Features",glue= {"com.stepdefinition"}, 
dryRun=false,monochrome=true,plugin= {"json:src\\test\\resources\\Reports\\report.json"})
public class Runner {

	@AfterClass
	public static void afterCass() {
		JVMReport.generateReport(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\report.json");
	}
}
