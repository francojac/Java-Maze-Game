
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franc
 */
public class Sequence extends MazeSolver {
    
private boolean[][] visited;

public Sequence()
	{
		super();	// Call parent constructor
	}

	public Sequence(int x, int y, Maze maze)
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
                visited = new boolean[20][20];
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++)
                        visited[i][j] = false;
                }
                
	}
        // Method name: randomMove 
	// Description: This method (with respectt to the Sequence class) moves 
        //the player in a random direction to an adjacent cell, but if the player 
        //has already been to that cell before, then does not move there.
        
        public void randomMove()
	{
		Random rnd = new Random();
		// Pick a random direction and if we can move that direction then do it
		boolean foundDirection = false;
		// Repeat if we picked a direction with a wall
		/*do
		{
			switch (rnd.nextInt(4))
			{*/
				//case 0:
					if  (!isWallUp() && (visited[x][y-1] == false))
					{
						foundDirection = true;
						visited[x][y-1] = true;
                                                moveUp();
                                                
					}
					//break;
				//case 1:
					if  (!isWallDown() && (visited[x][y+1] == false))
					{
						foundDirection = true;
						visited[x][y+1] = true;
                                                moveDown();
                                                
					}
					//break;
				//case 2:
					if  (!isWallLeft() && (visited[x-1][y] == false))
					{
						foundDirection = true;
						visited[x-1][y] = true;
                                                moveLeft();
                                                
					}
					//break;
				//case 3:
					if  (!isWallRight() && (visited[x+1][y] == false))
					{
						foundDirection = true;
						visited[x+1][y] = true;
                                                moveRight();
                                                
					}
					//break;
                                
                                        do
		{
			switch (rnd.nextInt(4))
			{
				case 0:
					if  (!isWallUp())
					{
						foundDirection = true;
                                                moveUp();
                                                
					}
					break;
				case 1:
					if  (!isWallDown())
					{
						foundDirection = true;
						moveDown();
					}
					break;
				case 2:
					if  (!isWallLeft())
					{
						foundDirection = true;
						moveLeft();
					}
					break;
				case 3:
					if  (!isWallRight())
					{
						foundDirection = true;
						moveRight();
					}
					break;
			}
		} while (!foundDirection);
	}
                        
                                       

                                           
                        
		 //while (!foundDirection);
	
}
