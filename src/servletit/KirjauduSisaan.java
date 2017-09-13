package servletit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import kohdeluokat.*;

/**
 * T‰m‰ servletti kirjaa asiakkaan sis‰‰n j‰rjestelm‰‰n.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 */
public class KirjauduSisaan extends Action
{
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String excecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException 
	{
		String paluu=null;
	
		System.out.println("** Kirjaudu sis‰‰n");
		HttpSession session = req.getSession(true);
		AsiakasOhjaus aOhjaus = new AsiakasOhjaus();
		String puhelin = req.getParameter("puhelin");
		String salasana = req.getParameter("salasana");

		

		try {
			AsiakasBean asiakas = aOhjaus.kirjauduSisaan(puhelin,salasana);
				session.setAttribute("asiakas", asiakas);
				if(asiakas==null){
					paluu="virhe";
				}else{
				paluu = "etusivu";
				}
		} catch (SQLException e) {

			throw e;
		}
		return paluu;
	}
}