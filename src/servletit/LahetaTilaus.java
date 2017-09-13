package servletit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kohdeluokat.*;


/**
 * Tämä servletti lähettää asiakkaan tekemän tilauksen tietokantaan.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.7
 */
public class LahetaTilaus extends Action{
	
	/* (non-Javadoc)
	 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public  String excecute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException,
	SQLException
	{
		System.out.println("** LahetaTilaus");
	    HttpSession session = req.getSession(true);
	    String paluu = "kiitos";
	    PizzaOhjaus po = new PizzaOhjaus();
	    TilausOhjaus to = new TilausOhjaus();
	    boolean ok = false;
	    TilausBean tilaus = null;
	    AsiakasBean sesson = (AsiakasBean)session.getAttribute("asiakas");
		System.out.println("LÄHETÄTILAUS -> sessionin asiakas: "+sesson.toString());

	    
	    if(sesson.getId()==0){
	    String etunimi = req.getParameter("tilaaja");	    	 
	    String mail = req.getParameter("mail");	    
	    String osoite = req.getParameter("katuosoite")+", "+req.getParameter("postinumero")+", "+req.getParameter("postitoimipaikka");	
	    System.out.println(osoite);
	    String puhelin = req.getParameter("puhelin");
	    AsiakasBean asiakas = new AsiakasBean(etunimi,osoite,mail,puhelin);
		System.out.println("LÄHETÄTILAUS -> Jos ei ole kirjautunut: "+sesson.toString());

	    String toimitustapa = req.getParameter("toimitustapa");
	    tilaus = new TilausBean(asiakas,toimitustapa,osoite);
	    }

	    if(sesson.getId()!=0){
		 String toimitustapa = req.getParameter("toimitustapa");
		 tilaus = new TilausBean(sesson,toimitustapa,sesson.getKatuosoite());
	    }
	    



	    ArrayList<PizzaBean> list = new ArrayList<PizzaBean>();

   		Vector lista = (Vector)session.getAttribute("lista");
		System.out.println(lista.size());

	    for(int i = 0;i < lista.size(); i++){
	    	TilausriviBean rivi = (TilausriviBean) lista.get(i);
	  	    		
		    PizzaBean pizza = po.haePizza(rivi.getNimi());
		    String oregano=rivi.getOregano();
		    String tabasco=rivi.getTabasco();
		    String valkosipuli=rivi.getValkosipuli();
		    if(tabasco != null || valkosipuli != null || oregano != null){
		    	pizza.setOregano(oregano);
		    	pizza.setValkosipuli(valkosipuli);
		    	pizza.setTabasco(tabasco);
		    }
			for(int j = 0;j<rivi.getMaara();j++){
			list.add(pizza);			 
		    }
		    }
			System.out.println("ARRAYLISTIN LISTAN KOKO: "+list.size());

	    	
	    	

	 	   try
	 	   {
	 		 ok=  to.lisaaTilaus(tilaus,list);
	 		 if(ok==true){
	 			Vector l = new Vector();
	 			int nollaa=0;
	 			double nolla=0.00;
				session.setAttribute("lista", l);
				session.setAttribute("hintayht",nolla);
				session.setAttribute("kpl",nollaa);
	 		 }


	 	   }
	 	   catch (SQLException e)
	 	   {
	 		 throw e;
	 	   }
	    
	    return paluu;
	
	}
	}

