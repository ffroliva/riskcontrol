package br.com.ffroliva.riskcontrol.repository.impl;


import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.repository.FileReaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository responsible reading a text file.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
@Slf4j
@Repository
public class FileReaderReaderRepositoryImpl implements FileReaderRepository {

    /**
     * Reads a file called text.txt and convert its content to a String.
     *
     * @return a string will all the lines read from the file.
     */
    @Override
    public String readFile() throws RiskControlBusinessException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("text.txt");
        Path path;
        try {
            path = Paths.get(url.toURI());
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            lines.stream().forEach(sb::append);
            log.debug(String.format("Text read from file: %s",sb.toString()));
            return sb.toString();
        } catch (URISyntaxException | IOException e) {
            throw new RiskControlBusinessException("Erro processing text.txt file.");
        }
    }


}