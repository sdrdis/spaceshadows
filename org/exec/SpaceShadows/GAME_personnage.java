package exec.SpaceShadows;

import java.util.Random;


public class GAME_personnage {

	GAME_personnage(GAME_typepersonnage nouveautypepersonnage)
	{
		typepersonnage = nouveautypepersonnage;
		posx = 50;
		posy = 50;
		majposition = System.currentTimeMillis();
		estselectionne = false;
		bougeable = true;
		iddependant = -1;
		persos = new GAME_personnage[0];
		augpersos = 10;
		visible = true;
		delaisupprimeqp = nouveautypepersonnage.getDelaisupprimeqp();
	}
	GAME_personnage()
	{
		majposition = System.currentTimeMillis();
		bougeable = true;
		iddependant = -1;
		visible = true;
		persos = new GAME_personnage[0];
		augpersos = 10;
		delaisupprimeqp = 0;
	}
	GAME_personnage(GAME_personnage nouveauperso)
	{
		typepersonnage = nouveauperso.typepersonnage;
		posx = nouveauperso.posx;
		posy = nouveauperso.posy;
		mouvementchoisi = nouveauperso.mouvementchoisi;
		direction = nouveauperso.direction;
		maj = nouveauperso.maj;
		vitesse = nouveauperso.vitesse;
		setdirectionx(nouveauperso.directionx);
		setdirectiony(nouveauperso.directiony);
		majposition = nouveauperso.majposition;
		bougeable = nouveauperso.bougeable;
		iddependant = nouveauperso.iddependant;
		vitessex = nouveauperso.vitessex;
		vitessey = nouveauperso.vitessey;
		persos = nouveauperso.persos;
		augpersos = nouveauperso.augpersos;
		taillepersos = nouveauperso.taillepersos;
		nbpersos = nouveauperso.nbpersos;
		visible = nouveauperso.visible;
		persos = new GAME_personnage[taillepersos];
		for (int n = 0; n < taillepersos; n++)
		{
			persos[n] = nouveauperso.persos[n];
		}
		augpersos = 10;
		PV = nouveauperso.PV;
		PVT = nouveauperso.PVT;
		bouclier = nouveauperso.bouclier;
		bouclierT = nouveauperso.bouclierT;
		rechargebouclier = nouveauperso.rechargebouclier;
		bouclierplus = nouveauperso.bouclierplus;
		derniereMAJvie = nouveauperso.derniereMAJvie;
		isIA = nouveauperso.isIA;
		IAinitialized = nouveauperso.IAinitialized;
		agressivite = nouveauperso.agressivite;
		joueurvise = nouveauperso.joueurvise;
		perimetre = nouveauperso.perimetre;
		typemissile = nouveauperso.typemissile;
		perimetreproche = nouveauperso.perimetreproche;
		perimetremax = nouveauperso.perimetremax;
		acceleration = nouveauperso.acceleration;
		derniereMAJIA = nouveauperso.derniereMAJIA;
		accangle = nouveauperso.accangle;
		dernierTir = nouveauperso.dernierTir;
		delaisupprimeqp = nouveauperso.delaisupprimeqp;
	}
	GAME_typepersonnage typedepersonnage()
	{
		return typepersonnage;
	}
	public int positionx()
	{
		return (int)posx;
	}
	public int positiony()
	{
		return (int)posy;
	}
	
	public double positionxd()
	{
		return posx;
	}
	public double positionyd()
	{
		return posy;
	}
	public void nouvellepositions(int nposx, int nposy)
	{
		posx = nposx;
		posy = nposy;
		setdirectionx((int)posx);
		setdirectiony((int)posy);
		majposition = System.currentTimeMillis();
	}
	public void nouvellepositionssansmaj(double nposx, double nposy)
	{
		posx = nposx;
		posy = nposy;
		//setdirectionx((int)posx);
		//setdirectiony((int)posy);
		//majposition = System.currentTimeMillis();
	}
	public void nouvellepositionssansmajimm(double nposx, double nposy)
	{
		if (nbechecs <= 6)
		{
		posx = nposx;
		posy = nposy;
		nbechecs++;
		}
		//setdirectionx((int)posx);
		//setdirectiony((int)posy);
		//majposition = System.currentTimeMillis();
	}
	public void choisirmouvement(int mouvement, int ndirection, long nmaj)
	{
		mouvementchoisi = mouvement;
		direction = ndirection;
		maj = nmaj;
	}
	public Sprite afficherimage(long nmaj)
	{
		if (typepersonnage.getanimation())
		{
			//dernieraffiche = 1;
			if (majaffichage == 0)
			{
				majaffichage = nmaj;
			}
			dernieraffiche +=((double) (nmaj - majaffichage) / typepersonnage.getlongueuranim()) * (double)typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			if (dernieraffiche >= typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements() - 1 && typepersonnage.isSupprimeqp() == true)
			{
				if (delaisupprimeqp > 0)
				{
					dernieraffiche = 0;
					delaisupprimeqp--;
				}
				else
				{
				perime = true;
				}
			}
			GAME_ES.println("G_" + dernieraffiche);
			GAME_ES.println("K_ICI");
			//GAME_EO.println(dernieraffiche);
			//GAME_EO.println(typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm());
			//dernieraffiche = dernieraffiche % typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			//GAME_ES.println(dernieraffiche);
			//GAME_ES.println(nmaj);
			//GAME_ES.println(majaffichage);
			//if (dernieraffiche >= typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements())
			//{
				//persoparent.persoattache = null;
			//	return afficherimage(nmaj);
			//}
			//else
			//{
			//while (dernieraffiche > typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm())
			//{
			//	dernieraffiche -= typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm();
			//}
			
			majaffichage = System.currentTimeMillis();
			return typepersonnage.mouvementsindex(mouvementchoisi).mouvementnum((int)dernieraffiche);
			//}
		}
		else
		{
		if (typepersonnage.getanimation())
		{
			GAME_ES.println("K_ICI2");
			//dernieraffiche = 1;
			
			dernieraffiche +=((double) (nmaj - majaffichage) / typepersonnage.getlongueuranim()) * (double)typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			//GAME_EO.println(dernieraffiche);
			//GAME_EO.println(typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm());
			dernieraffiche = dernieraffiche % typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			//while (dernieraffiche > typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm())
			//{
			//	dernieraffiche -= typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm();
			//}
			majaffichage = System.currentTimeMillis();
			return typepersonnage.mouvementsindex(mouvementchoisi).mouvementnum((int)dernieraffiche);
		}
		else
		{
			GAME_ES.println("K_ICI3");
			int nummouvement = ((int) ((nmaj - maj) / typepersonnage.mouvementsindex(mouvementchoisi).nombrefrequence())) % typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm();
			return typepersonnage.mouvementsindex(mouvementchoisi).mouvementnum(nummouvement + direction * typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm());
		}
		}
		
	}
	public Sprite afficherimage2(long nmaj, GAME_personnage persoparent)
	{
		
		if (typepersonnage.getanimation())
		{
			//dernieraffiche = 1;
			
			dernieraffiche +=((double) (nmaj - majaffichage) / typepersonnage.getlongueuranim()) * (double)typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			//GAME_EO.println(dernieraffiche);
			//GAME_EO.println(typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm());
			//dernieraffiche = dernieraffiche % typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
			//GAME_ES.println(dernieraffiche);
			//GAME_ES.println(nmaj);
			//GAME_ES.println(majaffichage);
			if (dernieraffiche >= typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements())
			{
				persoparent.persoattache = null;
				return afficherimage(nmaj);
			}
			else
			{
			//while (dernieraffiche > typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm())
			//{
			//	dernieraffiche -= typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm();
			//}
			
			majaffichage = System.currentTimeMillis();
			return typepersonnage.mouvementsindex(mouvementchoisi).mouvementnum((int)dernieraffiche);
			}
		}
		else
		{
			int nummouvement = ((int) ((nmaj - maj) / typepersonnage.mouvementsindex(mouvementchoisi).nombrefrequence())) % typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm();
			return typepersonnage.mouvementsindex(mouvementchoisi).mouvementnum(nummouvement + direction * typepersonnage.mouvementsindex(mouvementchoisi).nbimagesparm());
		}

		
	}
	public void selectionner()
	{
		estselectionne = true;
	}
	public void deselectionner()
	{
		estselectionne = false;
	}
	public boolean siselectionne()
	{
		return estselectionne;
	}
	
	public int centreX()
	{
		return (int)(posx + (int) (typepersonnage.mouvementsindex(0).largeur() / 2));
	}
	public int centreY()
	{
		return (int)(posy + (int) (typepersonnage.mouvementsindex(0).longueur() / 2));
	}
	
	public void setdirectionsxy(int x, int y, int nordrenum)
	{
		if (nordrenum != ordrenum)
		{
		setdirectionx(x);
		setdirectiony(y);
		majposition = System.currentTimeMillis();
		ordrenum = nordrenum;
		}
	}
	
	public void setdirectionsxyover(int x, int y, int nordrenum)
	{
		setdirectionx(x);
		setdirectiony(y);
		majposition = System.currentTimeMillis();
		ordrenum = nordrenum;
	}
	
	public void setdirectionsxysansmaj(int x, int y)
	{
		setdirectionx(x);
		setdirectiony(y);
	}
	public void setdirectionsxysansmajimm(int x, int y)
	{

		setdirectionx(x);
		setdirectiony(y);

		
	}
	
	public void setdirectionx(int x)
	{
		directionx = x;
		//GAME_ES.println("SET DX:".concat(String.valueOf(directionx)));
	}
	
	public void setdirectiony(int y)
	{
		directiony = y;
		//GAME_ES.println("SET DY:".concat(String.valueOf(directiony)));
	}
	
	public int getdirectionx()
	{
		return (int)directionx;
		//GAME_ES.println("ASKED DY:".concat(String.valueOf(directiony)));
	}
	public int getdirectiony()
	{
		return (int)directiony;
	}
	public boolean estarrive()
	{
	boolean isarrived = false;
	if (modecollision == true)
	{
		if (Math.abs(centreX() - directionx2) < 5 && Math.abs(centreY() - directiony2) < 5)
		{
			isarrived = true;
			modecollision = false;
		}
	}
	else
	{
	if (Math.abs(centreX() - getdirectionx()) < 5 && Math.abs(centreY() - getdirectiony()) < 5)
	{
		isarrived = true;
	}
	}

		return isarrived;
	}
	public int estcollision(GAME_personnage perso2, int idjoueur1, int idjoueur2)
	{
		
		boolean iscollision = false;
		
		int supprimer = 0;
		
		if (perso2.typepersonnage != null)
		{
		double pointsA[][] = new double[4][3];
		
		double X11 = posx;
		double X21 = posx + typepersonnage.mouvementsindex(0).largeur();
		double Y11 = posy + typepersonnage.mouvementsindex(0).longueur() - typepersonnage.mouvementsindex(0).largeur() / 1.5;
		double Y21 = posy + typepersonnage.mouvementsindex(0).longueur();
		
		pointsA[0][1] = X11;
		pointsA[0][2] = Y11;
		pointsA[1][1] = X21;
		pointsA[1][2] = Y11;
		pointsA[2][1] = X11;
		pointsA[2][2] = Y21;
		pointsA[3][1] = X21;
		pointsA[3][2] = Y21;
		
		double X12 = perso2.posx;
		double X22 = perso2.posx + perso2.typepersonnage.mouvementsindex(0).largeur();
		double Y12 = perso2.posy + perso2.typepersonnage.mouvementsindex(0).longueur() - perso2.typepersonnage.mouvementsindex(0).largeur() / 1.5;
		double Y22 = perso2.posy + perso2.typepersonnage.mouvementsindex(0).longueur();
		double pointsB[][] = new double[4][3];
		
		pointsB[0][1] = X12;
		pointsB[0][2] = Y12;
		pointsB[1][1] = X22;
		pointsB[1][2] = Y12;
		pointsB[2][1] = X12;
		pointsB[2][2] = Y22;
		pointsB[3][1] = X22;
		pointsB[3][2] = Y22;
		
		for (int n = 0; n < 4 && iscollision == false; n++)
		{
			if (pointsA[n][1] >= X12 && pointsA[n][1] <= X22 && pointsA[n][2] >= Y12 && pointsA[n][2] <= Y22)
			{
				iscollision = true;
			}
		}
		for (int n = 0; n < 4 && iscollision == false; n++)
		{
			if (pointsB[n][1] >= X11 && pointsB[n][1] <= X21 && pointsB[n][2] >= Y11 && pointsB[n][2] <= Y21)
			{
				iscollision = true;
			}
		}
		
		if (iscollision)
		{
			if (typepersonnage.getexplose() && perso2.typedepersonnage().getexplose())
			{
				supprimer = 0;
			}
			else
			{
		if (typepersonnage.getexplose() && perso2.typepersonnage.getsensibleexplosion())
		{
			if (typepersonnage.gethasexplosion())
			{
			perso2.persoattache =new GAME_personnage(typepersonnage.getimageexplosion());
			perso2.persoattache.majaffichage = System.currentTimeMillis();
			}
			perso2.realiserdegats(typedepersonnage().getdegats(), typedepersonnage().gettypedegats());
			perso2.joueurvise = idjoueur1;
			
			//perso2.setPV(perso2.getPV() - typedepersonnage().getdegats());
			//GAME_ES.println(perso2.persoattache.typedepersonnage().getanimation());
			supprimer = 1;
		}
		if (perso2.typedepersonnage().getexplose() && typepersonnage.getsensibleexplosion())
		{
			if (perso2.typedepersonnage().gethasexplosion())
			{
			persoattache = new GAME_personnage(perso2.typedepersonnage().getimageexplosion());
			persoattache.majaffichage = System.currentTimeMillis();
			}
			realiserdegats(perso2.typedepersonnage().getdegats(), perso2.typedepersonnage().gettypedegats());
			joueurvise = idjoueur2;
			
			//setPV(getPV() - perso2.typedepersonnage().getdegats());
			//GAME_ES.println(persoattache.typedepersonnage().getanimation());
			supprimer = 2;
		}
		// /!\ supprimer ETAT 3 : les deux elements /!\
		}
		//GAME_ES.println(iscollision);
		}
		}
		return supprimer;
	}
	public void mettreajourposition()
	{
		if (GAME_dialogue.estFini())
		{
		mettreajourparametres();
		long nmajposition = System.currentTimeMillis();
		posx += vitessex * ((double) (((double) (nmajposition - majposition)) / 1000));
		//GAME_ES.println((double) ((nmajposition - majposition)) / 1000);
		posy += vitessey * ((double) (((double) (nmajposition - majposition)) / 1000));
		majposition = nmajposition;
		calculerdirection();
		}
		else
		{
			derniereMAJvie = System.currentTimeMillis();
			majposition = System.currentTimeMillis();
			
		}
	}
	
	public void calculerdirection()
	{
		if (typepersonnage != null)
		{
		if (typepersonnage.getangledependantdirection())
		{
			angle = Math.atan2(vitessey,vitessex);
		}
		direction = ((int) (((angle / (2 * Math.PI)) * typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements())) + 15 + typepersonnage.mouvementsindex(mouvementchoisi).getdecalage()) % (typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements());
		if (direction < 0)
		{
			direction += typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements();
		}
		}
	}

	
	public double approximerangle(double nangle)
	{
		return (((double)((int) (((nangle / (2 * Math.PI)) * 24))) % (24)) * 2 * Math.PI / (24));
	}
	
	public void setmodecollision(boolean nmode)
	{
		if (bougeable)
		{
		modecollision = nmode;
		}
	}
	
	public boolean getmodecollision()
	{
		return modecollision;
	}
	
	public void setdirectionxy2(int x, int y)
	{
		directionx2 = x;
		directiony2 = y;
	}
	public int getdirectionx2()
	{
		return directionx2;
	}
	public int getdirectiony2()
	{
		return directiony2;
	}
	
	public double getangle()
	{
		return angle;
	}
	public int getordrenum()
	{
		return ordrenum;
	}
	
	public long getmajfinalposition()
	{
		return majfinalposition;
	}
	
	public int getdiminuervitesse()
	{
		return diminuervitesse;
	}
	public void setdiminuervitesse(int ndiminuervitesse)
	{
		diminuervitesse = ndiminuervitesse;
	}
	
	public void augmenterdiminuervitesse(int augmenter)
	{
		diminuervitesse += augmenter;
	}
	public void setvitesse(double vx, double vy)
	{
		vitessex = vx;
		vitessey = vy;
	}
	public void setangle(double nangle)
	{
		angle = nangle;
	}
	public int getmouvementchoisi()
	{
		return mouvementchoisi;
	}
	public double getvitessex()
	{
		return vitessex;
	}
	public double getvitessey()
	{
		return vitessey;
	}
	public boolean estcollisionbord(int width, int height)
	{
		boolean supprimer = false;
		boolean estcollision = false;
		if (posx < 0)
		{
			estcollision = true;
			posx = 0;
			vitessex = -vitessex;
		}
		if (posy < 0)
		{
			estcollision = true;
			posy = 0;
			vitessey = -vitessey;
		}
		if (posx + typepersonnage.mouvementsindex(mouvementchoisi).largeur() > width)
		{
			estcollision = true;
			posx = width - typepersonnage.mouvementsindex(mouvementchoisi).largeur();
			vitessex = -vitessex;
		}
		if (posy + typepersonnage.mouvementsindex(mouvementchoisi).longueur() > height)
		{
			estcollision = true;
			posy = height - typepersonnage.mouvementsindex(mouvementchoisi).longueur();
			vitessey = -vitessey;
		}
		if (estcollision && typepersonnage.isExplosebord())
		{
			supprimer = true;
		}
		return supprimer;
	}
	public GAME_personnage getpersoattache()
	{
		return persoattache;
	}
	public void setiddependant(int idperso)
	{
		iddependant = idperso;
	}
	public int getiddependant()
	{
		return iddependant;
	}
	public void supprimerlistepersos(int [] supprimerpersos, int nbsupprimerpersos)
	{
		for (int n = 0; n < nbsupprimerpersos; n++)
		{
			supprimerpersosid(supprimerpersos[n]);
		}
	}
	public void supprimerpersosid(int idperso)
	{
		//r (int n = idperso + 1; n < nbpersos; n++)
		//{
		persos[idperso] = new GAME_personnage (persos[nbpersos - 1]);
		nbpersos--;
		/*
		GAME_ES.println(taillepersos);
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
		 
		 */
	}
	public int ajouterpersonnage(GAME_personnage nouveauperso)
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
		return (nbpersos - 1);
	}
	

	public GAME_personnage [] getmissiles()
	{
		return persos;
	}
	public int getnbmissiles()
	{
		return nbpersos;
	}
	public void setnbmissiles(int nnbpersos)
	{
		nbpersos = nnbpersos;
	}
	
	public int transformerEnEntier(double nombre)
	{
		return Math.round((Math.round(nombre)));
	}
	
	public String genererChaine()
	{
		String chaine = "";
		chaine += "X" + transformerEnEntier(posx) + "/";
		chaine += "Y" + transformerEnEntier(posy) + "/";
		chaine += "A" + transformerEnEntier(direction) + "/";
		chaine += "I" + transformerEnEntier(vitessex) + "/";
		chaine += "J" + transformerEnEntier(vitessey) + "/";
		chaine += "T" + transformerEnEntier(typepersonnage.getId()) + "/";
		GAME_ES.println("B_" + transformerEnEntier(typepersonnage.getId()));
		chaine += "U" + transformerEnEntier(mouvementchoisi) + "/";
		chaine += "V" + transformerEnEntier(retournerVie()) + "/";
		chaine += "B" + transformerEnEntier(retournerBouclier()) + "/";
		return chaine;
	}
	
	public void setVisible(boolean nVisible)
	{
		visible = nVisible;
	}
	
	public boolean getVisible()
	{
		return visible;
	}
	public void directionToAngle()
	{
		//angle = direction;
		if (typepersonnage != null)
		{
		angle = (2 * Math.PI * (direction - 15) / typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements());
		}
		//direction = ((int) (((angle / (2 * Math.PI)) * typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements())) + 15) % (typepersonnage.mouvementsindex(mouvementchoisi).getnbanglesmouvements());
	}
	public int retournerVie()
	{
		if (PVT == 0)
		{
			return PV;
		}
		else
		{
			return Math.round(PV * 100 / PVT);
		}
	}
	public int retournerBouclier()
	{
		if (bouclierT == 0)
		{
			return bouclier;
		}
		else
		{
			return Math.round(bouclier * 100 / bouclierT);
		}
	}
	public void setPV(int nPV)
	{
		PV = nPV;
	}
	public void setPVT(int nPVT)
	{
		PVT = nPVT;
	}
	public int getPV()
	{
		return PV;
	}
	public void setbouclier(int nbouclier)
	{
		bouclier = nbouclier;
	}
	public void setbouclierT(int nbouclierT)
	{
		bouclierT = nbouclierT;
	}
	public void setrechargebouclier(double nrechargebouclier)
	{
		derniereMAJvie = System.currentTimeMillis();
		rechargebouclier = nrechargebouclier;
	}
	public void mettreajourparametres()
	{
		if (bouclierT > 0)
		{
		bouclierplus += (System.currentTimeMillis() - derniereMAJvie) * rechargebouclier / 1000;
		bouclier += Math.round(bouclierplus);
		bouclierplus -= Math.round(bouclierplus);
		if (bouclier > bouclierT)
		{
			bouclier = bouclierT;
		}
		derniereMAJvie = System.currentTimeMillis();
		}
	}
	public void realiserdegats(int degats, int typedegats)
	{
		if (typedegats >= 1 && typedegats <= 3)
		{
			if (degats < 0)
		{
			GAME_listemessages.ajouterMessage("GAIN VIE: " + (-degats));
			GAME_bibliotheque.jouerson(6, 1);
		}
		else
		{
			GAME_bibliotheque.jouerson(8, 1);
		}
		
		if (typedegats == 1) // TOUT
		{
		if (PVT > 0)
		{
		if (bouclier > degats)
		{
			bouclier -= degats;
		}
		else
		{
			degats -= bouclier;
			bouclier = 0;
			PV -= degats;
		}
		}
		}
		if (typedegats == 2) // SEULEMENT PV
		{
			if (PVT > 0)
			{
				PV -= degats;
			}
		}
		if (typedegats == 3) // SEULEMENT bouclier
		{
			if (PVT > 0)
			{
				bouclier -= degats;
			}
		}
		if (PVT  > 0)
		{
			if (PV < 0)
			{
				PV = 0;
			}
			if (PV > PVT)
			{
				PV = PVT;
			}
			if (bouclier < 0)
			{
				bouclier = 0;
			}
			if (bouclier > bouclierT)
			{
				bouclier = bouclierT;
			}
		}
		}
		if (typedegats == 4)
		{
			GAME_bibliotheque.genererbonusarme();
			GAME_bibliotheque.jouerson(6, 1);
		}
	}
	public void MAJposvitesse()
	{
		posx = MAJposx;
		posy = MAJposy;
		vitessex = MAJvitessex;
		vitessey = MAJvitessey;
	}
	public void setIsIA(boolean nIsIA)
	{
		isIA = nIsIA;
	}
	public boolean getIsIA()
	{
		return isIA;
	}
	public String gererIA(int width, int height, GAME_personnage listepersos[], int nblistepersos, int idjoueur)
	{
		String chaine = "";
		if (!IAinitialized)
		{
			chaine += InitialiserIA(listepersos, width, height);
		}
		chaine += gerercomportement(listepersos, nblistepersos, idjoueur, width, height);
		
		return chaine;
	}
	public String gerercomportement(GAME_personnage listepersos[], int nblistepersos, int idjoueur, int width, int height)
	{
		String chaine = "";
		int compteur[] = new int[GAME_bibliotheque.typesPersonnages.length];
		int nbselectionnable = 0;
		
		for (int n = 0; n < nbpersos; n++)
		{
			if (persos[n].typedepersonnage().getafficherselection())
			{
			nbselectionnable++;
			}
			chaine += persos[n].gererIA(listepersos, nblistepersos, idjoueur, width, height);
			if (persos[n].PV <= 0 && persos[n].PVT > 0)
			{
				double max = 10000;
				double posxadv = listepersos[0].centreX();
				double posyadv = listepersos[0].centreY();
				double ccentrex = centreX();
				double ccentrey = centreY();
				double distance = Math.sqrt((ccentrex - posxadv) * (ccentrex - posxadv) + (ccentrey - posyadv) * (ccentrey - posyadv));
				int centreX = persos[n].centreX();
				int centreY = persos[n].centreY();
				persos[n] = new GAME_personnage(GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.explosion]);
				GAME_ES.println("I_" + distance);
				if (distance < max)
				{
				double volume = (max - distance) / max;
			GAME_bibliotheque.jouerson(5, volume);
			GAME_ES.println("I_ -->" + volume);
				}
				persos[n].setCentreXY(centreX, centreY);
			}
			
			compteur[persos[n].typedepersonnage().getId()]++;
		}
		/*
		for (int i = 0; i < bibliotheque.proportions.length; i++)
		{
			if (bibliotheque.proportions[i] > compteur[bibliotheque.typeIA[i]])
			{
				for (int j = 0; j < (bibliotheque.proportions[i] - compteur[bibliotheque.typeIA[i]]); j++)
				{
					int id = ajouterpersonnage(bibliotheque.persoOrdi(i, width, height));
					chaine += "C/S" + id + "/" + persos[id].genererChaine();
				}
			}
		}
		*/
		int niveautemp = GAME_motsdepasses.getniveau();
		if (niveautemp == -1)
		{
		if (GAME_bibliotheque.typemissionactuel() < 0)
		{
			
			if (nbselectionnable == 0 && GAME_bibliotheque.typemissionactuel() != -4)
			{
				GAME_bibliotheque.niveausuivant();
				InitialiserIA(listepersos, width, height);
			}
		}
		else
		{
			Game.setnbrestant(compteur[GAME_bibliotheque.typeIA[GAME_bibliotheque.typemissionactuel()]]);
			
			if (compteur[GAME_bibliotheque.typeIA[GAME_bibliotheque.typemissionactuel()]] == 0)
			{
				GAME_bibliotheque.niveausuivant();
				InitialiserIA(listepersos, width, height);
			}
		}
		}
		else
		{
			GAME_bibliotheque.setniveau(niveautemp);
			InitialiserIA(listepersos, width, height);
		}
		
		return chaine;
	}
	public void setCentreXY(int ncentreX, int ncentreY)
	{
		posx = ncentreX - typepersonnage.mouvementsindex(direction).largeur() / 2;
		posy = ncentreY - typepersonnage.mouvementsindex(direction).longueur() / 2;
	}
	public String gererIA(GAME_personnage listepersos[], int nblistepersos, int idjoueur, int width, int height)
	{
		String chaine = "";
		double temps = (1.0 * (System.currentTimeMillis() - derniereMAJIA)) / 1000;
		derniereMAJIA = System.currentTimeMillis();
		
		double coef = 0;
		double coefv = 0;
		double angleparfait = 0;
		double posxadv = 0;
		double posyadv = 0;
		double ccentrex = 0;
		double ccentrey = 0;
		double distance = 0;
		
		//mettreajourposition();
		if (GAME_dialogue.estFini())
		{
			if (joueurvise == -1)
		{
			if (agressivite == 2)
			{
				ccentrex = centreX();
				ccentrey = centreY();
				posxadv = listepersos[0].centreX();
				posyadv = listepersos[0].centreY();
				distance = Math.sqrt((ccentrex - posxadv) * (ccentrex - posxadv) + (ccentrey - posyadv) * (ccentrey - posyadv));
				if (distance <= perimetreproche)
				{
					joueurvise = 0;
				}
		}
		}
		
			if (agressivite >= 1)
			{
				if (joueurvise != -1)
				{
				posxadv = listepersos[joueurvise].centreX();
			     posyadv = listepersos[joueurvise].centreY();
				ccentrex = centreX();
				ccentrey = centreY();
				 distance = Math.sqrt((ccentrex - posxadv) * (ccentrex - posxadv) + (ccentrey - posyadv) * (ccentrey - posyadv));
				if (distance < perimetremax)
				{
					angleparfait = Math.PI + Math.atan2(ccentrey - posyadv, ccentrex - posxadv);
					coef = 1;
					 coefv = 1;
				if (Math.tan(angle - angleparfait) > 0)
				{
					coef = -coef;
					if (Math.cos(angle) * Math.cos(angleparfait) < 0)
					{
						//coef = -coef;
						//coefv = -coefv;
					}
					//if (Math.cos(angleparfait) * Math.sin(angleparfait) > 0)
					//{
					//	coef = -coef;
					//}
				}
				}
				else
				{
					vitessex = 0;
					vitessey = 0;
					joueurvise = -1;
				}
				

				
					angle += coef * accangle * temps;	
					angle = angleparfait;
				if (distance < perimetre)
				{
					coefv = -coefv;
				}
				vitessex += coefv * acceleration * temps * Math.cos(angle);
				vitessey += coefv * acceleration * temps * Math.sin(angle);
				double vitesseactuelle = Math.sqrt(vitessex * vitessex + vitessey * vitessey);
				if (vitesseactuelle > vitesse)
				{
					vitessex = vitessex * vitesse / vitesseactuelle;
					vitessey = vitessey * vitesse / vitesseactuelle;
				}
				
				double max = 1000;
				if ((System.currentTimeMillis() - dernierTir) > GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[typemissile]].getcadence())
				{
					if (distance < max)
					{
					double volume = (max - distance) / max;
				GAME_bibliotheque.jouerson(GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[typemissile]].getSon(), volume);
					}
				GAME_personnage perso2 = new GAME_personnage(GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[typemissile]]);
				perso2.setiddependant(idjoueur);
				perso2.nouvellepositions(centreX() - perso2.typedepersonnage().mouvementsindex(perso2.getmouvementchoisi()).largeur() / 2,centreY() - perso2.typedepersonnage().mouvementsindex(perso2.getmouvementchoisi()).longueur() / 2);
				perso2.majposition = System.currentTimeMillis();
				perso2.visible = true;
				Random hasard = new Random();
				//GAME_ES.println((2.0 * (hasard.nextFloat() - 0.5)) * bibliotheque.typesPersonnages[bibliotheque.armes[armeselectionnee]].getprecision() / 180.0);
				double anglemissile = approximerangle(angle) + ((2.0 * (hasard.nextFloat() - 0.5)) * (GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[typemissile]].getprecision() * 1.0) * 2 * Math.PI / 360.0);
				//
				perso2.setangle(approximerangle(anglemissile));
				double vitessemissile = GAME_bibliotheque.typesPersonnages[GAME_bibliotheque.armes[typemissile]].getvitesse();
				GAME_ES.println("J_" + (Math.cos(anglemissile) * vitessemissile));
				perso2.setvitesse((Math.cos(anglemissile) * vitessemissile), (Math.sin(anglemissile) * vitessemissile));
				perso2.calculerdirection();
				
				listepersos[idjoueur].ajouterpersonnage(perso2);
				GAME_ES.println("TIR " + typemissile);
				dernierTir = System.currentTimeMillis();
				}
				
				
				}
				else
				{
					vitessex = 0;
					vitessey = 0;
					joueurvise = -1;
				}
			}
		}
		else
		{
			majposition = System.currentTimeMillis();
		}
		

		
		return chaine;
	}
	public String InitialiserIA(GAME_personnage listepersos[], int width, int height)
	{
		String chaine = "";
		while (nbpersos > 0)
		{
			supprimerpersosid(0);
		}
		while (listepersos[0].nbpersos > 0)
		{
			listepersos[0].supprimerpersosid(0);
		}
		listepersos[0].PV = listepersos[0].PVT;
		listepersos[0].bouclier = listepersos[0].bouclierT;
		GAME_bibliotheque.chargerniveau();
		listepersos[0].posx = GAME_bibliotheque.posx() * width;
		listepersos[0].posy = GAME_bibliotheque.posy() * height;
		listepersos[0].vitessex = 0;
		listepersos[0].vitessey = 0;
		Game.vitessex = 0;
		Game.vitessey = 0;
		int id = 0;
		for (int i = 0; i < GAME_bibliotheque.proportions.length; i++)
		{
			for (int j = 0; j < GAME_bibliotheque.proportions[i]; j++)
			{
				id = ajouterpersonnage(GAME_bibliotheque.persoOrdi(i, width, height));
				chaine += "C/S" + id + "/" + persos[id].genererChaine();
			}
		}
		

		
		//GAME_personnage aide = new GAME_personnage(bibliotheque.persoOrdi(1, width, height));
		IAinitialized = true;
		return chaine;
	}
	
	public boolean getperime()
	{
		return perime;
	}
	
	
	protected int delaisupprimeqp = 0;
	protected GAME_typepersonnage typepersonnage;
	protected double posx, posy;
	protected int directionx, directiony;
	protected int directionx2, directiony2;
	protected boolean modecollision;
	protected int mouvementchoisi;
	protected int direction;
	protected boolean estselectionne;
	protected long maj;
	protected long majposition;
	protected boolean bougeable;
	protected int ordrenum;
	protected long majfinalposition;
	protected int nbechecs;
	protected int diminuervitesse;
	protected double vitessex;
	protected double vitessey;
	protected double angle;
	protected double dernieraffiche = 0;
	protected long majaffichage = 0;
	protected int vitesse; //Par seconde
	protected GAME_personnage persoattache;
	protected int iddependant;
	protected GAME_personnage persos[];
	protected int nbpersos;
	protected int taillepersos;
	protected int augpersos;
	protected boolean visible;
	protected int PV;
	protected int PVT;
	protected int bouclier;
	protected int bouclierT;
	protected double rechargebouclier; //Par seconde...
	protected double bouclierplus;
	protected long derniereMAJvie;
	protected double MAJposx, MAJposy;
	protected double MAJvitessex, MAJvitessey;
	protected boolean isIA = false;
	protected boolean IAinitialized;
	//protected GAME_bibliotheque bibliotheque = new GAME_bibliotheque();
	protected int joueurvise;
	protected int agressivite;
	protected int perimetre;
	protected int perimetremax;
	protected int typemissile;
	protected double acceleration;
	protected long derniereMAJIA;
	protected double accangle;
	protected double dernierTir;
	protected boolean perime = false;
	protected double perimetreproche = 0;
}