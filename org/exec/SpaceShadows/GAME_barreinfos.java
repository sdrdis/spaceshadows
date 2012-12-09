package exec.SpaceShadows;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class GAME_barreinfos {
	GAME_barreinfos (int nflargeur, int nfhauteur)
	{
		largeur = nflargeur;
		fhauteur = nfhauteur;
		couleurmenu = GAME_bibliotheque.couleurs[4];
	}
	
	public int gethauteur()
	{
		return hauteur;
	}
	private AlphaComposite makeComposite(float alpha) {
	    int type = AlphaComposite.SRC_OVER;
	    return(AlphaComposite.getInstance(type, alpha));
	  }
	public void afficher(Graphics2D r, GAME_terrain terrain)
	{
		r.setComposite(makeComposite(0.5f));
		/*
		r.setColor(couleurmenu);
		r.fillRect(0, fhauteur - hauteur, largeur - hauteur, hauteur);
		*/
		GAME_minicarte minicarte = new GAME_minicarte (terrain, largeur - hauteur, fhauteur-hauteur, hauteur, hauteur);
		minicarte.afficher(r);
		r.setComposite(makeComposite(1f));
		
	}
	
	
	protected int hauteur = 120;
	protected int largeur = 600;
	protected int fhauteur;
	protected Color couleurmenu;
	//protected GAME_bibliotheque bibliotheque = new GAME_bibliotheque();
}
