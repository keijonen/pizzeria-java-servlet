package servletit;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kohdeluokat.PizzaBean;

/**
 * Action-luokka on abstraktiluokka, josta periytet‰‰n muut servletit, paitsi Controller.
 *@author Unknown?
 *@author Juho
 *@author Asta
 *@author Tiia
 *@author Pasi
 *@version 1.2
 */
public abstract class Action {
	
	/**
	 * @param req Kyselyn tiedot
	 * @param res Kyselyn vastauksen tiedot
	 * @return paluu
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public abstract String excecute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException,
	SQLException;
	

   /**
     *T‰m‰ metodi tarkastaa, onko parametrin pizza tiedot k‰yvi‰ kantaan viet‰viksi.
	 * @param pizza Pizzan tiedot
	 * @param req Kyselyn tulokset
	 * @return ok on true tai false
	 */

	public boolean pizzaOk(PizzaBean pizza,HttpServletRequest req )
	   {
		
		//T‰m‰ metodi tarkastaa, onko parametrin pizza tiedot k‰yvi‰ kantaan viet‰viksi.
		//eli nimi!=null ja sisalto !=null ja hinta != 0
		//metodi vie requestille attribuutit:
		//nimiVirhe=true, kun nimi ei kelpaa
		//sisaltoVirhe=true, kun sisalto ei kelpaa
		//hintaVirhe=true, kun hinta ei kelpaa
		
		
		   boolean ok = true;
		   if (pizza.getNimi()==null)
		   {
			   req.setAttribute("nimiVirhe", true);
			   ok = false;
		   }
		   if (pizza.getTayteLista() == null)
		   {
			   req.setAttribute("sisaltoVirhe", true);
			   ok = false;
		   }
		   if (pizza.getHintaB() == 0)
		   {
			   req.setAttribute("hintaVirhe", true);
			   ok = false;
		   }
		   
		   
		   return ok;
	   }
}
