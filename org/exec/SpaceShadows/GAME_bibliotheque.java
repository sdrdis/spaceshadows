package exec.SpaceShadows;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class GAME_bibliotheque {

	public static Sprite images[];
	public static Sprite imagesdeco[];
	public static GAME_typeterrain textures[];
	public static GAME_mouvements mouvements[][];
	public static GAME_typepersonnage typesPersonnages[];
	public static Sprite fonds[];
	public static int armes[];
	public static int vide;
	public static int proportions[];
	public static int typeIA[];
	public static Color couleurs[];
	public static int nbcharge = 0;
	public static int typejeu[];
	public static int explosion = 9;
	public static String sonscharges[];
	public static AudioInputStream stream[];
	public static AudioFormat format[];
	public static DataLine.Info info[];
	public static Clip clip[][];
	public static int nbsonsmax = 4;
	public static int pointeurssons[];
	public static File fichierscharges[];
	public static Audio2 audios[][];
	public static int maxsons[];
	public static double volumeglobal = 1;
	public static double volumemax[];
	public static int maxmunitions[];
	public static int bonusmaxmunitions[];
	public static int bonusminmunitions[];
	public static String nommunitions[];
	public static int missileIA[];
	public static int typevaisseauIA[];
	public static int comportementIA[];
	public static int nbitems = 15;
	public static int nbniveaux = 2;
	public static String texteniveaux[];
	public static int niveauactuel = 0;
	public static int proportionsniveau[][];
	public static double positionsxyniveau[][];
	public static int munitionsniveau[][];
	public static int typemission[];
	public static Sprite avatars[];
	public static String dialogues[][][];
	public static int parleurs[][];
	public static int nbdialogues[];
	public static int nbdialoguesmax = 10;
	public static int nbcaracmax = 55;
	public static int nbsecondes[];
	public static String motdepasse[];
	public static int playlist[];
	public static int encharge;
	//public static InputStream sons[];
	
	public static void augmentervolumeglobal()
	{
		modifiervolumeglobal(volumeglobal + 0.1);
	}
	public static void diminuervolumeglobal()
	{
		modifiervolumeglobal(volumeglobal - 0.1);
	}
	
	public static void modifiervolumeglobal(double nvolumeglobal)
	{	
		try
		{
		volumeglobal = nvolumeglobal;
		bornervolumeglobal();
		audios[4][0].setGain((int) Math.round(volumeglobal * 100));
		GAME_listemessages.ajouterMessage("Volume global : " + Math.round(volumeglobal * 10) + " / 10");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void bornervolumeglobal()
	{
		if (volumeglobal > 1)
		{
			volumeglobal = 1;
		}
		if (volumeglobal < 0)
		{
			volumeglobal = 0;
		}
	}
	
	
	
	public GAME_bibliotheque()
	{
		nbcharge++;
		GAME_ES.println("F_" + nbcharge);
		
		images = new Sprite[8];
		images[0] = SpriteStore.get().getSprite("sprites/TERRAIN/final/1.gif");
		images[1] = SpriteStore.get().getSprite("sprites/TERRAIN/final/2.gif");
		images[2] = SpriteStore.get().getSprite("sprites/TERRAIN/final/3.gif");
		images[3] = SpriteStore.get().getSprite("sprites/TERRAIN/final/4.gif");
		images[4] = SpriteStore.get().getSprite("sprites/TERRAIN/final/5.gif");
		images[5] = SpriteStore.get().getSprite("sprites/TERRAIN/final/6.gif");
		images[6] = SpriteStore.get().getSprite("sprites/TERRAIN/final/7.gif");
		images[7] = SpriteStore.get().getSprite("sprites/TERRAIN/final/8.gif");
		
		imagesdeco = new Sprite[4];
		imagesdeco[0] = SpriteStore.get().getSprite("images/logo.gif");
		imagesdeco[1] = SpriteStore.get().getSprite("images/munitions.gif");
		imagesdeco[2] = SpriteStore.get().getSprite("images/tempsrestant.png");
		imagesdeco[3] = SpriteStore.get().getSprite("images/nontempsrestant.png");
		
		avatars = new Sprite[4];
		avatars[0] = SpriteStore.get().getSprite("images/pilote.png");
		avatars[1] = SpriteStore.get().getSprite("images/general.png");
		avatars[2] = SpriteStore.get().getSprite("images/assistante.png");
		avatars[3] = SpriteStore.get().getSprite("images/osiris.png");
		/*
		Sprite a1 = SpriteStore.get().getSprite("sprites/TERRAIN/1.gif");
		Sprite a2 = SpriteStore.get().getSprite("sprites/TERRAIN/2.gif");
		Sprite a3 = SpriteStore.get().getSprite("sprites/TERRAIN/3.gif");
		Sprite a4 = SpriteStore.get().getSprite("sprites/TERRAIN/4.gif");
		*/
		
		
		
		textures = new GAME_typeterrain[8];
		for (int n = 0; n < 8; n++)
		{
			textures[n] = new GAME_typeterrain(images[n]);
		}
		
		/*
		textures[0] = new GAME_typeterrain(a1);
		textures[1] = new GAME_typeterrain(a2);
		textures[2] = new GAME_typeterrain(a3);
		textures[3] = new GAME_typeterrain(a4);
		*/
		
		
		mouvements = new GAME_mouvements[27][1];
		//vaisseau 5 - 14
		//mouvements[0][0] = new GAME_mouvements("sprites/persos/8/image.png", 1, 24, 5, 5);
		mouvements[0][0] = new GAME_mouvements("sprites/persos/vaisseau5/image.png", 1, 24, 5, 5);
		mouvements[0][0].adddecalage(18);
		mouvements[1][0] = new GAME_mouvements("sprites/persos/laser/image.gif", 1, 24, 5, 5);
		//mouvements[2][0] = new GAME_mouvements("sprites/explosions/exp", 1, 16);
		mouvements[2][0] = new GAME_mouvements("sprites/persos/touche2/image.png", 1, 7, 3, 3);
		mouvements[3][0] = new GAME_mouvements("sprites/persos/vaisseau7/image.png", 1, 24, 5, 5);
		mouvements[3][0].adddecalage(18);
		//mouvements[4][0] = new GAME_mouvements("sprites/persos/laser2/laser", 1, 24);
		mouvements[4][0] = new GAME_mouvements("sprites/persos/missile/image.png", 1, 24, 5, 5);
		mouvements[5][0] = new GAME_mouvements("sprites/persos/vide/image.gif", 1, 1, 1, 1);
		mouvements[6][0] = new GAME_mouvements("sprites/persos/pvplus/image.gif", 1, 1, 1, 1);
		//mouvements[7][0] = new GAME_mouvements("sprites/persos/9/image.png", 1, 24, 5, 5);
		
		//mouvements[8][0] = new GAME_mouvements("sprites/persos/6/image.png", 1, 24, 5, 5);
		mouvements[9][0] = new GAME_mouvements("sprites/persos/explosion/image.png", 1, 7, 3, 3);
		mouvements[10][0] = new GAME_mouvements("sprites/persos/mine/mine.png", 1, 1, 1, 1);
		mouvements[11][0] = new GAME_mouvements("sprites/persos/meteorite/image.gif", 1, 12, 4, 3);
		mouvements[12][0] = new GAME_mouvements("sprites/persos/munitions/image.gif", 1, 1, 1, 1);
		mouvements[13][0] = new GAME_mouvements("sprites/persos/laser puissant/image.gif", 1, 4, 2, 2);
		//mouvements[14][0] = new GAME_mouvements("sprites/persos/propulsion/image.png", 1, 4, 2, 2);
		
		mouvements[15][0] = new GAME_mouvements("sprites/persos/vaisseau6/image.png", 1, 24, 5, 5);
		mouvements[15][0].adddecalage(18);
		mouvements[16][0] = new GAME_mouvements("sprites/persos/vaisseau8/image.png", 1, 24, 5, 5);
		mouvements[16][0].adddecalage(0);
		mouvements[17][0] = new GAME_mouvements("sprites/persos/vaisseau9/image.png", 1, 24, 5, 5);
		mouvements[17][0].adddecalage(18);
		mouvements[18][0] = new GAME_mouvements("sprites/persos/vaisseau10/image.png", 1, 24, 5, 5);
		mouvements[18][0].adddecalage(18);
		mouvements[19][0] = new GAME_mouvements("sprites/persos/vaisseau11/image.png", 1, 24, 5, 5);
		mouvements[19][0].adddecalage(18);
		mouvements[20][0] = new GAME_mouvements("sprites/persos/vaisseau12/image.png", 1, 24, 5, 5);
		mouvements[20][0].adddecalage(18);
		mouvements[21][0] = new GAME_mouvements("sprites/persos/vaisseau13/image.png", 1, 24, 5, 5);
		mouvements[21][0].adddecalage(18);
		mouvements[22][0] = new GAME_mouvements("sprites/persos/vaisseau14/image.png", 1, 24, 5, 5);
		mouvements[22][0].adddecalage(18);
		mouvements[23][0] = new GAME_mouvements("sprites/persos/osiris/image.png", 1, 1, 1, 1);
		mouvements[23][0].adddecalage(18);
		mouvements[24][0] = new GAME_mouvements("sprites/persos/plus/image.png", 1, 1, 1, 1);
		mouvements[25][0] = new GAME_mouvements("sprites/persos/laser bouclier/image.png", 1, 24, 5, 5);
		mouvements[26][0] = new GAME_mouvements("sprites/persos/antipoursuite/image.png", 1, 4, 2, 2);

		
		
		/*
		mouvs = new GAME_mouvements [1];
		mouvs[0] = new GAME_mouvements("sprites/persos/laser/laser", 1, 24);
		GAME_mouvements mouvs3[];
		mouvs3 = new GAME_mouvements [1];
		mouvs3[0] = new GAME_mouvements("sprites/explosions/exp", 1, 16);
		
		GAME_mouvements mouvs2[];
		mouvs2 = new GAME_mouvements [1];
		mouvs2[0] = new GAME_mouvements("sprites/persos/4/arretf", 1, 24);
		*/
		
		typesPersonnages = new GAME_typepersonnage[26];
		typesPersonnages[0] = new GAME_typepersonnage(mouvements[0],1, 0); // VAISSEAU 1
		typesPersonnages[1] = new GAME_typepersonnage(mouvements[1],1, 1); // LASER
		typesPersonnages[2] = new GAME_typepersonnage(mouvements[2],1, 2); // EXPLOSION
		typesPersonnages[3] = new GAME_typepersonnage(mouvements[3],1, 3); // VAISSEAU 2
		typesPersonnages[4] = new GAME_typepersonnage(mouvements[4],1, 4); // LASER 2
		typesPersonnages[5] = new GAME_typepersonnage(mouvements[5],1, 5); // VIDE
		typesPersonnages[6] = new GAME_typepersonnage(mouvements[6],1, 6); // PVPLUS
		typesPersonnages[7] = new GAME_typepersonnage(mouvements[7],1, 7); // VAISSEAU 3
		typesPersonnages[8] = new GAME_typepersonnage(mouvements[8],1, 8); // VAISSEAU 4
		typesPersonnages[9] = new GAME_typepersonnage(mouvements[9],1, 9); // EXPLOSION
		typesPersonnages[10] = new GAME_typepersonnage(mouvements[10],1, 10); // MINE
		typesPersonnages[11] = new GAME_typepersonnage(mouvements[11],1, 11); // METEORITE
		typesPersonnages[12] = new GAME_typepersonnage(mouvements[12],1, 12); // MUNITIONS
		typesPersonnages[13] = new GAME_typepersonnage(mouvements[13],1, 13); // LASER PUISSANT
		typesPersonnages[14] = new GAME_typepersonnage(mouvements[15],1, 14); // VAISSEAU 14
		typesPersonnages[15] = new GAME_typepersonnage(mouvements[16],1, 15); // VAISSEAU 15
		typesPersonnages[16] = new GAME_typepersonnage(mouvements[17],1, 16); // VAISSEAU 16
		typesPersonnages[17] = new GAME_typepersonnage(mouvements[18],1, 17); // VAISSEAU 17
		typesPersonnages[18] = new GAME_typepersonnage(mouvements[19],1, 18); // VAISSEAU 18
		typesPersonnages[19] = new GAME_typepersonnage(mouvements[20],1, 19); // VAISSEAU 19
		typesPersonnages[20] = new GAME_typepersonnage(mouvements[21],1, 20); // VAISSEAU 20
		typesPersonnages[21] = new GAME_typepersonnage(mouvements[22],1, 21); // VAISSEAU 21
		typesPersonnages[22] = new GAME_typepersonnage(mouvements[23],1, 22); // VAISSEAU 22
		typesPersonnages[23] = new GAME_typepersonnage(mouvements[24],1, 23); // PLUS
		typesPersonnages[24] = new GAME_typepersonnage(mouvements[25],1, 24); // ARME ANTIBOUCLIER
		typesPersonnages[25] = new GAME_typepersonnage(mouvements[26],1, 25); // ARME ANTIPOURSUITE
		
		typesPersonnages[0].setafficherselection(true);
		typesPersonnages[3].setafficherselection(true);
		typesPersonnages[7].setafficherselection(true);
		typesPersonnages[8].setafficherselection(true);
		typesPersonnages[14].setafficherselection(true);
		typesPersonnages[15].setafficherselection(true);
		typesPersonnages[16].setafficherselection(true);
		typesPersonnages[17].setafficherselection(true);
		typesPersonnages[18].setafficherselection(true);
		typesPersonnages[19].setafficherselection(true);
		typesPersonnages[20].setafficherselection(true);
		typesPersonnages[21].setafficherselection(true);
		typesPersonnages[22].setafficherselection(true);
		
		typesPersonnages[1].setexplose(true);
		typesPersonnages[4].setexplose(true);
		typesPersonnages[6].setexplose(true);
		typesPersonnages[10].setexplose(true);
		typesPersonnages[11].setexplose(true);
		typesPersonnages[12].setexplose(true);
		typesPersonnages[13].setexplose(true);
		typesPersonnages[23].setexplose(true);
		typesPersonnages[24].setexplose(true);
		typesPersonnages[25].setexplose(true);
		
		typesPersonnages[1].setExplosebord(true);
		typesPersonnages[4].setExplosebord(true);
		typesPersonnages[6].setExplosebord(true);
		typesPersonnages[10].setExplosebord(true);
		typesPersonnages[13].setExplosebord(true);
		typesPersonnages[24].setExplosebord(true);
		typesPersonnages[25].setExplosebord(true);
		
		typesPersonnages[1].setangledependantdirection(true);
		typesPersonnages[4].setangledependantdirection(true);
		typesPersonnages[6].setangledependantdirection(true);
		typesPersonnages[10].setangledependantdirection(true);
		typesPersonnages[24].setangledependantdirection(true);
		
		typesPersonnages[2].setanimation(true);
		typesPersonnages[9].setanimation(true);
		typesPersonnages[11].setanimation(true);
		typesPersonnages[13].setanimation(true);
		typesPersonnages[25].setanimation(true);
		
		typesPersonnages[0].setlongueuranim(1000);
		typesPersonnages[1].setlongueuranim(1000);
		typesPersonnages[2].setlongueuranim(500);
		typesPersonnages[3].setlongueuranim(1000);
		typesPersonnages[4].setlongueuranim(1000);
		typesPersonnages[5].setlongueuranim(1000);
		typesPersonnages[6].setlongueuranim(1000);
		typesPersonnages[9].setlongueuranim(400);
		typesPersonnages[10].setlongueuranim(1000);
		typesPersonnages[11].setlongueuranim(1000);
		typesPersonnages[12].setlongueuranim(1000);
		typesPersonnages[13].setlongueuranim(300);
		typesPersonnages[25].setlongueuranim(300);
		
		typesPersonnages[0].setsensibleexplosion(true);
		typesPersonnages[3].setsensibleexplosion(true);
		typesPersonnages[7].setsensibleexplosion(true);
		typesPersonnages[8].setsensibleexplosion(true);
		typesPersonnages[14].setsensibleexplosion(true);
		typesPersonnages[15].setsensibleexplosion(true);
		typesPersonnages[16].setsensibleexplosion(true);
		typesPersonnages[17].setsensibleexplosion(true);
		typesPersonnages[18].setsensibleexplosion(true);
		typesPersonnages[19].setsensibleexplosion(true);
		typesPersonnages[20].setsensibleexplosion(true);
		typesPersonnages[21].setsensibleexplosion(true);
		typesPersonnages[22].setsensibleexplosion(true);
		
		typesPersonnages[0].setaffichervie(true);
		typesPersonnages[3].setaffichervie(true);
		typesPersonnages[7].setaffichervie(true);
		typesPersonnages[8].setaffichervie(true);
		typesPersonnages[14].setaffichervie(true);
		typesPersonnages[15].setaffichervie(true);
		typesPersonnages[16].setaffichervie(true);
		typesPersonnages[17].setaffichervie(true);
		typesPersonnages[18].setaffichervie(true);
		typesPersonnages[19].setaffichervie(true);
		typesPersonnages[20].setaffichervie(true);
		typesPersonnages[21].setaffichervie(true);
		typesPersonnages[22].setaffichervie(true);
		
		typesPersonnages[1].setdegats(10);
		typesPersonnages[4].setdegats(20);
		typesPersonnages[6].setdegats(-1000);
		typesPersonnages[10].setdegats(600);
		typesPersonnages[11].setdegats(1200);
		typesPersonnages[13].setdegats(50);
		typesPersonnages[23].setdegats(-1);
		typesPersonnages[24].setdegats(200);
		typesPersonnages[25].setdegats(50);
		
		typesPersonnages[1].settypedegats(1);
		typesPersonnages[4].settypedegats(1);
		typesPersonnages[6].settypedegats(2);
		typesPersonnages[10].settypedegats(1);
		typesPersonnages[11].settypedegats(1);
		typesPersonnages[12].settypedegats(4);
		typesPersonnages[13].settypedegats(1);
		typesPersonnages[23].settypedegats(1);
		typesPersonnages[24].settypedegats(3);
		typesPersonnages[25].settypedegats(1);
		
		typesPersonnages[1].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[4].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[10].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[11].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[13].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[24].setimageexplosion(typesPersonnages[2]);
		typesPersonnages[25].setimageexplosion(typesPersonnages[2]);
		
		typesPersonnages[1].setprecision(20);
		typesPersonnages[4].setprecision(5);
		typesPersonnages[10].setprecision(0);
		typesPersonnages[13].setprecision(10);
		typesPersonnages[24].setprecision(15);
		typesPersonnages[25].setprecision(30);
		
		typesPersonnages[1].setcadence(80);
		typesPersonnages[4].setcadence(200);
		typesPersonnages[10].setcadence(2000);
		typesPersonnages[13].setcadence(300);
		typesPersonnages[24].setcadence(150);
		typesPersonnages[25].setcadence(80);
		
		
		//VAISSEAU 0 : INACTIF; VAISSEAU
		//14 Rapide - peu de degats
		//15 Microvaisseau - Poseur de mine ??
		//16 GROS Chasseur : beaucoup de degats
		//17 Chasseur : moins de degats mais plus rapide
		//18 Chasseur missile : rapide
		//19 Vaisseau bizard : rapide - gros laser - fragile
		//20 Vaisseau le plus rapide : fragile : laser normal
		//21 GROS BOSS : Solide : gros laser, un peu lent
		//22 GROS GROS BOSS : Solide : gros laser, Immobile
		
		
		
		typesPersonnages[0].setPVT(200);
		typesPersonnages[3].setPVT(10000);
		typesPersonnages[7].setPVT(2500);
		typesPersonnages[8].setPVT(1000);
		typesPersonnages[14].setPVT(400);
		typesPersonnages[15].setPVT(400);
		typesPersonnages[16].setPVT(1000);
		typesPersonnages[17].setPVT(600);
		typesPersonnages[18].setPVT(800);
		typesPersonnages[19].setPVT(400);
		typesPersonnages[20].setPVT(500);
		typesPersonnages[21].setPVT(3000);
		typesPersonnages[22].setPVT(1000);
		
		typesPersonnages[0].setbouclierT(100);
		typesPersonnages[3].setbouclierT(6000);
		typesPersonnages[7].setbouclierT(2500);
		typesPersonnages[8].setbouclierT(4000);
		typesPersonnages[14].setbouclierT(300);
		typesPersonnages[15].setbouclierT(400);
		typesPersonnages[16].setbouclierT(1000);
		typesPersonnages[17].setbouclierT(400);
		typesPersonnages[18].setbouclierT(1000);
		typesPersonnages[19].setbouclierT(600);
		typesPersonnages[20].setbouclierT(700);
		typesPersonnages[21].setbouclierT(3000);
		typesPersonnages[22].setbouclierT(10000);
		
		typesPersonnages[0].setrechargebouclier(2);
		typesPersonnages[3].setrechargebouclier(50);
		typesPersonnages[7].setrechargebouclier(20);
		typesPersonnages[8].setrechargebouclier(30);
		typesPersonnages[14].setrechargebouclier(10);
		typesPersonnages[15].setrechargebouclier(10);
		typesPersonnages[16].setrechargebouclier(20);
		typesPersonnages[17].setrechargebouclier(10);
		typesPersonnages[18].setrechargebouclier(10);
		typesPersonnages[19].setrechargebouclier(10);
		typesPersonnages[20].setrechargebouclier(10);
		typesPersonnages[21].setrechargebouclier(20);
		typesPersonnages[22].setrechargebouclier(25);
		
		typesPersonnages[0].setaccangle(2);
		typesPersonnages[3].setaccangle(8);
		typesPersonnages[7].setaccangle(1);
		typesPersonnages[8].setaccangle(2);
		
		
		
		typesPersonnages[0].setvitesse(150);
		typesPersonnages[1].setvitesse(1000);
		typesPersonnages[3].setvitesse(380);
		typesPersonnages[7].setvitesse(380);
		typesPersonnages[8].setvitesse(350);
		typesPersonnages[4].setvitesse(400);
		typesPersonnages[10].setvitesse(0);
		typesPersonnages[11].setvitesse(600);
		typesPersonnages[13].setvitesse(800);

		
		typesPersonnages[14].setvitesse(300);
		typesPersonnages[15].setvitesse(250);
		typesPersonnages[16].setvitesse(200);
		typesPersonnages[17].setvitesse(350);
		typesPersonnages[18].setvitesse(300);
		typesPersonnages[19].setvitesse(300);
		typesPersonnages[20].setvitesse(400);
		typesPersonnages[21].setvitesse(200);
		typesPersonnages[22].setvitesse(0);
		typesPersonnages[24].setvitesse(1000);
		typesPersonnages[25].setvitesse(30);
		
		typesPersonnages[11].setSupprimeqp(false);
		typesPersonnages[13].setSupprimeqp(false);
		typesPersonnages[25].setDelaisupprimeqp(20);
		
		
		//VAISSEAU 0 : INACTIF; VAISSEAU
		//14 Rapide - peu de degats
		//15 Microvaisseau - Poseur de mine ??
		//16 GROS Chasseur : beaucoup de degats
		//17 Chasseur : moins de degats mais plus rapide
		//18 Chasseur missile : rapide
		//19 Vaisseau bizard : rapide - gros laser - fragile
		//20 Vaisseau le plus rapide : fragile : laser normal
		//21 GROS BOSS : Solide : gros laser, un peu lent
		//22 GROS GROS BOSS : Solide : gros laser, Immobile
		
		typesPersonnages[0].setacceleration(150);
		typesPersonnages[3].setacceleration(500);
		typesPersonnages[7].setacceleration(500);
		typesPersonnages[8].setacceleration(300);
		typesPersonnages[14].setacceleration(200);
		typesPersonnages[15].setacceleration(100);
		typesPersonnages[16].setacceleration(350);
		typesPersonnages[17].setacceleration(450);
		typesPersonnages[18].setacceleration(300);
		typesPersonnages[19].setacceleration(400);
		typesPersonnages[20].setacceleration(200);
		typesPersonnages[21].setacceleration(100);
		typesPersonnages[22].setacceleration(0);
		/*
		GAME_typepersonnage explosion = new GAME_typepersonnage(mouvs3,1);
		GAME_typepersonnage typeperso2 = new GAME_typepersonnage(mouvs,1);
		GAME_typepersonnage typeperso1 = new GAME_typepersonnage(mouvs2,1);
		typeperso1.setafficherselection(true);
		typeperso2.setafficherselection(false);
		explosion.setafficherselection(false);
		
		typeperso1.setexplose(false);
		typeperso2.setexplose(true);
		explosion.setexplose(false);
		
		typeperso1.setangledependantdirection(false);
		typeperso2.setangledependantdirection(true);
		explosion.setangledependantdirection(false);
		
		typeperso1.setanimation(false);
		typeperso2.setanimation(false);
		explosion.setanimation(true);
		
		typeperso1.setlongueuranim(1000);
		typeperso2.setlongueuranim(1000);
		explosion.setlongueuranim(500);
		
		typeperso1.setsensibleexplosion(true);
		typeperso2.setsensibleexplosion(true);
		explosion.setsensibleexplosion(false);
		
		typeperso2.setimageexplosion(explosion);
		*/
		fonds = new Sprite[3];
		fonds[0] = SpriteStore.get().getSprite("sprites/ciel.png");
		fonds[1] = SpriteStore.get().getSprite("sprites/fondmenu.png");
		fonds[2] = SpriteStore.get().getSprite("sprites/miniciel.png");
		/*
		Sprite fond = SpriteStore.get().getSprite("sprites/cosmos.jpg");
		*/

		
		
		armes = new int[6];
		armes[0] = 1;
		armes[1] = 4;
		armes[2] = 10;
		armes[3] = 13;
		armes[4] = 24;
		armes[5] = 25;
		
		
		
		
		                                     
		
		
		maxmunitions = new int[armes.length];
		maxmunitions[0] = 500;
		maxmunitions[1] = 200;
		maxmunitions[2] = 50;
		maxmunitions[3] = 200;
		maxmunitions[4] = 300;
		maxmunitions[5] = 800;
		
		nommunitions = new String[armes.length];
		nommunitions[0] = "Laser";
		nommunitions[1] = "Missile";
		nommunitions[2] = "Mine";
		nommunitions[3] = "Mega-Laser";
		nommunitions[4] = "Laser Anti-Bouclier";
		nommunitions[5] = "Laser Anti-Poursuite";
		
		bonusmaxmunitions = new int[armes.length];
		bonusmaxmunitions[0] = 200;
		bonusmaxmunitions[1] = 50;
		bonusmaxmunitions[2] = 10;
		bonusmaxmunitions[3] = 30;
		bonusmaxmunitions[4] = 150;
		bonusmaxmunitions[5] = 400;
		
		bonusminmunitions = new int[armes.length];
		bonusminmunitions[0] = 100;
		bonusminmunitions[1] = 20;
		bonusminmunitions[2] = 2;
		bonusminmunitions[3] = 8;
		bonusminmunitions[4] = 60;
		bonusminmunitions[5] = 250;
		
		vide = 5;
		
		
		
		
		
		
		
		
		
		
		
		//**************************************************** EDITION NIVEAUX
		
		
		
		
		
		
		
		//*********************** CA COMMENCE ICI
		nbniveaux = 21;
		
		motdepasse = new String [nbniveaux + 1];
		motdepasse[0] = "Il etait une fois";
		motdepasse[1] = "Le vrai debut";
		motdepasse[2] = "Bienvenue au cauchemar";
		motdepasse[3] = "Un repos bien merite";
		motdepasse[4] = "Operation mine";
		motdepasse[5] = "Christophe Colombe";
		motdepasse[6] = "Survivre ou pas";
		motdepasse[7] = "Asteroides";
		motdepasse[8] = "Le chaos";
		motdepasse[9] = "La fin approche";
		motdepasse[10] = "Operation destruction";
		motdepasse[11] = "Trop de violence";
		motdepasse[12] = "13";
		motdepasse[13] = "Operation survie";
		motdepasse[14] = "Impuissance";
		motdepasse[15] = "Une attente interminable";
		motdepasse[16] = "Champs de mine";
		motdepasse[17] = "Le niveau 17";
		motdepasse[18] = "Mines, mines, mines";
		motdepasse[19] = "Bye bye Osiris";
		motdepasse[20] = "Message de fin";
		
		texteniveaux = new String[nbniveaux + 1]; // EVITER LES ERREURS DE DEPASSEMENT
		texteniveaux[0] = "Niveau 1 : DETRUISEZ LES 5 VAISSEAUX";
		texteniveaux[1] = "Niveau 2 : DETRUISEZ TOUS LES VAISSEAUX";
		texteniveaux[2] = "Niveau 3 : DETRUISEZ TOUS LES VAISSEAUX";
		texteniveaux[3] = "Niveau 4 : DETRUISEZ TOUS LES VAISSEAUX";
		texteniveaux[4] = "Niveau 5 : DETRUISEZ LES POSEURS DE MINES";
		texteniveaux[5] = "Niveau 6 : DETRUISEZ LES POSEURS DE MINES";
		texteniveaux[6] = "Niveau 7 : DETRUISEZ LES POSEURS DE MINES";
		texteniveaux[7] = "Niveau 8 : RESISTEZ 3 MINUTES";
		texteniveaux[8] = "Niveau 9 : RESISTEZ 3 MINUTES";
		texteniveaux[9] = "Niveau 10 : RESISTEZ 3 MINUTES";
		texteniveaux[10] = "Niveau 11 : DETRUISEZ TOUS LES VAISSEAUX EN MOINS DE 10 MINUTES";
		texteniveaux[11] = "Niveau 12 : DETRUISEZ TOUS LES VAISSEAUX EN MOINS DE 10 MINUTES";
		texteniveaux[12] = "Niveau 13 : DETRUISEZ TOUS LES VAISSEAUX EN MOINS DE 10 MINUTES";
		texteniveaux[13] = "Niveau 14 : SURVIVEZ 5 MINUTES";
		texteniveaux[14] = "Niveau 15 : SURVIVEZ 3 MINUTES";
		texteniveaux[15] = "Niveau 16 : SURVIVEZ 1 MINUTE ET 30 SECONDES";
		texteniveaux[16] = "Niveau 17 : RECUPEREZ LES 15 UNITES D'ENERGIE";
		texteniveaux[17] = "Niveau 18 : RECUPEREZ LES 10 UNITES D'ENERGIE";
		texteniveaux[18] = "Niveau 19 : RECUPEREZ LES 5 UNITES D'ENERGIE";
		texteniveaux[19] = "Niveau 20 : DETRUISEZ OSIRIS.";
		texteniveaux[20] = "Niveau 21 : C'EST FINI !";
		/*
		0 : 0 -> VAISSEAU NON AGRESSIF
		1 : 6 -> PVPLUS
		2 : 10 -> MINE
		3 : 11 -> METEORITE
		4 : 12 -> MUNITIONS
		5 : 14 -> Rapide - peu de degats
		6 : 15 -> Microvaisseau - Poseur de mine ??
		7 : 16 -> GROS Chasseur MISSILE : beaucoup de degats
		8 : 17 -> Chasseur  : moins de degats mais plus rapide
		9 : 18 -> Chasseur ANTIBOUCLIER : rapide
		10 : 19 -> Vaisseau bizard : rapide - gros laser - fragile
		11 : 20 -> Vaisseau le plus rapide : fragile : laser normal
		12 : 21 -> GROS VAISSEAU : Solide : gros laser, un peu lent
		13 : 22 -> VAISSEAU MERE OSIRIS
		14 : 23 -> PLUS
		*/
		int niveaunum = 0;
		proportionsniveau = new int [nbniveaux + 1][nbitems];
		remplir(proportionsniveau);
		// NIVEAU 1
		proportionsniveau[niveaunum][0] = 5;
		// NIVEAU 2
		niveaunum++;
		proportionsniveau[niveaunum][0] = 5;
		proportionsniveau[niveaunum][5] = 4;
		proportionsniveau[niveaunum][4] = 30;
		proportionsniveau[niveaunum][1] = 10;
		// NIVEAU 2
		niveaunum++;
		proportionsniveau[niveaunum][0] = 3;
		proportionsniveau[niveaunum][5] = 3;
		proportionsniveau[niveaunum][6] = 3;
		proportionsniveau[niveaunum][8] = 1;
		proportionsniveau[niveaunum][4] = 30;
		proportionsniveau[niveaunum][1] = 10;
		niveaunum++;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][4] = 30;
		proportionsniveau[niveaunum][1] = 10;
		niveaunum++;
		proportionsniveau[niveaunum][6] = 10;
		proportionsniveau[niveaunum][4] = 20;
		proportionsniveau[niveaunum][1] = 15;
		proportionsniveau[niveaunum][2] = 30;
		niveaunum++;
		proportionsniveau[niveaunum][6] = 8;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][4] = 20;
		proportionsniveau[niveaunum][1] = 15;
		proportionsniveau[niveaunum][2] = 30;
		niveaunum++;
		proportionsniveau[niveaunum][6] = 5;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][7] = 3;
		proportionsniveau[niveaunum][4] = 20;
		proportionsniveau[niveaunum][1] = 20;
		proportionsniveau[niveaunum][2] = 30;
		niveaunum++;
		proportionsniveau[niveaunum][3] = 100;
		niveaunum++;
		proportionsniveau[niveaunum][3] = 100;
		proportionsniveau[niveaunum][9] = 3;
		niveaunum++;
		proportionsniveau[niveaunum][3] = 50;
		proportionsniveau[niveaunum][9] = 5;
		proportionsniveau[niveaunum][7] = 5;
		niveaunum++;
		proportionsniveau[niveaunum][7] = 5;
		proportionsniveau[niveaunum][8] = 10;
		proportionsniveau[niveaunum][9] = 5;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 20;
		niveaunum++;
		proportionsniveau[niveaunum][7] = 5;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][9] = 5;
		proportionsniveau[niveaunum][10] = 5;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 20;
		niveaunum++;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][9] = 5;
		proportionsniveau[niveaunum][10] = 10;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 20;
		niveaunum++;
		proportionsniveau[niveaunum][8] = 5;
		proportionsniveau[niveaunum][10] = 5;
		proportionsniveau[niveaunum][11] = 5;
		proportionsniveau[niveaunum][12] = 1;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 20;
		niveaunum++;
		proportionsniveau[niveaunum][8] = 10;
		proportionsniveau[niveaunum][10] = 5;
		proportionsniveau[niveaunum][11] = 10;
		proportionsniveau[niveaunum][12] = 3;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 20;
		proportionsniveau[niveaunum][2] = 40;
		niveaunum++;
		proportionsniveau[niveaunum][8] = 10;
		proportionsniveau[niveaunum][10] = 5;
		proportionsniveau[niveaunum][11] = 10;
		proportionsniveau[niveaunum][12] = 5;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 30;
		proportionsniveau[niveaunum][2] = 100;
		niveaunum++;
		proportionsniveau[niveaunum][2] = 100;
		proportionsniveau[niveaunum][14] = 15;
		niveaunum++;
		proportionsniveau[niveaunum][2] = 150;
		proportionsniveau[niveaunum][14] = 10;
		niveaunum++;
		proportionsniveau[niveaunum][2] = 200;
		proportionsniveau[niveaunum][14] = 5;
		niveaunum++;
		proportionsniveau[niveaunum][12] = 10;
		proportionsniveau[niveaunum][13] = 1;
		proportionsniveau[niveaunum][4] = 40;
		proportionsniveau[niveaunum][1] = 30;
		proportionsniveau[niveaunum][2] = 30;
		proportionsniveau[niveaunum][3] = 30;
		
		
		nbsecondes = new int [nbniveaux + 1];
		nbsecondes[0] = 0;
		nbsecondes[1] = 0;
		nbsecondes[2] = 0;
		nbsecondes[3] = 0;
		nbsecondes[4] = 0;
		nbsecondes[5] = 0;
		nbsecondes[6] = 0;
		nbsecondes[7] = 180;
		nbsecondes[8] = 180;
		nbsecondes[9] = 180;
		nbsecondes[10] = 600;
		nbsecondes[11] = 600;
		nbsecondes[12] = 600;
		nbsecondes[13] = 300;
		nbsecondes[14] = 180;
		nbsecondes[15] = 90;
		nbsecondes[16] = 0;
		nbsecondes[17] = 0;
		nbsecondes[18] = 0;
		nbsecondes[19] = 0;
		nbsecondes[20] = 1;
		
		
		typemission = new int[nbniveaux + 1];
		typemission[0] = -1;
		typemission[1] = -1;
		typemission[2] = -1;
		typemission[3] = -1;
		typemission[4] = 6;
		typemission[5] = 6;
		typemission[6] = 6;
		typemission[7] = -4;
		typemission[8] = -4;
		typemission[9] = -4;
		typemission[10] = -2;
		typemission[11] = -2;
		typemission[12] = -2;
		typemission[13] = -3;
		typemission[14] = -3;
		typemission[15] = -3;
		typemission[16] = 14;
		typemission[17] = 14;
		typemission[18] = 14;
		typemission[19] = 13;
		typemission[20] = -4;
		
		
		dialogues = new String[nbniveaux + 1][nbdialoguesmax][10];
		nbdialogues = new int[nbniveaux + 1];
		parleurs = new int[nbniveaux + 1][nbdialoguesmax];
		
		remplir(dialogues);
		
		niveaunum = 0;
		
		nbdialogues[niveaunum] = formatertexte(1, "Bonjour et bienvenue dans la galaxie Alpha-Solaris.||Je suis votre commandant, et le déroulement de votre mission se passera dorénavant sous ma directive.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(0, "Reçu 5 sur 5.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "La mission doit se dérouler de façon la plus discrète possible. C'est pourquoi vous devez avancer petit à petit en terrain ennemi.|Je sais que l'on ne vous a pas encore briefé, c'est aussi l'objectif de cette vidéo conférence.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Vous n'êtes pas sans savoir que le contrôle de l'armée est, depuis de nombreuses années, optimisé par le système informatique Osiris. Ce système nous a permis jusqu'ici maintes victoires et de sauver de nombreuses vies au combat.|Cependant, il y a quelques jours, le système n'a plus répondu et s'est mis à agir par lui meme. Rapidement, il a pris le contrôle de nos machines de guerre et les a retournées contre nous.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Inutile de préciser que les dégats ont étés considérables.|Le centre de commandement a été détruit et les membres éminents du gouvernements assassinés.|Nous avons totalement perdu le controle de la machine.|Les principaux filons de notre armée agissent maintenant contre nous et détruisent peu a peu nos dernières forces.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Votre mission, en collaboration avec plusieurs de nos agents de force spéciale, est de désactiver le centre IA d'Osiris par tous les moyens nécessaires.||La difficultée de la tâche réside dans le fait qu'Osiris a déjà organisé sa défense pour se prémunir de telles attaques.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "La position dans laquelle nous vous avons envoyé se trouve au commencement de cette dite défense.|Vous pouvez d'ailleurs observer dans votre cadran radar que des unités passives se trouvent à proximité.||Si ces unités ne sont pas encore agressives, c'est que le système Osiris n'est pas encore parvenu à prendre le contrôle de ces machines.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Cependant, ces unités restent potentiellement dangereuses, car il n'est plus qu'une question de temps avant qu'elles ne tombent dans le camps adverse.||L'objectif premier de votre mission consistera donc de détruire ces unités. Soyez cependant averti que ces unités répondrons à votre agression.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		
		niveaunum++;
		
		nbdialogues[niveaunum] = formatertexte(1, "Bien. Vous venez de progresser dans la zone de défense d'Osiris. Mais le chemin est encore long ; la ligne de défense est plus développée que nous le pensions.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(2, "Commandant. Des unités hostiles ont étés détectées proches de la zone AG-80-90.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Apparemment, Osiris s'est rendu compte de votre intrusion et compte bien se défendre. Nous vous envoyons des munitions supplémentaires et des kits de réparation. Tenez bon.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		
		niveaunum = 4;
		
		nbdialogues[niveaunum] = formatertexte(1, "Vous vous rapprochez sensiblement de la zone de défense. Soyez fier, car beaucoup de nos agents ont échoués sur cette premiere vague d'attaque.|Maintenant que vous avez réussi à pénétrer au sein de la zone controlée par Osiris, vous allez pouvoir nous aider à désorganiser le socle de défense.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Les poseurs de mines sont un atout essentiel d'Osiris, car les mines armées empêchent nos vaisseaux lourd de s'approcher du centre de commandement.||Détruisez donc en priorité les poseurs de mines, notre artillerie lourde s'occupera du reste.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		niveaunum = 7;
		
		nbdialogues[niveaunum] = formatertexte(1, "Vous venez de franchir une nouvelle étape dans le système de défense. Mes félicitation, agent spécial !|| Vous allez pouvoir vous rapprocher d'avantage d'Osiris !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Cependant, nos radars nous indiquent que vous devez franchir un champs d'astéroides important. Ces astéroides, destructibles seulement grâce à des rayons Alpha, peuvent occasionner d'important dégats à votre appareil.||Pour vous aider à avancer, des vaisseaux spécialisés enverrons des rayons Alpha vers votre position.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Par contre, vous devrez attendre un certain temps avant que ces rayons prennent effets.||Je suis désolé, mais aucune alternative n'a pu etre trouvée... Bonne chance !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		niveaunum = 10;
		
		nbdialogues[niveaunum] = formatertexte(1, "Bonne nouvelle, vous venez de franchir le champs d'astéroides ! Cependant, si la tâche semble avoir été bien difficile, le pire reste encore à venir.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Plusieurs de nos informateurs nous ont signalé qu'une importante quantité de vaisseaux lourds se trouvaient à proximité de votre position, contre lesquels vous n'avez aucune chance. Il faut donc essayer de passer discret.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Cependant, il faut savoir que tous les vaisseaux controlés par Osiris détiennent un systeme d'alerte qui se déclenche 10 minutes après la détection d'un ennemi.|Pour rester discret, il faut donc détruire ces vaisseaux avant que le signal ne soit envoyé. Bonne chance !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		
		
		
		niveaunum = 13;
		
		nbdialogues[niveaunum] = formatertexte(1, "Mes félicitations, agent spécial. Vous vous approchez sensiblement du système Osiris, et grâce a vous nos vaisseaux lourds sont parvenus à pénétrer éfficacement la défense Osiris. Il n'est plus qu'une question de temps avant que nous vous rejoignons !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(2, "Commandant, une poignée de vaisseaux lourds ont étés détectés à proximité de la zone IF-20-11. Ils se dirigent tout droit vers le vaisseau.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Il semble que certaines forces Osiris ont détecté votre présence. Mon seul ordre : survivez jusqu'à l'arrivée de nos vaisseaux.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		niveaunum = 16;
		
		nbdialogues[niveaunum] = formatertexte(1, "Vous n'êtes plus maintenant qu'à quelques systèmes solaires d'Osiris. Nos vaisseaux, quant à eux, s'approchent pour l'assaut final contre Osiris. Il ne vous reste plus qu'une derniere tâche à réaliser.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Nos plus gros croiseurs possèdent des canons speciaux, utilisant des protons pour detruire éfficacement l'ennemi. Pour pouvoir utiliser ces canons, les croiseurs ont besoin d'énergie, et c'est précisement ce que vous allez nous aider à récolter.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Il semble qu'il y ait 30 sources d'énergie a proximité. Nous ne pouvons pas nous y rendre, car ces sources se trouvent sur un champs de mine qu'Osiris a bati. Limitez vous à les recolter, ça suffira pour détruire rapidement Osiris.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		
		niveaunum = 19;
		
		nbdialogues[niveaunum] = formatertexte(1, "Bravo ! Nos vaisseaux viennent de vous rejoindre, et nous sommes prêts pour l'assaut final contre le système Osiris ! Tout cela grâce à vous, agent spécial !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(2, "Commandant, il semble qu'il y ait des anomalie dans notre système de pilotage. Nous commençons à perdre le contrôle de nos vaisseaux.", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Comment ça ? Que se passe t'il exactement ?", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(3, "Bonjour Commandant ! Je suis le grand méchant Osiris ouarf ouarf ouarf... Merci de m'envoyer vos vaisseaux, c'est tout ce que dont j'avais besoin pour me permettre de détruire l'univers... ouarf ouarf ouarf... C'est grâce aux 'sources d'énergie' insérées dans vos vaisseaux que j'ai pu en prendre le contrôle... Je vous ai bien eu, commandant !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "Oh mon dieu ! Agent special, vous êtes mon dernier espoir !!", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		
		niveaunum = 20;
		
		nbdialogues[niveaunum] = formatertexte(1, "Houra ! Bravo, agent special, vous êtes mon HERO !", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);
		nbdialogues[niveaunum] = formatertexte(1, "ET OUI ! C'EST FINI ! N'est ce pas magnifique ?? Vous avez gagné le jeu et vous connaissez la fin de ce scénario tout à fait extraordinaire ;)...|En espérant vous revoir bientôt pour de nouvelles (més)aventures ;),", dialogues[niveaunum][nbdialogues[niveaunum]], nbdialogues[niveaunum], parleurs[niveaunum]);

		
		//*************************** CA FINI ICI
		
		/*
		parleurs[0][0] = 1;
		parleurs[0][1] = 0;
		parleurs[0][2] = 1;
		parleurs[0][3] = 1;
		parleurs[0][4] = 1;
		parleurs[0][5] = 1;
		parleurs[0][6] = 1;
		parleurs[0][7] = 1;
		*/
		/*
		nbdialogues[0] = 8;
		nbdialogues[0] = 1;
		*/
		
		
		
		positionsxyniveau = new double[nbniveaux + 1][2];
		double positionsxydefaut[] = new double[2];
		positionsxydefaut[0] = 0.5;
		positionsxydefaut[1] = 0.5;
		
		remplirdefaut(positionsxyniveau, positionsxydefaut);
		munitionsniveau = new int[nbniveaux + 1][armes.length];
		
		
		int munitionsdefaut[] = new int[armes.length];
		
		munitionsdefaut[0] = 200;
		munitionsdefaut[1] = 100;
		munitionsdefaut[2] = 10;
		munitionsdefaut[3] = 40;
		munitionsdefaut[4] = 50;
		munitionsdefaut[5] = 200;
		
		remplirdefaut(munitionsniveau, munitionsdefaut);
		
		//********************************************************** FIN EDITION NIVEAUX
		
		
		  
		proportions = new int[nbitems];
		proportions[0] = 30;
		proportions[1] = 30;
		proportions[2] = 30;
		proportions[3] = 20;
		proportions[4] = 20;
		
		typeIA = new int[nbitems];
		typeIA[0] = 0;
		typeIA[1] = 6;
		typeIA[2] = 10;
		typeIA[3] = 11;
		typeIA[4] = 12;
		typeIA[5] = 14;
		typeIA[6] = 15;
		typeIA[7] = 16;
		typeIA[8] = 17;
		typeIA[9] = 18;
		typeIA[10] = 19;
		typeIA[11] = 20;
		typeIA[12] = 21;
		typeIA[13] = 22;
		typeIA[14] = 23;
		

		
		
		
		
		/*
		0 : 0 -> VAISSEAU NON AGRESSIF
		1 : 6 -> PVPLUS
		2 : 10 -> MINE
		3 : 11 -> METEORITE
		4 : 12 -> MUNITIONS
		5 : 14 -> Rapide - peu de degats
		6 : 15 -> Microvaisseau - Poseur de mine ??
		7 : 16 -> GROS Chasseur MISSILE : beaucoup de degats
		8 : 17 -> Chasseur  : moins de degats mais plus rapide
		9 : 18 -> Chasseur ANTIBOUCLIER : rapide
		10 : 19 -> Vaisseau bizard : rapide - gros laser - fragile
		11 : 20 -> Vaisseau le plus rapide : fragile : laser normal
		12 : 21 -> GROS VAISSEAU : Solide : gros laser, un peu lent
		13 : 22 -> VAISSEAU MERE OSIRIS
		14 : 23 -> PLUS
		*/
		missileIA = new int[nbitems];
		missileIA[0] = 0;
		missileIA[5] = 0;
		missileIA[6] = 2;
		missileIA[7] = 1;
		missileIA[8] = 0;
		missileIA[9] = 4;
		missileIA[10] = 3;
		missileIA[11] = 0;
		missileIA[12] = 3;
		missileIA[13] = 3;
		
		typevaisseauIA = new int[nbitems];
		typevaisseauIA[0] = 1;
		typevaisseauIA[1] = 2;
		typevaisseauIA[2] = 2;
		typevaisseauIA[3] = 3;
		typevaisseauIA[4] = 2;
		typevaisseauIA[5] = 1;
		typevaisseauIA[6] = 1;
		typevaisseauIA[7] = 1;
		typevaisseauIA[8] = 1;
		typevaisseauIA[9] = 1;
		typevaisseauIA[10] = 1;
		typevaisseauIA[11] = 1;
		typevaisseauIA[12] = 1;
		typevaisseauIA[13] = 1;
		typevaisseauIA[14] = 2;
		
		comportementIA = new int[nbitems];
		comportementIA[0] = 1;
		comportementIA[5] = 2;
		comportementIA[6] = 2;
		comportementIA[7] = 2;
		comportementIA[8] = 2;
		comportementIA[9] = 2;
		comportementIA[10] = 2;
		comportementIA[11] = 2;
		comportementIA[12] = 2;
		comportementIA[13] = 2;
		
		couleurs = new Color[11];
		couleurs[0] = new Color(0,0,0);
		couleurs[1] = new Color(100,100,200);
		couleurs[2] = new Color(120,120,255);
		couleurs[3] = new Color(180,180,255);
		couleurs[4] = new Color(100,100,200);
		couleurs[5] = new Color(0,0,0);
		couleurs[6] = new Color(255,255,255);
		couleurs[7] = new Color(0,255,0);
		couleurs[8] = new Color(255,0,0);
		couleurs[9] = new Color(0,180,120);
		couleurs[10] = new Color(255,120,120);
		
		typejeu = new int[3];
		typejeu[0] = 3;
		typejeu[1] = 7;
		typejeu[2] = 8;
		
		sonscharges = new String[13];
		maxsons = new int[sonscharges.length];
		volumemax = new double[sonscharges.length];
		
		
		

		sonscharges[0] = "sons/apparition.wav";//;//"sons/apparition.wav";
		maxsons[0] = 1;
		
		sonscharges[1] = "sons/boutons.wav";
		maxsons[1] = 4;
		volumemax[1] = 1;
		sonscharges[2] = "sons/laser2.wav";
		maxsons[2] = 4;
		volumemax[2] = 0.1;
		sonscharges[3] = "sons/missile3.wav";
		//sonscharges[3] = "C:/Documents and Settings/admin/workspace/JEU/org/sons/explosion.wav";
		maxsons[3] = 4;
		volumemax[3] = 0.3;
		sonscharges[4] = "musique/musique3.wav";
		maxsons[4] = 1;
		volumemax[4] = 1;
		sonscharges[5] = "sons/explosion.wav";
		maxsons[5] = 4;
		volumemax[5] = 0.5;
		sonscharges[6] = "sons/bonus.wav";
		maxsons[6] = 4;
		volumemax[6] = 0.5;
		sonscharges[7] = "sons/missile.wav";
		//sonscharges[3] = "C:/Documents and Settings/admin/workspace/JEU/org/sons/explosion.wav";
		maxsons[7] = 4;
		volumemax[7] = 0.3;
		sonscharges[8] = "sons/explosion5.wav";
		maxsons[8] = 4;
		volumemax[8] = 0.5;
		sonscharges[9] = "musique/musique4.wav";
		maxsons[9] = 1;
		volumemax[9] = 1;
		sonscharges[10] = "musique/musique5.wav";
		maxsons[10] = 1;
		volumemax[10] = 1;
		sonscharges[11] = "musique/musique6.wav";
		maxsons[11] = 1;
		volumemax[11] = 1;
		sonscharges[12] = "musique/musique7.wav";
		maxsons[12] = 1;
		volumemax[12] = 1;
		
		
		playlist = new int[6];
		playlist[0] = 4;
		playlist[1] = 9;
		playlist[2] = 10;
		playlist[3] = 11;
		playlist[4] = 12;
		
		
		//sonscharges[4] = "C:/Documents and Settings/admin/workspace/JEU/org/sons/explosion.wav";
		
		try
		{
		fichierscharges = new File[sonscharges.length];
		audios = new Audio2[sonscharges.length][nbsonsmax];
		pointeurssons = new int[sonscharges.length];
		for (int n = 0; n < sonscharges.length; n++)
		{
			//HashMap temp = new HashMap();
			//getClass().getResource(sonscharges[n]).
			
			//fichierscharges[n] = new File(GAME_bibliotheque.class.getClassLoader().getResource(sonscharges[n]).toURI());//new File(GAME_bibliotheque.class.getResource(sonscharges[n]).toURI()); //new File(sonscharges[n]);
			
			//URL url = this.getClass().getClassLoader().getResource(sonscharges[n]);
			//System.out.println(url.toURI().toString() + " | ");
			//fichierscharges[n] = new File(url.toURI());
			
			
			/*
			File temp = File.createTempFile("SS" + n, "EE");
			OutputStream out=new FileOutputStream(temp);
			byte buf[]=new byte[1024];
		    int len;
		    while((len=fichierscharges[n].read(buf))>0)
		    out.write(buf,0,len);
		    out.close();
		    fichierscharges[n].close();
*/
			
			
			//File test = new File("sprites/persos/vaisseau5/image.png");
			//System.out.println(fichierscharges[n].isAbsolute() + " " + fichierscharges[n].canRead());
			//System.out.println(fichierscharges[n].isAbsolute() + " " + fichierscharges[n].canRead());
			
			for (int m = 0; m < maxsons[n]; m++)
	        {
				InputStream eee = this.getClass().getClassLoader().getResourceAsStream(sonscharges[n]);
				
				audios[n][m] = new Audio2();
				audios[n][m].load(eee);
	        }
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		/*
		stream = new AudioInputStream[sonscharges.length];
		format = new AudioFormat[sonscharges.length];
		info = new DataLine.Info[sonscharges.length];
		clip = new Clip[sonscharges.length][nbsonsmax];

		try
		{
		for (int n = 0; n < sonscharges.length; n++)
		{
			for (int m = 0; m < nbsonsmax; m++)
	        {
			pointeurssons[n] = 0;
			stream[n] = AudioSystem.getAudioInputStream(new File(sonscharges[n]));
			format[n] = stream[n].getFormat();
			if (format[n].getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	            format[n] = new AudioFormat(
	                    AudioFormat.Encoding.PCM_SIGNED,
	                    format[n].getSampleRate(),
	                    format[n].getSampleSizeInBits()*2,
	                    format[n].getChannels(),
	                    format[n].getFrameSize()*2,
	                    format[n].getFrameRate(),
	                    true);        // big endian
	            stream[n] = AudioSystem.getAudioInputStream(format[n], stream[n]);
	        }
			info[n] = new DataLine.Info(
		            Clip.class, stream[n].getFormat(), ((int)stream[n].getFrameLength()*format[n].getFrameSize()));
		        
				clip[n][m] = (Clip) AudioSystem.getLine(info[n]);
		        clip[n][m].open(stream[n]);
		        }
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		*/
		
		typesPersonnages[1].setSon(2); //2
		typesPersonnages[4].setSon(3);
		typesPersonnages[6].setSon(6);
		typesPersonnages[10].setSon(7);
		typesPersonnages[13].setSon(2);
		typesPersonnages[24].setSon(2);
		typesPersonnages[25].setSon(2);
		/*
		sons = new ByteArrayInputStream[sonscharges.length];
		for (int n = 0; n < sonscharges.length; n++)
		{
			sons[n] = new ByteArrayInputStream(sonscharges[n].getSamples());
		}
		*/
		//player.play(stream);
	}
	
	public static GAME_personnage persoOrdi(int id, int width, int height)
	{
		GAME_personnage persoordi = new GAME_personnage(typesPersonnages[0]);
		if (typevaisseauIA[id] == 1)
		{
			GAME_typepersonnage typeperso = typesPersonnages[typeIA[id]];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
			persoordi.setPV(typeperso.getPVT());
			persoordi.setPVT(typeperso.getPVT());
			persoordi.setbouclier(typeperso.getbouclierT());
			persoordi.setbouclierT(typeperso.getbouclierT());
			persoordi.setrechargebouclier(typeperso.getrechargebouclier());
			persoordi.setIsIA(true);
			persoordi.setangle(Math.random() * 2 * Math.PI);
			persoordi.agressivite = comportementIA[id];
			persoordi.joueurvise = -1;
			persoordi.perimetre = 200;
			persoordi.perimetreproche = 1000;
			persoordi.perimetremax = 2000;
			persoordi.typemissile = missileIA[id];
			persoordi.vitesse = typeperso.getvitesse();
			persoordi.acceleration = typeperso.getacceleration();
			persoordi.accangle = typeperso.getaccangle();
		}
		if (typevaisseauIA[id] == 2)
		{
			GAME_typepersonnage typeperso = typesPersonnages[typeIA[id]];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(20 + hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur() - 40)), 20 + Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur() - 40)));
		}
		if (typevaisseauIA[id] == 3)
		{
			GAME_typepersonnage typeperso = typesPersonnages[typeIA[id]];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
			//persoordi.setdirectionsxyover(Math.round(Math.round((hasard.nextFloat() - 0.5) * typeperso.getvitesse())), Math.round(Math.round((hasard.nextFloat() - 0.5) * typeperso.getvitesse())), 0);
			persoordi.setvitesse((hasard.nextFloat() - 0.5) * typeperso.getvitesse(), (hasard.nextFloat() - 0.5) * typeperso.getvitesse());
		}
		/*
		if (id == 0)
		{
		GAME_typepersonnage typeperso = typesPersonnages[0];
		persoordi = new GAME_personnage(typeperso);
		Random hasard = new Random();
		persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
		persoordi.setPV(typeperso.getPVT());
		persoordi.setPVT(typeperso.getPVT());
		persoordi.setbouclier(typeperso.getbouclierT());
		persoordi.setbouclierT(typeperso.getbouclierT());
		persoordi.setrechargebouclier(typeperso.getrechargebouclier());
		persoordi.setIsIA(true);
		persoordi.agressivite = 1;
		persoordi.joueurvise = -1;
		persoordi.perimetre = 200;
		persoordi.perimetremax = 2000;
		persoordi.typemissile = 3;
		persoordi.vitesse = typeperso.getvitesse();
		persoordi.acceleration = typeperso.getacceleration();
		persoordi.accangle = typeperso.getaccangle();
		}
		if (id == 1)
		{
			GAME_typepersonnage typeperso = typesPersonnages[6];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
		}
		if (id == 2)
		{
			GAME_typepersonnage typeperso = typesPersonnages[10];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
		}
		if (id == 3)
		{
			GAME_typepersonnage typeperso = typesPersonnages[11];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
			//persoordi.setdirectionsxyover(Math.round(Math.round((hasard.nextFloat() - 0.5) * typeperso.getvitesse())), Math.round(Math.round((hasard.nextFloat() - 0.5) * typeperso.getvitesse())), 0);
			persoordi.setvitesse((hasard.nextFloat() - 0.5) * typeperso.getvitesse(), (hasard.nextFloat() - 0.5) * typeperso.getvitesse());
		}
		if (id == 4)
		{
			GAME_typepersonnage typeperso = typesPersonnages[12];
			persoordi = new GAME_personnage(typeperso);
			Random hasard = new Random();
			persoordi.nouvellepositions(Math.round(hasard.nextFloat() * (width - typeperso.mouvementsindex(0).largeur())), Math.round(hasard.nextFloat() * (height - typeperso.mouvementsindex(0).longueur())));
		}
		*/
		return persoordi;
	}
	/*
public static void jouerson(int n, double volume)
{
	
	surSound son = new surSound(clip[n][pointeurssons[n]], volume);
	pointeurssons[n]++;
	if (pointeurssons[n] >= nbsonsmax)
	{
		pointeurssons[n] = 0;
	}
	

}
*/
/*
public static void convertir(int n)
{
	try {
		File sourceFile = new File(sonscharges[0]);

		File targetFile = new File(sonscharges[0] + "f");

		AudioInputStream sourceAudioInputStream = AudioSystem.getAudioInputStream(sourceFile);

		AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("ULAW"), 8000, 8, 1, 8, 8000, true);

		AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE; 

		AudioInputStream targetAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, sourceAudioInputStream);


			AudioSystem.write(targetAudioInputStream, AudioFileFormat.Type.WAVE, targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

*/

public static void verifierplaylist()
{
	if (audios[playlist[encharge]][0].getMicrosecondPosition() >= audios[playlist[encharge]][0].getMicrosecondLength())
	{
		jouerplaylist(Math.round(Math.round(Math.floor(Math.random() * 6))), 1.0d);
	}
}



	public static void jouerplaylist(int n, double volume)
	{
	for (int i = 0; i < playlist.length; i++)
	{
		arreterson(playlist[i]);
	}
	encharge = n;
		jouerson(playlist[n], volume);
	}
	
public static void arreterson(int n)
{
	//audios[n][0].pause();
	audios[n][0].setMicrosecondPosition(0);
	audios[n][0].pause();
}
public static void jouerson(int n, double volume)
{
	jouerson(n, volume, false);
}
	public static void jouerson(int n, double volume, boolean continuellement)
	{
		GAME_ES.println("H_1: " + n + " : " + Math.round(volume * volumemax[n] * volumeglobal * 100));
		try
		{
		//GAME_ES.println("H_" + sonscharges[n] + " " + ((int) Math.round(volume * 100)));
		audios[n][pointeurssons[n]].setMicrosecondPosition(0);
		if (!continuellement)
		{
		audios[n][pointeurssons[n]].play();
		}
		else
		{
			audios[n][pointeurssons[n]].playloop();
		}
		//surSound son = new surSound(sonscharges[n], volume);
		GAME_ES.println("H_2: " + n + " : " + Math.round(volume * volumemax[n] * volumeglobal * 100));
		audios[n][pointeurssons[n]].setGain((int) Math.round(volume * volumemax[n] * volumeglobal * 100));
		}
		catch (Exception e)
		{
			
		}
		
		
		
		pointeurssons[n]++;
		if (pointeurssons[n] >= maxsons[n])
		{
			pointeurssons[n] = 0;
		}
//surSound son = new surSound(sonscharges[n], volume);
	}
	
	public static void genererbonusarme()
	{
		int as = Math.round(Math.round(Math.random() * (armes.length - 1)));
		int nb = Math.round(Math.round(Math.random() * (bonusmaxmunitions[as] - bonusminmunitions[as]) + bonusminmunitions[as]));
		GAME_munitions.ajouter(as, nb);
	}
	/*
	public static void jouerson(int n, double volume)
	{

try
{
	File file = new File(sonscharges[n]);
AudioInputStream in= AudioSystem.getAudioInputStream(file);

AudioInputStream din = null;
AudioFormat baseFormat = in.getFormat();
AudioFormat decodedFormat =
    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
din = AudioSystem.getAudioInputStream(decodedFormat, in);
// Play now.

rawplay(decodedFormat, din);
in.close();
}
catch(Exception e)
{
	e.printStackTrace();
}
}

	
	

	private static void rawplay(AudioFormat targetFormat, AudioInputStream din)
	   throws IOException, LineUnavailableException
	{
	  byte[] data = new byte[4096];
	  SourceDataLine line = getLine(targetFormat);
	  if (line != null)
	  {
	    // Start
	    line.start();
	    int nBytesRead = 0, nBytesWritten = 0;
	    while (nBytesRead != -1)
	    {
	        nBytesRead = din.read(data, 0, data.length);
	        if (nBytesRead != -1)
	            nBytesWritten = line.write(data, 0, nBytesRead);
	    }
	    // Stop
	    line.drain();
	    line.stop();
	    line.close();
	    din.close();
	  }
	}

	private static SourceDataLine getLine(AudioFormat audioFormat)
	    throws LineUnavailableException
	{
	  SourceDataLine res = null;
	  DataLine.Info info =
	    new DataLine.Info(SourceDataLine.class, audioFormat);
	  res = (SourceDataLine) AudioSystem.getLine(info);
	  res.open(audioFormat);
	  return res;
	}

	*/
	public static void niveausuivant()
	{
		niveauactuel++;
	}
	public static void setniveau(int niveau)
	{
		niveauactuel = niveau;
	}
	public static void chargerniveau()
	{
		if (niveauactuel >= nbniveaux)
		{
			GAME_menu.setdefile();
			Game.gameRunning = false;
			niveauactuel = 0;
		}
		else
		{
		if (typemission[niveauactuel] == -2 || typemission[niveauactuel] == -3 || typemission[niveauactuel] == -4)
		{
			GAME_munitions.setfintemps(getfintemps());
		}
		else
		{
			GAME_munitions.setfintemps(0);
		}
		GAME_munitions.munitions = munitionsniveau[niveauactuel].clone();
		proportions = proportionsniveau[niveauactuel];
		GAME_dialogue.loaddialogues(dialogues[niveauactuel], parleurs[niveauactuel], nbdialogues[niveauactuel]);
		}
	}
	
	 public static long getfintemps()
	 {
		 return System.currentTimeMillis() + nbsecondes[niveauactuel] * 1000;
	 }
	
	
	public static void affichermessageniveau()
	{
		GAME_listemessages.ajouterMessage(texteniveaux[niveauactuel]);
	}
	
	public static double posx()
	{
		return positionsxyniveau[niveauactuel][0];
	}
	public static double posy()
	{
		return positionsxyniveau[niveauactuel][1];
	}
	public static int typemissionactuel()
	{
		return typemission[niveauactuel];
	}
	public static void remplir(String texte[][][])
	{
		for (int i = 0; i < texte.length; i++)
		{
			for (int j = 0; j < texte[i].length; j++)
			{
				for (int h = 0; h < texte[i][j].length; h++)
				{
					texte[i][j][h] = "";
				}
			}
		}
	}
	public static void remplir(int tableau[][])
	{
		for (int i = 0; i < tableau.length; i++)
		{
			for (int j = 0; j < tableau[i].length; j++)
			{
				tableau[i][j] = 0;
			}
		}
	}
	
	public static void remplir(String texte[])
	{
		for (int i = 0; i < texte.length; i++)
		{
			
					texte[i] = "";
		}
	}
	
	
	public static int formatertexte(int parleur, String texte, String tabtexte[], int nbdialogue, int parleurs[])
	{
		parleurs[nbdialogue] = parleur;
		String texte2 = texte;
		nbdialogue++;
		int i = 0;
		while (texte2.length() > nbcaracmax)
		{
			int n = nbcaracmax;
			while (texte2.charAt(n) != ' ')
			{
				n--;
			}
			
			int nbcarac = traitersautsdelignes(texte2.substring(0,n));
			if (nbcarac > -1)
			{
				tabtexte[i] = texte2.substring(0,nbcarac);
				texte2 = texte2.substring(nbcarac + 1);
			}
			else
			{
				tabtexte[i] = texte2.substring(0,n);
				texte2 = texte2.substring(n + 1);
			}
			
			i++;
		}
		tabtexte[i] = texte2;
		return nbdialogue;
	}
	public static int traitersautsdelignes(String texte)
	{
		boolean quiter = false;
		for (int i = 0; i < texte.length() && quiter == false; i++)
		{
			if (texte.charAt(i) == '|')
			{
				String textetemp = new String(texte);
				texte = textetemp.substring(0, i);
				return i;
			}
		}
		return -1;
	}
	
	public static int motdepasseToniveau(String motdepasserentre)
	{
		int arenvoyer = -1;
		for (int i = 0; i < nbniveaux; i++)
		{
			GAME_ES.println("J_" + motdepasserentre + " " + motdepasse[i]);
			if (cmp(motdepasserentre,motdepasse[i]))
			{
				
				arenvoyer = i;
			}
		}
		return arenvoyer;
	}
	
	public static void remplirdefaut (int tableau1[][], int tableau2[])
	{
	for (int i = 0; i < tableau1.length; i++)
	{
	tableau1[i] = tableau2;	
	}
	}
	
	public static void remplirdefaut (double tableau1[][], double tableau2[])
	{
	for (int i = 0; i < tableau1.length; i++)
	{
	tableau1[i] = tableau2;	
	}
	}
	
	public static boolean cmp(String chaine1, String chaine2)
	{
		boolean arenvoyer = true;
		
		if (chaine1.length() == chaine2.length())
		{
			for (int i = 0; i < chaine1.length() && arenvoyer == true; i++)
			{
				if (chaine1.charAt(i) != chaine2.charAt(i))
				{
					arenvoyer = false;
				}
			}
		}
		else
		{
			arenvoyer = false;
		}
		
		return arenvoyer;
	}
	
	
}
