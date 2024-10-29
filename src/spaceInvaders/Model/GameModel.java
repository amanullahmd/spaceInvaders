package spaceInvaders.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameModel class implements the IGameModel interface and is responsible for managing the state of the Space Invaders game.
 * It keeps track of game entities like the player, enemy, bullets, and stars, and handles the logic for updating their states.
 * The class also manages the game's screen dimensions and initializes the starting positions of the player and enemy.
 *
 * @version 1.0
 * @author MD Amanullah
 */
public class GameModel implements IGameModel {
    /**
     * Constant representing the width of the game screen.
     */
    private static final int SCREEN_WIDTH = 1000;

    /**
     * Constant representing the height of the game screen.
     */
    private static final int SCREEN_HEIGHT = 600;


    /**
     * Initial X-coordinate for the player.
     */
    private static final int initialPlayerX = (SCREEN_WIDTH - Player.WIDTH) / 2;

    /**
     * Initial Y-coordinate for the player.
     */
    private static final int initialPlayerY = SCREEN_HEIGHT - Player.HEIGHT;

    /**
     * Initial X-coordinate for the enemy.
     */
    private static final int initialEnemyX = (SCREEN_WIDTH - Enemy.WIDTH) / 2;

    /**
     * Initial Y-coordinate for the enemy.
     */
    private static final int initialEnemyY = 10;

    /**
     * Instance of the player character.
     */
    private Player player;

    /**
     * Instance of the enemy character.
     */
    private Enemy enemy;


    /**
     * List to store bullets fired by the player.
     */
    private List<Bullet> bullets;


    /**
     * List to store bullets fired by the enemy.
     */
    private List<EnemyBullet> enemyBullets;

    /**
     * Instance of the star object in the game.
     */
    private Star star;

    /**
     * Flag indicating whether a star currently exists in the game.
     */
    private boolean starExists;

    /**
     * Timestamp of the last star update.
     */
    private long lastStarTime;

    /**
     * Random number generator for various game elements.
     */
    private Random random;

    /**
     * Flag to indicate whether the game has ended.
     */
    private boolean isGameOver;

    /**
     * Thread for managing periodic updates of the star.
     */
    private Thread starUpdateThread;

    /**
     * Flag to control the running state of the starUpdateThread.
     */
    private volatile boolean running = true;


    /**
     * Timestamp of the last shot fired by the player.
     * This is used to implement a cooldown mechanism, ensuring that there is a delay
     * between consecutive shots fired by the player. The time is stored in milliseconds.
     */
    private long lastPlayerShotTime = 0;

    /**
     * Timestamp of the last shot fired by the enemy.
     * Similar to lastPlayerShotTime, this field is used to enforce a delay
     * between consecutive shots fired by the enemy. The time is stored in milliseconds.
     */
    private long lastEnemyShotTime = 0;




    /**
     * Constructor for GameModel. Initializes the game by setting up the player, enemy,
     * bullets, and the star update thread.
     */
    public GameModel() {
        player = new Player(initialPlayerX, initialPlayerY);
        enemy = new Enemy(initialEnemyX, initialEnemyY);
        bullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        random = new Random();
        isGameOver = false;

        startStarUpdateThread(); // Starts a thread to manage star appearance
    }

    /**
     * Moves the player character based on the given deltas in x and y direction.
     *
     * @param dx Delta for the x-axis movement.
     * @param dy Delta for the y-axis movement.
     */
    @Override
    public void movePlayer(int dx, int dy) { player.move(dx, dy); }

    /**
     * Moves the enemy character based on the given deltas in x and y direction.
     *
     * @param dx Delta for the x-axis movement.
     * @param dy Delta for the y-axis movement.
     */
    @Override
    public void moveEnemy(int dx, int dy) { enemy.move(dx, dy); }

    /**
     * Shoots a bullet from the player's position. The bullet is added to the bullet list.
     */
    public void shootBullet() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPlayerShotTime >= 500) { // 500 milliseconds cooldown
            int bulletStartX = player.getX() + Player.WIDTH / 2 - Bullet.WIDTH / 2;
            int bulletStartY = player.getY() - Bullet.HEIGHT;
            bullets.add(new Bullet(bulletStartX, bulletStartY));
            lastPlayerShotTime = currentTime;
        }
    }

    /**
     * Shoots a bullet from the enemy's position. The enemy bullet is added to the enemy bullet list.
     */
    public void shootEnemyBullet() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastEnemyShotTime >= 500) { // 500 milliseconds cooldown
            int bulletStartX = enemy.getX() + Enemy.WIDTH / 2 - EnemyBullet.WIDTH / 2;
            int bulletStartY = enemy.getY() + Enemy.HEIGHT;
            enemyBullets.add(new EnemyBullet(bulletStartX, bulletStartY));
            lastEnemyShotTime = currentTime;
        }
    }

    /**
     * Updates the status of all player bullets. It moves the bullets, checks for collisions,
     * removes off-screen bullets, and updates the game state if the enemy is defeated.
     */
    private void updateBullets() {
        bullets.forEach(Bullet::update);
        bullets.removeIf(Bullet::isOffScreen);

        bullets.removeIf(bullet -> checkCollision(bullet, enemy) ? handleBulletEnemyCollision(bullet) :
                (starExists && checkCollision(bullet, star)) ? handleBulletStarCollision(bullet) : false);

        isGameOver = enemy.getLives() <= 0 ? true : isGameOver;
    }

    /**
     * Updates the status of all enemy bullets. It moves the bullets, checks for collisions,
     * removes off-screen bullets, and updates the game state if the player is defeated.
     */
    private void updateEnemyBullets() {
        enemyBullets.forEach(EnemyBullet::update);
        enemyBullets.removeIf(EnemyBullet::isOffScreen);

        enemyBullets.removeIf(bullet -> checkCollision(bullet, player) ? handleBulletPlayerCollision(bullet) :
                (starExists && checkCollision(bullet, star)) ? handleBulletStarCollision(bullet) : false);

        isGameOver = player.getLives() <= 0 ? true : isGameOver;
    }

    /**
     * Handles the collision between a player bullet and the enemy.
     * It decreases the enemy's lives and increases the player's score.
     *
     * @param bullet The bullet that hit the enemy.
     * @return true to indicate the bullet should be removed.
     */
    private boolean handleBulletEnemyCollision(Bullet bullet) {
        enemy.decreaseLives(1);
        player.increaseScore(50);
        return true;
    }

    /**
     * Handles the collision between any bullet and the star.
     * It increases the player's lives when a player bullet hits the star,
     * or the enemy's lives when an enemy bullet hits the star.
     *
     * @param bullet The bullet that hit the star.
     * @return true to indicate the bullet should be removed.
     */
    private boolean handleBulletStarCollision(Bullet bullet) {
        player.increaseLives(2); // Player gains a life for shooting the star
        starExists = false;
        return true;
    }

    /**
     * Checks if a bullet has collided with the enemy.
     *
     * @param bullet The bullet to check for collision.
     * @param enemy The enemy to check for collision with.
     * @return true if the bullet has collided with the enemy, false otherwise.
     */
    private boolean checkCollision(Bullet bullet, Enemy enemy) {
        return bullet.getX() < enemy.getX() + Enemy.WIDTH && bullet.getX() + Bullet.WIDTH > enemy.getX() && bullet.getY() < enemy.getY() + Enemy.HEIGHT && bullet.getY() + Bullet.HEIGHT > enemy.getY();
    }
    /**
     * Checks if a bullet has collided with the star.
     *
     * @param bullet The bullet to check for collision.
     * @param star   The star to check for collision with.
     * @return true if the bullet has collided with the star, false otherwise.
     */
    private boolean checkCollision(Bullet bullet, Star star) {
        return bullet.getX() < star.getX() + Star.SIZE && bullet.getX() + Bullet.WIDTH > star.getX() && bullet.getY() < star.getY() + Star.SIZE && bullet.getY() + Bullet.HEIGHT > star.getY();
    }

    /**
     * Handles the collision between an enemy bullet and the player.
     * It decreases the player's lives and increases the enemy's score.
     *
     * @param bullet The bullet that hit the player.
     * @return true to indicate the bullet should be removed.
     */
    private boolean handleBulletPlayerCollision(EnemyBullet bullet) {
        player.decreaseLives(1);
        enemy.increaseScore(50);
        return true;
    }

    /**
     * Handles the collision between an enemy bullet and the star.
     * It increases the enemy's lives when an enemy bullet hits the star.
     *
     * @param bullet The bullet that hit the star.
     * @return true to indicate the bullet should be removed.
     */
    private boolean handleBulletStarCollision(EnemyBullet bullet) {
        enemy.increaseLives(2); // Enemy gains a life for shooting the star
        starExists = false;
        return true;
    }

    /**
     * Checks if an enemy bullet has collided with the player.
     *
     * @param bullet The enemy bullet to check for collision.
     * @param player The player to check for collision with.
     * @return true if the enemy bullet has collided with the player, false otherwise.
     */
    private boolean checkCollision(EnemyBullet bullet, Player player) {
        return bullet.getX() < player.getX() + Player.WIDTH && bullet.getX() + EnemyBullet.WIDTH > player.getX() && bullet.getY() < player.getY() + Player.HEIGHT && bullet.getY() + EnemyBullet.HEIGHT > player.getY();
    }

    /**
     * Checks if an enemy bullet has collided with the star.
     *
     * @param bullet The enemy bullet to check for collision.
     * @param star   The star to check for collision with.
     * @return true if the enemy bullet has collided with the star, false otherwise.
     */
    private boolean checkCollision(EnemyBullet bullet, Star star) {
        return bullet.getX() < star.getX() + Star.SIZE && bullet.getX() + EnemyBullet.WIDTH > star.getX() && bullet.getY() < star.getY() + Star.SIZE && bullet.getY() + EnemyBullet.HEIGHT > star.getY();
    }
    /**
     * Starts a thread responsible for updating the appearance of the star at regular intervals.
     * The star's appearance changes every 10 seconds.
     */
    private void startStarUpdateThread() {
        starUpdateThread = new Thread(() -> {
            while (running) {
                updateStar();
                try {
                    Thread.sleep(10000); // Update every 10 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); }
            }
        });
        starUpdateThread.setPriority(Thread.MAX_PRIORITY); // Set the thread priority to maximum
        starUpdateThread.start();
    }


    /**
     * Updates the position of the star and its existence status based on time intervals.
     */
    private void updateStar() {
        long currentTime = System.currentTimeMillis();
        if ((starExists && currentTime - lastStarTime > 20000) || (!starExists && currentTime - lastStarTime > 10000)) {
            int starX = random.nextInt(SCREEN_WIDTH - Star.SIZE);
            int starY = random.nextInt(SCREEN_HEIGHT - Star.SIZE);
            star = new Star(starX, starY);
            lastStarTime = currentTime;
            starExists = !starExists;
        }
    }



    /**
     * Updates the game state including bullets, enemy bullets, and the star.
     * Checks for game over conditions.
     */
    public void updateGame() {
        if (!isGameOver) {
            updateBullets();
            updateEnemyBullets();
            updateStar();
        }
        isGameOver = isGameOver || (player.getLives() <= 0 || enemy.getLives() <= 0) ? true : isGameOver;
    }

    /**
     * Resets the game state to the initial state with the player, enemy, and empty bullet lists.
     */
    public void resetGame() {
        player = new Player(initialPlayerX, initialPlayerY);
        enemy = new Enemy(initialEnemyX, initialEnemyY);
        bullets.clear();
        enemyBullets.clear();
        isGameOver = false;
    }



    // Getters and setters
    public Player getPlayer() { return player; }
    public List<Bullet> getBullets() { return bullets; }
    public Enemy getEnemy() { return enemy; }
    public List<EnemyBullet> getEnemyBullets() { return enemyBullets; }
    public boolean isStarExists() { return starExists; }
    public Star getStar() { return star; }
    public boolean isGameOver() { return isGameOver; }
    public int getScore() { return player.getScore(); }
    public int getLifeCount() { return player.getLives(); }
    public int getEnemyScore() { return enemy.getScore(); }
    public int getEnemyLives() { return enemy.getLives(); }

  /* @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameModel {");
        sb.append("\n Player: ").append(getPlayer());
        sb.append("\n Enemy: ").append(getEnemy());
        sb.append("\n Bullets: ").append(getBullets());
        sb.append("\n Enemy Bullets: ").append(getEnemyBullets());
        sb.append("\n Star Exists: ").append(isStarExists());
        sb.append("\n Score: ").append(getScore());
        sb.append("\n Player Life Count: ").append(getLifeCount());
        sb.append("\n Enemy Score: ").append(getEnemyScore());
        sb.append("\n Enemy Lives: ").append(getEnemyLives());
        sb.append("\n}");
        return sb.toString();
    } */
}
