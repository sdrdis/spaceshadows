package exec.SpaceShadows;

import java.awt.Graphics2D;

public class GAME_minicarte {
GAME_minicarte(GAME_terrain nterrain, int nx, int ny, int nlargeur, int nlongueur)
{
terrain = nterrain;
x = nx;
y = ny;
largeur = nlargeur;
longueur = nlongueur;
}
public void afficher(Graphics2D r)
{
	int maxx = terrain.largeur * terrain.largeurcarreau;
	int maxy = terrain.longueur * terrain.longueurcarreau;
	r.setColor(GAME_bibliotheque.couleurs[5]);
	r.fillRect(x, y, largeur, longueur);
	GAME_bibliotheque.fonds[2].draw(r, x, y);
boolean special = false;
for (int n = 0; n < terrain.nbpersos; n++)
{
	if (n == 0)
	{
		special = true;
	}
	afficherperso(r, terrain.persos[n], maxx, maxy, special);
	for (int m = 0; m < terrain.persos[n].nbpersos; m++)
	{
		afficherperso(r, terrain.persos[n].persos[m], maxx, maxy, false);
	}
}
}

public void afficherperso(Graphics2D r, GAME_personnage perso, int maxx, int maxy, boolean special)
{
	if (perso.typedepersonnage() != null)
	{
	if (perso.typedepersonnage().getafficherselection())
	{
		int xx = (perso.centreX() * largeur / maxx) + x;
		int yy = (perso.centreY() * longueur / maxy) + y;
		if (special)
		{
			r.setColor(GAME_bibliotheque.couleurs[7]);
			r.fillRect(xx, yy, 3, 3);
		}
		else
		{
			r.setColor(GAME_bibliotheque.couleurs[10]);
			r.fillRect(xx, yy, 2, 2);
		}
		
		
	}
	if (perso.typedepersonnage().getexplose())
	{
		int xx = (perso.centreX() * largeur / maxx) + x;
		int yy = (perso.centreY() * longueur / maxy) + y;
		
		
		if (perso.typedepersonnage().getdegats() > 0)
		{
			r.setColor(GAME_bibliotheque.couleurs[8]);
		}
		else
		{
			r.setColor(GAME_bibliotheque.couleurs[9]);
		}
		
		
		r.fillRect(xx, yy, 2, 2);
	}
	}
}

protected GAME_terrain terrain;
protected int x, y, largeur, longueur;
}
