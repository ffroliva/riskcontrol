package br.com.ffroliva.riskcontrol.repository;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.repository.impl.FileReaderReaderRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class FileReaderRepositoryTest {

    FileReaderRepository fileReaderRepository;

    @Before
    public void setup() {
        fileReaderRepository = new FileReaderReaderRepositoryImpl();
    }

    @Test
    public void getCustomersFromFile() throws RiskControlBusinessException {
        Assert.assertNotNull(fileReaderRepository.readFile());
    }
}
