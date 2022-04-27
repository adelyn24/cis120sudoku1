package org.cis120.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle {

    // Instance variables
    private int[][] solution;
    int[][] numbers2 = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};
    boolean[][] isPrefilled2 = {{true, true, true, true, true, false, true, true, true},
            {true, true, true, true, true, true, true, true, false},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true}};
    boolean[][] isPrefilled;
    private int level;


    // Constructor
    public Puzzle() {
        super();
        generate();
        finishPuzzle();
        this.isPrefilled = generatePrefilled();
    }

    // Check if puzzle satisfies invariants
    public boolean checkValidity(int[][] numbers) {
        // Numbers must be between 0 and 9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (numbers[i][j] < 1 || numbers[i][j] > 9) {
                    return false;
                }
            }
        }

        // Each 3x3 box should have no duplicates
        ArrayList<Integer> boxList = new ArrayList<>();
        ArrayList<Integer> intsInOrder = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            intsInOrder.add(i);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 3; j < 6; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 6; j < 9; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 3; j < 6; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 6; j < 9; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
        }

        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 3; j < 6; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
            for (int j = 6; j < 9; j++) {
                boxList.add(numbers[i][j]);
            }
            Collections.sort(boxList);
            if (!boxList.equals(intsInOrder)) {
                return false;
            }
        }

        // Each row should have no duplicates
        ArrayList<Integer> rowList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                rowList.add(numbers[i][j]);
            }
            Collections.sort(rowList);
            if (!rowList.equals(intsInOrder)) {
                return false;
            }
        }

        // Each column should have no duplicates
        ArrayList<Integer> colList = new ArrayList<>();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                colList.add(numbers[i][j]);
            }
            Collections.sort(colList);
            if (!colList.equals(intsInOrder)) {
                return false;
            }
        }

        return true;
    }

    // Create new puzzle
    public void generate() {

        // Initialize a 9x9 grid
        int[][] puzzle = new int[9][9];

        // Populate the diagonal boxes since we don't need to check
        // for row and column existence for those yet
        populateFirstBoxes(puzzle, 0, 0);
        populateFirstBoxes(puzzle, 3, 3);
        populateFirstBoxes(puzzle, 6, 6);
//

        this.solution = puzzle;
    }

    // Populate 3x3 box with random integers 1-9
    public void populateFirstBoxes(int[][] box, int rowStart, int colStart) {
        // Shuffled List
        ArrayList<Integer> numbers1_9 = randomize();

        int count = 0;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                box[i][j] = numbers1_9.get(count);
                count++;
            }
        }
    }

    public boolean usedInRow(int[][] array, int number, int row) {
        for (int i = 0; i < 9; i++) {
            if (array[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    public boolean usedInColumn(int[][] array, int number, int col) {
        for (int i = 0; i < 9; i++) {
            if (array[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    public boolean usedInBox(int[][] array, int number, int row, int col) {
        int rowLowerBound;
        int colLowerBound;
        if (row < 3) {
            rowLowerBound = 0;
        } else if (row < 6) {
            rowLowerBound = 3;
        } else {
            rowLowerBound = 6;
        }
        if (col < 3) {
            colLowerBound = 0;
        } else if (col < 6) {
            colLowerBound = 3;
        } else {
            colLowerBound = 6;
        }
        for (int rowIndex = rowLowerBound; rowIndex < rowLowerBound + 3; rowIndex++) {
            for (int colIndex = colLowerBound; colIndex < colLowerBound + 3; colIndex++) {
                if (array[rowIndex][colIndex] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> randomize() {
        // List of integers 1-9
        ArrayList<Integer> intsInOrder = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            intsInOrder.add(i);
        }
        // List to shuffle
        ArrayList<Integer> numbers1_9 = new ArrayList<>(intsInOrder);
        // Shuffle the list
        Collections.shuffle(numbers1_9);
        return numbers1_9;
    }


    public boolean finishPuzzle() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9 ; j++) {
                if (this.solution[i][j] == 0) {
                    for(int k = 1; k <= 9; k++) {
                        if(!(usedInColumn(this.solution, k, j))
                                && !(usedInRow(this.solution, k, i))
                                && !(usedInBox(this.solution, k, i, j)))
                        {
                            this.solution[i][j] = k;
                            if (finishPuzzle()) {
                                return true;
                            } else {
                                this.solution[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Get puzzle array
    public int[][] getNumbers() {
        // Copy for encapsulation
        int[][] solutionCopy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(this.solution[i], 0,
                    solutionCopy[i], 0, 9);
        }
        return solutionCopy;
    }

    public boolean[][] generatePrefilled() {
        boolean[][] prefill = new boolean[9][9];
        int randomNumber;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Random random = new Random();
                randomNumber = random.nextInt(2);
                prefill[i][j] = randomNumber != 0;
            }
        }
        return prefill;
    }

}
