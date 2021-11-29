/*******************************************************************
 *  Maze.java
 *
 * This is the main class that displays a maze represented in a 2D
 * array, shown below. It displays the maze in a graphical window.
 * You can ignore most of the code in here for Homework #5 except
 * the line in the start method that creates a Maze solver object.
 *
 * You might like to look through this code to see how it works, though.
 *******************************************************************/
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


public class Maze extends Application
{
   public static final char WALL = 'X';
   public static final char BLANK = ' ';
   public static final int SIZE = 20;			// Maze is 20x20
   public static final int SPRITE_SIZE = 30;	// 30x30 pixel square for each cell in the maze
   // In the line below I hard-coded the maze with X's for walls and blanks for spaces
   public static char[][] maze =
	   	{
			{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
			{'X',' ','X',' ',' ',' ',' ','X',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ','X'},
			{'X',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ','X'},
			{'X',' ','X',' ',' ',' ',' ',' ','X','X','X','X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ','X','X','X','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ',' ',' ','X',' ',' ',' ',' ',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ','X','X',' ',' ',' ','X',' ',' ','X',' ',' ','X',' ','X'},
			{'X',' ',' ',' ','X',' ',' ',' ',' ',' ',' ','X',' ',' ','X',' ',' ','X',' ','X'},
			{'X',' ',' ',' ','X',' ',' ',' ','X','X',' ','X',' ',' ','X',' ',' ','X',' ','X'},
			{'X','X','X',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ','X','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ','X','X','X',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ',' ',' ','X','X','X',' ',' ','X'},
			{'X',' ',' ',' ',' ','X','X','X',' ','X',' ',' ',' ',' ','X','X','X',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','X',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}
		};
   private int playerX = 1, playerY = 1;  // Initial coordinate of player in maze
   private int exitX = 18, exitY = 18;	  // Coordinates of the maze exit
   private Scene scene;					  // This is a class variable so multiple methods can access it
   private GraphicsContext gc;			  // This is a class variable so multiple methods can access it
   private Image playerIcon;			  // An emoticon that displays location in the maze
   private Image flagIcon;				  // Image of a red flag that indicates the exit

   // This method returns the Scene object.
   // It is needed to add a keyboard handler to the scene from a separate class.
   public Scene getScene()
   {
	   return scene;
   }

   // By hard-coding the maze above, the y coordinate is first and x is second.
   // i.e.  maze[y][x].  This code flips it around so we can access it as the
   // more familiar [x][y] and have it match the depiction of the maze as
   // it is hard-coded above.
   private void flipMaze()
   {
	   char[][] flippedMaze = new char[SIZE][SIZE];
	   for (int y = 0; y < SIZE; y++)
		  for (int x = 0; x < SIZE; x++)
			flippedMaze[x][y] = maze[y][x];
	   maze = flippedMaze;
   }

   // This method draws the maze in a JavaFX window.  It draws an empty cell as white,
   // black for walls, and paints an emoticon icon for the player using drawImage
   // and a flag for the exit.  The emoticon and flag are loaded as image files.
   public void drawMaze()
   {
		for (int x = 0; x < SIZE; x++)
		{
		  for (int y = 0; y < SIZE; y++)
		  {
			  if (maze[x][y]==' ')
			  	 gc.setFill(Color.WHITE);
			  else if (maze[x][y]=='X')
			  	 gc.setFill(Color.BLACK);
			  gc.fillRect(x*SPRITE_SIZE,y*SPRITE_SIZE,SPRITE_SIZE,SPRITE_SIZE);
		  }
	    }
	    // Draw player emoji at coordinates of player
		gc.drawImage(playerIcon,playerX*SPRITE_SIZE,playerY*SPRITE_SIZE,SPRITE_SIZE,SPRITE_SIZE);
		// Draw flag at coordinates of exit
		gc.drawImage(flagIcon,exitX*SPRITE_SIZE,exitY*SPRITE_SIZE,SPRITE_SIZE,SPRITE_SIZE);
   }

   // This method moves the player in the specified direction (UP, DOWN, LEFT, RIGHT).
   // It first checks to make sure the target location is a blank space.
   public void movePlayer(String direction)
   {
	   switch (direction)
	   {
		   case "UP":
		   	  if (maze[playerX][playerY-1]==BLANK)
		   	  	playerY--;
		   	  break;
		   case "DOWN":
		   	  if (maze[playerX][playerY+1]==BLANK)
			   	  playerY++;
		   	  break;
		   case "LEFT":
		   	  if (maze[playerX-1][playerY]==BLANK)
		          playerX--;
		      break;
		   case "RIGHT":
		   	  if (maze[playerX+1][playerY]==BLANK)
		      	  playerX++;
		      break;
	   }
	   drawMaze();
	   // If we landed on the exit print a message and quit
	   if ((playerX == exitX) && (playerY == exitY))
	   {
		   System.out.println("You escaped the maze!");
		   System.exit(0);
	   }
   }

   // *******************************************************************
   // This is the start method for a JavaFX application
   // *******************************************************************
   @Override
   public void start(Stage primaryStage) throws Exception
   {
    Group root = new Group();
    scene = new Scene(root);
    Canvas canvas = new Canvas(SIZE * SPRITE_SIZE, SIZE * SPRITE_SIZE);
    gc = canvas.getGraphicsContext2D();

	// Load images for the player and exit
	playerIcon = new Image("emoji.png");
	flagIcon = new Image("flag.png");

	flipMaze();
	drawMaze();

	/**************************************************************************
	** TO DO:  The following line creates a child class of MazeSolver that
	** requires a human to type in arrow keys for directions to move.
	**
	** Comment it out and uncomment the RandomMazeSolver to instead make a
	** class that randomly picks a direction to move.
	**
	** In this assignment, you are asked to make a new child class of MazeSolver
	** that keeps moving in the same direction until it hits a wall, then picks
	** a random direction to move.  After your class is written you can fire it
	** off by creating an instance of it here instead of the HumanMazeSolver
	** or RandomMazeSolver.
	***************************************************************************/
	HumanMazeSolver solver = new HumanMazeSolver(playerX, playerY, this);
	//RandomMazeSolver solver = new RandomMazeSolver(playerX, playerY, this);
        //RandomDir solver = new RandomDir (playerX, playerY, this);
        //Sequence solver = new Sequence (playerX, playerY, this);

    root.getChildren().add(canvas);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Maze Mania");
    primaryStage.show();
   }

   // Only needed below for older versions of Java
   public static void main(String[] args)
   {
      launch(args);
   }
}
