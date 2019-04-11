import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/employee.feature",
        glue = "com.automation.steps",
        format={"pretty","html:cucumber-html-reports","json:cucumber-html-reports/cucumber.json"},
        dryRun=false
)
public class AllTests{

}
