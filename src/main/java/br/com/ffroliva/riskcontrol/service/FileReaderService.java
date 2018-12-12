package br.com.ffroliva.riskcontrol.service;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;

public interface FileReaderService {

    String readFile() throws RiskControlBusinessException;
}
