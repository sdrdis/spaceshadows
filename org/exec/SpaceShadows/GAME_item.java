package exec.SpaceShadows;
public class GAME_item {
GAME_item(GAME_typeitem nouveautypeditem)
{
	typeditem = nouveautypeditem;
}

GAME_item()
{

}

public void choisirtypeitem(GAME_typeitem nouveautypeditem)
{
	typeditem = nouveautypeditem;
}

GAME_typeitem typeitem()
{
	return typeditem;
}
	
	private GAME_typeitem typeditem;
}