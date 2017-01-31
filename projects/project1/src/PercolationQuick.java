/**
 * Created by everettr on 1/31/17.
 */
//Same as Percolation but uses WeightedQuickUnionUF instead
public class PercolationQuick {
    //private QuickUnionUF[][] grid; //columns x rows
    int length;
    public PercolationQuick(int n) {
        //Create a new n by n grid where all cells are initially blocked
        //grid = new QuickUnionUF[n][n];
        //for (int i = 0; i < n; i++) {
        //    grid[i][0] = new QuickUnionUF(n);
        //}
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
        //0,0 is bottom left
        //n-1,n-1 is top right
        //grid[x][y].union();
    }

    public boolean isOpen(int x, int y) {
        //Returns true if cell (x,y) is open due to a previous call to
        return false;
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

    }
}
