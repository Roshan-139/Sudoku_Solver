public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;
    
    public static void main(String[] args) {
        int[][] grid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        if (solveSudoku(grid)) {
            System.out.println("Sudoku solved:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists.");
        }
    }
    
    private static boolean solveSudoku(int[][] grid) {
        int row = 0;
        int col = 0;
        boolean isFull = true;
        
        // Find the next empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY_CELL) {
                    row = i;
                    col = j;
                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                break;
            }
        }
        
        // If the grid is full, Sudoku is solved
        if (isFull) {
            return true;
        }
        
        // Try each number from 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(grid, row, col, num)) {
                // Make a move
                grid[row][col] = num;
                
                // Recursively solve the Sudoku
                if (solveSudoku(grid)) {
                    return true;
                }
                
                // If the move doesn't lead to a solution, undo it
                grid[row][col] = EMPTY_CELL;
            }
        }
        
        // Backtrack if no number can be placed
        return false;
    }
    
    private static boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check if the number is already present in the same row or column
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        
        // Check if the number is already present in the 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxStartRow + i][boxStartCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static void printGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}