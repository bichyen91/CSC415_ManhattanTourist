package csc415_manhattantourist;

import java.util.ArrayList;
import java.util.List;

public class CSC415_ManhattanTourist {

    static class Cell {

        int costToRight;
        int costGoDown;
        int routeCost;
        boolean isFromLeft;

        public Cell(int costToRight, int costGoDown) {
            this.costToRight = costToRight;
            this.costGoDown = costGoDown;
            this.routeCost = 0;
            this.isFromLeft = false;
        }
    }

    // Returns the maximum path cost in the grid and print the path
    public static int manhattanTourist(Cell[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int r = 1; r < row; r++) {
            grid[r][0].routeCost = grid[r - 1][0].routeCost
                    + grid[r - 1][0].costGoDown;
        }

        for (int c = 1; c < col; c++) {
            grid[0][c].routeCost = grid[0][c - 1].routeCost
                    + grid[0][c - 1].costToRight;
        }
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                int costFromLeft = grid[r][c - 1].routeCost
                        + grid[r][c - 1].costToRight;
                int costFromTop = grid[r - 1][c].routeCost
                        + grid[r - 1][c].costGoDown;
                if (costFromLeft >= costFromTop) {
                    grid[r][c].routeCost = costFromLeft;
                    grid[r][c].isFromLeft = true;
                } else {
                    grid[r][c].routeCost = costFromTop;
                }
            }
        }
        return grid[row - 1][col - 1].routeCost;
    }

    // Function to print the path by backtracking
    public static void printPath(Cell[][] grid) {
        int curRow = grid.length - 1;
        int curCol = grid[0].length - 1;
        List<String> path = new ArrayList();
        path.add("Cell (" + curRow + ", " + curCol + ") [Cost: "
                + grid[curRow][curCol].routeCost + "]");
        while (curRow > 0 || curCol > 0) {
            if (grid[curRow][curCol].isFromLeft) {
                path.add("Cell (" + curRow + ", " + (curCol - 1) + ") [Cost: "
                        + grid[curRow][curCol - 1].routeCost + "]");
                curCol--;
            } else {
                path.add("Cell (" + (curRow - 1) + ", " + curCol + ") [Cost: "
                        + grid[curRow - 1][curCol].routeCost + "]");
                curRow--;
            }
        }

        System.out.println("Path begin from Cell(row, column)");
        for (int i = path.size() - 1; i >= 0; i--) {
            if (i == path.size() - 1) {
                System.out.print(" Start at ");
            } else if (i == 0) {
                System.out.print("Finish at ");

            } else {
                System.out.print("       => ");
            }
            System.out.println(path.get(i));
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        // Example input: 5x5 grid
        int n = 5, m = 5;

        // Create a grid of cells (n rows, m columns)
        Cell[][] grid = new Cell[n][m];

        // Initialize the grid with toRight 
        //and toDown costs based on the provided values
        grid[0][0] = new Cell(3, 1);
        grid[0][1] = new Cell(2, 0);
        grid[0][2] = new Cell(4, 2);
        grid[0][3] = new Cell(0, 4);
        grid[0][4] = new Cell(0, 3);

        grid[1][0] = new Cell(3, 4);
        grid[1][1] = new Cell(2, 6);
        grid[1][2] = new Cell(4, 5);
        grid[1][3] = new Cell(2, 2);
        grid[1][4] = new Cell(0, 1);

        grid[2][0] = new Cell(0, 4);
        grid[2][1] = new Cell(7, 4);
        grid[2][2] = new Cell(3, 5);
        grid[2][3] = new Cell(4, 2);
        grid[2][4] = new Cell(0, 1);

        grid[3][0] = new Cell(3, 5);
        grid[3][1] = new Cell(3, 6);
        grid[3][2] = new Cell(0, 8);
        grid[3][3] = new Cell(1, 5);
        grid[3][4] = new Cell(0, 3);

        grid[4][0] = new Cell(1, 0);
        grid[4][1] = new Cell(3, 0);
        grid[4][2] = new Cell(2, 0);
        grid[4][3] = new Cell(2, 0);
        grid[4][4] = new Cell(0, 0);

        // Calculate the maximum path cost and print the path
        int result = manhattanTourist(grid);
        printPath(grid);
        System.out.println("Maximum path cost: " + result);
        System.out.println("");
    }
}






