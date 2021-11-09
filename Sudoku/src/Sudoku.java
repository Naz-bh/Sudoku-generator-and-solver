import static java.lang.System.*;

public interface Sudoku {
    static void main(String[] args) {
        out.println("Unsolved Sudoku:");
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        sudokuGenerator.generateSudoku();

    }
}