package exec.SpaceShadows;

public class GAME_listemessages {
protected static int taillemessages = 5;
protected static String messages[] = new String[taillemessages];
protected static long datemessages[] = new long[taillemessages];
protected static int nbmessages = 0;

public static void ajouterMessage(String message)
{
	if (nbmessages == taillemessages)
	{
		decalagehaut();
	}
	messages[nbmessages] = message;
	datemessages[nbmessages] = System.currentTimeMillis();
	nbmessages++;
	GAME_ES.println("J_ : " + nbmessages);
}
public static void decalagehaut()
{
	if (nbmessages > 0)
	{
	for (int n = 0; n < taillemessages - 1; n++)
	{
		
		if (messages[n + 1] != null)
		{
			GAME_ES.println("J_" + messages[n]);
		messages[n] = new String(messages[n + 1]);
		datemessages[n] = datemessages[n + 1];
		GAME_ES.println("J_ --> " + messages[n]);
		}
		else
		{
			datemessages[n] = 0;
		messages[n] = "";
		}
	}
	messages[taillemessages - 1] = "";
	datemessages[taillemessages - 1] = 0;
	nbmessages--;
	}
	

}

public static String getMessageId(int id)
{
	mettreAJour();
	if (messages[id] == null)
	{
		return "";	
	}
	else
	{
	return messages[id];
	}
}
public static void mettreAJour()
{
	if (System.currentTimeMillis() - datemessages[0] > 5000 && messages[0] != "")
	{
		decalagehaut();
	}
}

public static int getNbMessage()
{
	return nbmessages;
}


}
