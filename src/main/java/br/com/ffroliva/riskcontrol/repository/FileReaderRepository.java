package br.com.ffroliva.riskcontrol.repository;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;

/**
 * Repository that reads files.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
public interface FileReaderRepository {

    public String readFile() throws RiskControlBusinessException;


}