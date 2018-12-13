package br.com.ffroliva.riskcontrol.service.impl;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.MatrixService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MatrixServiceImpl implements MatrixService {

    public static final String MY_CHAR = "1";
    public static final String ENEMY_CHAR = "2";

    private int maxColumIndex = 0;
    private int maxLineIndex = 0;


    @Override
    public int closestEnemyII(String[] strArr) {
        log.debug("The games initial input is:");
        Arrays.stream(strArr).forEach(log::debug);
        //Map<Integer,Integer> myPosition = mapMyPosition(strArr);
        int[][] myPosition = myPosition(strArr);
        log.debug(String.format("My index position is: [%s][%s]", myPosition[0][0],myPosition[0][1]));
        Map<Integer, List<Integer>> enemiesIndexPositions = null;
        try {
            enemiesIndexPositions = enemiesIndexPositions(strArr);
        } catch (RiskControlBusinessException e) {
            log.debug(e.getMessage());
            return 0;
        }
        log.debug(String.format("My enemies index position is: %s", enemiesIndexPositions));
        maxColumIndex = strArr[0].length() - 1;
        log.debug(String.format("Max Column index: %s", maxColumIndex));
        maxLineIndex = strArr.length - 1;
        log.debug((String.format("Max Line index: %s", maxLineIndex)));

        return searchClosestEnemy(myPosition, enemiesIndexPositions, maxLineIndex, maxColumIndex);
    }

    private int searchClosestEnemy(int[][] myPosition, Map<Integer, List<Integer>> enemiesIndexPositions, int maxLineIndex, int maxColumIndex) {
        int minColumIndex = 0;
        int minLineIndex = 0;
        int closestEnemy = 0;
        int columnDistance = 0;
        int lineDistance = 0;
        int columnMidDistance = (maxColumIndex + 1) / 2;
        int lineMidDistance = (maxLineIndex + 1) / 2;
        int enemyLineIndex = 0;
        int enemyColumIndex = 0;
        int myLineIndex = 0;
        int myColumIndex = 0;
        int lateralDistance = 0;
        int depthDistance = 0;
        for (Map.Entry<Integer, List<Integer>> enemyPosition : enemiesIndexPositions.entrySet()) {
            enemyLineIndex = enemyPosition.getKey();
            myLineIndex = myPosition[0][0];
            myColumIndex = myPosition[0][1];
            for (int i = 0; i < enemiesIndexPositions.get(enemyLineIndex).size(); i++) {
                enemyColumIndex = enemiesIndexPositions.get(enemyLineIndex).get(i);
                lateralDistance = Math.abs(enemyColumIndex - myColumIndex);
                columnDistance = getDistance(
                        enemyColumIndex,
                        maxColumIndex,
                        minColumIndex,
                        columnMidDistance,
                        myColumIndex,
                        lateralDistance);

                depthDistance = Math.abs(enemyLineIndex - myLineIndex);
                lineDistance = getDistance(
                        enemyLineIndex,
                        maxLineIndex,
                        minLineIndex,
                        lineMidDistance,
                        myLineIndex,
                        depthDistance);

                if (closestEnemy == 0) {
                    closestEnemy = columnDistance + lineDistance;
                } else if (closestEnemy > (columnDistance + lineDistance)) {
                    closestEnemy = columnDistance + lineDistance;
                }
            }
        }
        log.debug(String.format("Closest Enemy: %s",closestEnemy));
        return closestEnemy;
    }

    private int getDistance(int enemyIndex,
                            int maxIndex,
                            int minIndex,
                            int midIndex,
                            int myIndex,
                            int distanceFromEnemy) {
        int shortesPath = 0;
        if (midIndex+1 > distanceFromEnemy) {
            shortesPath = distanceFromEnemy;
        } else {
            shortesPath = midIndex -1;
        }
        if (enemyIndex == maxIndex
                && myIndex == minIndex) {
            shortesPath = 1;
        }
        // mid distance ether way
        if (midIndex == distanceFromEnemy) {
            shortesPath = midIndex;
        }

        return shortesPath;
    }

    private Map<Integer, Integer> mapMyPosition(String[] strArray) {
        Map<Integer, Integer> myPosition = new HashMap<>();
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].contains(MY_CHAR)) {
                myPosition.put(i, strArray[i].indexOf(MY_CHAR));
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
