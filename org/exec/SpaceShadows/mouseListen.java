package exec.SpaceShadows;
import java.awt.*;
import java.awt.event.*;

class mouseListen implements MouseMotionListener
{

	mouseListen()
	{
		coterectangleminimum = 100;
		m_dx = -1;
		m_dy = -1;
		ordrenum = 0;
	}
	
    public void mouseMoved(MouseEvent e)
    {
    m_x = e.getX();
    m_y = e.getY();

    }
    
    public void selectionner()
    {
 
    }
    
    public void mouseDragged(MouseEvent e)
    {
    	
    }
    
    public void mousePressed(MouseEvent e)
    {
    	//System.out.println("OK");
      
    }
    public boolean getclic()
    {
    	boolean clic2 = false;
    	if (System.currentTimeMillis() - clic < 50)
    	{
    		clic2 = true;
    		clic = System.currentTimeMillis() - 100;
    	}
    	
    	return clic2;
    }
    public void setclic(boolean nclic)
    {
    	if (nclic = true)
    	{
    		clic = System.currentTimeMillis();
    	}
    	else
    	{
    		clic = System.currentTimeMillis() - 100;
    	}
    }
    
    public int m_x;
    public int m_y;
    private static long clic;
    public int ma_x;
    public int ma_y;
    public int m_x2;
    public int m_y2;
    public int m_dx;
    public int m_dy;
    public int typebouton;
    public boolean selectionne;
    public int coterectangleminimum;
    public int ordrenum;

}