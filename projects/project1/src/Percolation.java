/**
 * Created by everettr on 1/31/17.
 */
public class Percolation {
    private QuickUnionUF find;
    private boolean[][] grid;   //false is closed, true is open
    int length;
    public Percolation(int n) {
        if (n <= 0) {
            return;
        }
        //Create a new n by n grid where all cells are initially blocked
        find = new QuickUnionUF(n * n);
        grid = new boolean[n][n];
        //make everything false/closed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        length = n;
    }


    public void open(int x, int y) {
        //Open the site at coordinate (x,y), where x represents the row number and y the column number.
        //For consistency purposes, (0,0) will be the bottom­left cell of
        //the grid and (n­1,n­1) will be on the top­right. The graphical capabilities discussed later assume a
        //similar convention.
        if (x < 0 || y < 0) {
            return;
        }
        if (x >= length || y >= length) {
            return;
        }
        //reverse coordinates
        //0,0 is bottom left
        //n-1,n-1 is top right
        int newX = x;
        int newY = (length - y) - 1;
        grid[newX][newY] = true;
        //convert from 2d to 1d
        int arrPosition = (length * newY) + newX;
        //method supplied here: http://stackoverflow.com/a/2151141
        //arrPosition = arrPosition;
        //check if there are neighbors can connect
    }

    public boolean isOpen(int x, int y) {
        //Returns true if cell (x,y) is open due to a previous call to
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= length || y >= length) {
            return false;
        }
        int newX = x;
        int newY = (length - y) - 1;
        return grid[newX][newY];
    }


    public boolean isFull(int x, int y) {
        //Returns true if there is a path from cell (x,y) to the surface
        //(i.e. there is percolation up to this cell)
        return false;
    }



    public boolean percolates() {
        //Analyzes the entire grid and returns true if the whole system percolates
        return false;
    }

    public static void main(String[] args) {
        //Create a main method that reads a description of a grid
        //from standard input and validates if the system described percolates or not, printing to standard
        //output a simple "Yes" or "No" answer.
        Percolation l = new Percolation(5);
        l.open(0,0);
        l.open(4,4);
        l.grid = l.grid;
    }

}

/*
    TODO:
    Change boolean to three state, initially closed
    Check neighbors
 */