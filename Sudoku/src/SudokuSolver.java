import java.util.Random;

public class SudokuSolver {

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;
    private static int validRow = 0;
    private static int validCol = 0;

    private final int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public boolean solveSudoku(int[][] values, int forbiddenNum) {

        if(!findUnassignedLocation(values)) return true;

        //suffle the nums array - for having a different valid sudoku


        for (int i = 0; i < GRID_SIZE; i++) {

            int num = nums[i];

            if(num == forbiddenNum) continue; //

            if(isSafe(values,validRow, validCol, num)) {
                values[validRow][validCol] = num;

                if(solveSudoku(values, forbiddenNum)) return true;

                if(validCol == 0) {
                    validRow--;
                    validCol = 8;
                }else{
                    validCol--;
                }

                values[validRow][validCol] = 0;
            }

        }


        return false;

    }

    public boolean createValidSudoku(int[][] values) {

        if(!findUnassignedLocation(values)) return true;

        shuffleNums();

        for (int i = 0; i < GRID_SIZE; i++) {

            int num = nums[i];

            if(isSafe(values,validRow, validCol, num)) {
                values[validRow][validCol] = num;

                if(createValidSudoku(values)) return true;

                if(validCol == 0) {
                    validRow--;
                    validCol = 8;
                }else{
                    validCol--;
                }

                values[validRow][validCol] = 0;
            }

        }


        return false;
    }

    private void shuffleNums() {

        Random random = new Random();
        for(int i = nums.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            int a = nums[index];
            nums[index] = nums[i];
            nums[i] = a;
        }

    }

    private boolean findUnassignedLocation(int[][] values) {
        for(int row = 0; row < GRID_SIZE; row++) {
            for(int col = 0; col < GRID_SIZE; col++) {
                if (values[row][col] == 0) {
                    validRow = row;
                    validCol = col;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean usedInRow(int[][] values, int row, int num) {
        for (int col = 0; col < GRID_SIZE; col++) {
            if(values[row][col] == num) return true;
        }

        return false;
    }

    private boolean usedInCol(int[][] values, int col, int num) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if(values[row][col] == num) return true;
        }

        return false;
    }

    private boolean usedInBox(int[][] values, int boxStartRow, int boxStartCol, int num) {
        for(int row = 0; row < SUBGRID_SIZE; row++) {
            for (int col = 0; col < SUBGRID_SIZE; col++) {
                if (values[row + boxStartRow][col + boxStartCol] == num) return true;
            }
        }

        return false;
    }

    private boolean isSafe(int[][] values,int row, int col, int num) {
        return !usedInRow(values, row, num) &&
                !usedInCol(values, col, num) &&
                !usedInBox(values, row - row % 3, col - col % 3, num);
    }

}