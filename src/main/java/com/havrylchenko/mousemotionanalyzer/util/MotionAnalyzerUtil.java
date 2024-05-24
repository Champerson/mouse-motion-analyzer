package com.havrylchenko.mousemotionanalyzer.util;

import org.apache.commons.lang3.IntegerRange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MotionAnalyzerUtil {

    private static Map<IntegerRange, Character> intervals = new HashMap<>();
    private List<Character> upperCaseAlphabet = new ArrayList<>();
    private List<Character> lowerCaseAlphabet = new ArrayList<>();

    public MotionAnalyzerUtil() {
        setEnglishAlphabets();
        setupIntervals();
    }

    public static List<Character> convertDerivativesListToLingusticList(List<Integer> derivativesList) {
        return derivativesList.stream()
                .flatMap(number -> intervals
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().contains(number))
                        .map(Map.Entry::getValue))
                .toList();
    }

    public static List<Integer> calculateDerivativeList(List<Integer> coordinatesList) {
        int index = 0;
        List<Integer> derivitiveList = new ArrayList<>();

        while (index + 1 <= coordinatesList.size() - 1) {
            int derivative = Math.abs(coordinatesList.get(index + 1) - coordinatesList.get(index));
            derivitiveList.add(derivative);
            index += 2;
        }

        return derivitiveList;
    }

    public static int[][] createCharacterMatrix(List<Character> uppercaseList, List<Character> lowercaseList) {
        int size = 26;
        int[][] matrix = new int[size][size];

        for (int i = 0; i < uppercaseList.size(); i++) {
            char upper = uppercaseList.get(i);
            char lower = lowercaseList.get(i);

            int upperIndex = upper - 'A';
            int lowerIndex = lower - 'a';

            matrix[lowerIndex][upperIndex]++;
        }

        return matrix;
    }

    public static double compareMatrices(int[][] matrix1, int[][] matrix2) {
        int sumOfSquares1 = calculateSumOfSquares(matrix1);
        int sumOfSquares2 = calculateSumOfSquares(matrix2);

        int difference = Math.abs(sumOfSquares1 - sumOfSquares2);

        return Math.sqrt(difference);
    }

    public static int calculateSumOfSquares(int[][] matrix) {
        int sumOfSquares = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sumOfSquares += matrix[i][j] * matrix[i][j];
            }
        }
        return sumOfSquares;
    }

    private void setEnglishAlphabets() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            upperCaseAlphabet.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            lowerCaseAlphabet.add(ch);
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void setupIntervals() {
        intervals.put(IntegerRange.of(-1, 0), upperCaseAlphabet.get(0)); //1 A
        intervals.put(IntegerRange.of(1, 5), upperCaseAlphabet.get(1)); //5 B
        intervals.put(IntegerRange.of(6, 15), upperCaseAlphabet.get(2)); //10 C
        intervals.put(IntegerRange.of(16, 30), upperCaseAlphabet.get(3)); //15 D
        intervals.put(IntegerRange.of(31, 50), upperCaseAlphabet.get(4)); //20 E
        intervals.put(IntegerRange.of(51, 75), upperCaseAlphabet.get(5)); //25 F
        intervals.put(IntegerRange.of(76, 105), upperCaseAlphabet.get(6)); //30 G
        intervals.put(IntegerRange.of(106, 135), upperCaseAlphabet.get(7)); //35 H
        intervals.put(IntegerRange.of(136, 175), upperCaseAlphabet.get(8)); //40 I
        intervals.put(IntegerRange.of(176, 220), upperCaseAlphabet.get(9)); //45 J
        intervals.put(IntegerRange.of(221, 275), upperCaseAlphabet.get(10)); //55 K
        intervals.put(IntegerRange.of(276, 335), upperCaseAlphabet.get(11)); //60 L
        intervals.put(IntegerRange.of(336, 400), upperCaseAlphabet.get(12)); //65 M
        intervals.put(IntegerRange.of(401, 470), upperCaseAlphabet.get(13)); //70 N
        intervals.put(IntegerRange.of(471, 545), upperCaseAlphabet.get(14)); //75 O
        intervals.put(IntegerRange.of(546, 625), upperCaseAlphabet.get(15)); //80 P
        intervals.put(IntegerRange.of(626, 710), upperCaseAlphabet.get(16)); //85 Q
        intervals.put(IntegerRange.of(711, 800), upperCaseAlphabet.get(17)); //90 R
        intervals.put(IntegerRange.of(801, 895), upperCaseAlphabet.get(18)); //95 S
        intervals.put(IntegerRange.of(896, 995), upperCaseAlphabet.get(19)); //100 T
        intervals.put(IntegerRange.of(996, 1105), upperCaseAlphabet.get(20)); //110 U
        intervals.put(IntegerRange.of(1106, 1225), upperCaseAlphabet.get(21)); //120 V
        intervals.put(IntegerRange.of(1226, 1355), upperCaseAlphabet.get(22)); //130 W
        intervals.put(IntegerRange.of(1356, 1505), upperCaseAlphabet.get(23)); //140 X
        intervals.put(IntegerRange.of(1506, 1665), upperCaseAlphabet.get(24)); //150 Y
        intervals.put(IntegerRange.of(1666, 1920), upperCaseAlphabet.get(25)); //255 Z

        intervals.put(IntegerRange.of(-1, 0), lowerCaseAlphabet.get(0)); //1
        intervals.put(IntegerRange.of(1, 4), lowerCaseAlphabet.get(1)); //3
        intervals.put(IntegerRange.of(5, 10), lowerCaseAlphabet.get(2)); //6
        intervals.put(IntegerRange.of(11, 19), lowerCaseAlphabet.get(3)); //9
        intervals.put(IntegerRange.of(20, 31), lowerCaseAlphabet.get(4)); //12
        intervals.put(IntegerRange.of(32, 46), lowerCaseAlphabet.get(5)); //15
        intervals.put(IntegerRange.of(47, 64), lowerCaseAlphabet.get(6)); //18
        intervals.put(IntegerRange.of(65, 85), lowerCaseAlphabet.get(7)); //21
        intervals.put(IntegerRange.of(86, 109), lowerCaseAlphabet.get(8)); //24
        intervals.put(IntegerRange.of(110, 136), lowerCaseAlphabet.get(9)); //27
        intervals.put(IntegerRange.of(167, 196), lowerCaseAlphabet.get(10)); //30
        intervals.put(IntegerRange.of(197, 230), lowerCaseAlphabet.get(11)); //33
        intervals.put(IntegerRange.of(231, 266), lowerCaseAlphabet.get(12)); //36
        intervals.put(IntegerRange.of(267, 305), lowerCaseAlphabet.get(13)); //39
        intervals.put(IntegerRange.of(306, 347), lowerCaseAlphabet.get(14)); //42
        intervals.put(IntegerRange.of(348, 392), lowerCaseAlphabet.get(15)); //45
        intervals.put(IntegerRange.of(393, 440), lowerCaseAlphabet.get(16)); //48
        intervals.put(IntegerRange.of(441, 491), lowerCaseAlphabet.get(17)); //51
        intervals.put(IntegerRange.of(492, 545), lowerCaseAlphabet.get(18)); //54
        intervals.put(IntegerRange.of(546, 602), lowerCaseAlphabet.get(19)); //57
        intervals.put(IntegerRange.of(603, 662), lowerCaseAlphabet.get(20)); //60
        intervals.put(IntegerRange.of(663, 728), lowerCaseAlphabet.get(21)); //65
        intervals.put(IntegerRange.of(729, 798), lowerCaseAlphabet.get(22)); //70
        intervals.put(IntegerRange.of(799, 878), lowerCaseAlphabet.get(23)); //80
        intervals.put(IntegerRange.of(879, 968), lowerCaseAlphabet.get(24)); //90
        intervals.put(IntegerRange.of(969, 1080), lowerCaseAlphabet.get(25)); //112
    }

    public Map<IntegerRange, Character> getIntervals() {
        return intervals;
    }

    public static void setIntervals(Map<IntegerRange, Character> intervals) {
        MotionAnalyzerUtil.intervals = intervals;
    }

    public List<Character> getUpperCaseAlphabet() {
        return upperCaseAlphabet;
    }

    public void setUpperCaseAlphabet(List<Character> upperCaseAlphabet) {
        this.upperCaseAlphabet = upperCaseAlphabet;
    }

    public List<Character> getLowerCaseAlphabet() {
        return lowerCaseAlphabet;
    }

    public void setLowerCaseAlphabet(List<Character> lowerCaseAlphabet) {
        this.lowerCaseAlphabet = lowerCaseAlphabet;
    }
}
