package exec.SpaceShadows;

import java.awt.Graphics2D;

public class GAME_motsdepasses {
protected static boolean actif = false;
protected static String mot = "";
protected static int niveau = -1;
protected static long affichebarre = 0;
protected static int decalagey = 20;
protected static int decalagey2 = 35;
protected static int decalagex = 20;
public static int height = 600;
public static void afficher(Graphics2D r)
{
	String enplus = "";
	r.drawString("Code niveau: " + GAME_bibliotheque.motdepasse[GAME_bibliotheque.niveauactuel],decalagex, height - decalagey2);
	if (actif)
	{
		if (Math.round((System.currentTimeMillis() - affichebarre) / 700) % 2 == 0)
		{
			enplus = "|";
			
		}
		r.drawString(mot + enplus,decalagex, height - decalagey);
		
	}
}

public static void changeretat()
{
	if (actif)
	{
		desactiver();
	}
	else
	{
		activer();
	}
}

public static int getniveau()
{
	int niveau2 = niveau;
	niveau = -1;
	return niveau2;
}

public static void setniveau(int nniveau)
{
	niveau = nniveau;
}

public static boolean getactif()
{
	return actif;
}
public static void activer()
{
	affichebarre = System.currentTimeMillis();
	actif = true;
}
public static void desactiver()
{
	actif = false;
	niveau = GAME_bibliotheque.motdepasseToniveau(mot);
	mot = "";
}
public static void ajouterLettre(char lettre)
{
	if (actif)
	{
	mot += lettre;
	}
}
public static void enleverLettre()
{
	if (actif && mot.length() > 0)
	{
	mot = mot.substring(0, mot.length() - 1);
	}
}

public static boolean caracterevalide(char lettre)
{
	if ((lettre >= 48 && lettre <= 57) || (lettre >= 65 && lettre <= 90)  || (lettre >= 97 && lettre <= 122)   || lettre == 32)
	{
		return true;
}
	else
	{
		return false;
}
}

}
