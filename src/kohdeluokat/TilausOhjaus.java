package kohdeluokat;
import java.sql.SQLException;
import java.util.ArrayList;
import tietokantaluokat.DataAccessObject;

/**
 * <code>TilausOhjaus</code>-luokka on <code>TilausBean</code>-luokan ohjausluokka.
 * <p>
 * Se toimii toiminnanhallitsijan ja tietokannan välissä
 *@author Juho
 *@author Asta 
 *@author Tiia
 *@author Pasi
 *@version 1.4
 */

public class TilausOhjaus {

	private DataAccessObject dao;
	
	/**
	 * Tämä metodi luo uuden DataAccessObject-olion.
	 * @see tietokantaluokat.DataAccessObject
	 */
	public TilausOhjaus ()
	{
		dao = new DataAccessObject();
	}
	
	/**
	 * Tämä metodi lisää tilauksen tietokantaan.
	 * @param tilaus Tilauksen tiedot
	 * @param list	 Lista tilatuista pizzoista
	 * @return true tai false, riippuen siitäö onko tilauksen vienti kantaan onnistunut vai ei. true=onnistunut
	 * @throws SQLException
	 */
	public boolean lisaaTilaus (TilausBean tilaus, ArrayList<PizzaBean> list)
			throws SQLException
	{
		boolean ok = false;
		System.out.println("** TilausOhjaus");


		if (tilaus != null)
		{
			try
			{
				ok = dao.lisaaTilaus(tilaus,list);
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		return ok;
	}
	
}
