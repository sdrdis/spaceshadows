package exec.SpaceShadows;

public class GAME_typeitem {

	GAME_typeitem(Sprite nouvelleImage)
	{
		Image = nouvelleImage;
		posx = (int) (Image.getWidth() / 2);
		posy = (int) (Image.getHeight() / 2);
		imagewidth = Image.getWidth();
		imageheight = Image.getHeight();
	}
	
	Sprite imageitem()
	{
		return Image;
	}
	
	public int positionx()
	{
		return posx;
	}
	
	public int positiony()
	{
		return posy;
	}
	
	public int width()
	{
		return imagewidth;
	}
	public int height()
	{
		return imageheight;
	}
	private Sprite Image;
	private int posx, posy, imagewidth, imageheight;
}
