package exec.SpaceShadows;

public class GAME_case {
GAME_case(GAME_typeterrain nouveautypedeterrain)
{
	typedeterrain = nouveautypedeterrain;
	isitem = false;
}

GAME_case()
{

}

public void choisirtypeterrain(GAME_typeterrain nouveautypedeterrain)
{
	typedeterrain = nouveautypedeterrain;
}

GAME_typeterrain typeterrain()
{
	return typedeterrain;
}

public void ajoutertypeitem(GAME_typeitem nouveauitem)
{
	listeitems = new GAME_item(nouveauitem);
	isitem = true;
	//nbitems++;
}
public GAME_item item()
{
	return listeitems;
}

public boolean aitem()
{
	return isitem;
}
	
	private GAME_typeterrain typedeterrain;
	private GAME_item listeitems;
	private boolean isitem;
}
