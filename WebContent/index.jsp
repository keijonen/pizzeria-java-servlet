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

<title>Pizzeria Karvakäsi Carlos</title>
</head>
<body>
<%@ page import="java.util.*, kohdeluokat.*, javax.servlet.http.HttpSession" %>
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
<a class="sivulla" href="index.jsp">&#9679; Etusivu</a>
<a class="napit" href="Controller?action=haePizzat">&#9679; Pizzat</a>
<a class="napit"href="palvelu.jsp">&#9679; Palvelumme</a>
<a class="napit"href="yhteystiedot.jsp">&#9679; Yhteystiedot</a>
</div>




<script src= "slideshow/jquery-1.11.3.min.js"></script>
<script src= "slideshow/jquery.cycle.all.js"></script>
<script type="text/javascript">
$('#slider').cycle({ 
    fx:     'scrollHorz', 
    speed:  'slow', 
    next:   '#next', 
    prev:   '#prev' 
});
</script>


<div id="wrapper"> 
	<div id="container"> 
		<div class="controller" id="prev"></div>
		<div id="slider">
        <img src="kuvat/uudet/pizza_mainos1_edit.jpg" id="kuwa1" >
        <img src="kuvat/uudet/pizza_mainos2_edit.jpg" id="kuwa2">
        <img src="kuvat/uudet/pizza_mainos3_edit.jpg" id="kuwa3">
        </div>
        <div class="controller" id="next"></div>
    </div>
</div>

<article>
<section> 
<h2>Tervetuloa nauttimaan laadukkaista italialaisista pizzoistamme!</h2>

<p>
Meiltä voit tilata laadukkaat ja rakkaudella tehdyt pizzat kotiin tai tulla syömään paikan päälle upouuten ravintolaamme.
Meiltä lötyy pizzan eriskoistuokavalioille sekä vegaaneille! Tule mukaan nauttimaan herkkulisen maukkaista pizzoistamme!
<br>
<br>Yrityksemme on perustettu vuonna 2015 Pasilan sydämeen. Perustaja sekä omistaja Carlos Karvakäsi vaimoineen pitää huolen
pizzojen laadusta ja puhtaasta italialaisuudesta! </br>

<br>Nopeasti pizzat mukaan tai paikanpäällä!
</p>

</section>
<img src="kuvat/uudet/pizza_png_wub.png" id="pizza">
</article>
<footer>
<span class="valkoinen">Pizzeria Karvakäsi Carlos<br>
Ratapihantie 13, 00520 Helsinki</span>
</footer>
</div>

</body>
</html>

