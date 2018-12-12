package br.com.ffroliva.riskcontrol.service.wordReplacement;

import br.com.ffroliva.riskcontrol.AppConfiguration;
import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.WordReplacementService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Behavior Driven Step Definions.
 */

@Slf4j
@ContextConfiguration(classes = {AppConfiguration.class})
public class WordReplacementServiceStepDefs {

    @Autowired
    private WordReplacementService wordReplacementService;

    private String text = null;
    private String word1 = null;
    private String word2 = null;

    @Given("that I would like to replace the word {string}")
    public void that_I_would_like_to_replace_the_word(String word1) {
        this.word1 = word1;
    }

    @And("for the word {string}")
    public void for_the_word(String word2) {
        this.word2 = word2;
    }

    @When("the word replacement service is executed")
    public void the_word_replacement_service_is_executed() throws RiskControlBusinessException {
        text = wordReplacementService.replaceWordsFromFile(word1,word2);
        log.debug(String.format("Text after the words replacement: %s",text));
    }

    @Then("the resulted string should not have the word {string} in the final text")
    public void the_resulted_string_should_not_have_the_word_in_the_final_text(String word1) {
        Assert.assertFalse(text.contains(word1));
    }

}
