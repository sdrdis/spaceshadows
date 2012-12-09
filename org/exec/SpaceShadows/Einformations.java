package exec.SpaceShadows;

public class Einformations {
	Informations eInfos[];
	int taille = 0;
	int nb = 0;
	int tailleplus = 10;
	public Einformations()
	{
		eInfos = new Informations[taille];
	}
	public Einformations(Einformations nouveau)
	{
		taille = nouveau.taille;
		nb = nouveau.nb;
		tailleplus = nouveau.tailleplus;
		eInfos = new Informations[taille];
		for (int n = 0; n < nb; n++)
		{
			eInfos[n] = new Informations(nouveau.eInfos[n]);
		}
	}
	public void ajouterInformations(String info)
	{
		if (nb >= taille)
		{
			agrandir();
		}
		eInfos[nb] = new Informations(info);
		nb++;
	}
	public void agrandir()
	{
		Informations[] infostemp = new Informations[nb];
		infostemp = eInfos;
		taille = taille + tailleplus;
		eInfos = new Informations[taille];
		for (int n = 0; n < nb; n++)
		{
			eInfos[n] = infostemp[n];
		}
	}
	public String ensembleInfos()
	{
		String chaine = "";
		for (int n = 0; n < nb; n++)
		{
			chaine += "M" + (System.currentTimeMillis() - eInfos[n].getMaj()) + "/" + eInfos[n].getInfo();
		}
		nb = 0;
		taille = 0;
		return chaine;
	}
}
