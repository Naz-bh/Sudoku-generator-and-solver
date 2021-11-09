import java.util.Arrays;
import java.util.Random;

import static java.lang.System.*;

public class SudokuGenerator {

    private final int[][] generatorValues = new int[9][9];
    private final int[][] solvedValues = new int[9][9];


    public void generateSudoku() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        //generate a random valid sudoku for an empty table
        sudokuSolver.createValidSudoku(generatorValues);

        //Solved sudoku is copying
        for (int i = 0; i < generatorValues.length; i++)
            System.arraycopy(generatorValues[i], 0, solvedValues[i], 0, generatorValues.length);

        int count = 0;

        while(count < 40){
            Random random = new Random();

            int row;
            int col;

            if(count < 15){
                row = random.nextInt(3);
                col = random.nextInt(9);
            } else if (count >= 15 && count < 30) {
                row = random.nextInt(3) + 3;
                col = random.nextInt(9);
            }else {
                row = random.nextInt(3) + 6;
                col = random.nextInt(9);
            }


            int num = generatorValues[row][col];
            int[][] tempValues = Arrays.copyOf(generatorValues, generatorValues.length);



            //Set the cell to 0;
            if(generatorValues[row][col] != 0){
                generatorValues[row][col] = 0;
            } else{
                continue;
            }

            //If found a solution, set cell back to original num
            if(sudokuSolver.solveSudoku(tempValues, num)) {
                generatorValues[row][col] = num;
                continue;
            }

            count++;

        }
        printNums(generatorValues);
        System.out.println("\n------------------------------");
        System.out.println("Solved Sudoku:");
        printNums(solvedValues);


    }

    private void printNums(int[][] values) {
        for (int row = 0; row < 9; row++) {
            if(row%3==0) out.println("  ");
            for (int col = 0; col < 9; col++) {
                if (col%3==0){
                    out.print("  ");
                }
                switch (values[row][col]) {
                    case 0 -> out.print("_ ");
                    case 1 -> out.print("C ");
                    case 2 -> out.print("N ");
                    case 3 -> out.print("G ");
                    case 4 -> out.print("B ");
                    case 5 -> out.print("I ");
                    case 6 -> out.print("M ");
                    case 7 -> out.print("2 ");
                    case 8 -> out.print("1 ");
                    case 9 -> out.print("3 ");
                }
            }
            out.println();
        }
    }

}