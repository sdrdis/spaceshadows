package exec.SpaceShadows;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class GAME_menu {
public GAME_menu (int nwidth, int nheight, mouseListen a_listener, int netape)
{
etape = netape;
width = nwidth;
height = nheight;
souris = a_listener;
gereretape();
texte = new String[74];
GAME_bibliotheque.remplir(texte);
texte[2] = "Conception";
texte[3] = "----------------------";
texte[4] = "Sébastien Drouyer";

texte[8] = "Programmation / Réalisation";
texte[9] = "----------------------";
texte[10] = "Sébastien Drouyer";

texte[14] = "Infographie";
texte[15] = "----------------------";
texte[16] = "Fonds";
texte[17] = "*********";
texte[18] = "Sébastien Drouyer";
texte[20] = "Sprites / Vaisseaux";
texte[21] = "*********";
texte[22] = "Sébastien Drouyer";
texte[23] = "Michael Petersen";
texte[24] = "Wone Stone";
texte[25] = "Bruce Lehmann";
texte[26] = "Utilisateur sady sur 3dvia.com";
texte[27] = "Utilisateur Ratafu sur 3dvia.com";

texte[31] = "Musique";
texte[32] = "----------------------";
texte[33] = "3, 2, 1";
texte[34] = "*********";
texte[35] = "Outsider";
texte[37] = "Lets go back to the rock";
texte[38] = "*********";
texte[39] = "Outsider";
texte[41] = "Spring";
texte[42] = "*********";
texte[43] = "Outsider";
texte[45] = "Dizor";
texte[46] = "*********";
texte[47] = "Outsider";
texte[49] = "Instable";
texte[50] = "*********";
texte[51] = "Revang3r";
texte[53] = "Erotic dream";
texte[54] = "*********";
texte[55] = "Fractal";

texte[59] = "Remerciements";
texte[60] = "----------------------";
texte[61] = "jamendo.com";
texte[62] = "*********";
texte[63] = "Pour les musiques indépendantes de qualité";
texte[64] = "mises a disposition";
texte[66] = "3dvia.com";
texte[67] = "*********";
texte[68] = "Pour les nombreux modeles 3d hébergés.";
texte[70] = "Le club IGC de l'Insa Lyon";
texte[71] = "*********";
texte[72] = "Pour l'initiation au développement de jeux vidéos";
texte[73] = "ainsi que des nombreuses aides.";

}
public void gereretape()
{
	if (etape == 0)
	{
	boutons = new GAME_bouton[4];
	boutons[0] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Jouer", 1);
	boutons[1] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Aide", 1);
	boutons[2] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Crédits", 1);
	boutons[3] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Quitter", 1);
	}
	if (etape == 1)
	{
		boutons = new GAME_bouton[2];
		String texte[] = new String[6];
		texte[0] = "Flèches pour se déplacer.";
		texte[1] = "Espace pour tirer.";
		texte[2] = "Chiffres 0 - 9 pour changer d'arme.";
		texte[3] = "Entrée pour mot de passe niveau.";
		texte[4] = "u / j pour modifier volume son.";
		texte[5] = "Echap pour quitter.";
		boutons[0] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, texte, 3);
		boutons[1] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Retour", 1);
	}
	if (etape == 2)
	{
		boutons = new GAME_bouton[2];
		String texte[] = new String[3];
		texte[0] = "Vous avez perdu !";
		texte[1] = "--------------";
		texte[2] = "http://www.spaceshadows.com";
		boutons[0] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, texte, 3);
		boutons[1] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Retour", 1);
	}
	if (etape == 3)
	{
	boutons = new GAME_bouton[4];
	boutons[0] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "Choisissez votre vaisseau", 1);
	boutons[1] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "The Invader", 1);
	boutons[2] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "The Transporter", 1);
	boutons[3] = new GAME_bouton(differencetaillex, differencetailley, width - 2 * differencetaillex,height - 2 * differencetailley, "The Exilian", 1);
	}
}


private AlphaComposite makeComposite(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }

public void afficher(Graphics2D r)
{
	
	//Color couleur = new Color(0, 0, 0);
	//r.setBackground(couleur);
	GAME_bibliotheque.verifierplaylist();
	GAME_bibliotheque.fonds[1].draw(r, 0, 0);
	r.setComposite(makeComposite(0.6f));
	r.setColor(GAME_bibliotheque.couleurs[1]);
	if (tempsdefile == 0)
	{
	
	//r.setXORMode(Color.blue);
	r.fillRoundRect(differencetaillex,differencetailley,width - 2 * differencetaillex,height - 2 * differencetailley, largeurbordure, largeurbordure);
	afficherboutons(r);
	
	}
	else
	{
		r.fillRect(differencetaillex,0,width - 2 * differencetaillex,height);
		r.setColor(GAME_bibliotheque.couleurs[0]);
		int vitesse = 50;
		int top = height - Math.round(((System.currentTimeMillis() - tempsdefile) * vitesse / 1000));
		
		int espaceentreligne = 20;
		int espacenoncentrey = 30;
		
		int tailletexte = 20;
		Font font = new Font("Verdana", 0, tailletexte);
		r.setFont(font);
		FontRenderContext frc = r.getFontRenderContext(); 
		
		
		
		
		for (int nn = 0; nn < texte.length; nn++)
		{
			if (texte[nn].length() > 0)
			{
			TextLayout layout = new TextLayout(texte[nn], font, frc); 
			float twidth = layout.getVisibleAdvance();
			r.drawString(texte[nn], Math.round((width - twidth) /2), Math.round(top + espacenoncentrey + nn * ((tailletexte / 2) + espaceentreligne)));
			//r.drawString(titre[n], Math.round(left + (width - twidth) /2), Math.round(top + ((height + tailletexte / 2) / 2) * (n + 1) / titre.length));
			}
		}
		
		if (Math.round(top + espacenoncentrey + (texte.length - 1) * ((tailletexte / 2) + espaceentreligne)) < -20)
		{
			setdefile();
		}
		
		
		
		
		
		
		if (souris.getclic())
		{
			unsetdefile();
		}
	}
	r.setComposite(makeComposite(1f));
}
public void afficherboutons(Graphics2D r)
{
	int i = 0;
	int j = 0;
	
	for (int n = 0; n < boutons.length; n++)
	{
		j = boutons[n].getnbplacebouton();
		if (boutons[n].afficher(r, i, souris.m_x, souris.m_y))
		{
			if (souris.getclic())
			{
				//System.out.println("CC");
				gererclic(n);
			}
		}
		i += j;
	}
}

public void gererclic(int n)
{
	if (etape == 0)
	{
	if (n == 0)
	{
		choisi = true;
		choixvaisseau = 0;
		choix = 0;
		GAME_bibliotheque.niveauactuel = 0;
		unsetdefile();
		return;
		/*
		etape = 3;
		gereretape();*/
	}
	if (n == 1)
	{
		etape = 1;
		gereretape();
		unsetdefile();
		return;
	}
	if (n == 2)
	{
		setdefile();
		return;
	}
	if (n == 3)
	{
		choisi = true;
		choix = 3;
		unsetdefile();
		return;
	}
	}
	if (etape == 1)
	{
	if (n == 1)
	{
		etape = 0;
		gereretape();
		unsetdefile();
		return;
	}
	}
	if (etape == 2)
	{
	if (n == 1)
	{
		etape = 0;
		gereretape();
		unsetdefile();
		return;
	}
	}
	if (etape == 3)
	{
		if (n >= 1)
		{
		choisi = true;
		choixvaisseau = n - 1;
		choix = 0;
		unsetdefile();
		return;
		}
	}
	
}

public boolean getchoisi()
{
	return choisi;
}
public int getchoix()
{
	return choix;
}
public int getchoixvaisseau()
{
	return choixvaisseau;
}

public static void setdefile()
{
	if (!achargemusique)
	{
	GAME_bibliotheque.jouerplaylist(1, 1);
	achargemusique = true;
	}
	tempsdefile = System.currentTimeMillis();
}
public static void unsetdefile()
{
	tempsdefile = 0;
	achargemusique = false;
}
protected static boolean achargemusique = false;
protected String texte[];
protected static long tempsdefile = 0;
protected int etape = 0;
protected int choixvaisseau = 0;
protected boolean choisi = false;
protected int choix = -1;
protected int width, height;
protected mouseListen souris;
protected int differencetaillex = 100;
protected int differencetailley = 50;
protected int largeurbordure = 100;
//GAME_bibliotheque bibliotheque = new GAME_bibliotheque();
protected GAME_bouton boutons[];
}
