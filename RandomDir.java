/*******************************************************************
 *  RandomMazeSolver.java
 *
 * This is an example of implementing a child class of MazeSolver.
 * You don't need to change anything in this class.
 *
 * You should use this class as a starting point to write your own class
 * to complete the homework problem.
 *
 * It randomly picks an available direction from possible directions
 * and moves there.  It can take a long time to randomly make its
 * way to the exit!
 *******************************************************************/
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RandomDir extends MazeSolver
{
	public RandomDir()
	{
		super();	// Call parent constructor
	}

	public RandomDir(int x, int y, Maze maze)
	{
		super(x,y,maze);	// Call parent constructor

		// This timeline object calls the randomMove method
		// once every 0.1 seconds.  You can change the 0.1 to a different
		// number to make the move more quickly or more slowly.
		Timeline timeline = new Timeline(
			new KeyFrame(Duration.seconds(0.1), e -> {
				randomMove();
			})
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	// This method moves the player in a random up/down/left/right direction
	// as long as there is not a wall in that direction
	public void randomMove()
	{
		Random rnd = new Random();
		// Pick a random direction and if we can move that direction then do it
		boolean foundDirection = false;
		// Repeat if we picked a direction with a wall
		do
		{
			switch (rnd.nextInt(4))
			{
				case 0:
					while  (!isWallUp())
					{
						foundDirection = true;
						moveUp();
					}
					break;
				case 1:
					while  (!isWallDown())
					{
						foundDirection = true;
						moveDown();
					}
					break;
				case 2:
					while  (!isWallLeft())
					{
						foundDirection = true;
						moveLeft();
					}
					break;
				case 3:
					while  (!isWallRight())
					{
						foundDirection = true;
						moveRight();
					}
					break;
			}
		} while (!foundDirection);
	}
}
