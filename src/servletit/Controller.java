package servletit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Controller-luokka on j‰rjestelm‰n p‰‰servletti. K‰yttˆliittym‰t keskustelevat sen kanssa 
 * ja se kutsuu k‰yttˆliittym‰n parametrien perusteella vastaavan Action-luokan execute-metodia.
 * @author Unknown?
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @Version 1.4
 */
public class Controller extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private HashMap<String,String> kartta;

	/**
	 * Sivukartta HashMap<String,String>-muodossa, jota Conroller-luokan metodit k‰ytt‰v‰t.
	 */
	public Controller()
	{
		kartta = new HashMap<String,String>();
		kartta.put("etusivu","index.jsp");
		kartta.put("listaus","lista.jsp");
		kartta.put("ostoskori", "ostoskori.jsp");
		kartta.put("kiitos", "kiitos.jsp");
		kartta.put("kirjaudu", "kirjaudu.jsp");
		kartta.put("luotunnus", "luotunnus.jsp");
		kartta.put("omasivu", "omasivu.jsp");
		kartta.put("palvelu", "palvelu.jsp");
		kartta.put("tunnusluotu", "tunnusluotu.jsp");
		kartta.put("tunnusvirhe", "tunnusvirhe.jsp");
		kartta.put("virhe", "virhe.jsp");
		kartta.put("yhteystiedot", "yhteystiedot.jsp");
		kartta.put("muutosvirhe", "muutosvirhe.jsp");

	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}
	
   /* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
public void doGet(HttpServletRequest req,
		   			 HttpServletResponse res)
                                throws IOException, ServletException
   {
        doPost(req, res);
   }
   
/* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
	  
	   RequestDispatcher disp;
	   Action actionOlio;
       String action = req.getParameter("action");
       
       //K‰ytt‰j‰ on valinnut pizzalistan

       if(action != null && action.equals("haePizzat")||action.equalsIgnoreCase("Pizzalista"))
       {
    	   actionOlio=new ListAction();
    	   doList(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ lis‰‰ pizzan ostoskoriin
       if(action.equalsIgnoreCase("Lis‰‰ ostoskoriin"))
       {

    	   actionOlio=new LisaaKoriin();
    	   doLisaaKoriin(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ kirjautuu ulos j‰rjestelm‰st‰
       if(action.equalsIgnoreCase("Kirjaudu ulos"))
       {

    	   actionOlio=new KirjauduUlos();
    	   doKirjauduUlos(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ kirjautuu ulos j‰rjestelm‰st‰
       if(action.equalsIgnoreCase("Tyhjenn‰ ostoskori"))
       {

    	   actionOlio=new TyhjennaOstoskori();
    	   doTyhjennaOstoskori(req, res, actionOlio);
       }
       
       if(action.equalsIgnoreCase("Muuta asiakastietoja"))
       {

    	   actionOlio=new MuutaTunnusta();
    	   doMuutaTunnusta(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ luo k‰ytt‰j‰tunnuksen sivustolle
       if(action.equalsIgnoreCase("Luo k‰ytt‰j‰tunnus"))
       {

    	   actionOlio=new LuoTunnus();
    	   doLuoTunnus(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ kirjautuu j‰rjestelm‰‰n
       if(action.equalsIgnoreCase("Kirjaudu"))
       {

    	   actionOlio=new KirjauduSisaan();
    	   doKirjauduSisaan(req, res, actionOlio);
       }
       
       //K‰ytt‰j‰ l‰hett‰‰ tilauksen tietokantaan.
       if(action.equalsIgnoreCase("Tilauksen lahetys") || action.equalsIgnoreCase("Tilauksen l‰hetys"))
       {

    	   actionOlio=new LahetaTilaus();
    	   doLahetaTilaus(req, res, actionOlio);
       }
             
       //K‰ytt‰j‰ aloittaa ohjelman tai painaa alkuun-linkki‰.
       else if(action == null ||
    		   (action != null && action.equalsIgnoreCase("alkuun")))
       {
           disp = req.getRequestDispatcher("index.jsp");
           disp.forward(req, res);
          }
       
       /*
        * K‰ytt‰j‰ kirjautuu sivuston j‰rjestelm‰nhallintaan.
       if(action.equals("Kirjaudu j‰rjestelm‰‰n"))
       {

    	   actionOlio=new KirjauduSisaan();
    	   doKirjauduSisaan(req, res, actionOlio);
       }*/

}

/**
 * T‰m‰ metodi luo k‰ytt‰j‰tunnuksen asiakkaalle.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void doLuoTunnus(HttpServletRequest req, HttpServletResponse res,
		Action action)
           throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}

/**
 * T‰m‰ metodi muuttaa k‰ytt‰j‰tunnuksen tietoja.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void doMuutaTunnusta(HttpServletRequest req, HttpServletResponse res,
		Action action)
           throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}

/**
 * T‰ll‰ metodilla kirjaudutaan sivustolle luodun k‰ytt‰j‰tunnuksen avulla.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void doKirjauduSisaan(HttpServletRequest req, HttpServletResponse res,
			Action action)
               throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}

private void doTyhjennaOstoskori(HttpServletRequest req, HttpServletResponse res,
		Action action)
           throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}

/**
 * T‰ll‰ metodilla kirjaudutaan ulos sivustolta.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void doKirjauduUlos(HttpServletRequest req, HttpServletResponse res,
		Action action)
           throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}


/**
 * T‰m‰ metodi l‰hett‰‰ tilauksen asiakkaan toimesta.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void doLahetaTilaus(HttpServletRequest req,
		      HttpServletResponse res, Action action)
   throws IOException, ServletException
{
HttpSession session = req.getSession(true);

RequestDispatcher disp;

String kohde;
try
{
kohde= action.excecute(req,res);
System.out.println("kohde: " + kohde);

String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);

}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}


}


/**
 * T‰m‰ metodi lis‰‰ tilausrivin ostoskoriin.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
   private void doLisaaKoriin(HttpServletRequest req, HttpServletResponse res,
			Action action)
                  throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);
String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}
   
   /**
    * T‰m‰ metodi listaa kaikki pizzat.
    * @param req Kyselyn tulos
    * @param res Kyselyn vastaus
    * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
    * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
    * @throws ServletException Servlettipohjainen virhe.
    */
   private void doList(HttpServletRequest req, HttpServletResponse res,
				Action action)
                       throws IOException, ServletException
{
RequestDispatcher disp;

HttpSession session = req.getSession(true);

String kohde;
try
{
kohde= action.excecute(req,res);
String sivu = kartta.get(kohde);
disp = req.getRequestDispatcher(sivu);
disp.forward(req, res);
}
catch (SQLException e)
{
tietokantaVirhe(req,res);
}

}


   /**
    * T‰m‰ metodi tuhoaa luodun sessio-olion.
    * @param req Kyselyn tulos
    * @param res Kyselyn vastaus
    * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
    * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
    * @throws ServletException Servlettipohjainen virhe.
    */
private void doExit(HttpServletRequest req, HttpServletResponse res)
                                       throws IOException, ServletException
   {
       System.out.println("** EXIT");
       HttpSession session = req.getSession(false);
       session.invalidate();
   }
  

/**
 *Metodia kutsutaan, kun tietokannan k‰sittelyss‰ on tapahtunut virhe.
 * @param req Kyselyn tulos
 * @param res Kyselyn vastaus
 * @param action Action olio, jolla metodin suoritus ohjataan oikealle servletille.
 * @throws IOException Esim. yritys lukea tiedoston lopun yli, yritys hakea verkosta olematonta resurssia.
 * @throws ServletException Servlettipohjainen virhe.
 */
private void tietokantaVirhe (HttpServletRequest req, HttpServletResponse res)
   			throws IOException, ServletException
   {
	  RequestDispatcher disp;
	  req.setAttribute("TkVirhe",true);
 	  disp = req.getRequestDispatcher("index.jsp");
	  disp.forward(req, res);
   }
}