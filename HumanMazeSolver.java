/*******************************************************************
 *  HumanMazeSolver.java
 *
 * This is an example of implementing a child class of MazeSolver.
 * You don't need to change anything in this class.
 *
 * It gets input from the keyboard as arrow keys and sends it to
 * the maze to make the move.
 *******************************************************************/
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class HumanMazeSolver extends MazeSolver
{
	public HumanMazeSolver()
	{
		super();
	}
	public HumanMazeSolver(int x, int y, Maze maze)
	{
		super(x,y,maze);

		// We haven't covered what this setOnKeyPressed EventHandler does,
		// but basically it creates an instance of a class here in the code
		// with a method that is run when a key is pressed.
                // Method name: handle
                // Description: This method gives the player control of the cursor.
        maze.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    	if (!isWallUp())
                    		moveUp();
                    	break;
                    case DOWN:
                        if (!isWallDown())
                    		moveDown();
                    	break;
                    case LEFT:
                        if (!isWallLeft())
                    		moveLeft();
                    	break;
                    case RIGHT:
                        if (!isWallRight())
                    		moveRight();
                    	break;
                }
            }
        });
	}
}