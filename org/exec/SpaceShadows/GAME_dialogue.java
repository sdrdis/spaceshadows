package exec.SpaceShadows;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;


public class GAME_dialogue {
public static String dialogues[][];
public static int parleurs[];
public static int position = 0;
protected static int largeurbordure = 20;
protected static int nbdialogues;
protected static int espaceentreligne = 10;
protected static int espacenoncentrex = 10;
protected static int espacenoncentrey = 30;
public static void loaddialogues(String ndialogues[][], int nparleurs[], int nnbdialogues)
{
	dialogues = ndialogues;
	parleurs = nparleurs;
	nbdialogues = nnbdialogues;
	position = 0;
	if (estFini())
	{
		GAME_bibliotheque.affichermessageniveau();
	}
}
public static boolean estFini()
{
	return (position == nbdialogues);
}
public static void afficher(Graphics2D r)
{
	r.setComposite(makeComposite(0.8f));
	r.setColor(GAME_bibliotheque.couleurs[2]);
	r.fillRoundRect(100,200,600,200, largeurbordure, largeurbordure);
	GAME_bibliotheque.avatars[parleurs[position]].draw(r, 120, 220);
	int tailletexte = 14;
	Font font = new Font("Verdana", 0, tailletexte);
	r.setFont(font);
	FontRenderContext frc = r.getFontRenderContext(); 
	int left = 250;
	int top = 220;
	r.setComposite(makeComposite(0.8f));
	r.setColor(GAME_bibliotheque.couleurs[0]);
	for (int nn = 0; nn < dialogues[position].length; nn++)
	{
		r.drawString(dialogues[position][nn], Math.round(left + espacenoncentrex), Math.round(top + espacenoncentrey + nn * ((tailletexte / 2) + espaceentreligne)));
		//r.drawString(titre[n], Math.round(left + (width - twidth) /2), Math.round(top + ((height + tailletexte / 2) / 2) * (n + 1) / titre.length));
	}
	
	r.setComposite(makeComposite(1f));
}

private static AlphaComposite makeComposite(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }

public static void positionsuivante()
{
	if (!estFini())
	{
	position++;
	if (estFini())
	{
		GAME_bibliotheque.affichermessageniveau();
	}
	}
}
}
