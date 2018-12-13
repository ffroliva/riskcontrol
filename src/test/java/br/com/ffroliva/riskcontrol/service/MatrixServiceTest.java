package br.com.ffroliva.riskcontrol.service;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.impl.MatrixServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class MatrixServiceTest {

    static final String[] MATRIX = new String[]{"0000", "1000", "0002", "0002"};
    static final String[] MATRIX_WITHOUT_ENEMIES = new String[]{"0000", "1000", "0000", "0000"};
    static final String[] MATRIX_MULTI_ENEMIES_SAME_LINE = new String[]{"0000", "1000", "0002", "0022"};

    MatrixService matrixService = new MatrixServiceImpl();

    @Test
    public void testMatrix() {
        Assert.assertEquals(2, matrixService.closestEnemyII(MATRIX));
    }

    @Test
    public void testMatrixWithoutEnemies(){
        Assert.assertEquals(0, matrixService.closestEnemyII(MATRIX_WITHOUT_ENEMIES));
    }

    @Test
    public void testMatrixEnemiesSameLine(){
        Assert.assertEquals(0, matrixService.closestEnemyII(MATRIX_MULTI_ENEMIES_SAME_LINE));
    }
}
