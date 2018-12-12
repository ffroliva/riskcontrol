package br.com.ffroliva.riskcontrol.service.impl;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.repository.FileReaderRepository;
import br.com.ffroliva.riskcontrol.service.WordReplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Services related to words replacement in a text.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
@Service
public class WordReplacementServiceImpl implements WordReplacementService {

    @Autowired
    private FileReaderRepository fileReaderRepository;

    @Override
    public String replaceWordsFromFile(String word1, String word2) throws RiskControlBusinessException {
        return fileReaderRepository.readFile().replaceAll(Pattern.quote(word1).toString(), word2);
    }
}
