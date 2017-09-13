package servletit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kohdeluokat.PizzaBean;
import kohdeluokat.PizzaOhjaus;

/**
 * Tämä servletti listaa pizzat tietokannasta.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 */
public class ListAction extends Action
{
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String excecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException 
	{
		String paluu=null;
	
		System.out.println("** Pizza list");
		HttpSession session = req.getSession(true);
		ArrayList<PizzaBean> pizzat = null;
		PizzaOhjaus pOhjaus = new PizzaOhjaus();

		try {
			pizzat = pOhjaus.haePizzat();

			if (pizzat != null)
			{
				req.setAttribute("haePizzat", pizzat);
				paluu = "listaus";
				
			}
			else
				throw new SQLException();

		} catch (SQLException e) {

			throw e;
		}
		return paluu;
	}
}
