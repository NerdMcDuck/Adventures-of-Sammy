package omega_Iota_Tao;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Sammy extends BasicGameState {
	public Sammy(int state) {
	}
	
	
	private boolean quit = false;
	private boolean gameover = false;
	private String mouse;
	private String Senergy, Sguilt;
	private Image hud, menu, bg;
	private float samXpos, samYpos;
	private Font font,mfont;
	private TrueTypeFont ttf,mttf;
	private boolean talking = false;
	private int i; //for iterating through dialog
	private String[] dialog = {"Welcome","This is the play state", "To continue press the down arrow key","See how it advances?","Great!","Goodbye!"}; //just a test
	private int CurrentAnimation;
	private Image heroFacingDown, heroFacingUp, heroFacingLeft,heroFacingRight;
	private Image[] heroPlayerRight,heroPlayerLeft, heroPlayerDown,heroPlayerUp;
	Shape herorect;
	private Music guileTheme; 
	/* 
	 * Initialize the game. This can be used to load static resources. It's called before the game loop starts
	 * Load Menu background image
	 * Play Menu music 
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Stage Setup
		bg = new Image("res\\Forest Stage.png");
		hud = new Image("res\\hud.png");
		menu = new Image("res\\Menu.png");
	   gc.setMusicOn(true);
	   gc.setUpdateOnlyWhenVisible(true);
	   font = new Font("Forte", Font.BOLD, 16); ttf = new TrueTypeFont(font, true);
	   mfont = new Font("OCR A STD", Font.BOLD, 12); mttf = new TrueTypeFont(mfont, true);
	   //Player setup
	   Senergy = ""; Sguilt = ""; //Energy and guilt meters
	   herorect = new Rectangle(samXpos, samYpos, 32,52); //for collision detection
	   mouse = "";
	   guileTheme = new Music("res\\Guile Theme (SNES).ogg");
}
	/* 
	 * Used to show to the game screen
	 * Java's default coordinate system for GRAPHICS's (0,0) is TOP LEFT
	 * To track the mouse, we use Slick's default coordinate system with (0,0) being BOTTOM LEFT
	 */
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		bg.draw(0, 0);
		hud.draw(10,50);
		Input input = gc.getInput();
		ttf.drawString(54.0f, 90.0f, Senergy,  org.newdawn.slick.Color.blue);
		ttf.drawString(183.0f, 90.0f, Sguilt,  org.newdawn.slick.Color.blue);
		mttf.drawString(10, 25, mouse,  org.newdawn.slick.Color.blue);
		
		if(quit == true){ g.drawImage(menu, 300,250);}
		
		
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
		float samEnergy = 100; float samGuilt = 0; //Sam energy start 100, if 0 die, guilt starts 0
		Senergy = ""+samEnergy; Sguilt = ""+samGuilt;
		int xpos = Mouse.getX(); //Gets the X position of the mouse. Anywhere from 0 to 1024
		int ypos = gc.getHeight() - Mouse.getY(); //Gets the Y position of the mouse. Anywhere from 0 to 768
		mouse = "Mouse position x: "+ xpos + " y:" + ypos;
		
		isTalking(input);
		
		//If menu is brought up
		if(input.isKeyDown(Input.KEY_ESCAPE)){ quit = true; }
		if(quit == true){
			
			if(input.isKeyDown(Input.KEY_R)){ quit = false; } //Resume the game
			if(input.isKeyDown(Input.KEY_M)){ quit = false; sbg.enterState(0, new FadeOutTransition(),  new FadeInTransition());} //Go back to menu
			if(input.isKeyDown(Input.KEY_Q)){ gc.exit();} //Quit the game
		}
		if(input.isKeyDown(Input.KEY_0)){
			gameover = true;
		}
		if(gameover == true){input.clearKeyPressedRecord(); sbg.enterState(2,new FadeOutTransition(),  new FadeInTransition());}
		
		
		
	}
	
	/* A Toggle to advance through dialog 
	 * @params Input object to detect key pressed
	 * returns nothing*/
	public void isTalking(Input input){
		
		if(input.isKeyPressed(input.KEY_SPACE) && (i < dialog.length)){
			i++;
			talking = true;
		}
		
		else if(input.isKeyPressed(input.KEY_SPACE)){
			talking = !talking;
			
		}else if(input.isKeyPressed(input.KEY_SPACE) && (i == dialog.length)){
			i=0;
			talking = !talking;
			System.out.println("i = " +i);
		}
	}
	
    /*
     * Enter and Leave states
     */
     @Override
     public void enter(GameContainer gc, StateBasedGame sbg) {
    	 guileTheme.play();
    	 guileTheme.loop();
      }
      
     @Override
      public void leave(GameContainer gc, StateBasedGame sbg) {
    	guileTheme.fade(1000, 2, true);
      }
	
	/*
	 * Returns the ID of the current state 
	 */
	public int getID(){
		return 3;
	}

}
