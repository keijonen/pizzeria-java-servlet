package kohdeluokat;
import java.sql.SQLException;
import java.util.ArrayList;
import tietokantaluokat.DataAccessObject;

/**
 * <code>PizzaOhjaus</code>-luokka on <code>PizzaBean</code>-luokan ohjausluokka.
 * <p>
 * Se toimii toiminnanhallitsijan ja tietokannan välissä
 *@author Juho
 *@author Asta
 *@author Tiia
 *@author Pasi
 *@Version 1.2
 */
public class PizzaOhjaus {

	private DataAccessObject dao;
	
	/**
	 * Tämä metodi luo uuden DataAccessObject-olion.
	 * @see tietokantaluokat.DataAccessObject
	 */
	public PizzaOhjaus ()
	{
		dao = new DataAccessObject();
	}
	/**
	 * Tällä metodilla haetaan pizza tietokannasta nimen avulla.
	 * 
	 * @param nimi pizzan nimi
	 * @return pizzan tiedot
	 * @throws SQLException
	 */
	public PizzaBean haePizza (String nimi)
		throws SQLException
	{
		PizzaBean pizza = null;
		try
		{
			pizza = dao.haePizza(nimi);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return pizza;
	}
	

	/**
	 * Tällä metodilla haetaan pizza tietokannasta tunnuksen avulla.
	 * @param tunnus pizzan tunnus
	 * @return pizzan tiedot
	 * @throws SQLException
	 */
	public PizzaBean haePizza (int tunnus)
			throws SQLException
	{
		PizzaBean pizza = null;
		try
		{
			pizza = dao.haePizza(tunnus);
		}
		catch (SQLException e)
		{
			throw e;
		}
		return pizza;
}
	/**
	 * Tällä metodilla haetaan kaikki pizzat tietokannasta.
	 * @return listan kaikista pizzoista
	 * @throws SQLException
	 */
	public ArrayList<PizzaBean > haePizzat ()
			throws SQLException
	{
		ArrayList<PizzaBean > lista = null;
		try
		{
			lista = dao.haePizzat();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return lista;
	}

}
