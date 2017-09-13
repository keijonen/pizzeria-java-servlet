package servletit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import kohdeluokat.*;

/**
 * Tämä servletti muuttaa käyttäjätunnuksen tietoja.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 */
public class MuutaTunnusta extends Action
{
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String excecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException 
	{
		String paluu;
	
		System.out.println("** Muuta tunnusta");
		HttpSession session = req.getSession(true);
		AsiakasOhjaus aOhjaus = new AsiakasOhjaus();
		boolean ok = false; 
		
		AsiakasBean asiakas = (AsiakasBean)session.getAttribute("asiakas");		
		asiakas.setEtunimi(req.getParameter("etunimi"));
		asiakas.setSukunimi(req.getParameter("sukunimi"));
		asiakas.setKatuosoite(req.getParameter("katuosoite"));
		asiakas.setPostinumero(req.getParameter("postinumero"));
		asiakas.setPostitoimipaikka(req.getParameter("postitoimipaikka"));
		asiakas.setPuhelin(req.getParameter("puhelin"));
		asiakas.setMail(req.getParameter("mail"));
		asiakas.setMarkkinointilupa(req.getParameter("markkinointilupa"));
		System.out.println(asiakas.getMarkkinointilupa());
		try {
			ok = aOhjaus.muutaTunnusta(asiakas);
			if(ok==true){
			session.setAttribute("asiakas", asiakas);
			paluu="omasivu";
			}else{
				paluu="muutosvirhe";
			}
				
		} catch (SQLException e) {

			throw e;
		}
		return paluu;
	}
}