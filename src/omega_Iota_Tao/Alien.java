package omega_Iota_Tao;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Alien extends BasicGameState{

	public Alien(int state) {
	}

	private Image spaceship, hud, menu, spaceshipWE;
	private Music impendingDoom, eerie; 
	private Shape textbox;
	private Image black; //A background for text
	private int i, si; //for iterating through dialog
	String[] dialog = {"Let me bring you up to speed zworthonn","We were conducting tests in the milky way galaxy","and discovered several life forms on planet YZS747",
			"third closest to the galaxy’s sun","and YZS748, fourth closest to the sun","We do not think their satellites picked us up","so they are going to be unaware of our arrival",
			"We have no data on these life forms","so we must decide what we want to do","It seems like the life forms on planet YZS747","have developed much more than the ones on planet YZS748",
			"Shall we observe these life forms and remain peaceful", "or shall we terminate them", "and use the planet for our research?"};
	
	String[] script = {"For years on end","the idea that foreign life forms", "exist has been the vanguard of science fiction films", "movies", "TV shows", "books, and other areas.",
			"The work of Matt Ridley in “The Origins of Virtue”", "describes the trust humans have", "and what may or may not influence this trust.", 
			"Are we too submerged in social problems?", "Are we oblivious of what may be coming for our species?", "Is it time for a change?","Now go this way --> until you find him"};
	
	String[] dialogg = {"kalzukowong: " + "I am kalzukowong, and I am a destroyer. "
			+ "We must destroy those species, as it is pertinent to our research and survival in the universe. "
			+ "Destroy the life forms, and I will give you a speed boost power up"};
	
	boolean poweredup = false, intersect = false, talked= false, talking = false, sTalked = false, sTalking = false, Sintersect, storyTime = false;
	private boolean quit = false;
	private boolean gameover = false;
	private String mouse;
	private SpriteSheet alienSS, ramonSS;
	private Animation alienAnimationUp,alienAnimationDown, alienAnimationLeft, alienAnimationRight, ramonAn;
	private Image  alienFacingDown, alienFacingUp, alienFacingLeft,alienFacingRight, alienAlly, sage;
	private Image[] alienPlayerRight,alienPlayerLeft, alienPlayerDown,alienPlayerUp,ramonTU;
	private float alienPosX, alienPosY, alienEnergy = 100, alienGuilt = 30;
	private int CurrentAnimation;
	private String Aenergy, Aguilt;
	private Font font,mfont;
	private TrueTypeFont ttf,mttf;
	private float counter, ramonX, ramonY;
	Shape alienrect, ramonRect, alienAllyrect;
	float alienmovement = .2f;
	private File alienchoices;
	private BufferedWriter writer = null;
	int ki;

	/*
	 * Initialize the game. This can be used to load static resources. It's called before the game loop starts
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//Stage setup stuff
		impendingDoom = new Music("res\\Impending doom.ogg");   i = 0; counter = 0; si = 0;
		spaceship = new Image("res\\Space Ship Stage.png");
		spaceshipWE = new Image("res\\Space Ship Stage No Earth.png");
		hud = new Image("res\\hud.png");
		menu = new Image("res\\Menu.png");
		textbox = new Rectangle(100, 600, 400, 50);
		black = new Image("res\\black.png"); 
		gc.setMusicOn(true);
		font = new Font("Forte", Font.BOLD, 12); ttf = new TrueTypeFont(font, true);
		mfont = new Font("OCR A STD", Font.BOLD, 12); mttf = new TrueTypeFont(mfont, true);
		mouse = ""; 
		eerie = new Music("res\\Creepy Space Music - Dark Spaceships.ogg");
		//ALIEN ANIMATIONS AND POSITION
		alienPosX = 700; alienPosY = 600;
		alienFacingDown = new Image("res\\Sprites\\alien_with_clothing_sprite_forward.png");
		alienFacingUp = new Image("res\\Sprites\\alien_with_clothing_sprite_Standing_back.png");
		alienFacingLeft = new Image("res\\Sprites\\alien_with_clothing_sprite_FacingLeft.png");
		alienFacingRight = new Image("res\\Sprites\\alien_with_clothing_sprite_FacingRight.png");
		alienSS = new SpriteSheet("res\\Sprites\\alien_with_clothing_sprite_walk.png",544,248);
		alienPlayerRight = new Image[9]; alienPlayerLeft = new Image[9]; alienPlayerDown = new Image[9]; alienPlayerUp = new Image[9];
		CurrentAnimation = 0;
		alienAlly = new Image("res\\Sprites\\alien_sprite_1_left.png");
		sage = new Image("res\\Sprites\\sage.png");
		
		//Alien starting energy and guilt meter.
		Aenergy = ""; Aguilt = ""; 

		//Moving right
		int spaceInbetween = 0;
		for(int i=0; i <alienPlayerRight.length; i++){
			alienPlayerRight[i] = alienSS.getSubImage(1+spaceInbetween, 198, 28,55);
			spaceInbetween += 64; //keep this the same value
		}
		alienAnimationRight = new Animation(alienPlayerRight, 100,false);

		//Moving left
		spaceInbetween = 0;
		for(int i=0; i <alienPlayerLeft.length; i++){
			alienPlayerLeft[i] = alienSS.getSubImage(1+spaceInbetween, 70, 28,55);
			spaceInbetween += 64;   
		}
		alienAnimationLeft = new Animation(alienPlayerLeft, 100,false);

		//Moving Up
		spaceInbetween = 0;
		for(int i=0; i <alienPlayerUp.length; i++){
			alienPlayerUp[i] = alienSS.getSubImage(1+spaceInbetween, 6, 32,55);
			spaceInbetween += 64;   
		}
		alienAnimationUp = new Animation(alienPlayerUp, 100,false);

		//Moving Down
		spaceInbetween = 0;
		for(int i=0; i <alienPlayerDown.length; i++){
			alienPlayerDown[i] = alienSS.getSubImage(1+spaceInbetween, 132, 32,55);
			spaceInbetween += 64;   
		}
		alienAnimationDown = new Animation(alienPlayerDown, 100,false);

		//ramon sprite
		ramonSS = new SpriteSheet("res\\Sprites\\Ramon_sprite_walk.png",547,243);
		ramonTU = new Image[4];
		spaceInbetween = 0;
		for(int i=0; i<ramonTU.length;i++){
			ramonTU[i] = ramonSS.getSubImage(0, 3+spaceInbetween, 32, 42);
			spaceInbetween += 64;
		}
		ramonAn = new Animation(ramonTU, 300, false);
		ramonX = 500; ramonY=700;

		//Collision Detection Stuff
		alienrect = new Rectangle(alienPosX, alienPosY, 32,52);
		ramonRect = new Rectangle(ramonX,ramonY, 32, 42);
		alienAllyrect = new Rectangle(850,650, 32, 50);
		ki = 0;
	}//end init


	/* 
	 * Used to show to the game screen
	 * Java's default coordinate system for GRAPHICS's (0,0) is TOP LEFT
	 * To track the mouse, we use Slick's default coordinate system with (0,0) being BOTTOM LEFT
	 */
	boolean choice = false; 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		if(counter > 760 || counter < -250 ){
			spaceshipWE.draw(0,0,counter,0,counter+1024,768);
		}else{
			spaceship.draw(0,0,counter,0,counter+1024,768);
		}
		hud.draw(10,50);
		mttf.drawString(10, 160, "Walk around to find The Storyteller and your companion", org.newdawn.slick.Color.darkGray);
		
		//This controls the direction the sprite faces
		if(CurrentAnimation == 1){//1=up,2=down,3=left,4=right
			alienAnimationUp.draw(alienPosX, alienPosY);
			g.setLineWidth(0);
			alienrect.setX(alienPosX); 
			alienrect.setY(alienPosY);
			g.draw(alienrect);


		}else if(CurrentAnimation == 2){
			alienAnimationDown.draw(alienPosX, alienPosY);
			alienrect.setX(alienPosX); 
			alienrect.setY(alienPosY);
			g.draw(alienrect);
		}else if(CurrentAnimation == 3){
			alienAnimationLeft.draw(alienPosX, alienPosY);
			alienrect.setX(alienPosX); 
			alienrect.setY(alienPosY);
			g.draw(alienrect);
		}else if(CurrentAnimation == 4){
			alienAnimationRight.draw(alienPosX, alienPosY);
			alienrect.setX(alienPosX); 
			alienrect.setY(alienPosY);
			g.draw(alienrect);
		}
		else{ //default
			alienFacingDown.draw(alienPosX, alienPosY); 
			alienrect.setX(alienPosX); 
			alienrect.setY(alienPosY);
			g.draw(alienrect);
		}

		//alienFacingDown.draw(800, 300, 12);
		ttf.drawString(54.0f, 90.0f, Aenergy,  org.newdawn.slick.Color.blue);
		ttf.drawString(183.0f, 90.0f, Aguilt,  org.newdawn.slick.Color.blue);
		mttf.drawString(10, 25, mouse,  org.newdawn.slick.Color.blue);

		//alien is talking to the ramon sprite
		if(talking && (i < dialog.length) && poweredup == false && intersect == true){
			mttf.drawString(420, 588, "Press Space to advance dialog:", org.newdawn.slick.Color.darkGray);	
			mttf.drawString(420, 605, "kalzukazan: "+ dialog[i], org.newdawn.slick.Color.cyan);	
			
		} else if(!talking && intersect == true && talked == false){
			talking = true;
		}
		 
		if(!talking && poweredup == false && intersect == true && talked == true){
			
			if(choice){
				
				sage.draw(ramonX-200,ramonY-30, 3);
				
				mttf.drawString(100, 440, "KALZUKOWONG: " + "I AM KALZUKOWONG, AND I AM A DESTROYER!!!!!." ,org.newdawn.slick.Color.red);	
				mttf.drawString(100, 455, "WE MUST DESTROY THOSE SPECIES, AS IT IS PERTINENT TO OUR RESEARCH AND SURVIVAL IN THE UNIVERSE.",  org.newdawn.slick.Color.red);
				mttf.drawString(100, 470, "DESTROY THE LIFE FORMS and I will give you a speed boost power up", org.newdawn.slick.Color.red);
			}
			
			mttf.drawString(430, 620, "Press A or B on your keyboard to choose:", org.newdawn.slick.Color.darkGray);	
			mttf.drawString(430, 630, "A: Take the powerup and destroy all humans", org.newdawn.slick.Color.red);
			mttf.drawString(430, 640, "B: Don't take the powerup and observe the humans", org.newdawn.slick.Color.red);


		} 
		if(quit == true){ g.drawImage(menu, 300,250);}

		g.setColor(org.newdawn.slick.Color.transparent);

		//Only show ramon sprite only in this location
		if(counter >= 1000 && counter <=1500){
			g.draw(ramonRect);
			ramonAn.draw(ramonX,ramonY);
			
		}
		if(counter >= -1000 && counter <=-500){
			g.draw(alienAllyrect);
			alienAlly.draw(850,650);
		}
		
		
		if(sTalking && Sintersect == true && (counter >= -1000 && counter <=-500) && storyTime == false){
			
			mttf.drawString(420, 630, "Press A or B on your keyboard to choose:", org.newdawn.slick.Color.darkGray);	
			mttf.drawString(430, 640, "A: Listen to the story", org.newdawn.slick.Color.red);
			mttf.drawString(430, 650, "B: Don't listen to the story.", org.newdawn.slick.Color.red);
			
			
		}else if(!sTalking && Sintersect == true && sTalked == false && (counter >= -1000 && counter <=-500) ){
			sTalking = true;
		}
		
		if(storyTime && sTalking && (si < script.length) && Sintersect == true && (counter >= -1000 && counter <=-500)){
			sage.draw(650,350, 15);
			
			mttf.drawString(250, 550, script[si], org.newdawn.slick.Color.cyan);
		} else if((si == script.length) &&!impendingDoom.playing()){
			eerie.stop();
			impendingDoom.play();
		}

	}

	/*
	 * Used to update the game logic
	 *  @params 
	 * 	gc - The container holding the game
	 *  sbg - The game holding this state
	 *  delta - The amount of time thats passed in millisecond since last update
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//Animation updates
		alienAnimationUp.update(delta); alienAnimationDown.update(delta); ramonAn.update(delta);
		alienAnimationLeft.update(delta); alienAnimationRight.update(delta);
		
		

		//Alien energy start 100, if 0 die, guilt starts 0
		Aenergy = ""+alienEnergy; Aguilt = ""+alienGuilt;

		//Mouse inputs
		Input input = gc.getInput();
		int xpos = Mouse.getX(); //Gets the X position of the mouse. Anywhere from 0 to 1024
		int ypos = gc.getHeight() - Mouse.getY(); //Gets the Y position of the mouse. Anywhere from 0 to 768
		mouse = "Mouse position x: "+ xpos + " y:" + ypos + " counter: " + counter;

		
	
		
		//If menu is brought up
		if(input.isKeyDown(Input.KEY_ESCAPE)){ quit = true; }
		if(quit == true){
			impendingDoom.pause();
			if(input.isKeyDown(Input.KEY_R)){ quit = false; impendingDoom.resume(); } //Resume the game
			if(input.isKeyDown(Input.KEY_M)){ quit = false; sbg.enterState(0);} //Go back to menu
			if(input.isKeyDown(Input.KEY_Q)){ gc.exit();} //Quit the game
		}
		if(input.isKeyDown(Input.KEY_0)){
			gameover = true;
		}
		if(gameover == true){input.clearKeyPressedRecord(); sbg.enterState(2,new FadeOutTransition(),  new FadeInTransition());}

		//Moving around the map
		if(input.isKeyDown(Input.KEY_UP)){ //move up
			CurrentAnimation = 1;
			alienAnimationUp.start();
			alienPosY -= delta *alienmovement;

			//Collision Detection
			if(alienPosY<518){
				alienPosY += delta *alienmovement;
			}else if(alienrect.intersects(ramonRect) && (counter >= 1000 && counter <=1500) ){ //ramon sprite
				ramonAn.stop();
				alienPosY += delta * alienmovement;
			}else if(alienrect.intersects(alienAllyrect) && (counter >= -1000 && counter <=-500) ){ //ally sprite
				alienPosY += delta * alienmovement;
			}
		}else {
			alienAnimationUp.stop();
			alienFacingUp.draw(alienPosX, alienPosY);			   
		}

		//Move down
		if(input.isKeyDown(Input.KEY_DOWN)){
			CurrentAnimation = 2;
			alienAnimationDown.start();
			alienPosY += delta *alienmovement;

			//Collision Detection
			if(alienPosY>718){
				alienPosY -= delta *alienmovement;
			}else if(alienrect.intersects(ramonRect) && (counter >= 1000 && counter <=1500)){ //ramon sprite
				ramonAn.stop();
				alienPosY -= delta *alienmovement;
			}else if(alienrect.intersects(alienAllyrect) && (counter >= -1000 && counter <=-500) ){ //ally sprite
				alienPosY -= delta * alienmovement;
			}
		}else {
			alienAnimationDown.stop();
			alienFacingDown.draw(alienPosX, alienPosY);			   
		}

		//Move Left
		if(input.isKeyDown(Input.KEY_LEFT)){
			CurrentAnimation = 3;
			alienAnimationLeft.start();
			alienPosX -= delta *alienmovement;

			//Collision Detection
			if(alienPosX<100){
				alienPosX += delta *alienmovement;
				counter -= .5f;
			}else if(alienrect.intersects(ramonRect) && (counter >= 1000 && counter <=1500)){ //ramon sprite
				ramonAn.stop();
				alienPosX += delta *alienmovement;
			}else if(alienrect.intersects(alienAllyrect) && (counter >= -1000 && counter <=-500) ){ //ally sprite
				alienPosX += delta * alienmovement;
			}
		}else {
			alienAnimationLeft.stop();
			alienFacingLeft.draw(alienPosX, alienPosY);			   
		}

		//Move Right
		if(input.isKeyDown(Input.KEY_RIGHT)){
			CurrentAnimation = 4;
			alienAnimationRight.start();
			alienPosX += delta *alienmovement;

			//Collision Detection
			if(alienPosX>900){
				counter += .5f;
				alienPosX -= delta *alienmovement;
			}else if(alienrect.intersects(ramonRect) && (counter >= 1000 && counter <=1500)){ //ramon sprite
				ramonAn.stop();
				alienPosX -= delta *alienmovement;
			}else if(alienrect.intersects(alienAllyrect) && (counter >= -1000 && counter <=-500) ){ //ally sprite
				alienPosX -= delta * alienmovement;
			}
		}else {
			alienAnimationRight.stop();
			alienFacingRight.draw(alienPosX, alienPosY);			   
		}

		//Set the location of the rectangles
		alienrect.setLocation(alienPosX, alienPosY);
		ramonRect.setLocation(500, 700);

		//More collision detection for ramonAn
		if(ramonAn.isStopped() ){
			intersect = true;
		}else{
			intersect = false; 
		}
		
		if(alienrect.intersects(alienAllyrect)){
			Sintersect = true;
		}else{
			Sintersect = false;
		}
		
		if(ramonAn.isStopped() && poweredup == false){
			isTalking(input, "ramonAn");

			if(ramonAn.isStopped() && alienrect.getMinX() < ramonRect.getMinX()){ //go left
				if(input.isKeyDown(Input.KEY_LEFT)){
					CurrentAnimation = 3;
					alienAnimationLeft.start();
					alienPosX -= delta *alienmovement;
					ramonAn.restart();}
			}else if(ramonAn.isStopped() && alienrect.getMaxX() > ramonRect.getMaxX()){ //go right
				if(input.isKeyDown(Input.KEY_RIGHT)){
					CurrentAnimation = 4;
					alienAnimationRight.start();
					alienPosX += delta *alienmovement;
					ramonAn.restart();}
			}else if(ramonAn.isStopped() && alienrect.getMinY() < ramonRect.getMinY()){ //go up
				CurrentAnimation = 1;
				alienAnimationUp.start();
				alienPosY -= delta *alienmovement;
				ramonAn.restart();
			} 
		}else if(ramonAn.isStopped() && poweredup == true){
			if(ramonAn.isStopped() && alienrect.getMinX() < ramonRect.getMinX()){ //go left
				if(input.isKeyDown(Input.KEY_LEFT)){
					CurrentAnimation = 3;
					alienAnimationLeft.start();
					alienPosX -= delta *alienmovement;
					ramonAn.restart();}
			}else if(ramonAn.isStopped() && alienrect.getMaxX() > ramonRect.getMaxX()){ //go right
				if(input.isKeyDown(Input.KEY_RIGHT)){
					CurrentAnimation = 4;
					alienAnimationRight.start();
					alienPosX += delta *alienmovement;
					ramonAn.restart();}
			}else if(ramonAn.isStopped() && alienrect.getMinY() < ramonRect.getMinY()){ //go up
				CurrentAnimation = 1;
				alienAnimationUp.start();
				alienPosY -= delta *alienmovement;
				ramonAn.restart();
			}

		}

		//Decrease the energy of the alien
		if(!talking && poweredup == false && intersect == true && talked == true){
			if(input.isKeyDown(Input.KEY_A)){
				alienmovement = .6f;
				poweredup = true; 
				alienEnergy -= 10f;
				alienGuilt -= 20f;
				writoTofile("Decision: You Decided to kill the humans") ;
				sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
			}else if(input.isKeyDown(Input.KEY_B)){
				poweredup = true; 
				alienGuilt += 10f;
				alienEnergy += 10f;
				writoTofile("Decision: Decided to observe the humans peacefully");
			}
		}

		//More collision detection for allyalien 1=up,2=down,3=left,4=right
		if(alienrect.intersects(alienAllyrect)){
			
			if(alienrect.getMinX() < alienAllyrect.getMinX()){ //go left
				if(input.isKeyDown(Input.KEY_LEFT)){
					CurrentAnimation = 3;
					alienAnimationLeft.start();
					alienPosX -= delta *alienmovement;
				}
			}else if(alienrect.getMaxX() > alienAllyrect.getMaxX()){ //go right
				if(input.isKeyDown(Input.KEY_RIGHT)){
					CurrentAnimation = 4;
					alienAnimationRight.start();
					alienPosX += delta *alienmovement;
				}	
			}else if(alienrect.getMinY() < alienAllyrect.getMinY()){ //go up
				CurrentAnimation = 1;
				alienAnimationUp.start();
				alienPosY -= delta *alienmovement;
			}else if(alienrect.getMinY() < alienAllyrect.getMaxY()){ //go down
				CurrentAnimation = 2;
				alienAnimationDown.start();
				alienPosY += delta *alienmovement;
			}

		} 
		
		if(sTalking && Sintersect == true && (counter >= -1000 && counter <=-500) && storyTime == false){
			if(input.isKeyDown(Input.KEY_A)){
				storyTime = true; 
				eerie.play();
				if(alienEnergy <= 90){	alienEnergy += 10f;	}
				if(alienGuilt >= 5){alienGuilt -= 5f;  }
				writoTofile("Decision: You listened to the Sage's story") ;

			}else if(input.isKeyDown(Input.KEY_B)){
				alienGuilt += 5f;
				writoTofile("Decision: You didn't listened to the Sage's story");
				
			}
		}else if(Sintersect == true && storyTime == true){
			if(input.isKeyPressed(input.KEY_SPACE) && (si < script.length)){ //The script 
				si++;
				sTalking = true;
			}else if((si == script.length)){
				
				sTalking = !sTalking;	
			}

			else if((si == script.length) ){
				sTalking = false;
				sTalked = true;
				impendingDoom.play();
			}
			
		}
		
		if(input.isKeyPressed(Input.KEY_ENTER) && ki < dialogg.length && choice ){
			ki++;
		}

		//A way to get to Sammy screen
		if(input.isKeyDown(Input.KEY_1)){
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
	}

	/* A Toggle to advance through dialog 
	 * @params Input object to detect key pressed
	 * returns nothing*/
	public void isTalking(Input input, String caller){
		
		if(input.isKeyPressed(input.KEY_SPACE) && (i < dialog.length) && caller.equals("ramonAn")){
			i++;
			talking = true;
		}

		else if(input.isKeyPressed(input.KEY_SPACE) && caller.equals("ramonAn")){
			talking = !talking;

		}else if((i == dialog.length) && caller.equals("ramonAn")){
			talking = false;
			talked = true;
			choice = true;

		}

	}
	//Write to file
	public void writoTofile(String choice){
		try{
			alienchoices = new File("alienchoices");
			if(! alienchoices.exists()){
				alienchoices.createNewFile();

				writer = new BufferedWriter(new FileWriter(alienchoices, true));
				writer.write(choice);
				writer.close();  
			}else{
				writer = new BufferedWriter(new FileWriter(alienchoices, true));
				writer.write("\n"+choice);
				writer.close();  
			}


		}catch(NullPointerException e){
			System.out.println("No Such File or Directory");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Enter and Leave states
	 */
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		impendingDoom.play();
		impendingDoom.loop();
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) {
		impendingDoom.stop();
		i = 0;
		talking = !talking;
		gc.getInput().clearKeyPressedRecord();
	    
	}


	/*
	 * Returns the ID of the current state 
	 */
	public int getID(){
		return 1;
	}

}
