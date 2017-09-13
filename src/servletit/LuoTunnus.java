package servletit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kohdeluokat.AsiakasBean;
import kohdeluokat.AsiakasOhjaus;

/**
 * Tämä servletti luo asiakkaalle käyttäjätunnuksen.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 */
public class LuoTunnus extends Action
{
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String excecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, SQLException 
	{
		String paluu=null;
	
		System.out.println("** Tunnuksen luonti");
		HttpSession session = req.getSession(true);
		boolean ok = false;
		String etunimi=req.getParameter("etunimi");
		String sukunimi=req.getParameter("sukunimi");
		String katuosoite=req.getParameter("katuosoite");
		String postinumero=req.getParameter("postinumero");
		String postitoimipaikka=req.getParameter("postitoimipaikka");
		String puhelin=req.getParameter("puhelin");
		String mail=req.getParameter("mail");
		String salasana=req.getParameter("salasana");
		String markkinointilupa=req.getParameter("markkinointilupa");
		
		AsiakasBean asiakas = new AsiakasBean(etunimi,sukunimi,katuosoite,postinumero,postitoimipaikka,mail,puhelin,salasana,markkinointilupa);
		AsiakasOhjaus aOhjaus = new AsiakasOhjaus();

		try {
			ok = aOhjaus.luoTunnus(asiakas);

			if (ok == true)
			{

				paluu = "tunnusluotu";
				
			}
			else{
				paluu = "tunnusvirhe";
			}

		} catch (SQLException e) {

			throw e;
		}
		return paluu;
	}
}