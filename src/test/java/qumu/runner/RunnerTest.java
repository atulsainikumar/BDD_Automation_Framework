package qumu.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "qumu.stepdefinitions",
                "qumu.hooks",
                "qumu.retry"
        },
        tags = "@UI or @API",
        monochrome = true,
        plugin = {
                "pretty",
                "html:test-output/cucumber-report.html",
                "json:test-output/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
@Listeners(qumu.retry.RetryListener.class)
public class RunnerTest extends AbstractTestNGCucumberTests {

}
