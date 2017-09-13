package kohdeluokat;
import java.sql.SQLException;
import kohdeluokat.AsiakasBean;
import tietokantaluokat.DataAccessObject;

/**
 * <code>AsiakasOhjaus</code>-luokka on <code>AsiakasBean</code>-luokan ohjausluokka.
 * <p>
 * Se toimii toiminnanhallitsijan ja tietokannan v‰liss‰
 *@author Juho
 *@author Asta 
 *@author Tiia
 *@author Pasi
 *@version 1.2
 */
public class AsiakasOhjaus {

	private DataAccessObject dao;
	
	/**
	 * T‰m‰ metodi luo uuden DataAccessObject-olion.
	 * @see tietokantaluokat.DataAccessObject
	 */
	public AsiakasOhjaus ()
	{
		dao = new DataAccessObject();
	}
	/**
	 * T‰ll‰ metodilla kirjaudutaan sis‰‰n palveluun.
	 * 
	 * @param puhelin asiakkaan puhelinnumero
	 * @param salasana asiakkaan salasana
	 * @return asiakkaan tiedot
	 * @throws SQLException
	 */
	public AsiakasBean kirjauduSisaan (String puhelin,String salasana)
		throws SQLException
	{
		AsiakasBean asiakas = null;
		try
		{
			asiakas = dao.kirjauduSisaan(puhelin,salasana);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return asiakas;
	}
	

	/**
	 * T‰ll‰ metodilla luodaan asiakkaalle tunnus palveluun.
	 * @param asiakas Asiakkaan tiedot
	 * @return arvon true tai false
	 * @throws SQLException
	 */
	public boolean luoTunnus (AsiakasBean asiakas)
			throws SQLException
	{
		boolean ok = false;
		try
		{
			ok = dao.luoTunnus(asiakas);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return ok;
}
	
	/**
	 * T‰m‰ metodi muuttaa asiakkaan aiemmin luomaa tunusta.
	 * @param asiakas Muutetut asiakastiedot
	 * @return arvon true tai false
	 * @throws SQLException
	 */
	public boolean muutaTunnusta (AsiakasBean asiakas)
			throws SQLException
	{
		boolean ok = false;
		try
		{
			ok = dao.muutaTunnusta(asiakas);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return ok;
}


}