package br.com.ffroliva.riskcontrol.service;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;

/**
 * Interface with methods related  Words Replacement Service.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
public interface WordReplacementService {
    /**
     * Replace words from text.txt file.
     *
     * @param word1 word to be placed.
     * @param word2  word to be replaced with
     * @return text with the words replaced.
     */
    public String replaceWordsFromFile(String word1, String word2) throws RiskControlBusinessException;
}
