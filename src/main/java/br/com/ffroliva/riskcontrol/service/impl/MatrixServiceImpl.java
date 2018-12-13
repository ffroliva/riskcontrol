package br.com.ffroliva.riskcontrol.service.impl;

import br.com.ffroliva.riskcontrol.exceptions.RiskControlBusinessException;
import br.com.ffroliva.riskcontrol.service.MatrixService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class MatrixServiceImpl implements MatrixService {

    public static final String MY_CHAR = "1";
    public static final String ENEMY_CHAR = "2";

    private int maxColumIndex = 0;
    private int maxLineIndex = 0;

    /**
     * Searches a Array of string and finds the shortest path from my position and my enemy position.
     * @param strArr a string array with the following format.
     *
     *               Exemple: 0000
     *                        1000
     *                        0002
     *                        0000
     *
     * @return return the shortest distance between my position (number "1" in the array) and my ememies' position
     * (number "2" in the array)
     * if the array does not have a one number 1 and one number 1 the result should be 0.
     */
    @Override
    public int closestEnemyII(String[] strArr) {
        log.info("####CLOSEST_ENEMY_II(BEGIN)####");
        log.info("The games initial input is:");
        Arrays.stream(strArr).forEach(log::info);
        int[][] myPosition;
        int[][] enemyPosition;
        Map<Integer, List<Integer>> enemiesIndexPositions = null;
        try {
            myPosition = getPosition(strArr, MY_CHAR);
            log.info(String.format("My index position is: [%s][%s]", myPosition[0][0], myPosition[0][1]));
            enemyPosition = getPosition(strArr, ENEMY_CHAR);
            log.info(String.format("Enemy's position is: [%s][%s]", enemyPosition[0][0], enemyPosition[0][1]));
        } catch (RiskControlBusinessException e) {
            log.info(e.getMessage());
            return 0;
        }
        log.info(String.format("My enemies index position is: %s", enemiesIndexPositions));
        maxColumIndex = strArr[0].length() - 1;
        log.info(String.format("Max Column index: %s", maxColumIndex));
        maxLineIndex = strArr.length - 1;
        log.info((String.format("Max Line index: %s", maxLineIndex)));
        int closestEmeny = calculateClosestEnemy(myPosition, enemyPosition, maxLineIndex, maxColumIndex);
        log.info("####CLOSEST_ENEMY_II(END)####");
        return closestEmeny;
    }

    /**
     * Calculate's the closest enemy from list.
     * @param myPosition 2D array with the index position from where the distance should be calculated from.
     * @param enemyPostion 2D array with the index position of the emeny
     * @param maxLineIndex max index from a line of the string array.
     * @param maxColumIndex max index from a column of one given string of the string array.
     * @return the calculated the closest distance beetween the myPosition and enemyPosition.
     */
    private int calculateClosestEnemy(int[][] myPosition, int[][] enemyPostion, int maxLineIndex, int maxColumIndex) {
        int closestEnemy = 0;
        int columnDistance = 0;
        int lineDistance = 0;
        columnDistance = getDistance(enemyPostion[0][1], maxColumIndex, 0, myPosition[0][1]);
        lineDistance = getDistance(enemyPostion[0][0], maxLineIndex, 0, myPosition[0][0]);
        if (closestEnemy == 0) {
            closestEnemy = columnDistance + lineDistance;
        } else if (closestEnemy > (columnDistance + lineDistance)) {
            closestEnemy = columnDistance + lineDistance;
        }
        log.info(String.format("Closest Enemy: %s", closestEnemy));
        return closestEnemy;
    }

    /**
     * find the shortest distance of a given element
     *
     * @param enemyIndex the distance of the enemy related to the maxIndex.
     * @param maxIndex   extracted from the String array.
     * @param minIndex   will always be zero.
     * @param myIndex    base point to calculate te shortest path to the enemy.
     * @return the shortest distance between myIndex and the enemyIndex.
     */
    private int getDistance(int enemyIndex,
                            int maxIndex,
                            int minIndex,
                            int myIndex) {
        int midIndex = (maxIndex + 1) / 2;
        int distance = Math.abs(enemyIndex - myIndex);
        int shortesPath = 0;
        if (midIndex + 1 > distance) {
            shortesPath = distance;
        } else {
            shortesPath = midIndex - 1;
        }
        if (enemyIndex == maxIndex
                && myIndex == minIndex) {
            shortesPath = 1;
        }
        // mid distance ether way
        if (midIndex == distance) {
            shortesPath = midIndex;
        }
        return shortesPath;
    }

    /**
     * get the index position of the given element
     *
     * @param strArray the string array to be searched.
     * @param element  the element to be found.
     * @return a 2D array with line and column index.
     * @throws RiskControlBusinessException if no element is found in the searched array.
     */
    private int[][] getPosition(String[] strArray, String element) throws RiskControlBusinessException {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].contains(element)) {
                return new int[][]{{i, strArray[i].indexOf(element)}};
            }
        }
        throw new RiskControlBusinessException(
                String.format("Invalid matrix. There matrix should have one char %s", element));
    }


}
