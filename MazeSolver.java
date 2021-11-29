/*******************************************************************
 *  MazeSolver.java
 *
 * This class helps you build a maze solver.  You don't need to change
 * anything in this class, but you should make a child of MazeSolver
 * and use the methods inherited from this class.  The methods give you access to
 * the coordinates in the maze, access to the maze itself, and methods
 * to check if there is a wall up/down/left/right and methods to move
 * up, down, left, or right.
 *******************************************************************/
public class MazeSolver
{
	protected int x,y;		// Coordinates in the maze
	protected Maze maze;	// Access back to the Maze object

	// Default constructor; not used in this program
	public MazeSolver()
	{
		x=1;
		y=1;
		maze = null;
	}
	// This program sets the coordinates and saves a reference to the maze object.
	// This reference allows us to make moves on the maze and to access the 2D array.
	public MazeSolver(int x, int y, Maze maze)
	{
		this.x = x;
		this.y = y;
		this.maze = maze;
	}

	// Returns true or false depending on whether there is a wall in the
	// specified direction from our current position.
	public boolean isWallUp()
	{
		return (maze.maze[x][y-1] == Maze.WALL);
	}
	public boolean isWallDown()
	{
		return (maze.maze[x][y+1] == Maze.WALL);
	}
	public boolean isWallLeft()
	{
		return (maze.maze[x-1][y] == Maze.WALL);
	}
	public boolean isWallRight()
	{
		return (maze.maze[x+1][y] == Maze.WALL);
	}

	// Attempts to move the player in the specified direction in the maze.
	public void moveUp()
	{
		if (!isWallUp())
		{
			y--;
			maze.movePlayer("UP");
		}
	}
	public void moveDown()
	{
		if (!isWallDown())
		{
			y++;
			maze.movePlayer("DOWN");
		}
	}
	public void moveLeft()
	{
		if (!isWallLeft())
		{
			x--;
			maze.movePlayer("LEFT");
		}
	}
	public void moveRight()
	{
		if (!isWallRight())
		{
			x++;
			maze.movePlayer("RIGHT");
		}
	}
}