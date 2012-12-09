package exec.SpaceShadows;

import java.awt.Graphics2D;

public class GAME_munitions {
	public static void afficher(Graphics2D r, int armeselectionnee)
	{
		if (fintemps == 0)
		{
		GAME_bibliotheque.imagesdeco[3].draw(r, width - decalagex2, decalagey);
		}
		else
		{
		GAME_bibliotheque.imagesdeco[2].draw(r, width - decalagex2, decalagey);
		
		if (!GAME_dialogue.estFini())
		{
			setfintemps(GAME_bibliotheque.getfintemps());
		}
		r.drawString(enminutes(fintemps - System.currentTimeMillis()), width - decalagex2+ GAME_bibliotheque.imagesdeco[2].getWidth() + decalageximage, decalagey2);
		if (fintemps - System.currentTimeMillis() <= 0)
		{
		if (GAME_bibliotheque.typemissionactuel() == -2)
		{
		Game.gameRunning = false;
		Game.etapemenu = 2;
		}
		if (GAME_bibliotheque.typemissionactuel() == -3 || GAME_bibliotheque.typemissionactuel() == -4)
		{
		GAME_bibliotheque.niveausuivant();
		GAME_motsdepasses.setniveau(GAME_bibliotheque.niveauactuel);
		}
		}
		}
		if (GAME_bibliotheque.typemissionactuel() > 0)
		{
		r.drawString("Restant: " + Game.getnbrestant(), width - decalagex3, decalagey2);
		}
		GAME_bibliotheque.imagesdeco[1].draw(r, width - decalagex, decalagey);
		r.drawString(munitions[armeselectionnee] + " / " + GAME_bibliotheque.maxmunitions[armeselectionnee], width - decalagex + GAME_bibliotheque.imagesdeco[1].getWidth() + decalageximage, decalagey2);
	}
	public static void ajouter(int armeselectionnee, int nb)
	{
		munitions[armeselectionnee] += nb;
		GAME_listemessages.ajouterMessage("Arme: " + GAME_bibliotheque.nommunitions[armeselectionnee] + " +" + nb);
		if (munitions[armeselectionnee] > GAME_bibliotheque.maxmunitions[armeselectionnee])
		{
			munitions[armeselectionnee] = GAME_bibliotheque.maxmunitions[armeselectionnee];
		}
	}
	public static void selectionner(int armeselectionnee)
	{
		GAME_listemessages.ajouterMessage("Arme sélectionnée: " + GAME_bibliotheque.nommunitions[armeselectionnee]);
	}
	public static void setfintemps(long nfintemps)
	{
		fintemps = nfintemps;
	}
	public static String enminutes(long tempsenmili)
	{
		int tempsensecondes = Math.round(tempsenmili / 1000);
		int nbminutes = Math.round(Math.round(Math.floor(tempsensecondes / 60)));
		int nbsecondes = tempsensecondes - nbminutes * 60;
		if (nbsecondes >= 10)
		{
		return nbminutes + " : " + nbsecondes;
		}
		else
		{
		return nbminutes + " : 0" + nbsecondes;
		}
	}
	public static int munitions[];
	public static int width = 800;
	public static int decalagex = 90;
	public static int decalagex2 = 180;
	public static int decalagex3 = 270;
	public static int decalagey = 5;
	public static int decalageximage = 10;
	public static int decalagey2 = 15;
	public static long fintemps;
}
