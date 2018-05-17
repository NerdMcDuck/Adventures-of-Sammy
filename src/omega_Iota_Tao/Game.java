package omega_Iota_Tao; //Package name(OIT) inspired by Carlos 

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	/* gamename: the name of the game. It will be displayed on the top of the window
	 * menu, play: the id we will be using to change between the menu screen and the play screen
	 * By the default, game opens to the menu screen
	 */
	
	private static final String GAMENAME ="The Adventures of Sammy!";
	private static final int MENU = 0; 
	private static final int ALIEN = 1;
	private static final int GAMEOVER = 2;
	private static final int SAMMY = 3;
	/*
	 * @param gamename is the name of the game
	 */
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(MENU)); //Menu state
		this.addState(new Alien(ALIEN)); //Play state
		this.addState(new GameOver(GAMEOVER));
		this.addState(new Sammy(SAMMY));
		this.enterState(MENU); //The default state that will be displayed when the game first starts
	}
	
	/*
	 * Initialize all the states 
	 */
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MENU).init(gc, this);
		this.getState(ALIEN).init(gc, this);
		this.getState(GAMEOVER).init(gc, this);
		this.getState(SAMMY).init(gc, this);
	}
	
	/*
	 *  Create container to display the game as a stand alone window
	 *  Wrap the game in this container
	 *  Set the game to open 1024x768 windowed mode 
	 */
	public static void main(String[] args) {
		AppGameContainer appgc; 
		
		try{
			
			appgc = new AppGameContainer(new Game(GAMENAME)); 
			appgc.setDisplayMode(1024, 768, false);
			appgc.start();
			
		}catch(SlickException e){
			System.out.println("Failed to initialize the display. Somebody dun goofed!");
			e.printStackTrace();
		}

	}

}
