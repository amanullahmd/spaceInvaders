# Space Invaders Game - Java Edition

## Overview
This Java version of "Space Invaders," crafted as a student project, revitalizes the classic
arcade game with modern programming techniques. Players engage in an interactive battle
controlling spaceships, with one assuming the role of the player and another as the enemy. 
The project, developed in Java and utilizing the Processing library, demonstrates the 
application of object-oriented programming and graphics integration. Featuring dynamic 
player-enemy interactions, collision detection, scoring, and various game states, this 
game showcases both technical skill and an understanding of game development.


## Features

### Gameplay Mechanics
- **Player Control**: Navigate using arrow keys and shoot with the spacebar.
- **Enemy Control**: Move using 'A', 'W', 'S', 'D' keys and shoot with 'F'.
- **Collision Detection**: Tracks interactions between all game entities.
- **Scoring System**: Both player and enemy score points by striking each other.
- **Life Counts**: Both entities have limited lives, influencing game flow.
- **Game States**: Manage game flow with Start, Pause, and Game Over states.

### Technical Features
- **Java-Based**: Core programming in Java for cross-platform compatibility.
  - [StandardCharsets](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/class-use/Charset.html)
  - [Files Documentation](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html)
  - [Paths Documentation](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html)
  - [IOException Documentation](https://docs.oracle.com/javase/8/docs/api/index.html?java/io/IOException.html)
  

- **Processing Library**: Utilized for rendering graphics and handling user inputs. [Processing Library](https://processing.org/) 
  -  [PApplet](https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html)
  -  [PImage](https://processing.github.io/processing-javadocs/core/processing/core/PImage.html)
  -  [KeyEvent](https://processing.github.io/processing-javadocs/core/processing/event/KeyEvent.html)
  -  [PFont](https://processing.org/reference/PFont.html)


- **JUnit 5 Testing**: Comprehensive unit tests for model classes to ensure code reliability.
  - [JUnit Library](https://junit.org/junit5/)

## Project Structure

### Packages and Key Classes
- `spaceInvaders.Controller`: Contains `GameController` for game logic and user interaction handling.
- `spaceInvaders.Main`: Houses `Main` class, the entry point of the application.
- `spaceInvaders.Model`: Includes entity classes (`Player`, `Enemy`, `Bullet`, `EnemyBullet`, `Star`) and `GameModel` for game state management.
- `spaceInvaders.View`: For graphical interface rendering, with `GameView` as the primary class.
- `ModelTest`: Dedicated to JUnit testing of model components.

## Screenshots
### Starting Gaming display with "Game Info" button.
<img alt="" src="images/startScreenGameInfo.png" width="400" height="300">  

### GamePlay Instructions with "Back" button.
<img alt="" src="images/gamePlayInstructions.png" width="400" height="300"> 

### The player and the enemy engage in a fierce battle, shooting with each other on the gaming display.
<img height="300" src="images/shootPlayerEnemy.png" width="400"/>

### Paused the game.
<img alt="" src="images/pausedGame.png" width="400" height="300"> 

### The player's victory is displayed.
<img alt="" src="images/playerWin.png" width="400" height="300"> 

### The enemy's victory is displayed.
<img alt="" src="images/enemyWin.png" width="400" height="300"> 


## Setup and Execution

### Prerequisites
- Java Development Kit (JDK) 21 version.
- Processing 4.3
- JUnit 5 for unit testing.

### Running the Game
1. Open the entire game project in an editor, such as IntelliJ IDEA.
2. Open the `spaceInvaders.Main.Main` class in your preferred IDE or Processing environment.
3. Compile and run the main method. In Processing IDE, simply run the sketch.
4. Use keyboard controls (arrow keys, spacebar, 'A', 'W', 'S' 'D', 'F') to play the game. Press 'P' to pause.
5. User can see Game Info to click the "GameInfo" button to read the Gameplay Instructions.
6. Can also return the Game Start Screen to click "Back" button or press "Space" key.

### How to Play: 
- After run the Main class:

- **Starting the Game**: Press spacebar.
  
- **Player Controls**:
    - Use the left, right, up, and down arrow keys to navigate the player
  spaceship across the entire game display.
    - Press the spacebar to shoot bullets towards the enemy and Star.

- **Enemy Controls**:
    - Use the 'A', 'W', 'S' and 'D' keys for left, up, down and right movement to move the enemy
  spaceship across the entire game display.
    - Press 'F' to shoot bullets towards the player and Star.

- **Pausing the Game**: Press 'P'.

- **Scoring and Winning**:
    - Each hit on the enemy scores points for the player. Likewise, the enemy scores points for hitting the player.
    - The game ends when either the player or the enemy runs out of lives.
    - The side with the higher score at the end of the game is declared the winner.

- **Restarting the Game**: Press spacebar.


### Testing with JUnit
To execute unit tests:
1. Navigate to the `ModelTest` package.
2. Run the provided JUnit test cases to validate model logic.

#### Here's a method to test the spaceInvaders.Model using JShell
```java

//Open the SpaceInvaders folder in the command line and enter the command in Windows.
//then enter the following commands in the JShell Import PokePong.Model classes:
jshell --class-path ".\SpaceInvaders\out\production\SpaceInvaders" 

import spaceInvaders.Model.*;

//Example JShell test for the Player class:
// Creating a new Player instance

Player player = new Player(100, 100);  // Start at coordinates (100, 100)
System.out.println("Initial Player State: " + player);

// Moving the Player
player.move(1, 0);  // Move right
player.move(0, 1);  // Move down
System.out.println("Player position after moving: x = " + player.getX() + ", y = " + player.getY());

// Changing Player Lives and Score
player.decreaseLives(1);  // Decrease a life
player.increaseLives(2);  // Increase lives by 2
player.increaseScore(50); // Increase score by 50
System.out.println("Player stats: Lives = " + player.getLives() + ", Score = " + player.getScore());

// Displaying Player's Current State
System.out.println("Current Player State: " + player);

// Exit the JShell

/exit

