package exec.SpaceShadows;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class GAME_mouvements {
	GAME_mouvements(String adresse, int nbimagesparmouvementsnouveau, int nbanglesmouvementsnouveau)
	{
		nbanglesmouvements = nbanglesmouvementsnouveau;
		Images = new Sprite[nbimagesparmouvementsnouveau * nbanglesmouvements];
		for (int n = 0; n < nbimagesparmouvementsnouveau * nbanglesmouvements; n++)
		{
			Images[n] = SpriteStore.get().getSprite(adresse + (n + 1) + ".gif");
			//System.out.print(adresse + (n + 1) + ".jpg");
			//Images[n].Load(adresse + (n + 1) + ".bmp");
			
		}
		nbimagesparmouvements = nbimagesparmouvementsnouveau;
		height = (int)Images[0].getHeight();
		width = (int)Images[0].getWidth();
		frequence = (int) (1000 / nbimagesparmouvements);
	}
	GAME_mouvements(String adresse, int nbimagesparmouvementsnouveau, int nbanglesmouvementsnouveau, int nbcolonnes, int nblignes)
	{
		Image image = SpriteStore.get().getSprite(adresse).getImage();
		BufferedImage i = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		i.getGraphics().drawImage(image, 0, 0, null);
	    
		BufferedImage tableau[][] = new BufferedImage[nbcolonnes][nblignes];
		nbanglesmouvements = nbanglesmouvementsnouveau;
		Images = new Sprite[nbimagesparmouvementsnouveau * nbanglesmouvements];
		diviserImage(i, tableau, nbcolonnes, nblignes);
		decalage = 8;
		nbimagesparmouvements = nbimagesparmouvementsnouveau;
		height = (int)Images[0].getHeight();
		width = (int)Images[0].getWidth();
		frequence = (int) (1000 / nbimagesparmouvements);
	}
	public void diviserImage(BufferedImage i, BufferedImage tableau[][], int nbcolonnes, int nblignes)
	{
		int indice = -1;
			for (int y = 0; y < nblignes; y++)
			{
				for (int x = 0; x < nbcolonnes; x++)
				{
				indice++;
				GAME_ES.println("F_" + x + ", " + y + ", " + indice);
				if (indice < nbanglesmouvements)
				{
						BufferedImage a1 = new BufferedImage(i.getWidth()/nbcolonnes, i.getHeight()/nblignes, BufferedImage.TYPE_INT_ARGB);
						a1.getGraphics().drawImage(i, 0, 0, a1.getWidth(), a1.getHeight(), x * i.getWidth()/nbcolonnes, y * i.getHeight()/nblignes, (x + 1) * i.getWidth()/nbcolonnes, (y + 1) * i.getHeight()/nblignes,null);
						Images[indice] = new Sprite(a1);
				}
			}
		}
	}
	
	public int nbimagesparm()
	{
		return nbimagesparmouvements;
	}
	public Sprite mouvementnum(int num)
	{
		return Images[(num) % nbanglesmouvements];
	}
	public int largeur()
	{
		return width;
	}
	public int longueur()
	{
		return height;
	}
	int nombrefrequence()
	{
		return frequence;
	}
	int getnbanglesmouvements()
	{
		return nbanglesmouvements;
	}
	int getdecalage()
	{
		return decalage;
	}
	void adddecalage(int plusdecalage)
	{
		decalage = decalage + plusdecalage;
	}
	private Sprite Images[];
	private int nbanglesmouvements;
	private int nbimagesparmouvements;
	private int height;
	private int width;
	private int frequence;
	private int decalage = 0;
}
