package omega_Iota_Tao;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameOver extends BasicGameState {
	
	private Image gameover, gameovertext;
	private Music gameoverSFX;
	public GameOver(int state) {
	}
	
	/*
	 * Initialize the game. This can be used to load static resources. It's called before the game loop starts
	 * Load Menu background image
	 * Play Menu music 
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gameover = new Image("res\\Gameover.jpg");
		gameovertext = new Image("res\\gameovertext.png");
		gameoverSFX = new Music("res\\Game Over sound effect.ogg");
	}
	
	/* 
	 * Used to show to the game screen
	 * Java's default coordinate system for GRAPHICS's (0,0) is TOP LEFT
	 * To track the mouse, we use Slick's default coordinate system with (0,0) being BOTTOM LEFT
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(gameover,0,0);
		g.drawImage(gameovertext,250,550);
	} 
	
	/*
	 * Used to update the game logic
	 * @params 
	 * 	gc - The container holding the game
	 *  sbg - The game holding this state
	 *  delta - The amount of time thats passed in millisecond since last update 
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		 if(input.isKeyDown(Input.KEY_ESCAPE)){
			input.clearKeyPressedRecord();
			gc.exit();
		 }
	}
	
    /*
     * Enter and Leave states
     */
     @Override
     public void enter(GameContainer gc, StateBasedGame sbg) {
    	 gameoverSFX.loop();
      }
      
     @Override
      public void leave(GameContainer gc, StateBasedGame sbg) {
    	 gameoverSFX.stop();
      }
	
	/*
	 * Returns the ID of the current state 
	 */
	public int getID(){
		return 2;
	}

}
