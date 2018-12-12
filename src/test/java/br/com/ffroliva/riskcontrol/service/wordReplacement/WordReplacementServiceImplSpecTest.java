package br.com.ffroliva.riskcontrol.service.wordReplacement;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/br/com/ffroliva/riskcontrol/service/wordReplacement.feature",
        plugin = {"pretty", "html:target/cucumber/wordReplacement"}
)
@Slf4j
public class WordReplacementServiceImplSpecTest {
}
