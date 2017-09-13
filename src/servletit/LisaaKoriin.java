package servletit;

	import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kohdeluokat.*;


/**
 * T‰m‰ servletti lis‰‰ pizzan ostoskoriin.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.5
 */
	public class LisaaKoriin extends Action
	{
		/* (non-Javadoc)
		 * @see servletit.Action#excecute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		public String excecute(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException, SQLException 
		{
			DecimalFormat format = new DecimalFormat("0.00");
			String paluu="listaus";
		
			System.out.println("** LisaaKoriin");
			HttpSession session = req.getSession(true);
			PizzaOhjaus pOhjaus = new PizzaOhjaus();			
			
	       	String tabasco = req.getParameter("tabasco");
	       	String valkosipuli = req.getParameter("valkosipuli");
	       	String oregano = req.getParameter("oregano");
       		System.out.println(tabasco + valkosipuli + oregano);
	       	String nimi = req.getParameter("nimi");
	       	String m = req.getParameter("maara");
	       	int maara = Integer.parseInt(m);
	       	String hintaString = req.getParameter("hinta");
	       	hintaString = hintaString.replace(",",".");
   			System.out.println("HINTA HINTASTRING "+hintaString);
   	        BigDecimal hinta = apuluokat.MuokkaaNumerot.pyoristysKahdella(hintaString);
   	     
       		ArrayList<PizzaBean> pizzat = pOhjaus.haePizzat();
       		req.setAttribute("haePizzat", pizzat);

       		Vector lista = (Vector)session.getAttribute("lista");
       		if(lista == null){
       			lista = new Vector();
       		}
       		if(req.getParameter("nimi") != null && req.getParameter("maara") != null){
           		
       			BigDecimal yht = apuluokat.MuokkaaNumerot.bigDecimalMultiply(maara,hinta);


       		TilausriviBean rivi = new TilausriviBean(nimi,maara,hinta,yht);
	       	
	       	if(tabasco != null || oregano != null || valkosipuli != null){
	       		rivi.setOregano(oregano);
	       		rivi.setTabasco(tabasco);
	       		rivi.setValkosipuli(valkosipuli);
	       	}
	       	System.out.println(rivi.toString());

	       	lista.add(rivi);
	       	session.setAttribute("lista", lista);
       		}	
       	
				System.out.println(lista.size());
       		if(lista!=null){
       			int kpl=0;
       			for(int i=0;i<lista.size();i++){     				
       				System.out.println(kpl);
       				TilausriviBean rivi = (TilausriviBean) lista.get(i);
       				int apu = rivi.getMaara();
       				kpl = kpl + apu;
       				session.setAttribute("kpl", kpl);}
       			}
       		
           		if(lista!=null){
           			BigDecimal bd =BigDecimal.ZERO;;
           			for(int i=0;i<lista.size();i++){
           	   
           				TilausriviBean rivi = (TilausriviBean) lista.get(i);
           				BigDecimal apu = rivi.getHintayht();
           				
           				bd = bd.add(apu);
           				

           				session.setAttribute("hintayht", bd);
           			}}
       		
	
	       			
			return paluu;
		}}
	
