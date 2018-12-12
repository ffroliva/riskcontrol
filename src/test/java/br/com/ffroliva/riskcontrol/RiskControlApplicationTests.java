package br.com.ffroliva.riskcontrol;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.WordReplacementService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RiskControlApplicationTests {

    @Autowired
    WordReplacementService wordReplacementService;

    @Test
    public void contextLoads() throws RiskControlBusinessException {
        log.info("Executing metodo ");
        log.info(wordReplacementService.replaceWordsFromFile("name","Flavio"));
        log.info("End of execution of method findCustomersWithinDistance");
    }

}
