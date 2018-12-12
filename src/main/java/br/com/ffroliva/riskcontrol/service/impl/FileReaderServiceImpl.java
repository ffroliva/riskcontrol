package br.com.ffroliva.riskcontrol.service.impl;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.repository.FileReaderRepository;
import br.com.ffroliva.riskcontrol.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services with methods that manipulates files.
 */
@Service
public class FileReaderServiceImpl implements FileReaderService {

    @Autowired
    FileReaderRepository fileReaderRepository;

    /**
     *
     * @return a string with all lines read from the file.
     */
    @Override
    public String readFile() throws RiskControlBusinessException {
        return fileReaderRepository.readFile();
    }
}
