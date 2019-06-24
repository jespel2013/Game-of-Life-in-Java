//Jesse Pelletier
/**
 * The model for John Conway's Game of Life.
 *
 * This class has all needed methods as stubs.
 * 
 * Comments explain each method what each method does.
 *
 * @author Rick Mercer and Your Name
 */
public class GameOfLife {
	private int[][] grid;
	private int rows;
	private int cols;

	/**
	 * Write the constructor so it takes two integer arguments to represent the
	 * number of rows and columns in the game of life. The constructor creates a
	 * society with no cells but space to store rows*cols cells.
	 *
	 * @param rows The height of the grid that shows the cells.
	 * @param cols The width of the grid that shows the cells.
	 */
	public GameOfLife(int r, int c) {
		// TODO: Complete this method
		grid = new int[r][c];
		rows = r;
		cols = c;

	}

	/**
	 * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfRows() {
		// TODO: Complete this method
		return rows;
	}

	/**
	 * The number of columns, which can be indexed from 0..numberOfColumns()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfColumns() {
		// TODO: Complete this method
		return cols;
	}

	/**
	 * Place a new cell in the society.
	 * 
	 * @param row The row to grow the cell.
	 * @param col The column to grow the cell.
	 *
	 *            Precondition: row and col are in range.
	 */
	public void growCellAt(int row, int col) {
		grid[row][col] = 1;
	}

	/**
	 * 5) Return true if there is a cell at the given row and column. Return false
	 * if there is none at the specified location.
	 *
	 * @param row The row to check.
	 * @param col The column to check.
	 * @return True if there is a cell at the given row or false if none
	 *
	 *         Precondition: row and col are in range.
	 */
	public boolean cellAt(int row, int col) {
		// TODO: Complete this method
		return grid[row][col] == 1;
	}

	/**
	 * Return one big string of cells to represent the current state of the society
	 * of cells (see output below where '.' represents an empty space and 'O' is a
	 * live cell. There is no need to test toString. Simply use it to visually
	 * inspect if needed. Here is one sample output from toString:
	 *
	 * GameOfLife society = new GameOfLife(4, 14); society.growCellAt(1, 2);
	 * society.growCellAt(2, 3); society.growCellAt(3, 4);
	 * System.out.println(society.toString());
	 *
	 * @return A textual representation of this society of cells.
	 */
	// Sample Output:
	// ..............
	// ..O...........
	// ...O..........
	// ....O.........
	@Override
	public String toString() {// to do
		String gridStr = "";

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1) {
					gridStr += "O";
				} else {
					gridStr += ".";
				}
			}
			if (r != rows - 1) {
				gridStr += "\n";
			}
		}

		return gridStr;
	}

	/**
	 * The return values should always be in the range of 0 through 8.
	 *
	 * @return The number of neighbors around any cell using wrap around.
	 * 
	 *         Precondition: row and col are in range.
	 *
	 *         Count the neighbors around the given location. Use wraparound. A cell
	 *         in row 0 has neighbors in the last row if a cell is in the same
	 *         column, or the column to the left or right. In this example, cell 0,5
	 *         has two neighbors in the last row, cell 2,8 has four neighbors, cell
	 *         2,0 has four neighbors, cell 1,0 has three neighbors. The cell at 3,8
	 *         has 3 neighbors. The potential location for a cell at 4,8 would have
	 *         three neighbors.
	 */
	// .....O..O
	// O........
	// O.......O
	// O.......O
	// ....O.O..
	public int neighborCount(int row, int col) {
		int count = 0;

		if (row == rows - 1 || col == cols - 1) {
			if (row == rows - 1 && col == cols - 1) {// bottom right corner
				count += grid[row - 1][col - 1];
				count += grid[row - 1][col];
				count += grid[row - 1][0];
				count += grid[row][col - 1];
				count += grid[row][0];
				count += grid[0][0];
				count += grid[0][col];
				count += grid[0][col - 1];
			} else if (row == 0 && col == cols - 1) {// top right
				count += grid[rows - 1][col - 1];
				count += grid[rows - 1][col];
				count += grid[rows - 1][0];
				count += grid[row][col - 1];
				count += grid[row][0];
				count += grid[row + 1][col - 1];
				count += grid[row + 1][col];
				count += grid[row + 1][0];
			} else if (row == rows - 1 && col == 0) {// bottom left
				count += grid[row - 1][cols - 1];
				count += grid[row - 1][col];
				count += grid[row - 1][col + 1];
				count += grid[row][cols - 1];
				count += grid[row][col + 1];
				count += grid[0][cols - 1];
				count += grid[0][col];
				count += grid[0][col + 1];
			} else if (row == rows - 1) { // last row
				count += grid[row - 1][col - 1];
				count += grid[row - 1][col];
				count += grid[row - 1][col + 1];
				count += grid[row][col - 1];
				count += grid[row][col + 1];
				count += grid[0][col - 1];
				count += grid[0][col];
				count += grid[0][col + 1];
			} else { // last column
				count += grid[row - 1][col - 1];
				count += grid[row - 1][col];
				count += grid[row - 1][0];
				count += grid[row][col - 1];
				count += grid[row][0];
				count += grid[row + 1][col - 1];
				count += grid[row + 1][col];
				count += grid[row + 1][0];

			}
		} else if (row == 0 || col == 0) {
			if (row == 0 && col == 0) {// top left corner
				count += grid[rows - 1][cols - 1];
				count += grid[rows - 1][col];
				count += grid[rows - 1][col + 1];
				count += grid[row][cols - 1];
				count += grid[row][col + 1];
				count += grid[row + 1][cols - 1];
				count += grid[row + 1][col];
				count += grid[row + 1][col + 1];
			} else if (row == 0) { // top
				count += grid[rows - 1][col - 1];
				count += grid[rows - 1][col];
				count += grid[rows - 1][col + 1];
				count += grid[row][col - 1];
				count += grid[row][col + 1];
				count += grid[row + 1][col - 1];
				count += grid[row + 1][col];
				count += grid[row + 1][col + 1];
			} else {// left side
				count += grid[row - 1][cols - 1];
				count += grid[row - 1][col];
				count += grid[row - 1][col + 1];
				count += grid[row][cols - 1];
				count += grid[row][col + 1];
				count += grid[row + 1][cols - 1];
				count += grid[row + 1][col];
				count += grid[row + 1][col + 1];

			}
		} else {
			count += grid[row - 1][col - 1];
			count += grid[row - 1][col];
			count += grid[row - 1][col + 1];
			count += grid[row][col - 1];
			count += grid[row][col + 1];
			count += grid[row + 1][col - 1];
			count += grid[row + 1][col];
			count += grid[row + 1][col + 1];
		}

		return count;
	}

	public void update() {
		int[][] gridCopy = new int[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				gridCopy[r][c] = grid[r][c];
				if (grid[r][c] == 0 && neighborCount(r, c) == 3) {
					gridCopy[r][c] = 1;
				} else if (grid[r][c] == 1 && (neighborCount(r, c) < 2 || neighborCount(r, c) > 3)) {
					gridCopy[r][c] = 0;
				}
			}
		}
		grid = gridCopy;

	}
}