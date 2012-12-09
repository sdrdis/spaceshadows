package exec.SpaceShadows;

public class surClient extends Thread
{
	int cleJoueur;
	int numJoueur;
	String chaineAEnvoyer = "";
	String chaineAEnvoyer2 = "";
	String chaineRecue = "";
	int portdefaut = 49999;
	int port = 49999;
	String nomServeur = "ns353534.ovh.net";
	boolean communiquationEnCours = false;
	int attentereinit = 10;
	Einformations infos;
	Einformations infos2;
	boolean actif = false;
	boolean passefonction = false;
	int nbinfoss = 0;
	int nbinfose = 0;
	int dernierbilan = 0;
	public surClient(boolean nactif)
	{
		infos = new Einformations();
		infos2 = new Einformations();
		actif = nactif;
		if (nactif)
		{
		creerCompte();
		this.start();
		}
	}
	public void run()
	{
		while (true)
		{
			if (nbinfoss - dernierbilan > 100)
			{
				GAME_ES.println("A_BILAN: SAVED: " + nbinfoss + " | SENT: " + nbinfose + " | DIFF: " + (nbinfoss - nbinfose) + " (" + (Math.round((nbinfoss - nbinfose) * 10000 / nbinfoss)) * 1.0f / 100 + " %)");
				dernierbilan = nbinfoss;
			}
			envoyerInfos();
			try { Thread.sleep(attentereinit); } catch (Exception e) {}
		}
	}
	public void quit()
	{
		
	}
	public void envoyerInfos()
	{
		//GAME_ES.println("ENVOI INFO");
		if (!envoyerInfos2())
		{
			creerCompte();
		}
	}
	public boolean envoyerInfos2()
	{
		
		boolean connecte = true;
		if (passefonction == false)
		{
		communiquationEnCours = true;
		Client reseau = new Client(nomServeur, port);
		String chaine;
		nbinfose += infos.nb;
		chaine = reseau.Communiquer("{$0}*" + cleJoueur + "}#" + numJoueur + "}%" + infos.ensembleInfos());
			if (chaine == null)
			{
				GAME_ES.println("PERTE DE CONNECTION... RECONNECTION...");
				connecte = false;
			}
		chaineRecue += chaine;
		//reseau.Communiquer("*");
		//reseau.Communiquer("#" + numJoueur);
		//GAME_ES.println(">" + chaineAEnvoyer);
		//chaineRecue += reseau.Communiquer("%" + chaineAEnvoyer);
		chaineAEnvoyer = "";
		//reseau.Communiquer("D");
		communiquationEnCours = false;
		}
		return connecte;
		
	}
	public void envoyerInfosSpeciales(String chaine)
	{
		GAME_ES.println("AILLE");
		communiquationEnCours = true;
		Client reseau = new Client(nomServeur, port);
		reseau.Communiquer(chaine);
		communiquationEnCours = false;
	}
	public int nouveauPort()
	{
		Client reseau = new Client(nomServeur, portdefaut);
		return StringToInt(reseau.Communiquer("IP"));
	}
	public void creerCompte()
	{
		GAME_ES.println("BOUM");
		port = nouveauPort();
		Client reseau = new Client(nomServeur, port);
		reseau.Communiquer("$0");
		numJoueur = StringToInt(reseau.Communiquer("#N"));
		cleJoueur = StringToInt(reseau.Communiquer("&"));
		reseau.Communiquer("D");
	}
	public void ajouterEnvoyer(String chaine)
	{
		nbinfoss++;
		//if (passefonction == false)
		//{
			passefonction = true;
		if (!actif)
		{
			chaine = "";
		}
		if (communiquationEnCours)
		{
		infos2.ajouterInformations(chaine);
		}
		else
		{
		if (infos2.nb > 0)
		{
		infos = new Einformations(infos2);
		//GAME_ES.println (infos2.nb);
		//GAME_ES.println (infos.nb);
		infos2 = new Einformations();
		//GAME_ES.println (infos.nb);
		}
		infos.ajouterInformations(chaine);
		//GAME_ES.println (infos.nb);
		}
		passefonction = false;
		//}
		
	}
	public String recupererRecu()
	{
		String chaineTempRecue = chaineRecue;
		chaineRecue = "";
		return chaineTempRecue.replaceAll("%", "");
	}
	
	static public int StringToInt(String chaine)
	{
		int entier = 0;
		int coefMult = 1;
		for (int n = 0; n < chaine.length(); n++)
		{
			if (chaine.toCharArray()[chaine.length() - n - 1] != '-')
			{
			entier += CharToInt(chaine.toCharArray()[chaine.length() - n - 1]) * coefMult;
			coefMult *= 10;
			}
			else
			{
			entier = -entier;	
			}
			
		}
		return entier;
	}

	static public int CharToInt(char caractere)
	{
		return (caractere - 48);
	}
    public void finalize()
    {
		Client reseau = new Client(nomServeur, port);
		reseau.Communiquer("$0");
		reseau.Communiquer("*" + cleJoueur);
		reseau.Communiquer("#" + numJoueur);
		reseau.Communiquer("S");
		//GAME_ES.println("DECONNECTION");
    }
}
