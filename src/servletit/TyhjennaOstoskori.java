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
	public class TyhjennaOstoskori extends Action
	{

		public String excecute(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException, SQLException 
		{

			String paluu="ostoskori";
			HttpSession session = req.getSession(true);
			session.removeAttribute("kpl");
			session.removeAttribute("hintayht");
			session.removeAttribute("lista");

	
	       			
			return paluu;
		}}
/*		
<form action="ostoskori.jsp">
<%
session.removeAttribute("kpl");
session.removeAttribute("hintayht");
session.removeAttribute("lista");
%>
<input type="submit" value="Tyhjenn‰ ostoskori"/>
</form>*/