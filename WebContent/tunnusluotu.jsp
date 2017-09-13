<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
    <%@include file="/WEB-INF/css/pizzeria.css" %>
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='shortcut icon' type='image/x-icon' href='kuvat/uudet/favicon.ico' />
<!-- linkki google-fonttiin -->
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin napit-->
<link href='https://fonts.googleapis.com/css?family=Architects+Daughter' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin leip‰ teksti -->
<link href='https://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
<title>Tunnuksen luonti</title>
</head>
<body>
<jsp:useBean id="asiakas" scope="session" class="kohdeluokat.AsiakasBean"/>
<div>

<div class="ylinpalkki">
<a href="https://www.facebook.com/Pizzeria-Karvak%C3%A4si-Carlos-501059033387397/" target="_blank">
<img src="kuvat/uudet/facebook_nabu.png" alt="facebookNappi" style="position: relative;right:60px;float:left;width:42px;height:42px;border:0;"></a>

<%if(asiakas.getId() == 0){ %>
<a id="kirjaudu" href="kirjaudu.jsp">
<img src="kuvat/uudet/kirjaudu_nabu1.png" 
onmouseover="this.src='kuvat/uudet/kirjaudu_nabu2.png'"
onmouseout="this.src='kuvat/uudet/kirjaudu_nabu1.png'" />
</a>
<%}%>


<%if(asiakas.getId() != 0){ %>
<p class="valkoinen"><%out.print("Olet kirjautunut nimell‰: "+asiakas.getEtunimi()+" "+asiakas.getSukunimi()); %></p>
<form action="Controller" method="get">
<input type="submit" name="action" value="Kirjaudu ulos"/>
</form>
<%}%>
<br>
<%if(asiakas.getId() != 0){ %>
<a href="omasivu.jsp">
<img src="kuvat/uudet/omasivu_nabu1.png" 
onmouseover="this.src='kuvat/uudet/omasivu_nabu2.png'"
onmouseout="this.src='kuvat/uudet/omasivu_nabu1.png'"/></a>
<%}%>

<%if(asiakas.getId() == 0){ %>
<%}%>

<a id="ostoskori" href="ostoskori.jsp">
<img src="kuvat/uudet/ostoskori_nabu1.png" 
onmouseover="this.src='kuvat/uudet/ostoskori_nabu2.png'"
onmouseout="this.src='kuvat/uudet/ostoskori_nabu1.png'"/>
</a>
</div>

<%if(asiakas.getId() == 0){ %>

<%}%>
<%if(asiakas.getId() != 0){ %>
<div class="ylapalkki">
<p class="valkoinen"><%out.print("Olet kirjautunut nimell‰: "+asiakas.getEtunimi()+" "+asiakas.getSukunimi()); %></p>
<form action="Controller" method="get">
<input type="submit" name="action" value="Kirjaudu ulos"/>
</form>
</div>
<%}%>

<header>
<a id="otsikkolinkki" href="index.jsp"><h1>Pizzeria Karvak‰si Carlos</h1>
</header>
<div class="napit">
<a class="napit" href="Controller?action=alkuun">Alkuun</a>
<a class="napit" href="kirjaudu.jsp">Kirjaudu</a>
</div>

<article>
<h2>Tunnuksen luonti onnistui. Nyt voit kirjautua sis‰‰n palveluun.</h2>
<img src="kuvat/uudet/pizzerialol.jpg" alt="pizzerialolkuva"></a>
<br>
<br> 
</article>
<footer>
<p class="valkoinen">Pizzeria Karvak‰si Carlos<br>
Ratapihantie 13, 00520 Helsinki</>
</footer>
</div>
</body>
</html>