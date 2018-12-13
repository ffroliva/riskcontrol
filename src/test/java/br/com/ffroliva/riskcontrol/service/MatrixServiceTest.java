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
    static final String[] MATRIX_ENEMY_SAMELINE = new String[]{"0000", "1200", "0000", "0000"};
    static final String[] MATRIX_6_9 = new String[]{"000000000", "001000000", "000000000", "000000002"};
    static final String[] MATRIX_6_9_EDGE = new String[]{"100000000", "000000000", "000000000", "000000002"};

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
        Assert.assertEquals(2, matrixService.closestEnemyII(MATRIX_MULTI_ENEMIES_SAME_LINE));
    }

    @Test
    public void testMatrix6x9(){
        Assert.assertEquals(5, matrixService.closestEnemyII(MATRIX_6_9));
    }

    @Test
    public void testMatrix6x9Edge(){
        Assert.assertEquals(2, matrixService.closestEnemyII(MATRIX_6_9_EDGE));
    }

    @Test
    public void testMatrixEnemySameLine(){
        Assert.assertEquals(1, matrixService.closestEnemyII(MATRIX_ENEMY_SAMELINE));

    }
}
