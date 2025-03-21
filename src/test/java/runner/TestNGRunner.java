package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/java/feature/login.feature",
    glue = "stepDef",
    plugin = {"pretty"},
    monochrome = true,
    tags = "@SmokeTest"
)

@Test
public class TestNGRunner extends AbstractTestNGCucumberTests {
}