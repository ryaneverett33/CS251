/**
 * Created by everettr on 1/31/17.
 */
public class Percolation {
    private QuickUnionUF find;
    //y * x
    public int[][] grid;   //-1 is closed, 0 is open, 1 is full
    int length;
    public Percolation(int n) {
        if (n <= 0) {
            return;
        }
        //Create a new n by n grid where all cells are initially blocked
        find = new QuickUnionUF(n * n);
        grid = new int[n][n];
        //make everything false/closed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = -1;
            }
        }
        length = n;
    }


    public void open(int x, int y) {
        //x is rows
        //y is columns
        //Open the site at coordinate (x,y), where x represents the row number and y the column number.
        //For consistency purposes, (0,0) will be the bottom­left cell of
        //the grid and (n­1,n­1) will be on the top­right. The graphical capabilities discussed later assume a
        //similar convention.
        if (!isValidIndex(x,y)) {
            return;
        }
        //reverse coordinates
        //0,0 is bottom left
        //n-1,n-1 is top right
        int row = (length - x) - 1;
        int col = y;
        grid[row][col] = 0;
        //convert from 2d to 1d
        //int arrPosition = (length * gridY) + gridX;
        //(length * gridX) + gridY;
        int arrPosition = (length * row) + col;
        //method supplied here: http://stackoverflow.com/a/2151141
        //check if there are neighbors can connect top,left,right and bottom neighbors (4­neighbors).
        //check above
        if (isValidIndex(row-1,col)) {
            int topRow = row - 1, topCol = col;
            if (grid[topRow][topCol] == 1 || grid[topRow][topCol] == 0) {
                //valid, so connect
                int topArr = (length * topRow) + topCol;
                find.union(arrPosition,topArr);
                grid[topRow][topCol] = 1;
                //if bottom, connect the two
                if (x == (0)) {
                    grid[row][col] = 1;
                }
            }
        }
        //check below
        if (isValidIndex(row+1,col)) {
            //int bottomX  = x, bottomY = (length - (y-1) -1);
            int bottomRow = row + 1, bottomCol = col;
            if (grid[bottomRow][bottomCol] == 1 || grid[bottomRow][bottomCol] == 0) {
                //valid, so connect
                int topArr = (length * bottomRow) + bottomCol;
                find.union(arrPosition,topArr);
                grid[bottomRow][bottomCol] = 1;
                //if top, connect the two
                if (x == (length - 1)) {
                    grid[row][col] = 1;
                }
            }
        }
        //check right
        if (isValidIndex(row,col+1)) {
            //int rightX  = x+1, rightY = (length - (y) -1);
            int rightRow = row, rightCol = col+1;
            if (grid[rightRow][rightCol] == 1 || grid[rightRow][rightCol] == 0) {
                //valid, so connect
                int topArr = (length * rightRow) + rightCol;
                find.union(arrPosition,topArr);
                grid[rightRow][rightCol] = 1;
                //if left, connect the two
                if (y == 0) {
                    grid[row][col] = 1;
                }
            }
        }
        //check left
        if (isValidIndex(row,col-1)) {
            //int leftX  = x-1, leftY = (length - (y) -1);
            int leftRow = row, leftCol = col - 1;
            if (grid[leftRow][leftCol] == 1 || grid[leftRow][leftCol] == 0) {
                //valid, so connect
                int topArr = (length * leftRow) + leftCol;
                find.union(arrPosition,topArr);
                grid[leftRow][leftCol] = 1;
                //if right, connect the two
                if (y == (length - 1)) {
                    grid[row][col] = 1;
                }
            }
        }
    }

    public boolean isOpen(int x, int y) {
        //Returns true if cell (x,y) is open due to a previous call to
        if (!isValidIndex(x,y)) {
            return false;
        }
        int gridX = (length - x) - 1;
        int gridY = y;
        if (grid[gridX][gridY] == 0 || grid[gridX][gridY] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isValidIndex(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= length || y >= length) {
            return false;
        }
        else {
            return true;
        }
    }


    public boolean isFull(int x, int y) {
        //Returns true if there is a path from cell (x,y) to the surface
        //(i.e. there is percolation up to this cell)
        if (isValidIndex(x,y)) {
            int gridX = (length - x) - 1;
            int gridY = y;
            if (grid[gridX][gridY] == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }



    public boolean percolates() {
        //Analyzes the entire grid and returns true if the whole system percolates
        if (length == 1 && (grid[0][0] == 0 || grid[0][0] == 1)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (grid[0][i] == 1) {
                //int topArr = (length * leftY) + leftX;
                int p = (length * 0) + i;
                for (int j = 0; j < length; j++) {
                    if (grid[length - 1][j] == 1) {
                        int q = (length * (length - 1)) + j;
                        if (find.connected(p,q)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //Create a main method that reads a description of a grid
        //from standard input and validates if the system described percolates or not, printing to standard
        //output a simple "Yes" or "No" answer.
        //second arg is < > for slow and fast
        //java ­classpath .:stdlib.jar Percolation < testCase.txt
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            if (line.isEmpty()) {
                //empty, skip
                continue;
            }
            String[] objs = line.split(" ");
            int x, y;
            try {
                x = Integer.parseInt(objs[0]);
                y = Integer.parseInt(objs[1]);
            }
            catch (Exception e) {
                //System.out.println("Failed to parse line: " + line);
                continue;
            }
            perc.open(x,y);
        }
        if (perc.percolates()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
    public void testLocation(int x, int y) {
        //int newX = x;
        //int newY = (length - y) - 1;
        int gridX = (length - x) - 1;
        int gridY = y;
        System.out.println(String.format("X: %d, Y: %d", gridX, gridY));
    }
    public void testArr(int x, int y) {
        int gridX = (length - x) - 1;
        int gridY = y;
        int location = (length * gridX) + gridY;
        System.out.println("Loc: " + location);
    }
}

/*
    TODO:
    Change values to 0,1,2 like PercolationVisualizer
    Don't show full from open()
 */