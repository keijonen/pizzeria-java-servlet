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

<!-- linkki google-fonttiin leip√§ teksti -->
<link href='https://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
<title>Palvelumme</title>
</head>
<body>
<%@ page import="java.util.*, kohdeluokat.*" %>
<jsp:useBean id="asiakas" scope="session" class="kohdeluokat.AsiakasBean"/>

<div class="ylinpalkki">
<a href="https://www.facebook.com/Pizzeria-Karvak%C3%A4si-Carlos-501059033387397/" target="_blank">
<img src="kuvat/uudet/facebook_nabu.png" alt="facebookNappi" style="position: relative;right:60px;float:left;width:42px;height:42px;border:0;"></a>

<%if(asiakas.getId() == 0){ %>
<a id="kirjaudu" href="kirjaudu.jsp">
<img src="kuvat/uudet/kirjaudu_nabu1.png" 
onmouseover="this.src='kuvat/uudet/kirjaudu_nabu2.png'"
onmouseout="this.src='kuvat/uudet/kirjaudu_nabu1.png'" /></a>
<%}%>


<%if(asiakas.getId() != 0){ %>
<span class="valkoinen"><%out.print("Olet kirjautunut nimell‰: "+asiakas.getEtunimi()+" "+asiakas.getSukunimi()); %><br></br></span>
<!-- <form action="Controller" method="get">
<input type="submit" name="action" value="Kirjaudu ulos"/>
</form> -->
<a id="kirajuduulos" href="Controller?action=Kirjaudu+ulos">
<img src="kuvat/uudet/kirjauduulos_nabu1.png" 
onmouseover="this.src='kuvat/uudet/kirjauduulos_nabu2.png'"
onmouseout="this.src='kuvat/uudet/kirjauduulos_nabu1.png'"/></a>

<%}%>
<br>
<%if(asiakas.getId() != 0){ %>
<a id="omasivu"href="omasivu.jsp">
<img src="kuvat/uudet/omasivu_nabu1.png" 
onmouseover="this.src='kuvat/uudet/omasivu_nabu2.png'"
onmouseout="this.src='kuvat/uudet/omasivu_nabu1.png'"/></a>
<%}%>

<%if(asiakas.getId() == 0){ %>
<%}%>

<a id="ostoskori" href="ostoskori.jsp">
<img src="kuvat/uudet/ostoskori_nabu1.png" 
onmouseover="this.src='kuvat/uudet/ostoskori_nabu2.png'"
onmouseout="this.src='kuvat/uudet/ostoskori_nabu1.png'" />
</a>

</div>

<header> 
<a id="otsikkolinkki" href="index.jsp"><h1>Pizzeria Karvak‰si Carlos</h1>
</header>
<div class="napit">
<a class="napit"href="index.jsp">&#9679; Etusivu</a>
<a class="napit"href="Controller?action=haePizzat">&#9679; Pizzat</a>
<a class="sivulla"href="palvelu.jsp">&#9679; Palvelumme</a>
<a class="napit"href="yhteystiedot.jsp">&#9679; Yhteystiedot</a>
</div>

<article>
<h2>Olemme juuri uudistaneet tilamme!</h2>
<p>Tulkaa nauttimaan italialaisesta tunnelmasta ja live-musiikista.</p>
<p>Meilt‰ saat herkulliset pizzat kotiin tai paikan p‰‰lt‰!</p>
<p> Tule nauttimaan maukkaat pizzat paikan p‰‰lle tai ota k‰tev‰sti mukaan.</p>
<img src="kuvat/uudet/carlos_pizzria.jpg">
<br>
<br>
</article>
<footer>
<span class="valkoinen">Pizzeria Karvak‰si Carlos<br>
Ratapihantie 13, 00520 Helsinki</span>
</footer>
</div>
</body>
</html>