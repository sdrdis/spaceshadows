package exec.SpaceShadows;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameManager implements KeyListener
{
GameManager()
{
	posx = 0;
	posy = 0;
	vitesse = 4;
}

	
	public void keyTyped (KeyEvent e)  {
	


	}
	public void keyPressed (KeyEvent e) {
	    
	    if(e.getKeyCode() == KeyEvent.VK_UP) 
	    {
	    	posy = posy - vitesse;
	    }
	    if(e.getKeyCode() == KeyEvent.VK_DOWN) 
	    {
	    	posy = posy + vitesse;
	    }
	    if(e.getKeyCode() == KeyEvent.VK_LEFT) 
	    {
	    	posx = posx - vitesse;
	    }
	    if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
	    {
	    	posx = posx + vitesse;
	    }
	    //System.out.print(posy);
	    //System.out.print(": ");
}
	public void keyReleased (KeyEvent e) {
}
	public int getposx()
	{
		return posx;
	}
	public int getposy()
	{
		return posy;
	}
	public void setposx(int nposx)
	{
		posx = nposx;
	}
	public void setposy(int nposy)
	{
		posy = nposy;
	}
	protected int posx;
	protected int posy;
	protected int vitesse;
}
