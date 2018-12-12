package br.com.ffroliva.riskcontrol.controller;


import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.FileReaderService;
import br.com.ffroliva.riskcontrol.service.WordReplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that controls de APIs related to words
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
@RestController
public class TextWordReplacementController {

    private static final String UNEXPECTED_ERROR = new StringBuilder("Ops, an unexpected error ocurred ")
            .append("while processing you request. Sorry for the inconvinience. Please try again later.")
            .toString();

    @Autowired
    WordReplacementService wordReplacementService;

    @Autowired
    FileReaderService fileReaderService;

    @RequestMapping("/text")
    public ResponseEntity<String> fetchWords() {
        try {
            return ResponseEntity.ok().body(fileReaderService.readFile());
        } catch (RiskControlBusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(UNEXPECTED_ERROR);
        }
    }

    @RequestMapping("/text/replace/{word1}/word2")
    public ResponseEntity<String>
    replaceWords(@PathVariable(value = "word1") String word1, @PathVariable(value = "word2") String word2) {
        try {
            return ResponseEntity.ok().body(wordReplacementService.replaceWordsFromFile(word1, word2));
        } catch (RiskControlBusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(UNEXPECTED_ERROR);
        }
    }
}
