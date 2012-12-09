package exec.SpaceShadows;
/*
import GAME_mouvements;
import GAME_personnage;
import GAME_terrain;
import GAME_typeitem;
import GAME_typepersonnage;
import GAME_typeterrain;
import GameManager;
import MRender3D;
import MTexture;
import mouseListen;
import mouseListen2;
*/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main hook of our game. This class with both act as a manager
 * for the display and central mediator for the game logic. 
 * 
 * Display management will consist of a loop that cycles round all
 * entities in the game asking them to move and then drawing them
 * in the appropriate place. With the help of an inner class it
 * will also allow the player to control the main ship.
 * 
 * As a mediator it will be informed when entities within our game
 * detect events (e.g. alient killed, played died) and will take
 * appropriate game actions.
 * 
 * @author Kevin Glass
 */
public class Game extends Canvas {
	/** The stragey that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	public static boolean gameRunning = true;
	/** The list of all the entities that exist in our game */
	private ArrayList entities = new ArrayList();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList removeList = new ArrayList();
	/** The entity representing the player */
	private Entity ship;
	/** The speed at which the player's ship should move (pixels/sec) */
	private double moveSpeed = 300;
	/** The time at which last fired a shot */
	private long lastFire = 0;
	/** The interval between our players shot (ms) */
	private long firingInterval = 500;
	/** The number of aliens left on the screen */
	private int alienCount;
	
	protected Entity sol[][];
	
	/** The message to display which waiting for a key press */
	private String message = "";
	/** True if we're holding up game play until a key has been pressed */
	private boolean waitingForKeyPress = true;
	/** True if the left cursor key is currently pressed */
	private boolean leftPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean rightPressed = false;
	/** True if we are firing */
	private boolean firePressed = false;
	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;
	
	/**
	 * Construct our game and set it running.
	 */
	public Game() {
		// create a frame to contain our game
		JFrame container = new JFrame("SpaceShadows");
		
		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,800,600);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		addKeyListener(new KeyInputHandler());
		
		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		// initialise the entities in our game so there's something
		// to see at startup
		initEntities();
	}
	
	/**
	 * Start a fresh game, this should clear out any old data and
	 * create a new set.
	 */
	private void startGame() {
		// clear out any existing entities and intialise a new set
		entities.clear();
		initEntities();
		
		// blank out any keyboard settings we might currently have
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
	}
	
	/**
	 * Initialise the starting state of the entities (ship and aliens). Each
	 * entitiy will be added to the overall list of entities in the game.
	 */
	private void initEntities() {
	
	}
	
	/**
	 * Notification from a game entity that the logic of the game
	 * should be run at the next opportunity (normally as a result of some
	 * game event)
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}
	
	/**
	 * Remove an entity from the game. The entity removed will
	 * no longer move or be drawn.
	 * 
	 * @param entity The entity that should be removed
	 */
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}
	
	/**
	 * Notification that the player has died. 
	 */
	public void notifyDeath() {
		message = "Oh no! They got you, try again?";
		waitingForKeyPress = true;
	}
	
	/**
	 * Notification that the player has won since all the aliens
	 * are dead.
	 */
	public void notifyWin() {
		message = "Well done! You Win!";
		waitingForKeyPress = true;
	}
	
	/**
	 * Notification that an alien has been killed
	 */
	public void notifyAlienKilled() {
		// reduce the alient count, if there are none left, the player has won!
		alienCount--;
		
		if (alienCount == 0) {
			notifyWin();
		}
		
		// if there are still some aliens left then they all need to get faster, so
		// speed up all the existing aliens
		for (int i=0;i<entities.size();i++) {
			Entity entity = (Entity) entities.get(i);
			
			if (entity instanceof AlienEntity) {
				// speed up by 2%
				entity.setHorizontalMovement(entity.getHorizontalMovement() * 1.02);
			}
		}
	}
	
	/**
	 * Attempt to fire a shot from the player. Its called "try"
	 * since we must first check that the player can fire at this 
	 * point, i.e. has he/she waited long enough between shots
	 */
	public void tryToFire() {
		// check that we have waiting long enough to fire

	}
	
	/**
	 * The main game loop. This loop is running during all game
	 * play as is responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves
	 * - Moving the game entities
	 * - Drawing the screen contents (entities, text)
	 * - Updating game events
	 * - Checking Input
	 * <p>
	 */
	
	  public void stop ()
	  {
	    // Arrêt des deux threads 
	   System.out.println("QUITTAGE");
	  }
	 

	public void gameLoop() {
		
		
		GameManager gm = new GameManager();
		this.addKeyListener(gm);
		
        //Frame win = new Frame("MyWindow");
        mouseListen a_listener = new mouseListen();
        mouseListen2 a_listener2 = new mouseListen2();
        //this.addMouseListener(a_listener);
        this.addMouseMotionListener(a_listener);
        this.addMouseListener(a_listener2);
        a_listener2.lll = a_listener;
        		//init();			

		long temps;
		long temps2;
		long mili;
		mili = 2;
		int taillecarreauy = 24;
		int taillecarreaux = 50;
		int dimensionx;
		int dimensiony;
		//Taille carreau : 40 / 40
		//Sprite a8 = SpriteStore.get().getSprite("sprites/persos/laser/laser.gif");
		
		
		
		
		Graphics2D gt = (Graphics2D) strategy.getDrawGraphics();
		gt.setColor(Color.black);
		gt.fillRect(0,0,800,600);
		gt.dispose();
		strategy.show();
		
		GAME_bibliotheque bibliotheque = new GAME_bibliotheque();

		long initi = System.currentTimeMillis();
		while (System.currentTimeMillis() - initi < 2000)
		{
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			GAME_bibliotheque.imagesdeco[0].draw(g, 400 - GAME_bibliotheque.imagesdeco[0].getWidth() / 2, 300 - GAME_bibliotheque.imagesdeco[0].getHeight() / 2);
			g.dispose();
			strategy.show();
			try { Thread.sleep(10); } catch (Exception e) {}
		}

		
		
		
		
		
		
		GAME_bibliotheque.jouerplaylist(0, 1);
		
		boolean quitter = false;
		//int etapemenu = 0;
		while (!quitter)
		{
			
			double anglejoueur = 0;
			
			
			double nvitessex = 0;
			double nvitessey = 0;
		//gameRunning = true;
			//boolean choisi = false;
			GAME_menu menu = new GAME_menu((int) getWidth(), (int) getHeight(), a_listener, etapemenu);
			while (!menu.getchoisi())
			{
				//Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
				//Graphics2D g = new Graphics2D();
				Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
				
				menu.afficher(g);
				g.dispose();
				strategy.show();
				try { Thread.sleep(10); } catch (Exception e) {}
			}
			
			
			
			GAME_barreinfos barreinfos = new GAME_barreinfos(getWidth(), getHeight());
			
			GAME_terrain nouveauterrain = new GAME_terrain(120, 120, (int) getWidth(), (int) getHeight(), bibliotheque.textures[0], a_listener, bibliotheque.fonds[0], true);
			GAME_personnage perso1 = new GAME_personnage(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]]);
			
			
			
			
			if (menu.getchoix() == 0)
			{
				bibliotheque.jouerson(0, 1D);
				quitter = false;
				gameRunning = true;
			
				GAME_ES.println("G_" + GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]);
				perso1.setPV(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getPVT());
				perso1.setPVT(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getPVT());
				perso1.setbouclier(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getbouclierT());
				perso1.setbouclierT(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getbouclierT());
				perso1.setrechargebouclier(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getrechargebouclier());
				perso1.setIsIA(false);

				GAME_personnage persoordi = new GAME_personnage(bibliotheque.typesPersonnages[bibliotheque.vide]);
				persoordi.nouvellepositions(500, 500);
				persoordi.setPV(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getPVT());
				persoordi.setPVT(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getPVT());
				persoordi.setbouclier(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getbouclierT());
				persoordi.setbouclierT(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getbouclierT());
				persoordi.setrechargebouclier(bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getrechargebouclier());
				persoordi.setIsIA(true);
				
				
				/*
				GAME_personnage expl = new GAME_personnage(bibliotheque.typesPersonnages[9]);
				expl.nouvellepositions(300, 300);
				perso1.ajouterpersonnage(expl);
				*/
				
				accangle = bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getaccangle();
				accangler = accangle;
				anglejoueur = 0;
				vitessex = 0;
				vitessey = 0;
				vitessemax = bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getvitesse();
				vitessemaxr = vitessemax;
				acceleration = bibliotheque.typesPersonnages[GAME_bibliotheque.typejeu[menu.getchoixvaisseau()]].getacceleration();
				accelerationr = acceleration;
				nvitessex = 0;
				nvitessey = 0;

				
				



				nouveauterrain.ajouterpersonnage(perso1);
				nouveauterrain.ajouterpersonnage(persoordi);
				nouveauterrain.envoyerInfosPersonnage();

			
			}
			
			if (menu.getchoix() == 1)
			{
				nouveauterrain = new GAME_terrain(120, 120, (int) getWidth(), (int) getHeight(), bibliotheque.textures[0], a_listener, bibliotheque.fonds[0], false);
				
				quitter = false;
				gameRunning = true;
			
				perso1.nouvellepositions(200, 200);
				perso1.setPV(2000);
				perso1.setPVT(2000);
				perso1.setbouclier(2000);
				perso1.setbouclierT(2000);
				perso1.setrechargebouclier(10);
				perso1.setIsIA(false);
				
				
				
				

				
				



				nouveauterrain.ajouterpersonnage(perso1);
				nouveauterrain.envoyerInfosPersonnage();

			
			}
			if (menu.getchoix() == 3)
			{
				quitter = true;
				gameRunning = false;
			}
			
			
			
			for (int xx = 0; xx < 120; xx++)
			{
				for (int yy = 0; yy < 120; yy++)
				{
					nouveauterrain.getcase(xx, yy).choisirtypeterrain(bibliotheque.textures[Math.round(Math.round(Math.random() * 7))]);
				}
			}
		
		
		temps = System.currentTimeMillis();
		temps2 = temps;
		Font f = new Font("Verdana", 0, 8);

		
		
				
		//GAME_personnage [] persos = new GAME_personnage[1];
		
		long dernieremaj = System.currentTimeMillis();

		
		long dernierefoistiree = 0;
		long lastLoopTime = System.currentTimeMillis();

		
		// keep looping round til the game ends
		int nbfps = 0;
		float moyenne = 0;
		float fps = 0;
		
		while (gameRunning) {
			
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			
			
			
			long delta = System.currentTimeMillis() - lastLoopTime;
			if (delta > 0)
			{
				fps = (float) (1000 / ((float) delta));
			}
			else
			{
				fps = 0;
			}
			moyenne += fps;
			nbfps++;
			lastLoopTime = System.currentTimeMillis();
			
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			
			// cycle round asking each entity to move itself
			/*if (!waitingForKeyPress) {
				for (int i=0;i<entities.size();i++) {
					Entity entity = (Entity) entities.get(i);
					
					entity.move(delta);
				}
			}*/
			
			if (GAME_dialogue.estFini())
			{
			
				if (bonusvitesseapp)
				{
					
					double vitesseact = Math.sqrt((vitessex * vitessex) + (vitessey * vitessey));
					vitessemax = vitessemaxr * 2;
					acceleration = accelerationr * 2;
					accangle = accangler * 2;
					
					vitessex = vitessemax * vitessex / vitesseact;
					vitessey = vitessemax * vitessey / vitesseact;
				}
				else
				{
					vitessemax = vitessemaxr;
					acceleration = accelerationr;
					accangle = accangler;
				}
				
				
				
				
			boolean achange  = false;
			if (nouveauterrain.getvitessexjoueur() * vitessex < 0)
			{
				achange = true;
				vitessex = -vitessex;
			}
			if (nouveauterrain.getvitesseyjoueur() * vitessey < 0)
			{
				achange = true;
				vitessey = -vitessey;
			}
			
			nvitessex = vitessex;
			nvitessey = vitessey;
			
			
			if (rightPressed)
			{
				achange = true;
			anglejoueur += (accangle * (System.currentTimeMillis() - dernieremaj) / 1000);
			}
			if (leftPressed)
			{
				achange = true;
			anglejoueur -= (accangle * (System.currentTimeMillis() - dernieremaj) / 1000);
			}
			if (upPressed)
			{
				achange = true;
			nvitessex += Math.cos(anglejoueur) * acceleration * (System.currentTimeMillis() - dernieremaj) / 1000;
			nvitessey +=  Math.sin(anglejoueur) * acceleration * (System.currentTimeMillis() - dernieremaj) / 1000;
			}
			if (downPressed)
			{
				achange = true;
			nvitessex -= Math.cos(anglejoueur) * acceleration / 2 * (System.currentTimeMillis() - dernieremaj) / 1000;
			nvitessey -=  Math.sin(anglejoueur) * acceleration / 2 * (System.currentTimeMillis() - dernieremaj) / 1000;
			}
			if ( Math.sqrt(nvitessex * nvitessex + nvitessey * nvitessey) > vitessemax)
			{
				achange = true;
			vitessex = (nvitessex * vitessemax / (Math.sqrt(nvitessex * nvitessex + nvitessey * nvitessey)));
			vitessey = (nvitessey * vitessemax / (Math.sqrt(nvitessex * nvitessex + nvitessey * nvitessey)));
			}
			else
			{
			vitessex = nvitessex;
			vitessey = nvitessey;
			}
			if (GAME_motsdepasses.getactif())
			{
				firePressed = false;
			}
			
			if (firePressed)
			{
				if ((System.currentTimeMillis() - dernierefoistiree) > bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]].getcadence() && GAME_munitions.munitions[armeselectionnee] > 0)
				{
				GAME_personnage perso2 = new GAME_personnage(bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]]);
				GAME_munitions.munitions[armeselectionnee]--;
				perso2.setiddependant(0);
				perso2.nouvellepositions(nouveauterrain.positionxjoueur() - perso2.typedepersonnage().mouvementsindex(perso2.getmouvementchoisi()).largeur() / 2,nouveauterrain.positionyjoueur() - perso2.typedepersonnage().mouvementsindex(perso2.getmouvementchoisi()).longueur() / 2);
				GAME_bibliotheque.jouerson(GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[armeselectionnee]].getSon(), 1D);
				Random hasard = new Random();
				//System.out.println((2.0 * (hasard.nextFloat() - 0.5)) * bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]].getprecision() / 180.0);
				double anglemissile = perso1.approximerangle(anglejoueur) + ((2.0 * (hasard.nextFloat() - 0.5)) * (bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]].getprecision() * 1.0) * 2 * Math.PI / 360.0);
				//
				perso2.setangle(perso2.approximerangle(anglemissile));
				double vitessemissile = bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]].getvitesse();
				perso2.setvitesse((Math.cos(anglemissile) * vitessemissile), (Math.sin(anglemissile) * vitessemissile));
				perso2.calculerdirection();
				
				nouveauterrain.ajoutermissile(perso2, 0);
				dernierefoistiree = System.currentTimeMillis();
				}
			}
			//System.out.println(nvitessex);
			//System.out.println(vitessemax);
			//System.out.println(Math.sqrt(nvitessex * nvitessex + nvitessey * nvitessey));

			nouveauterrain.setanglepersonnage(anglejoueur);
			nouveauterrain.setvitessejoueur(vitessex, vitessey);
			
			//nouveauterrain.setvitessejoueur(vitessex * (System.currentTimeMillis() - dernieremaj) / 1000, vitessey * (System.currentTimeMillis() - dernieremaj) / 1000);
			//if (achange == true)
			//{
				nouveauterrain.envoyerInfosPersonnage();
			//}
			}
			nouveauterrain.afficher(g, modedebug);
			GAME_munitions.afficher(g, armeselectionnee);
			GAME_motsdepasses.afficher(g);
			barreinfos.afficher(g, nouveauterrain);
			dernieremaj = System.currentTimeMillis();
			if (nouveauterrain.getPV() == 0)
			{
				gameRunning = false;
				etapemenu = 2;
			}
			//a8.drawrotation(g, 50, 50, 0.1);
			
			g.setColor(Color.white);
			//g.drawString("FPS: ".concat(String.valueOf(((int) (fps * 100)) / 100)).concat(" | AVG: ").concat(String.valueOf(moyenne / ((float)nbfps))), 10, 10);
			/*if (waitingForKeyPress) {
				//g.setColor(Color.white);
				g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
				g.drawString("Press any key",(800-g.getFontMetrics().stringWidth("Press any key"))/2,300);
			}*/
			
			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			
			
			
			if (!GAME_dialogue.estFini())
			{
				GAME_dialogue.afficher(g);
			}
			
			g.dispose();
			strategy.show();
			
			/*
			// resolve the movement of the ship. First assume the ship 
			// isn't moving. If either cursor key is pressed then
			// update the movement appropraitely
			ship.setHorizontalMovement(0);
			
			if ((leftPressed) && (!rightPressed)) {
				ship.setHorizontalMovement(-moveSpeed);
			} else if ((rightPressed) && (!leftPressed)) {
				ship.setHorizontalMovement(moveSpeed);
			}
			
			// if we're pressing fire, attempt to fire
			if (firePressed) {
				tryToFire();
			}
			*/
			// finally pause for a bit. Note: this should run us at about
			// 100 fps but on windows this might vary each loop due to
			// a bad implementation of timer
			
			try { Thread.sleep(1); } catch (Exception e) {}
		}
		
		}
		System.exit(0);
	}
	
	/**
	 * A class to handle keyboard input from the user. The class
	 * handles both dynamic input during game play, i.e. left/right 
	 * and shoot, and more static type input (i.e. press any key to
	 * continue)
	 * 
	 * This has been implemented as an inner class more through 
	 * habbit then anything else. Its perfectly normal to implement
	 * this as seperate class if slight less convienient.
	 * 
	 * @author Kevin Glass
	 */
	private class KeyInputHandler extends KeyAdapter {
		/** The number of key presses we've had while waiting for an "any key" press */
		private int pressCount = 1;
		
		/**
		 * Notification from AWT that a key has been pressed. Note that
		 * a key being pressed is equal to being pushed down but *NOT*
		 * released. Thats where keyTyped() comes in.
		 *
		 * @param e The details of the key that was pressed 
		 */
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "press"
			/*if (waitingForKeyPress) {
				return;
			}
			*/
			/*
			if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			{
				bonusvitesseapp = true;
			}
			*/
			if (!GAME_motsdepasses.getactif())
			{
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				
				firePressed = true;
			
			}
			}
		} 
		
		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e The details of the key that was released 
		 */
		public void keyReleased(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "released"
			/*if (waitingForKeyPress) {
				return;
			}
			*/
			if (!GAME_motsdepasses.getactif())
			{
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
			
			
			
			if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			{
				bonusvitesseapp = false;
			}
			
			
			
			if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
				armeselectionnee = e.getKeyCode() - 49;
				if (armeselectionnee >= GAME_bibliotheque.armes.length)
				{
					armeselectionnee = GAME_bibliotheque.armes.length - 1;
				}
				if (armeselectionnee < 0)
				{
					armeselectionnee = 0;
				}
				GAME_munitions.selectionner(armeselectionnee);
			}
			if (e.getKeyCode() == KeyEvent.VK_D)
			{
				//modedebug = !modedebug;
			}
			if (e.getKeyCode() == KeyEvent.VK_U)
			{
				GAME_bibliotheque.augmentervolumeglobal();
			}
			if (e.getKeyCode() == KeyEvent.VK_J)
			{
				GAME_bibliotheque.diminuervolumeglobal();
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if (!GAME_dialogue.estFini())
				{
				GAME_dialogue.positionsuivante();
				}
				else
				{
					GAME_motsdepasses.changeretat();
				}
			}
			}
			else
			{
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					GAME_motsdepasses.enleverLettre();
				}
				if (GAME_motsdepasses.caracterevalide(e.getKeyChar()))
				{
					GAME_motsdepasses.ajouterLettre(e.getKeyChar());
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if (!GAME_dialogue.estFini())
					{
					GAME_dialogue.positionsuivante();
					}
					else
					{
						GAME_motsdepasses.changeretat();
					}
				}
			}
		}

		/**
		 * Notification from AWT that a key has been typed. Note that
		 * typing a key means to both press and then release it.
		 *
		 * @param e The details of the key that was typed. 
		 */
		public void keyTyped(KeyEvent e) {
			// if we're waiting for a "any key" type then
			// check if we've recieved any recently. We may
			// have had a keyType() event from the user releasing
			// the shoot or move keys, hence the use of the "pressCount"
			// counter.
			if (waitingForKeyPress) {
				if (pressCount == 1) {
					// since we've now recieved our key typed
					// event we can mark it as such and start 
					// our new game
					waitingForKeyPress = false;
					startGame();
					pressCount = 0;
				} else {
					pressCount++;
				}
			}
			
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				gameRunning = false;
			}
		}
	}
	
	/**
	 * The entry point into the game. We'll simply create an
	 * instance of class which will start the display and game
	 * loop.
	 * 
	 * @param argv The arguments that are passed into our game
	 */
	public static void main(String argv[]) {
		Game g =new Game();

		// Start the main game loop, note: this method will not
		// return until the game has finished running. Hence we are
		// using the actual main thread to run the game.
		g.gameLoop();
	}
	
	public static int getnbrestant()
	{
		return nbrestant;
	}
	
	public static void setnbrestant(int nnbrestant)
	{
		nbrestant = nnbrestant;
	}
	static double vitessex = 100;
	static double vitessey = 100;
	static int nbrestant = 0;
	boolean bonusvitesseapp = false;
	double vitessemax = 300;
	double acceleration = 300;
	double vitessemaxr = 300;
	double accelerationr = 300;
	double accangle = 2;
	double accangler = 2;
	static boolean modedebug = false;
	boolean upPressed;
	boolean downPressed;
	int armeselectionnee = 0;
	static int etapemenu = 0;
}
