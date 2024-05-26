package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"./src/test/java/features"}
				 ,glue= {"stepDefinitions","hooks"}
				 ,monochrome = true
				 ,plugin = {"pretty","html:test-output/cucumber-html-report.html", 
						 "junit:test-output/cucumber-junit-report.xml", "summary"}
				 ,snippets = SnippetType.CAMELCASE
				 ,dryRun  = false
				 )
public class TestRunner {

}