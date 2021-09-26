# Multiplayer-Maze-Game
##How to run the program:
The program can be run by using the JAR file present in the ZIP folder.
1. The Zip folder contains 3 folders. 
2. Navigate to the 'res' folder which contains the HW6.jar file.
3. Using terminal run the HW6.jar file by navigating to the folder where the current file is present
 and using the following command, "java -jar HW6.jar".
4. The JAR file has to be placed outside the Res folder to run.
5. The above the command line along with the "--gui" would run the GUI and the "--text" would run
the text based game.

Note: Make sure JAVA class path has been added to the environment variables.

##Design:
1. An interface has been created for a building a maze with an abstract class implementing the 
interface.
2. There are 2 concrete classes namely perfect maze and non-perfect maze. A wrapping maze has been
 passed as a parameter to the maze in all 4 kind of mazes can be created.
3. Player is a separate class and inorder to avoid mutation of objects the player and maze interact 
in the GamePlay class. 
4. Each of the room is assumed to be an object of node class, and the connect with various nodes, 
or the rooms of the maze are stored in the adjacency list.
5. The Gameplay class has been extended to accommodate new changes to the problem statement keeping
in mind the extension rule of SOLID principles.
6. The arrow has been separately represented as class as it has directions just like the player.
8. A new controller has been created to incorporate the GUI based game along with an interface.
9. For creating a view a new package has been created with a IView interface. 2 classes have been 
created have been created to have views, one for taking the inputs from the user to build all the
features required to build the maze. The other class has been used for making the view for displaying 
the maze as well as the player movement during the game.


##Design Changes:
1. A new class called GamePlayMultiPlayer has been created which extends the previous created 
GamePlayExtended class to accomodate the new changes of player switching as well as to add the 
additional functionality of making the game multi player based.
2. The arrows has been from the gameplay class to the to player class so that each player can 
keep track of his own arrow count.
3. The player has been given a new functionality of remembering his previous moves inorder to
make changes to the visibility of the maze during the game play.
7. No other changes have been made to the model apart from these. 

##Further Enhancements:
All the required features mentioned in the homework have been implemented and tested thoroughly.

1. Expose all game settings through menus or other controls. These settings include maze size,
 number of walls, number of players (1 or 2), and difficulty (specified as number of superbats, 
 pits, etc). Tested completely.

2. Provide an option for restarting the game as a new game or as the same game (with the same random
 seed resulting in the same maze). Tested completely.

3. Allow the maze to be bigger than the area allocated to it on the screen providing the ability to 
scroll the view. Tested completely.

4. Allow the player to move through the maze using a mouse click in addition to the keyboard arrow 
keys. A click on an invalid space, or pressing an invalid key would not advance the game.
Tested completely.

5. Provide an option for two players where players take turns making moves or shooting arrows as 
they race to be the first to kill the Wumpus. In this mode, the rules of the game remain the same
 as before. The game ends when one player has killed the Wumpus, or both players have died. 
 Arrows shot by a player miraculously miss the other player. 
 Tested completely.

6. Provide a clear indication of the results of each action a player takes as well as whose turn it
 is.
 Tested completely.

##Assumptions:
1. The bats and pits percentage calculation is done after creation of the hallways.
2. The bats remain come back to their own location after the player is picked up and transported.
3. A 3X3 maze cannot be created.
4. The arrows a player have are fixed to 3.
5. Once all the arrows are over the game is ended.
6. Wampus and bats cannot co exist but bats and pits can exist together.
7. The hints wont be visible in the room once the player leaves.
8. The bat dont come back to their previous position after dropping the player at the new position.
9. The Wampus location will be visible to the other player if one of the player is killed by it.
10. The bat and pit percentage can be given anywhere between 0 and 100. 
11. The mouse click in the game is taken throught button rather than maze clicks. 
12. Only arrows have been configured for player movement and arrows has not been configured.

##Demo:
`https://youtu.be/BuK3S4sIgyo`
https://youtu.be/YsWA7StLSCg
https://youtu.be/luLzYE7FULI
The above the link for the demo of the game play. The first frame which appears on the screen is the view 
for taking in all the inputs required for building the maze. In the video a 2 player maze has been built,
which is of an imperfect type.
As seen in the video the choice of players is switched after every move. The video showed all features 
as per the assignment requirement.
