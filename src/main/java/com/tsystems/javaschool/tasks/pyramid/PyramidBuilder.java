package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {

        if (inputNumbers == null) throw new CannotBuildPyramidException();
        for (int i = 0; i < inputNumbers.size(); i++)
            if (inputNumbers.get(i) == null)
                throw new CannotBuildPyramidException();

        double triangNum = (Math.sqrt(8 * inputNumbers.size() + 1) - 1) / 2;
        if (triangNum % 1 != 0)
            throw new CannotBuildPyramidException();

        int[][] pyramid = new int[(int)triangNum][(int)triangNum * 2 - 1];
        for (int i = 0; i < pyramid.length; i++)
            for (int j = 0; j < pyramid[i].length; j++)
                pyramid[i][j] = 0;

        Collections.sort(inputNumbers);

        int currentElemNum = 0;
        for (int i = 0; i < pyramid.length; i++) {
            int j = (int)triangNum - i - 1;
            int numbersInARow = i + 1;
            while (numbersInARow > 0) {
                pyramid[i][j] = inputNumbers.get(currentElemNum);
                currentElemNum++;
                numbersInARow--;
                j += 2;
            }
        }

        return pyramid;

    }


}
