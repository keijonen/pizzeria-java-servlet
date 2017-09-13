package servletit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import kohdeluokat.*;

/**
 * Tämä servletti kirjaa asiakkaan ulos järjestelmästä.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 */

public class KirjauduUlos extends Action
{
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String excecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException 
	{
		String paluu="etusivu";
	
		System.out.println("** Kirjaudu ulos");
		HttpSession session = req.getSession(true);
		session.removeAttribute("asiakas");

		return paluu;
	}
}