package omega_Iota_Tao;

import java.awt.Cursor;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;

public class Menu extends BasicGameState {

	private String mouse;
	private Image Menubg, startButton; 
	private Music openingMenuMusic; 
	private Font font;
	private TrueTypeFont ttf;
	
	
	public Menu(int state) {
	}
	
	/*
	 * Initialize the game. This can be used to load static resources. It's called before the game loop starts
	 * Load Menu background image
	 * Play Menu music 
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		mouse =  "Waiting for input..."; //Actually helps with stuff 
		Menubg = new Image("res\\sunrise_horizon_earth_4k_8k-1024x768.jpg"); //cause black screens are boring
		startButton = new Image("res\\Start_Button.png"); 
		openingMenuMusic = new Music("res\\Zelda_Main_Theme_Song.ogg"); //what's a game without music?
		font = new Font("OCR A STD", Font.BOLD, 12); ttf = new TrueTypeFont(font, true);
	}
	
	/* 
	 * Used to show to the game screen
	 * Java's default coordinate system for GRAPHICS's (0,0) is TOP LEFT
	 * To track the mouse, we use Slick's default coordinate system with (0,0) being BOTTOM LEFT
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(Menubg, 0, 0); //draw the image
		g.drawImage(startButton, 412, 370); //place button
		//g.drawString(mouse, 10, 25); //mouse coordinates
		ttf.drawString(10, 25, mouse,  org.newdawn.slick.Color.cyan);
		
		
	} 
	
	/*
	 * Used to update the game logic
	 * @params 
	 * 	gc - The container holding the game
	 *  sbg - The game holding this state
	 *  delta - The amount of time thats passed in millisecond since last update 
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		 
		Input input = gc.getInput(); //Gets the input from the user
		int xpos = Mouse.getX(); //Gets the X position of the mouse. Anywhere from 0 to 1024
		int ypos = gc.getHeight() - Mouse.getY(); //Gets the Y position of the mouse. Anywhere from 0 to 768
		mouse = "Mouse position x: "+ xpos + " y:" + ypos;
	
		if((xpos >= 424 && xpos <= 618) && (ypos >=388 && ypos <= 450) ){ //Uhh did some simple math, its the space within the button 
			if(input.isMouseButtonDown(0)){ //just gets the input. Just read the slick documentation 
				
				input.clearMousePressedRecord();
				sbg.enterState(1, new FadeOutTransition(),  new FadeInTransition() ); //Go to the play state
				
			}
		}
		
	}
	
    /*
     * Enter and Leave states
     */
     @Override
     public void enter(GameContainer gc, StateBasedGame sbg) {
    	 openingMenuMusic.loop();
    	 
    	 
      }
      
     @Override
      public void leave(GameContainer gc, StateBasedGame sbg) {
    	 openingMenuMusic.stop();
  
      }
	
	/*
	 * Returns the ID of the current state 
	 */
	public int getID(){
		return 0;
	}

}
