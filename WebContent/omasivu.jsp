<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page isThreadSafe="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='shortcut icon' type='image/x-icon' href='kuvat/uudet/favicon.ico' />

<!-- linkki ulkoiseen tyylitiedostoon  -->
<style type="text/css">
    <%@include file="/WEB-INF/css/pizzeria.css" %>
    </style>

<!-- linkki google-fonttiin -->
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin napit-->
<link href='https://fonts.googleapis.com/css?family=Architects+Daughter' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin leipÃ¤ teksti -->
<link href='https://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>

<title>Oma sivu</title>
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
<span class="valkoinen"><%out.print("Olet kirjautunut nimellä: "+asiakas.getEtunimi()+" "+asiakas.getSukunimi()); %><br></br></span>
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
<a id="otsikkolinkki" href="index.jsp"><h1>Pizzeria Karvakäsi Carlos</h1>
</header>

<div class="napit">
<a class="napit"href="index.jsp">&#9679; Etusivu</a>
<a class="napit"href="Controller?action=haePizzat">&#9679; Pizzat</a>
<a class="napit"href="palvelu.jsp">&#9679; Palvelumme</a>
<a class="napit"href="yhteystiedot.jsp">&#9679; Yhteystiedot</a>
</div>


<article>
<h2>Käyttäjätiedot: </h2>
<form action="Controller" method="get">

	Etunimi:<br>
	<input type="text"	name="etunimi" minlength="2" value="<%=asiakas.getEtunimi()%>" required><br>
	Sukunimi:<br>
	<input type="text"	name="sukunimi" minlength="2" value="<%=asiakas.getSukunimi() %>" required><br>
	Katuosoite:<br>
	<input type="text"	name="katuosoite" minlength="6" value="<%=asiakas.getKatuosoite()%>" required><br>
	Postinumero:<br>
	<input type="text"	name="postinumero" minlength="5" maxlength="5" value="<%=asiakas.getPostinumero()%>" required><br>
	Postitoimipaikka:<br>
	<input type="text"	name="postitoimipaikka" minlength="2" value="<%=asiakas.getPostitoimipaikka()%>" required><br>
	Puhelin:<br>
	<input type="number" minlength="6" maxlength="10" title="Anna vähintään 6 numeroa, enintään 10"	name="puhelin" value="<%=asiakas.getPuhelin()%>" required><br>
	Sähköposti:<br>
	<input type="email"	name="mail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Anna sähköposti oikeassa muodossa." value="<%=asiakas.getMail()%>" required><br>
	<br><input type="checkbox" name="markkinointilupa"></input>Sähköpostiosoitteeseeni saa lähettää tarjouksia ja muita uutisia. <br>
	<br><input type="submit" name="action" value="Muuta asiakastietoja"/><br></br>
	
</form>
</article>
<footer>
<p class="valkoinen">Pizzeria Karvakäsi Carlos<br>
Ratapihantie 13, 00520 Helsinki</>
</footer>
</div>




</body>
</html>
