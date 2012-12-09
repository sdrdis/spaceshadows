package exec.SpaceShadows;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class GAME_bouton {
public GAME_bouton(int nftop, int nfleft, int nfwidth, int nfheight, String ntitre, int nnbplacebouton)
{
nbplacebouton = nnbplacebouton;
ftop = nftop;
fleft = nfleft;
fwidth = nfwidth;
fheight = nfheight;
titre = new String[1];
titre[0] = ntitre;
width = fwidth - 2 * espacefenetre;
height = ((fheight - (nbboutons + 1) * espaceentrebouton) / nbboutons) * nbplacebouton + espaceentrebouton * (nbplacebouton - 1);
}

public GAME_bouton(int nftop, int nfleft, int nfwidth, int nfheight, String[] ntitre, int nnbplacebouton)
{
nbplacebouton = nnbplacebouton;
ftop = nftop;
fleft = nfleft;
fwidth = nfwidth;
fheight = nfheight;
titre = ntitre;
width = fwidth - 2 * espacefenetre;
height = ((fheight - (nbboutons + 1) * espaceentrebouton) / nbboutons) * nbplacebouton + espaceentrebouton * (nbplacebouton - 1);
}

public boolean afficher(Graphics2D r, int n, int sx, int sy)
{
	boolean estdessus = false;
	top = (espaceentrebouton + 1) * n + height * n + ftop;
	left = 2 * espacefenetre + fleft;
	r.setColor(GAME_bibliotheque.couleurs[2]);
	
	if (sx >= left && sx <= width + left && sy >= top && sy <= height + top)
	{
		r.setColor(GAME_bibliotheque.couleurs[3]);
		estdessus = true;
	}
	
	r.fillRoundRect(left,top,width,height, largeurbordure, largeurbordure);
	r.setColor(GAME_bibliotheque.couleurs[0]);
	int tailletexte = 20;
	Font font = new Font("Verdana", 0, tailletexte);
	r.setFont(font);
	FontRenderContext frc = r.getFontRenderContext(); 
	TextLayout layout = new TextLayout(titre[0], font, frc); 
	float twidth = layout.getVisibleAdvance();
	if (titre.length == 1)
	{
		r.drawString(titre[0], Math.round(left + (width - twidth) /2), Math.round(top + ((height + tailletexte / 2) / 2)));
	}
	else
	{
	for (int nn = 0; nn < titre.length; nn++)
	{
		r.drawString(titre[nn], Math.round(left + espacenoncentrex), Math.round(top + espacenoncentrey + nn * ((tailletexte / 2) + espaceentreligne)));
		//r.drawString(titre[n], Math.round(left + (width - twidth) /2), Math.round(top + ((height + tailletexte / 2) / 2) * (n + 1) / titre.length));
	}
	}
	//FontMetrics fm=new FontMetrics("Verdana"); 
	//fm.getWidth("blablabla" );
	
	if (etaitaudessus == false && estdessus == true)
	{
		GAME_bibliotheque.jouerson(1, 1D);
	}
	etaitaudessus = estdessus;
return estdessus;
}
public int getnbplacebouton()
{
	return nbplacebouton;
}

protected int top, left, width, height, fwidth, fheight, ftop, fleft;
protected int espaceentrebouton = 20;
protected int nbboutons = 5;
protected int nbplacebouton = 1;
protected int espacefenetre = 50;
protected int largeurbordure = 20;
protected int espaceentreligne = 20;
protected int espacenoncentrex = 10;
protected int espacenoncentrey = 30;
protected String titre[];
protected boolean etaitaudessus = false;
//protected GAME_bibliotheque bibliotheque = new GAME_bibliotheque();
}
