
//Jesse Pelletier
import static org.junit.Assert.*;
import org.junit.Test;

public class GameOfLifeTest {
	@Test
	public void testConstructorAndGetters() {
		GameOfLife society = new GameOfLife(5, 8);
		assertEquals(5, society.numberOfRows());
		assertEquals(8, society.numberOfColumns());
		for (int r = 0; r < society.numberOfRows(); r++)
			for (int c = 0; c < society.numberOfColumns(); c++)
				assertFalse(society.cellAt(r, c));
	}

	@Test
	public void testGrowCellAtAndCellAt() {
		GameOfLife society = new GameOfLife(4, 4);
		society.growCellAt(1, 1);
		society.growCellAt(2, 2);
		society.growCellAt(3, 3);
		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(2, 2));
		assertTrue(society.cellAt(3, 3));
	}

	@Test
	public void testNeighbors() {
		GameOfLife society = new GameOfLife(7, 11);
		society.growCellAt(3, 3);
		society.growCellAt(3, 4);
		society.growCellAt(3, 5);
		assertEquals(0, society.neighborCount(2, 1));
		assertEquals(1, society.neighborCount(2, 2));
		assertEquals(2, society.neighborCount(2, 3));
		assertEquals(3, society.neighborCount(2, 4));
		assertEquals(2, society.neighborCount(2, 5));
		assertEquals(1, society.neighborCount(2, 6));

		// corners

	}

	@Test
	public void testNeighborsWraparound() {
		GameOfLife society = new GameOfLife(7, 11);

		society.growCellAt(0, 0);
		society.growCellAt(2, 0);
		society.growCellAt(0, 10);
		society.growCellAt(2, 10);
		society.growCellAt(6, 0);
		society.growCellAt(6, 10);
		assertEquals(2, society.neighborCount(1, 1));
		assertEquals(2, society.neighborCount(0, 1));
		assertEquals(4, society.neighborCount(1, 0));
		assertEquals(2, society.neighborCount(0, 9));
		assertEquals(2, society.neighborCount(5, 10));
		assertEquals(2, society.neighborCount(6, 9));
		assertEquals(2, society.neighborCount(6, 1));

		// corners
		assertEquals(3, society.neighborCount(0, 0));
		assertEquals(3, society.neighborCount(6, 10));
		assertEquals(3, society.neighborCount(0, 10));
		assertEquals(3, society.neighborCount(6, 0));

	}

	@Test
	public void testToString() {
		GameOfLife society = new GameOfLife(5, 5);

		String outputStr = society.toString();
		assertEquals(".....\n.....\n.....\n.....\n.....", outputStr);

		society.growCellAt(0, 0);
		society.growCellAt(1, 1);
		society.growCellAt(2, 2);
		society.growCellAt(3, 3);
		society.growCellAt(4, 4);

		outputStr = society.toString();

		assertEquals("O....\n.O...\n..O..\n...O.\n....O", outputStr);

	}

	@Test
	public void testUpdateMicro() {
		GameOfLife society = new GameOfLife(3, 3);

		society.update();

		// assert empty
		for (int r = 0; r < society.numberOfRows(); r++) {
			for (int c = 0; c < society.numberOfColumns(); c++) {
				assertFalse(society.cellAt(r, c));
			}
		}

		society.growCellAt(1, 1);

		society.update(); // cell should die off

		// assert empty
		for (int r = 0; r < society.numberOfRows(); r++) {
			for (int c = 0; c < society.numberOfColumns(); c++) {
				assertFalse(society.cellAt(r, c));
			}
		}

		society.growCellAt(0, 1);
		society.growCellAt(1, 0);
		society.growCellAt(1, 2);
		society.growCellAt(2, 1);

		society.update();

		assertFalse(society.cellAt(0, 0));
		assertTrue(society.cellAt(0, 1));
		assertFalse(society.cellAt(0, 2));
		assertTrue(society.cellAt(1, 0));
		assertFalse(society.cellAt(1, 1));
		assertTrue(society.cellAt(1, 2));
		assertFalse(society.cellAt(2, 0));
		assertTrue(society.cellAt(2, 1));
		assertFalse(society.cellAt(2, 2));

	}

	@Test
	public void testUpdateMacro() {
		GameOfLife society = new GameOfLife(7, 7);

		society.growCellAt(0, 0);
		society.growCellAt(0, 6);
		society.growCellAt(2, 2);
		society.growCellAt(3, 2);
		society.growCellAt(4, 4);
		society.growCellAt(6, 6);
		society.growCellAt(2, 1);
		society.growCellAt(3, 1);
		society.growCellAt(4, 1);
		society.growCellAt(6, 5);

		society.update();

		// alive
		assertTrue(society.cellAt(0, 6));
		assertTrue(society.cellAt(1, 0));
		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(2, 1));
		assertTrue(society.cellAt(2, 2));
		assertTrue(society.cellAt(3, 0));
		assertTrue(society.cellAt(3, 3));
		assertTrue(society.cellAt(4, 1));
		assertTrue(society.cellAt(4, 2));
		assertTrue(society.cellAt(5, 5));
		assertTrue(society.cellAt(6, 5));
		assertTrue(society.cellAt(6, 6));
		assertTrue(society.cellAt(0, 0));
		assertTrue(society.cellAt(6, 0));

		// dead
		assertFalse(society.cellAt(3, 1));
		assertFalse(society.cellAt(3, 2));
		assertFalse(society.cellAt(4, 4));

	}
}