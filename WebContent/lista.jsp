<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isThreadSafe="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='shortcut icon' type='image/x-icon' href='kuvat/uudet/favicon.ico' />

<style type="text/css">
    <%@include file="/WEB-INF/css/pizzeria.css" %>
    </style>
    <!-- linkki google-fonttiin -->
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin napit-->
<link href='https://fonts.googleapis.com/css?family=Architects+Daughter' rel='stylesheet' type='text/css'>

<!-- linkki google-fonttiin leipÃ¤ teksti -->
<link href='https://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
<title>Pizzat</title>
</head>
<body>
<%@ page import="java.util.*, kohdeluokat.*" %>
<jsp:useBean id="asiakas" scope="session" class="kohdeluokat.AsiakasBean"/>

<div>

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
<a class="sivulla"href="Controller?action=haePizzat">&#9679; Pizzat</a>
<a class="napit"href="palvelu.jsp">&#9679; Palvelumme</a>
<a class="napit"href="yhteystiedot.jsp">&#9679; Yhteystiedot</a>
</div>

<article>

<h2> PIZZALISTA </h2>

<table>
  <tr>
    <th>Pizza</th>
    <th>Hinta</th>
    <th>Sisältö</th>
    <th>Tilaus</th>
  </tr>
   
  <c:forEach items="${haePizzat}"  var="pizza" >
  <tr>
    <td><c:out value="${pizza.nimi}" /></td>
    <td><c:out value="${pizza.hinta}" /></td>
    <td>  	
    <c:forEach items="${pizza.tayteLista}"  var="tayte" >
  	<c:out value="${tayte.nimi}" />&nbsp
  	</c:forEach> 
  	</td>

  	<td>
  	<form action="Controller" method="get">
  	<input type="checkbox" name="tabasco"/>Tabasco:<br>
  	<input type="checkbox" name="valkosipuli"/>Valkosipuli:<br>
  	<input type="checkbox" name="oregano"/>Oregano:<br>
  	 Määrä:<input type="number" min="1" name="maara"/><br>
  	 <br>
	<input type="hidden" name="nimi" value="${pizza.nimi}"/>
	<input type="hidden" name="hinta" value="${pizza.hinta}"/>
	 <input type="submit" name="action" value="Lisää ostoskoriin"/>
	 </form>
  	</td>
  </tr>
  </c:forEach>
</table>
<a id="ostoskoriin"href="ostoskori.jsp">
<img src="kuvat/uudet/ostoskoriin_nabu1.png" 
onmouseover="this.src='kuvat/uudet/ostoskoriin_nabu2.png'"
onmouseout="this.src='kuvat/uudet/ostoskoriin_nabu1.png'" /></a>

<a id="pizzalista" href="tiedostot/pizzalista.pdf" target="_blank">
<img src="kuvat/uudet/pizzalista_nabu1.png" 
onmouseover="this.src='kuvat/uudet/pizzalista_nabu2.png'"
onmouseout="this.src='kuvat/uudet/pizzalista_nabu1.png'" /></a>
<br>
<br>
</article>
<footer>
<span class="valkoinen">Pizzeria Karvakäsi Carlos<br>
Ratapihantie 13, 00520 Helsinki</span>
</footer>
</div>

</body>
</html>
