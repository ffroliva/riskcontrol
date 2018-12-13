package br.com.ffroliva.riskcontrol.service.impl;

import br.com.ffroliva.riskcontrol.RiskControlApplication;
import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.MatrixService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
public class MatrixServiceImpl implements MatrixService {

    public static final String MY_CHAR = "1";
    public static final String ENEMY_CHAR = "2";

    private int minColumIndex = 0;
    private int maxColumIndex = 0;
    private int minLineIndex = 0;
    private int maxLineIndex = 0;


    @Override
    public int closestEnemyII(String[] strArr) {
        log.debug("The games initial input is:");
        Arrays.stream(strArr).forEach(log::debug);
        //Map<Integer,Integer> myPosition = mapMyPosition(strArr);
        int[][] myPosition = myPosition(strArr);
        log.debug(String.format("My index position is: %s", myPosition));
        Map<Integer, List<Integer>> enemiesIndexPositions = null;
        try {
            enemiesIndexPositions = enemiesIndexPositions(strArr);
        } catch (RiskControlBusinessException e) {
            log.debug(e.getMessage());
            return 0;
        }
        log.debug(String.format("My enemies index position is: %s", enemiesIndexPositions));
        maxColumIndex = strArr[0].length()-1;
        log.debug(String.format("Max Column index: %s",maxColumIndex));
        maxLineIndex = strArr.length-1;
        log.debug((String.format("Max Line index: %s",maxLineIndex)));

        return searchClosestEnemy(myPosition,enemiesIndexPositions,maxLineIndex, maxColumIndex);
    }

    private int searchClosestEnemy(int[][] myPosition, Map<Integer, List<Integer>> enemiesIndexPositions, int maxLineIndex, int maxColumIndex) {
        int closestEnemy = 0;
        int countColudmnDistance = 0;
        int countLineDistance = 0;
        for(Map.Entry<Integer,List<Integer>> enemyPosition : enemiesIndexPositions.entrySet()){
            int enemyLineIndex = enemyPosition.getKey();
            int enemyColumIndex = 0;
            int myLineIndex = myPosition[0][0];
            int myColumIndex = myPosition[0][1];
            for(int i = 0; i < enemiesIndexPositions.get(enemyLineIndex).size(); i++){
                enemyColumIndex = enemiesIndexPositions.get(enemyLineIndex).get(i);
                if(enemyColumIndex > myColumIndex){
                    countColudmnDistance = enemyColumIndex - myColumIndex;
                } else {
                    countColudmnDistance = myColumIndex - enemyColumIndex;
                }
                if(enemyLineIndex > myLineIndex){
                    countLineDistance = enemyLineIndex - myLineIndex;
                }
                if(closestEnemy == 0) {
                    closestEnemy = countColudmnDistance + countLineDistance;
                } else if(closestEnemy < (countColudmnDistance + countLineDistance)){
                    closestEnemy = countColudmnDistance + countLineDistance;
                }
            }
        }
        return closestEnemy;
    }

    private Map<Integer,Integer> mapMyPosition(String[] strArray) {
        Map<Integer, Integer> myPosition = new HashMap<>();
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].contains(MY_CHAR)) {
                myPosition.put(i,strArray[i].indexOf(MY_CHAR));
                return myPosition;
            }
        }
        throw new IllegalArgumentException("Invalid matrix. There matrix should have at list one char 1");
    }

    private int[][] myPosition(String[] strArray) {
        Map<Integer, Integer> myPosition = new HashMap<>();
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].contains(MY_CHAR)) {
                return new int[][]{{i, strArray[i].indexOf(MY_CHAR)}};
            }
        }
        throw new IllegalArgumentException("Invalid matrix. There matrix should have at list one char 1");
    }
    private Map<Integer, List<Integer>> enemiesIndexPositions(String[] strArr) throws RiskControlBusinessException {
        Map<Integer, List<Integer>> enemiesIndexPositions = new HashMap<>();
        List<Integer> indexPositions = new ArrayList<>();
        int enemiesCounted = 0;
        for (int i = 0; i < strArr.length; i++) {
            int index = strArr[i].indexOf(ENEMY_CHAR);
            int matchLength = ENEMY_CHAR.length();
            while (index >= 0) {  // indexOf returns -1 if no match found
                indexPositions.add(index);
                index = strArr[i].indexOf(ENEMY_CHAR, index + matchLength);
                enemiesCounted++;
            }
            if (indexPositions.size() > 0) {
                enemiesIndexPositions.put(i, indexPositions);
                indexPositions = new ArrayList<>();
            }
        }
        if (enemiesCounted == 0) {
            throw new RiskControlBusinessException(new StringBuilder("The inputed matrix does not have enemies.")
                    .append("Please provide a matrix with at list on digit 2.").toString());
        }
        log.debug(String.format("Enemies found: %s", enemiesCounted));
        return enemiesIndexPositions;
    }

}
