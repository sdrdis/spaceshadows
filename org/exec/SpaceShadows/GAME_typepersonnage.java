package exec.SpaceShadows;

public class GAME_typepersonnage {
	GAME_typepersonnage(GAME_mouvements nouveauxmouvements[], int nbnouveauxmouvements, int nId)
	{
		mouvements = nouveauxmouvements;
		nbmouvements = nbnouveauxmouvements;
		id = nId;
	}
	GAME_mouvements mouvementsindex(int nummouvements)
	{
		return mouvements[nummouvements];
	}
	int nombremouvements()
	{
		return nbmouvements;
	}
	public boolean getafficherselection()
	{
		return afficherselection;
	}
	public void setafficherselection(boolean nafficherselection)
	{
		afficherselection = nafficherselection;
	}
	public boolean getexplose()
	{
		return explose;
	}
	public void setexplose(boolean nexplose)
	{
		explose = nexplose;
	}
	public void setangledependantdirection(boolean nangledependantdirection)
	{
		angledependantdirection = nangledependantdirection;
	}
	public boolean getangledependantdirection()
	{
		return angledependantdirection;
	}
	public boolean getanimation()
	{
		return animation;
	}
	public void setanimation(boolean nanimation)
	{
		animation = nanimation;
	}
	public int getlongueuranim()
	{
		return longueuranim;
	}
	public void setlongueuranim(int nlongueuranim)
	{
		longueuranim = nlongueuranim;
	}
	public GAME_typepersonnage getimageexplosion()
	{
		return imageexplosion;
	}
	public void setimageexplosion(GAME_typepersonnage nimageexplosion)
	{
		hasexplosion = true;
		imageexplosion = nimageexplosion;
	}
	public boolean getsensibleexplosion()
	{
		return sensibleexplosion;
	}
	public void setsensibleexplosion(boolean nsensibleexplosion)
	{
		sensibleexplosion = nsensibleexplosion;
	}
	public int getId()
	{
		return id;
	}
	public void setaffichervie(boolean naffichervie)
	{
		affichervie = naffichervie;
	}
	public boolean getaffichervie()
	{
		return affichervie;
	}
	public void setdegats(int ndegats)
	{
		degats = ndegats;
	}
	public int getdegats()
	{
		return degats;
	}
	public int getprecision()
	{
		return precision;
	}
	public void setprecision(int nprecision)
	{
		precision = nprecision;
	}
	public int getcadence()
	{
		return cadence;
	}
	public void setcadence(int ncadence)
	{
		cadence = ncadence;
	}
	public void sethasexplosion(boolean nhasexplosion)
	{
		hasexplosion = nhasexplosion;
	}
	public boolean gethasexplosion()
	{
		return hasexplosion;
	}
	public void settypedegats(int ntypedegats)
	{
		typedegats = ntypedegats;
	}
	public int gettypedegats()
	{
		return typedegats;
	}
	public void setPVT(int nPVT)
	{
		PVT = nPVT;
	}
	public void setbouclierT(int nbouclierT)
	{
		bouclierT = nbouclierT;
	}
	public void setrechargebouclier(int nrechargebouclier)
	{
		rechargebouclier = nrechargebouclier;
	}
	public int getPVT()
	{
		return PVT;
	}
	public int getbouclierT()
	{
		return bouclierT;
	}
	public int getrechargebouclier()
	{
		return rechargebouclier;
	}
	public void setvitesse(int nvitesse)
	{
		vitesse = nvitesse;
	}
	public int getvitesse()
	{
		return vitesse;
	}
	public void setacceleration(int nacceleration)
	{
		vitesse = nacceleration;
	}
	public int getacceleration()
	{
		return vitesse;
	}
	public void setaccangle(double naccangle)
	{
		accangle = naccangle;
	}
	public double getaccangle()
	{
		return accangle;
	}
	public void setsupprimerfinanim(boolean nsupprimerfinanim)
	{
		supprimerfinanim = nsupprimerfinanim;
	}
	public boolean getsupprimerfinanim()
	{
		return supprimerfinanim;
	}
	
	public int getSon() {
		return son;
	}
	public void setSon(int son) {
		this.son = son;
	}
	public boolean isSupprimeqp() {
		return supprimeqp;
	}
	public void setSupprimeqp(boolean supprimeqp) {
		this.supprimeqp = supprimeqp;
	}

	
	
	public boolean isExplosebord() {
		return explosebord;
	}
	public void setExplosebord(boolean explosebord) {
		this.explosebord = explosebord;
	}


	public int getDelaisupprimeqp() {
		return delaisupprimeqp;
	}
	public void setDelaisupprimeqp(int delaisupprimeqp) {
		this.delaisupprimeqp = delaisupprimeqp;
	}


	protected int delaisupprimeqp = 0;
	protected boolean explosebord = false;
	protected boolean supprimeqp = true;
	private boolean sensibleexplosion = false;
	private boolean afficherselection = false;
	private boolean explose = false;
	private boolean angledependantdirection = false;
	private int longueuranim;
	private GAME_mouvements mouvements[];
	private int nbmouvements;
	private boolean animation = false;
	private GAME_typepersonnage imageexplosion;
	private int id;
	private boolean affichervie = false;
	private int degats = 0;
	private int precision = 0;
	private int cadence = 0;
	private boolean hasexplosion = false;
	private int typedegats = 0;
	private int PVT = 0;
	private int bouclierT = 0;
	private int rechargebouclier = 0;
	private int vitesse = 0;
	private int acceleration = 0;
	private double accangle = 0;
	private boolean supprimerfinanim = false;
	private int son = -1;
}
