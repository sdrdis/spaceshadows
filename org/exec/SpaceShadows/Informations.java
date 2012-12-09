package exec.SpaceShadows;

public class Informations {
protected String info;
protected long maj;

public String getInfo()
{
	return info;
}
public Informations(Informations nouveau)
{
	info = nouveau.info;
	maj = nouveau.maj;
}
public long getMaj()
{
	return maj;
}
public Informations(String ninfo)
{
info = ninfo;
maj = System.currentTimeMillis();
}
}
