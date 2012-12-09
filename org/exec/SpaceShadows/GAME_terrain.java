package exec.SpaceShadows;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class GAME_terrain {
	GAME_terrain(int nouvellelargeur, int nouvellelongueur, int widthecran, int heightecran, GAME_typeterrain nouveautypeterrain, mouseListen a_listener, Sprite nouveaufond, boolean njeuperso)
	{

		jeuperso = njeuperso;
		
		reseau = new surClient(!jeuperso);
		
		largeur = nouvellelargeur;
		longueur = nouvellelongueur;
		positionx = 0;
		positiony = 0;
		vitessedefilement = 10;
		largeurcarreau = 40;
		longueurcarreau = 40;
		fwidth = widthecran;
		fheight = heightecran;
		bordures = 50;
		dernierchangementposition = System.currentTimeMillis();
		accelerationdefilement = 1;
		debutvitessedefilement = 4;
		finvitessedefilement = 30;
		augpersos = 10;
		taillepersos = 0;
		fond = nouveaufond;
		persos = new GAME_personnage[0];
		map = new GAME_case [largeur][longueur];
		souris = a_listener;
		for (int x = 0; x < largeur; x++)
		{
			for (int y = 0; y < longueur; y++)
			{
				map[x][y] = new GAME_case(nouveautypeterrain);
			}
	
		}
	}
	
	private AlphaComposite makeComposite(float alpha) {
	    int type = AlphaComposite.SRC_OVER;
	    return(AlphaComposite.getInstance(type, alpha));
	  }
	public GAME_case getcase(int x, int y)
	{
		return map[x][y];
	}
	public boolean modifierpositionsouris()
	{
		boolean positionmodifiee = false;
		//int vitessereelle = (int) ((System.currentTimeMillis() - dernierchangementposition) * vitessedefilement / 100);
		
		
		if (positionx < 0)
		{
			positionx = 0;
			vitessedefilement = debutvitessedefilement;
		}
		if (positiony < 0)
		{
			positiony = 0;
			vitessedefilement = debutvitessedefilement;
		}
		if (positionx > largeurcarreau * largeur - fwidth)
		{
			positionx = largeurcarreau * largeur - fwidth;
			vitessedefilement = debutvitessedefilement;
		}
		if (positiony > longueurcarreau * longueur - fheight)
		{
			positiony = longueurcarreau * longueur - fheight;
			vitessedefilement = debutvitessedefilement;
		}
		dernierchangementposition = System.currentTimeMillis();
       	return positionmodifiee;
	}
	
	

	public void afficher(Graphics2D r, boolean modedebug)
	{
		compteuritems = 0;
		GAME_bibliotheque.verifierplaylist();

//r.isoptimized = true;
		int debutx = (int) (positionx / largeurcarreau);
		int finx = (int) ((fwidth / largeurcarreau) + debutx) + 2;
		int debuty = (int) (positiony / longueurcarreau) - 4;
		int finy = (int) ((fheight / longueurcarreau) + debuty + 5);
		if (debutx < 0)
		{
			debutx = 0;
		}
		if (debutx > largeur)
		{
			debutx = largeur;
		}
		if (finx < 0)
		{
			finx = 0;
		}
		if (finx > largeur)
		{
			finx = largeur;
		}
		if (debuty < 0)
		{
			debuty = 0;
		}
		if (debuty > longueur)
		{
			debuty = longueur;
		}
		if (finy < 0)
		{
			finy = 0;
		}
		if (finy > longueur)
		{
			finy = longueur;
		}
		
		//persos[0].nouvellepositions(persos[0].positionx() + 1, persos[0].positiony());
		
		//GAME_ES.println(debutx);
		//GAME_ES.println(fwidth);
		//int [][] nombremappersos = new int[largeur][longueur];
		//GAME_personnage[][] mappersos = new GAME_personnage[largeur][longueur];
		afficherfond(r);	
		
		r.setComposite(makeComposite(0.4f));
		
		
		int i=0;
	for (int x = debutx; x < finx; x++)
	{
		
		for (int y = debuty; y < finy; y++)
		{
			if (poscasex(x, y) + largeurcarreau > 0 || poscasex(x,y) < fwidth || poscasey(x,y) + longueurcarreau > 0 || poscasey(x,y) < fheight)
			{
				//r.DrawImage(map[x][y].typeterrain().imageterrain(), poscasex(x, y), poscasey(x, y), 50, 50);	
				map[x][y].typeterrain().imageterrain().draw(r, poscasex(x, y), poscasey(x, y));
				
				//if (map[x][y].aitem() == true)
				//{
					
					//r.DrawImage2(map[x][y].item().typeitem().imageitem(), poscasexitem(x, y) - map[x][y].item().typeitem().positionx(), poscaseyitem(x, y) - map[x][y].item().typeitem().positiony(), map[x][y].item().typeitem().width(), map[x][y].item().typeitem().height());	
				//}
			}
		}
		
	}
	r.setComposite(makeComposite(1f));
	specifierdirection();
	positionx = persos[0].centreX() - fwidth / 2;
	positiony = persos[0].centreY() - fheight / 2;
	modifierpositionsouris();
	collisions();
	//r.isoptimized = false;
	personnages(r);
	affichermessages(r);

	//selectionnerunites();
	if (modedebug)
	{
	if (jeuperso)
	{
		r.drawString(persos[0].retournerVie() + " " + persos[0].retournerBouclier() + " " + compteuritems +  " " + numerreur, 300, 20);
	}
	else
	{
	r.drawString(String.valueOf(reseau.numJoueur) + " " + String.valueOf(reseau.cleJoueur) + " "  + reseau.port + " " + persos[0].retournerVie() + " " + persos[0].retournerBouclier() + " " + compteuritems +  " " + numerreur, 300, 20);
	r.drawString(lastsend, 300, 40);
	}
	}
	//rectanglesouris(r);
	gererIA();
	MAJServeur();
	}
	
	public void affichermessages(Graphics2D r)
	{
		r.setColor(Color.WHITE);
		GAME_listemessages.mettreAJour();
		for (int n = 0; n < GAME_listemessages.getNbMessage(); n++)
		{
			r.drawString(GAME_listemessages.getMessageId(n), 30, 30 + n * 20);
		}
	}
	
	public void gererIA()
	{
		for (int n = 0; n < nbpersos; n++)
		{
			if (persos[n].getIsIA())
			{
				reseau.ajouterEnvoyer(persos[n].gererIA(largeur * largeurcarreau,longueur * longueurcarreau, persos, nbpersos, n));
			}
		}
	}
	public void MAJServeur()
	{   
		if (!jeuperso)
		{
		decalageMAJ = 0;
		traiterInfos(reseau.recupererRecu());
		}
	}
	
	
	public void traiterInfos(String nouvellesInfos)
	{
		if (nouvellesInfos != "")
		{
		lastsend = nouvellesInfos;
		//GAME_ES.println(nouvellesInfos);
		}
		String parametreencours = "";
		int joueurSelectionne = 0;
		int typeinfos = 0;
		int itemSelectionne = 0;
		boolean changementtypeinfo = false;
		//GAME_ES.println("<" + nouvellesInfos);
		for (int n = 0; n < nouvellesInfos.length(); n++)
		{
			changementtypeinfo = false;
			if (nouvellesInfos.charAt(n) == '/') //EXECUTION DU PARAMETRE
			{
			changementtypeinfo = true;
			if (typeinfos == 12)
			{
			joueurSelectionne = executerParametre(joueurSelectionne, itemSelectionne, typeinfos, parametreencours);
			}
			else
			{
				itemSelectionne = executerParametre(joueurSelectionne, itemSelectionne, typeinfos, parametreencours);
			}
			typeinfos = 0;
			parametreencours = "";
			}
			if (nouvellesInfos.charAt(n) == 'C') //CREATION
			{
			GAME_ES.println("C_C " + nouvellesInfos);
			changementtypeinfo = true;
			typeinfos = 1;
			}
			if (nouvellesInfos.charAt(n) == 'S') //SELECTIONER
			{
				changementtypeinfo = true;
				typeinfos = 2;
			}
			if (nouvellesInfos.charAt(n) == 'X') //POSITION X
			{
				changementtypeinfo = true;
				typeinfos = 3;
			}
			if (nouvellesInfos.charAt(n) == 'Y') //POSITION Y
			{
				changementtypeinfo = true;
				typeinfos = 4;
			}
			if (nouvellesInfos.charAt(n) == 'A') //ANGLE - IMAGE NUM
			{
				changementtypeinfo = true;
				typeinfos = 5;
			}
			if (nouvellesInfos.charAt(n) == 'I') //DIRECTION X
			{
				changementtypeinfo = true;
				typeinfos = 6;
			}
			if (nouvellesInfos.charAt(n) == 'J') //DIRECTION Y
			{
				changementtypeinfo = true;
				typeinfos = 7;
			}
			if (nouvellesInfos.charAt(n) == 'V') //PV (INACTIF)
			{
				changementtypeinfo = true;
				typeinfos = 8;
			}
			if (nouvellesInfos.charAt(n) == 'T') //TYPE
			{
				changementtypeinfo = true;
				typeinfos = 9;
			}
			if (nouvellesInfos.charAt(n) == 'U') //SOUS TYPE (IMAGE)
			{
				changementtypeinfo = true;
				typeinfos = 10;
			}
			if (nouvellesInfos.charAt(n) == 'D') //SUPPRESSION (DELETE)
			{
				changementtypeinfo = true;
				typeinfos = 11;
			}
			if (nouvellesInfos.charAt(n) == '#') //SELECTION JOUEUR
			{
				changementtypeinfo = true;
				typeinfos = 12;
			}
			if (nouvellesInfos.charAt(n) == '@') //SELECTION JOUEUR
			{
				changementtypeinfo = true;
				typeinfos = 13;
			}
			if (nouvellesInfos.charAt(n) == 'M') //SELECTION JOUEUR
			{
				changementtypeinfo = true;
				typeinfos = 14;
			}
			if (nouvellesInfos.charAt(n) == 'B') //SELECTION JOUEUR
			{
				changementtypeinfo = true;
				typeinfos = 20;
			}
			if (!changementtypeinfo)
			{
				parametreencours += nouvellesInfos.charAt(n);
				
			}
		}
	}

	public int executerParametre(int idjoueur, int Item, int typedinfos, String parametre)
	{
		int idItem = Item;
		try
		{
		GAME_personnage ItemSelectionne = new GAME_personnage();

		
		if (typedinfos != 1 && typedinfos != 2 && typedinfos != 12 && typedinfos != 13 && typedinfos != 14)
		{
		if (Item != -1)
		{
			ItemSelectionne = persos[idjoueur].getmissiles()[Item];
		}
		else
		{
			ItemSelectionne = persos[idjoueur];
			
		}
		}
		if (typedinfos == 1)
		{
		ajouterItem(idjoueur);
		}
		else
		{
		if (typedinfos == 2)
		{
		idItem = surClient.StringToInt(parametre);
		GAME_ES.println(idItem);
		}
		else
		{
			if (typedinfos != 1 && typedinfos != 2 && typedinfos != 12 && typedinfos != 13 && typedinfos != 14)
			{
				//AVANT MAJ... mais maintenant dans vitesse...
			}
		}
		}
		if (typedinfos == 3)
		{
			ItemSelectionne.MAJposx = surClient.StringToInt(parametre);
		}
		if (typedinfos == 4)
		{
			ItemSelectionne.MAJposy = surClient.StringToInt(parametre);
		}
		if (typedinfos == 5)
		{
			ItemSelectionne.direction = surClient.StringToInt(parametre);
			ItemSelectionne.directionToAngle();
		}
		if (typedinfos == 6)
		{
			ItemSelectionne.MAJvitessex = surClient.StringToInt(parametre);
		}
		if (typedinfos == 7)
		{
			ItemSelectionne.MAJvitessey = surClient.StringToInt(parametre);
			ItemSelectionne.MAJposvitesse();
			try
			{
		ItemSelectionne.majposition = System.currentTimeMillis() - decalageMAJ;
			}
			catch (Exception e)
			{
				GAME_ES.println("ERREUR: " + typedinfos + "; " + Item + "; " + idjoueur + "; " + parametre);
				GAME_ES.println("ERREUR FATALE: " + e.getMessage());
				reseau.envoyerInfosSpeciales("~");
			}
			// /!\ A AJOUTER: Un coef qui limite le tremblement : par exemple si c'est 5 a cote
			//, pas grave, on ne modifie pas la position... A voir, car il pourrait avoir apres
			// de gros decalages tout d'un coup... /!\
		}
		
		if (typedinfos == 8)
		{
			ItemSelectionne.setPV(surClient.StringToInt(parametre));
		}
		
		if (typedinfos == 9)
		{
			ItemSelectionne.typepersonnage = GAME_bibliotheque.typesPersonnages[surClient.StringToInt(parametre)];
			GAME_ES.println("C_" + GAME_bibliotheque.typesPersonnages[surClient.StringToInt(parametre)].getId() + " | " + ItemSelectionne.typepersonnage.getId());
		}
		if (typedinfos == 10)
		{
			ItemSelectionne.mouvementchoisi = surClient.StringToInt(parametre);
		}
		if (typedinfos == 11)
		{
			supprimerpersosid2(Item, idjoueur);
			//supprimerItem(idjoueur, Item);
		}
		if (typedinfos == 12)
		{
			creerPersonnageSiInexistant(surClient.StringToInt(parametre));
			idItem = surClient.StringToInt(parametre);
			//supprimerItem(idjoueur, Item);
		}
		if (typedinfos == 13)
		{
			
			persos[idjoueur] = ItemSelectionne;
			//supprimerItem(idjoueur, Item);
		}
		if (typedinfos == 14)
		{
			if (lasttypedinfos == typedinfos)
			{
				decalageMAJ += surClient.StringToInt(parametre);
			}
			else
			{
				decalageMAJ = surClient.StringToInt(parametre);	
			}
		}
		if (typedinfos == 20)
		{
			ItemSelectionne.setbouclier(surClient.StringToInt(parametre));
		}
		lasttypedinfos = typedinfos;
		}
		catch (Exception e)
		{
			try
			{
				numerreur++;
			BufferedWriter sortie = new BufferedWriter(new FileWriter("erreurs.txt", true));
			sortie.write("-- ERREUR " + numerreur + "\n" + "TYPE: " + e.getCause() + "\n");
			}
			catch (Exception a)
			{
				
			}
		}
		return idItem;
		//if (typedinfos == )
	}
	
	public boolean personnageExiste(int idpersonnage)
	{
		boolean existe = true;
		if (idpersonnage < 0 || idpersonnage >= nbpersos)
		{
			existe = false;
		}
		return existe;
	}
	
	public void creerPersonnageSiInexistant(int idpersonnage)
	{
		if (!personnageExiste(idpersonnage))
		{
			for (int n = nbpersos; n <= idpersonnage; n++)
			{
				GAME_personnage persovide = new GAME_personnage();
				ajouterpersonnage(persovide);
				
			}
		}
	}
	
	public void ajouterItem(int idjoueur)
	{
		GAME_personnage nouveauperso = new GAME_personnage();
		GAME_ES.println("C:" + idjoueur + "; " + persos[idjoueur].ajouterpersonnage(nouveauperso));
	}
	
	
	
	public void envoyerInfosPersonnage()
	{
		reseau.ajouterEnvoyer("S-1/" + persos[0].genererChaine());
	}
	public void envoyerInfosSousPersonnage(int id)
	{
		reseau.ajouterEnvoyer("S" + id + "/" + persos[0].getmissiles()[id].genererChaine());
	}
	
	public void afficherfond(Graphics2D r)
	{
		fond.draw(r, (int)(-(((double)(fond.getWidth() - fwidth) / (double)(largeurcarreau * largeur))) * positionx), (int)(-(((double) (fond.getHeight() - fheight) / (double) (longueurcarreau * longueur))) * positiony));
	}
	
	public void collisions()
	{
		int[] supprimerpersos = new int[taillepersos];
		int[][] supprimerpersos2 = new int[taillepersos][10000];
		boolean[] persosasupprimer = new boolean[taillepersos];
		boolean[][] persosasupprimer2 = new boolean[taillepersos][10000];
		int nbsupprimerpersos = 0;
		int[] nbsupprimerpersos2 = new int[taillepersos];
		for (int n = 0; n < nbpersos; n++)
		{
			if (persos[n].typedepersonnage() != null)
			{
			GAME_personnage [] missiles;
			missiles = persos[n].getmissiles();
			int nbmissiles = persos[n].getnbmissiles();
			for (int m = 0; m < nbmissiles; m++)
			{
				if (missiles[m].getVisible() && missiles[m].typedepersonnage() != null)
				{
				if (missiles[m].estcollisionbord(largeur * largeurcarreau, longueur * longueurcarreau))
				{
					if (!persosasupprimer2[n][m])
					{
						GAME_ES.println("D_" + n + "." + m);
					supprimerpersos2[n][nbsupprimerpersos2[n]] = m;
					nbsupprimerpersos2[n]++;
					persosasupprimer2[n][m] = true;
					}
				}
				}
				if (missiles[m].getperime())
				{
					if (!persosasupprimer2[n][m])
					{
						GAME_ES.println("D_" + n + "." + m);
					supprimerpersos2[n][nbsupprimerpersos2[n]] = m;
					nbsupprimerpersos2[n]++;
					persosasupprimer2[n][m] = true;
					}
				}
			}
			
			
			if (persos[n].estcollisionbord(largeur * largeurcarreau, longueur * longueurcarreau))
			{
				if (!persosasupprimer[n])
				{
				supprimerpersos[nbsupprimerpersos] = n;
				nbsupprimerpersos++;
				persosasupprimer[n] = true;
				}
			}
			

/*
			
			if (persos[n].estcollisionbord(largeur * largeurcarreau, longueur * longueurcarreau))
			{
				if (!persosasupprimer[n])
				{
				supprimerpersos[nbsupprimerpersos] = n;
				nbsupprimerpersos++;
				persosasupprimer[n] = true;
				}
			}
	*/		
			
			for (int m = 0; m < nbpersos; m++)
			{
				if (m != n)
				{
					
				for (int o = 0; o < nbmissiles; o++)
				{
					if (persos[n].getmissiles()[o].getVisible() && persos[n].getmissiles()[o].typedepersonnage() != null)
					{
					int reponse = persos[n].getmissiles()[o].estcollision(persos[m], n,m);
					if (reponse == 2)
					{
						if (!persosasupprimer[m])
						{
						supprimerpersos[nbsupprimerpersos] = m;
						nbsupprimerpersos++;
						persosasupprimer[m] = true;
						}
					}
					if (reponse == 1)
					{
						if (!persosasupprimer2[n][o])
						{
						supprimerpersos2[n][nbsupprimerpersos2[n]] = o;
						nbsupprimerpersos2[n]++;
						persosasupprimer2[n][o] = true;
						}
					}
					
					
					if (persos[m].isIA)
					{
						for (int p = 0; p < persos[m].getnbmissiles(); p++)
						{
					if (persos[m].getmissiles()[p].getVisible() && persos[m].getmissiles()[p].typedepersonnage() != null)
					{
					reponse = persos[n].getmissiles()[o].estcollision(persos[m].getmissiles()[p],n , m);
					if (reponse == 2)
					{
						if (!persosasupprimer2[m][p])
						{
							if (!persosasupprimer2[m][p])
							{
							supprimerpersos2[m][nbsupprimerpersos2[m]] = p;
							nbsupprimerpersos2[m]++;
							persosasupprimer2[m][p] = true;
							}
						}
					}
					if (reponse == 1)
					{
						if (!persosasupprimer2[n][o])
						{
						supprimerpersos2[n][nbsupprimerpersos2[n]] = o;
						nbsupprimerpersos2[n]++;
						persosasupprimer2[n][o] = true;
						}
					}
					}
					}
					}
					
					
					
					
					
					
					
					
					
					
					}
				}

				
			
				}
			}
			
			/*
		for (int m = 0; m < nbpersos; m++)
		{
			if (m != n)
			{
			int reponse = persos[n].estcollision(persos[m]);
		if (reponse == 2)
		{
			if (!persosasupprimer[m])
			{
			supprimerpersos[nbsupprimerpersos] = m;
			nbsupprimerpersos++;
			persosasupprimer[m] = true;
			}
		}
		if (reponse == 1)
		{
			if (!persosasupprimer[n])
			{
			supprimerpersos[nbsupprimerpersos] = n;
			nbsupprimerpersos++;
			persosasupprimer[n] = true;
			}
		}
			}
		}
		
		*/ // /!\ MEMES PROBLEMES QUE POUR LE PRINCIPAL : PAS DE TYPE : DONC ERREUR !!!!! /!\
			}
		}
		//supprimerlistepersos(supprimerpersos, nbsupprimerpersos);
		supprimerlistepersos2(supprimerpersos2, nbsupprimerpersos2);
	}
	public void supprimerlistepersos(int [] supprimerpersos, int nbsupprimerpersos)
	{
		for (int n = 0; n < nbsupprimerpersos; n++)
		{
			supprimerpersosid(supprimerpersos[n]);
		}
	}
	public void supprimerlistepersos2(int [][] supprimerpersos, int [] nbsupprimerpersos)
	{
		for (int m = 0; m < nbsupprimerpersos[0]; m++)
		{
		supprimerpersosid2(supprimerpersos[0][m], 0);
		}
		if (jeuperso)
		{
			for (int n = 1; n < nbpersos; n++)
			{
				for (int m = 0; m < nbsupprimerpersos[n]; m++)
				{
				supprimerpersosid2(supprimerpersos[n][m], n);
//				supprimerpersosid2(supprimerpersos[n][m], 0);
				}
			}
		}
		else
		{
		for (int n = 1; n < nbpersos; n++)
		{
			for (int m = 0; m < nbsupprimerpersos[n]; m++)
			{
				GAME_ES.println("D_ INV" + n + "." + m);
			persos[n].getmissiles()[supprimerpersos[n][m]].setVisible(false);
//			supprimerpersosid2(supprimerpersos[n][m], 0);
			}
		}
		}
	}
	public void supprimerpersosid(int idperso)
	{
		//r (int n = idperso + 1; n < nbpersos; n++)
		//{
		persos[idperso] = null;
		int idremplacer = nbpersos - 1;
		while (persos[idremplacer] == null && idremplacer > 0)
		{
			idremplacer--;
		}
		if (idremplacer > idperso)
		{
		persos[idperso] = new GAME_personnage (persos[idremplacer]);
		}
		nbpersos--;
		//}
	}
	public void supprimerpersosid2(int idmissile, int idperso)
	{
		//r (int n = idperso + 1; n < nbpersos; n++)
		//{
		/*
		persos[idperso].getmissiles()[idmissile] = null;
		int idremplacer = persos[idperso].getnbmissiles() - 1;
		while (persos[idperso].getmissiles()[idremplacer] == null && idremplacer > 0)
		{
			idremplacer--;
		}
		if (idremplacer > idperso)
		{
		persos[idperso].getmissiles()[idmissile] = new GAME_personnage (persos[idperso].getmissiles()[idremplacer]);
		}
		persos[idperso].setnbmissiles(persos[idperso].getnbmissiles() - 1);
		*/
		persos[idperso].supprimerpersosid(idmissile);
		GAME_ES.println("S:" + idperso + "; " + idmissile);
		if (idperso == 0)
		{
			reseau.ajouterEnvoyer("S" + idmissile + "/D/");
		}
		//}
	}
	public void specifierdirection()
	{
		
		persos[0].setvitesse(vitessexjoueur, vitesseyjoueur);
		persos[0].setangle(anglepersonnage);
		for (int n = 0; n < nbpersos; n++)
		{
if (persos[n].typedepersonnage() != null)
{
			persos[n].mettreajourposition();
			GAME_personnage [] missiles;
			missiles = persos[n].getmissiles();
			int nbmissiles = persos[n].getnbmissiles();
			for (int m = 0; m < nbmissiles; m++)
			{
				missiles[m].mettreajourposition();
			}
}
		}
	}
	
	
	
	public void selectionnerunites()
	{
		if (souris.selectionne)
		{
			int rectX, rectXX, rectY, rectYY;
			if (souris.m_x < souris.ma_x)
			{
				rectX = souris.m_x;
				rectXX = souris.ma_x;
			}
			else
			{
				rectX = souris.ma_x;
				rectXX = souris.m_x;
			}
			
			if (souris.m_y < souris.ma_y)
			{
				rectY = souris.m_y;
				rectYY = souris.ma_y;
			}
			else
			{
				rectY = souris.ma_y;
				rectYY = souris.m_y;
			}
			for (int n = 0; n < nbpersos; n++)
			{
				persos[n].deselectionner();
				int XX = persos[n].centreX() - positionx;
				int YY = persos[n].centreY() - positiony;
				if (XX >= rectX && XX <= rectXX && YY >= rectY && YY <= rectYY)
				{
					persos[n].selectionner();
				}
			}
			souris.selectionne = false;
		}
	}
	
	public void personnages(Graphics2D r)
	{
		GAME_personnage[] listepersos = new GAME_personnage[taillepersos];
		listepersos = persos;
		/*
		for (int n = 0; n < nbpersos; n++)
		{	
			listepersos[n] = persos[n];
		}
		*/
		//int selectid = 0;
		//GAME_personnage persotemp = new GAME_personnage();
		Color couleur = new Color(255,255,255);
		//persos = listepersos;
		/*
		for (int n = 0; n < nbpersos; n++)
		{	
			selectid = n;
			for (int m = n; m < nbpersos; m++)
			{
				if (listepersos[m].positiony() < listepersos[selectid].positiony())
				{
					selectid = m;
				}

			}
			persotemp = listepersos[n];
			listepersos[n] = listepersos[selectid];
			listepersos[selectid] = persotemp;
			
			
			//nombremappersos[posxcase(persos[n].positionx(),persos[n].positiony())][posycase(persos[n].positionx(), persos[n].positiony())] = 0;
			//nombremappersos[posxcase(persos[n].positionx(),persos[n].positiony())][posycase(persos[n].positionx(), persos[n].positiony())]++;
			//mappersos[posxcase(persos[n].positionx(),persos[n].positiony())][posycase(persos[n].positionx(), persos[n].positiony())] = persos[n];
		}
		*/
		
		
		//couleur.r = 0;
		//couleur.g = 0;
		//couleur.b = 255;
		int Xperso;
		int Yperso;
		int Xpersoj;
		int Ypersoj;
		int Wperso;
		int Hperso;
		//color couleur = new color();
		//couleur.r = 0;
		//couleur.g = 533;
		//couleur.b = 0;
		compteuritems += nbpersos;
		for (int n = 0; n < nbpersos; n++)
		{
			if (listepersos[n].typedepersonnage() != null)
			{
			Xperso = listepersos[n].positionx() - positionx;
			Yperso = listepersos[n].positiony() - positiony;
			Xpersoj = listepersos[n].centreX() - positionx;
			Ypersoj = listepersos[n].centreY() - positiony;
			Wperso = listepersos[n].typedepersonnage().mouvementsindex(0).largeur();
			Hperso = listepersos[n].typedepersonnage().mouvementsindex(0).longueur();
		compteuritems += listepersos[n].getnbmissiles();
			//GAME_ES.println(listepersos[n].positiony());
			
		//vector SA = new vector();
		
		
			
			if (listepersos[n].typedepersonnage().getafficherselection())
			{
				dessinerrectangle(Xperso, Yperso, Wperso,  Hperso,6, r, couleur);
			}
			if (listepersos[n].typedepersonnage().getaffichervie())
			{
				dessinerrectanglevie(Xperso, Yperso, Wperso, 8, 4, listepersos[n].retournerVie(), r);
				dessinerrectanglebouclier(Xperso, Yperso, Wperso, 4, 4, listepersos[n].retournerBouclier(), r);
			}
				//r.DrawRect(couleur, Xperso, Yperso + Wperso - Hperso, Wperso, Wperso);

		/*
		if (listepersos[n].typedepersonnage().mouvementsindex(listepersos[n].getmouvementchoisi()).getnbanglesmouvements() == 1)
		{
			listepersos[n].afficherimage(System.currentTimeMillis()).drawrotation(r, (int) Xperso + positionx - Math.cos(listepersos[n].getangle()) * positiony * Math.sqrt(2)), (int)  Yperso + positiony  - Math.sin(listepersos[n].getangle()) * positionx * Math.sqrt(2)), listepersos[n].getangle());
			//listepersos[n].afficherimage(System.currentTimeMillis()).draw(r, Xperso, Yperso);
		}
		else
		{
			*/
		listepersos[n].afficherimage(System.currentTimeMillis()).draw(r, Xperso, Yperso);
		GAME_personnage [] missiles;
		missiles = listepersos[n].getmissiles();
		int nbmissiles = listepersos[n].getnbmissiles();
		for (int m = 0; m < nbmissiles; m++)
		{
			int Xperso2 = missiles[m].positionx() - positionx;
			int Yperso2 = missiles[m].positiony() - positiony;
			int Xperso3 = missiles[m].centreX() - positionx;
			int Yperso3 = missiles[m].centreY() - positiony;
			if (missiles[m].getVisible() && missiles[m].typedepersonnage() != null)
			{
				int Wperso2 = missiles[m].typedepersonnage().mouvementsindex(0).largeur();
				int Hperso2 = missiles[m].typedepersonnage().mouvementsindex(0).longueur();
				if (missiles[m].typedepersonnage().getafficherselection())
				{
					dessinerrectangle(Xperso2, Yperso2, Wperso2,  Hperso2,6, r, couleur);
				}
				if (missiles[m].typedepersonnage().getaffichervie())
				{
					dessinerrectanglevie(Xperso2, Yperso2, Wperso2, 8, 4, missiles[m].retournerVie(), r);
					dessinerrectanglebouclier(Xperso2, Yperso2, Wperso2, 4, 4, missiles[m].retournerBouclier(), r);
				}
			missiles[m].afficherimage(System.currentTimeMillis()).draw(r, Xperso2, Yperso2);
			if (missiles[m].getpersoattache() != null)
			{
				int decalagebas = missiles[m].getpersoattache().typedepersonnage().mouvementsindex(0).largeur() / 2;
				int decalagegauche = missiles[m].getpersoattache().typedepersonnage().mouvementsindex(0).longueur() / 2;
				GAME_ES.println("F_ b = " + decalagebas);
				missiles[m].getpersoattache().afficherimage2(System.currentTimeMillis(), missiles[m]).draw(r, Xperso3 - decalagegauche, Yperso3 - decalagebas);
			}
			}
		}
		if (listepersos[n].getpersoattache() != null)
		{
			int decalagebas = listepersos[n].getpersoattache().typedepersonnage().mouvementsindex(0).largeur() / 2;
			int decalagegauche = listepersos[n].getpersoattache().typedepersonnage().mouvementsindex(0).longueur() / 2;
			listepersos[n].getpersoattache().afficherimage2(System.currentTimeMillis(), listepersos[n]).draw(r, Xpersoj - decalagegauche, Ypersoj - decalagebas);
		}
			//}
		//r.drawString(String.valueOf(listepersos[n].getdirectionx()).concat(String.valueOf(listepersos[n].getdirectiony())), listepersos[n].positionx() - positionx, listepersos[n].positiony() - 40 - positiony);
		//r.drawString(String.valueOf(listepersos[n].positionx()).concat(String.valueOf(listepersos[n].positiony())), listepersos[n].positionx() - positionx, listepersos[n].positiony() - 60 - positiony);
			}
			}
		
	}
	
	public void dessinerrectangle(int X, int Y, int largeur, int hauteur, int largeurbout, Graphics2D r, Color couleur)
	{
		r.setColor(couleur);
		r.drawLine(X, Y, X + largeurbout, Y);
		r.drawLine(X, Y, X, Y + largeurbout);
		r.drawLine(X + largeur - largeurbout, Y, X + largeur, Y);
		r.drawLine(X + largeur, Y, X + largeur, Y + largeurbout);
		r.drawLine(X + largeur - largeurbout, Y + hauteur, X + largeur, Y + hauteur);
		r.drawLine(X + largeur, Y + hauteur - largeurbout, X + largeur, Y + hauteur);
		r.drawLine(X, Y + hauteur, X + largeurbout, Y + hauteur);
		r.drawLine(X, Y + hauteur - largeurbout, X, Y + hauteur);
		//for (int n = 0; n < nbcotes; n++)
		//{
			//r.FillLine(Px, Py, Pz, Pl, Pm, Pn, Pr, Pg, Pb, Pa, Pu, Pv, Pd, dx, dy, dz, dl, dm, dn, dr, dg, db, da, du, dv, dd, x1, x2, y)
		//}
	}
	
	
	public void dessinerrectanglevie(int X, int Y, int largeur, int decalage, int epaisseur, int vie, Graphics2D r)
	{
		if (vie > 0 && vie < 100)
		{
		r.setColor(Color.GREEN);
		dessinerrectangleplein(X, Y - decalage - epaisseur, (Math.round(largeur * vie / 100)), epaisseur, r);
		r.setColor(Color.RED);
		dessinerrectangleplein(X + (Math.round(largeur * vie / 100)), Y - decalage - epaisseur, largeur - (Math.round(largeur * vie / 100)), epaisseur, r);
		}
	if (vie == 100)
	{
		r.setColor(Color.GREEN);
		dessinerrectangleplein(X, Y - decalage - epaisseur, largeur, epaisseur, r);
	}
	if (vie == 0)
	{
		r.setColor(Color.RED);
		dessinerrectangleplein(X, Y - decalage - epaisseur, largeur, epaisseur, r);
	}
	}
	public void dessinerrectanglebouclier(int X, int Y, int largeur, int decalage, int epaisseur, int vie, Graphics2D r)
	{
		if (vie > 0 && vie < 100)
		{
		r.setColor(Color.BLUE);
		dessinerrectangleplein(X, Y - decalage - epaisseur, (Math.round(largeur * vie / 100)), epaisseur, r);
		r.setColor(Color.GRAY);
		dessinerrectangleplein(X + (Math.round(largeur * vie / 100)), Y - decalage - epaisseur, largeur - (Math.round(largeur * vie / 100)), epaisseur, r);
		}
	if (vie == 100)
	{
		r.setColor(Color.BLUE);
		dessinerrectangleplein(X, Y - decalage - epaisseur, largeur, epaisseur, r);
	}
	if (vie == 0)
	{
		r.setColor(Color.GRAY);
		dessinerrectangleplein(X, Y - decalage - epaisseur, largeur, epaisseur, r);
	}
	}
	
	public void dessinerrectangleplein(int x, int y, int largeur, int hauteur, Graphics2D r)
	{
		for (int n = 0; n < hauteur; n++)
		{
		r.drawLine(x, y + n, x + largeur, y + n);
		}
	}
	
	public void dessinerpolygone(int X, int Y, int largeur, int hauteur, int nbcotes, Graphics2D r, Color couleur)
	{

		int CX = X + largeur / 2;
		int CY = Y + hauteur / 2;
		int rayon = (int) (Math.sqrt(largeur * largeur + hauteur * hauteur) / 3);
		float coefhauteur = (float) (hauteur * 1.0 / largeur);
		float coeflargeur = (float) (largeur * 1.0 / hauteur);
		//r.DrawLine( couleur, X, Y, X + largeur, Y + hauteur );
		r.setColor(couleur);
		for (int n = 0; n < nbcotes; n++)
		{
			/*System.out.print((Math.cos(2 * n * Math.PI / nbcotes) * rayon + CX));
			System.out.print(" : ");
			System.out.print((Math.sin(2 * n * Math.PI / nbcotes) * rayon + CY));
			System.out.print(" : ");
			System.out.print((Math.cos(2 * (n + 1) * Math.PI / nbcotes) * rayon + CX));
			System.out.print(" : ");
			System.out.print((Math.sin(2 * (n + 1) * Math.PI / nbcotes) * rayon + CY));
			GAME_ES.println();*/
			
			r.drawLine((int)(Math.cos(2 * n * Math.PI / nbcotes) * coeflargeur * rayon + CX), (int)(Math.sin(2 * n * Math.PI / nbcotes) * coefhauteur * rayon + CY), (int)(Math.cos(2 * (n + 1) * Math.PI / nbcotes) * coeflargeur * rayon + CX), (int)(Math.sin(2 * (n + 1) * Math.PI / nbcotes) * coefhauteur * rayon + CY));;
		}
		//for (int n = 0; n < nbcotes; n++)
		//{
			//r.FillLine(Px, Py, Pz, Pl, Pm, Pn, Pr, Pg, Pb, Pa, Pu, Pv, Pd, dx, dy, dz, dl, dm, dn, dr, dg, db, da, du, dv, dd, x1, x2, y)
		//}
	}
	
	public void rectanglesouris(Graphics2D r)
	{
		Color couleur = new Color(0, 0, 0);
		r.setColor(couleur);
		int rectX, rectY, rectXX, rectYY;
	if (souris.getclic())
	{
		
		if (souris.m_x < souris.ma_x)
		{
			rectX = souris.m_x;
			rectXX = souris.ma_x - souris.m_x;
		}
		else
		{
			rectX = souris.ma_x;
			rectXX = souris.m_x - souris.ma_x;
		}
		
		if (souris.m_y < souris.ma_y)
		{
			rectY = souris.m_y;
			rectYY = souris.ma_y - souris.m_y;
		}
		else
		{
			rectY = souris.ma_y;
			rectYY = souris.m_y - souris.ma_y;
		}
		r.drawRect(rectX, rectY, rectXX, rectYY);
		//r.DrawRect( rectX, rectY, rectXX, rectYY);
		//System.out.print("RECTANGLE: ");
	//GAME_ES.println(compteur++);
	}
	}

	public void modifierpositionx(int nouveaupositionx)
	{
		positionx = nouveaupositionx;
	}
	public void modifierpositiony(int nouveaupositiony)
	{
		positiony = nouveaupositiony;
	}
	
	public int recevoirpositionx()
	{
		return positionx;
	}
	public int recevoirpositiony()
	{
		return positiony;
	}
	
	public int posxcase(int x, int y)
	{
		int yy;
		yy = ((y + positiony) / longueurcarreau);
		if (yy % 2 == 0)
		{
			return (int) ((x - positionx) / largeurcarreau);
		}
		else
		{
			return (int) (((x - positionx) / largeurcarreau) + 0.5);
		}
		
	}
	
	public int posycase(int x, int y)
	{
		return (int) ((y + positiony) / longueurcarreau);
	}
	
	public int poscasex(int x, int y)
	{

			return x * largeurcarreau - positionx;
	}
	public int poscasey(int x, int y)
	{
			return y * longueurcarreau - positiony;
	}
	public int poscasexitem(int x, int y)
	{
		if (y % 2 == 0)
		{
			return  (int) x * largeurcarreau - positionx;
		}
		else
		{
			return  (int) x * largeurcarreau - (largeurcarreau / 2) - positionx;
		}
	}
	public int poscaseyitem(int x, int y)
	{
			return (int) ((y + 1) * longueurcarreau - positiony);
	}
	public void ajoutertypeitem(int x, int y, GAME_typeitem nouveauitem)
	{
		map[x][y].ajoutertypeitem(nouveauitem);
	}
	public void ajouterpersonnage(GAME_personnage nouveauperso)
	{
		if (nbpersos >= taillepersos)
		{
		GAME_personnage[] persostemp = new GAME_personnage[nbpersos];
		persostemp = persos;
		taillepersos = taillepersos + augpersos;
		persos = new GAME_personnage[taillepersos];
		for (int n = 0; n < nbpersos; n++)
		{
			persos[n] = persostemp[n];
		}
		}
		persos[nbpersos] = new GAME_personnage(nouveauperso);
		nbpersos++;
	}
	public void setvitessejoueur(double vx, double vy)
	{
		vitessexjoueur = vx;
		vitesseyjoueur = vy;
	}
	public double getvitessexjoueur()
	{
		return persos[0].getvitessex();
	}
	public double getvitesseyjoueur()
	{
		return persos[0].getvitessey();
	}
	public void setanglepersonnage(double nangle)
	{
		anglepersonnage = nangle;
	}
	public int positionxjoueur()
	{
		return persos[0].centreX();
	}
	public int positionyjoueur()
	{
		return persos[0].centreY();
	}
	public void ajoutermissile(GAME_personnage missile, int persoid)
	{
		int id = persos[persoid].ajouterpersonnage(missile);
		GAME_ES.println("C:" + persoid + "; " + id);
		if (persoid == 0)
		{
			GAME_ES.println("C/S" + id + "/" + persos[persoid].getmissiles()[id].genererChaine());
		reseau.ajouterEnvoyer("C/S" + id + "/" + persos[persoid].getmissiles()[id].genererChaine());
		}
	}
	public void setjeuperso(boolean njeuperso)
	{
		jeuperso = njeuperso;
	}
	
	public int getPV()
	{
		return persos[0].PV;
	}
	protected GAME_case[][] map;
	protected GAME_personnage[] persos;
	protected int nbpersos;
	protected int taillepersos;
	protected int augpersos;
	protected int largeur;
	protected int longueur;
	protected int positionx;
	protected int positiony;
	protected int largeurcarreau;
	protected int longueurcarreau;
	protected int fwidth;
	protected int fheight;
	protected int vitessedefilement;
	protected int accelerationdefilement;
	protected int debutvitessedefilement;
	protected int finvitessedefilement;
	protected int bordures;
	protected double vitessexjoueur;
	protected double vitesseyjoueur;
	protected double anglepersonnage;
	protected int compteur;
	protected Sprite fond;
	protected long dernierchangementposition;
	protected mouseListen souris;
	//GAME_bibliotheque bibliotheque = new GAME_bibliotheque();
	surClient reseau;
	protected long decalageMAJ;
	String lastsend = "";
	protected int lasttypedinfos;
	protected int compteuritems;
	protected int numerreur = 0;
	protected boolean jeuperso = false;
}
