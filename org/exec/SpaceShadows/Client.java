package exec.SpaceShadows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
	Socket reseau;
	BufferedReader in;
	OutputStreamWriter out;
	String aenvoyer;
	public boolean iserror = false;

	public Client(String nomServeur, int port)
	{
		try
    	{
    		reseau = new Socket (nomServeur, port);
    		in = new BufferedReader(new InputStreamReader(reseau.getInputStream()));
    		out = new OutputStreamWriter(reseau.getOutputStream());
    	}
    	catch (IOException ie)
    	{
    		System.out.println("CLIENT ERROR (1)!");
    		iserror = true;
    	}
	}
	public String Communiquer(String chaine)
	{
		String chaine2 = new String(chaine);
		if (chaine2 != null)
		{
    	try
    	{
    	//	System.out.println(chaine);
		out.write(chaine2 + "\n");
		out.flush();
		return in.readLine();
    	}
    	catch (IOException ie)
    	{
    		System.out.println("CLIENT ERROR (2)!");
    		iserror = true;
    		return "";
    	}
		}
		else
		{
			return "";
		}
	}
	
	
}
