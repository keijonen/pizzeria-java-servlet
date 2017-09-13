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

<!-- linkki google-fonttiin leipÃ¤ teksti -->
<link href='https://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
<title>Virhe</title>
</head>
<body>
<jsp:useBean id="asiakas" scope="session" class="kohdeluokat.AsiakasBean"/>
<%if(asiakas.getId() == 0){ %>

<%}%>
<%if(asiakas.getId() != 0){ %>
<div class="ylapalkki">
<p class="valkoinen"><%out.print("Olet kirjautunut nimellä: "+asiakas.getEtunimi()+" "+asiakas.getSukunimi()); %></p>
<form action="Controller" method="get">
<input type="submit" name="action" value="Kirjaudu ulos"/>
</form>

</div>
<%}%>
<br>
<span class="valkoinen">Käyttäjätunnuksen muokkaus epäonnistui. Yritä uudelleen tai palaa alkuun.</span><br>
<nav>
<br>
<br>
<a href="Controller?action=alkuun">
<img src="kuvat/uudet/alkuun_nabu1.png" 
onmouseover="this.src='kuvat/uudet/alkuun_nabu2.png'"
onmouseout="this.src='kuvat/uudet/alkuun_nabu1.png'"/></a>

<a href="omasivu.jsp">
<img src="kuvat/uudet/yritauudelleen_nabu1.png" 
onmouseover="this.src='kuvat/uudet/yritauudelleen_nabu2.png'"
onmouseout="this.src='kuvat/uudet/yritauudelleen_nabu1.png'"/></a>

</nav>

</body>
</html>