package kohdeluokat;
import java.util.Date;
import java.util.ArrayList;

/**
 * <code>TilausBean</code>-luokka kuvaa pizzerian asiakkaan tekem‰‰ pizzatilausta.
 *
 * @author Juho
 *@author Asta
 *@author Tiia
 *@author Pasi
 *@version 1.3
 */
public class TilausBean {
private int id;							//Tilauksen yksilˆiv‰ id-numero.
private AsiakasBean asiakas;			//Tilauksen tehnyt asiakas. @see kohdeluokat.AsiakasBean
private Date tilausaika;				//Aika, jolloin tilaus on tehty.
private Date toimitusaika;				//Aika, jolloin tilaus halutaan toimitettavaksi.
private String toimitustapa;			//Tapa, jolla tilaus toimitetaan. Nouto (=n) tai kuljetus (=k).
private String toimitusosoite;			//Osoite, johon tilaus toimitetaan kun toimitustapa on kuljetus (=k).
private Double toimitusmaksu;			//Maksu, joka perit‰‰n tilauksen kotiinkuljetuksesta.
private String maksutilanne;			//Kertoo onko tilaus jo maksettu. Kyll‰ (=k) tai Ei (=e)
private String maksutapa;				//Kertoo tilauksen maksutavan. Kortti (=ko) tai K‰teinen (=ka)
private ArrayList<PizzaBean> pizzat;	//Lista tilatuista pizzoista. @see kohdeluokat.PizzaBean

/**
 * <code>TilausBean</code>-luokan parametriton konstruktori. Luo uuden <code>TilausBean</code> olion alla olevin oletusarvoin.
 */
public TilausBean(){
	id = 0;
	asiakas = null;
	tilausaika = new Date();
	toimitusaika = null;
	toimitustapa = "t";
	toimitusosoite = "";
	toimitusmaksu = 0.00;
	maksutilanne = "ei";
	maksutapa = "";
	pizzat = null;
}

/**
 * <code>TilausBean</code>-luokan parametrillinen konstruktori. 
 * <p>
 * Luo uuden <code>TilausBean</code> olion annettujen parametrien mukaisesti.
 * @param id tilauksen id
 * @param asiakas asiakas, joka tilauksen on tehnyt
 * @param tilausaika aika, jolloin tilaus on tehty
 * @param toimitusaika aika, jolloin tilaus toimitetaan asiakkaalle
 * @param toimitustapa tapa, jolla tilaus toimitetaan.
 * @param toimitusosoite Osoite, johon tilaus toimitetaan. 
 * @param pizzat Lista tilatuista pizzoista
 * @param toimitusmaksu Maksu, joka perit‰‰n pizzan kuljetuksesta asiakkaalle.
 * @param maksutilanne Kertoo, onko tilaus jo maksettu.
 * @param maksutapa Tapa, jolla tilaus maksetaan.
 */
public TilausBean(int id,AsiakasBean asiakas,Date tilausaika,Date toimitusaika, String toimitustapa, String toimitusosoite,
		ArrayList<PizzaBean> pizzat,double toimitusmaksu, String maksutilanne,String maksutapa){
	  this.id = id;
	  this.pizzat= pizzat;
	  this.asiakas=asiakas;
	  this.toimitustapa=toimitustapa;
	  this.toimitusosoite=toimitusosoite;
	  this.tilausaika= tilausaika;
	  this.toimitusaika= toimitusaika;
	  this.maksutapa=maksutapa;
	  this.maksutilanne=maksutilanne;
}

/**
 * <code>TilausBean</code>-luokan parametrillinen konstruktori. 
 * <p>
 * Luo uuden <code>TilausBean</code> olion annettujen parametrien mukaisesti.
 * @param id Tilauksen id-numero
 */
public TilausBean(int id){
	  this.id = id;
}

/**
 * <code>TilausBean</code>-luokan parametrillinen konstruktori. 
 * <p>
 * Luo uuden <code>TilausBean</code> olion annettujen parametrien mukaisesti.
 * @param asiakas Tilauksen tehnyt asiakas
 * @param toimitustapa Tapa, jolla tilaus toimitetaan.
 * @param toimitusosoite osoite, johon tilaus toimitetaan.
 */
public TilausBean(AsiakasBean asiakas,String toimitustapa, String toimitusosoite){
	  this.id = 0;
	  this.asiakas=asiakas;
	  this.toimitustapa=toimitustapa;
	  this.toimitusosoite=toimitusosoite;
	  tilausaika= new Date();
}

/**
 * @return the maksutapa
 */
public String getMaksutapa() {
	return maksutapa;
}

/**
 * @param maksutapa the maksutapa to set
 */
public void setMaksutapa(String maksutapa) {
	this.maksutapa = maksutapa;
}

/**
 * @return the pizzat
 */
public ArrayList<PizzaBean> getPizzat() {
	return pizzat;
}

/**
 * @param pizzat the pizzat to set
 */
public void setPizzat(ArrayList<PizzaBean> pizzat) {
	this.pizzat = pizzat;
}

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return the asiakas
 */
public AsiakasBean getAsiakas() {
	return asiakas;
}

/**
 * @param asiakas the asiakas to set
 */
public void setAsiakas(AsiakasBean asiakas) {
	this.asiakas = asiakas;
}

/**
 * @return the tilausaika
 */
public Date getTilausaika() {
	return tilausaika;
}

/**
 * @param tilausaika the tilausaika to set
 */
public void setTilausaika(Date tilausaika) {
	this.tilausaika = tilausaika;
}

/**
 * @return the toimitusaika
 */
public Date getToimitusaika() {
	return toimitusaika;
}

/**
 * @param toimitusaika the toimitusaika to set
 */
public void setToimitusaika(Date toimitusaika) {
	this.toimitusaika = toimitusaika;
}

/**
 * @return the toimitustapa
 */
public String getToimitustapa() {
	return toimitustapa;
}

/**
 * @param toimitustapa the toimitustapa to set
 */
public void setToimitustapa(String toimitustapa) {
	this.toimitustapa = toimitustapa;
}

/**
 * @return the toimitusosoite
 */
public String getToimitusosoite() {
	return toimitusosoite;
}

/**
 * @param toimitusosoite the toimitusosoite to set
 */
public void setToimitusosoite(String toimitusosoite) {
	this.toimitusosoite = toimitusosoite;
}

/**
 * @return the toimitusmaksu
 */
public Double getToimitusmaksu() {
	return toimitusmaksu;
}

/**
 * @param toimitusmaksu the toimitusmaksu to set
 */
public void setToimitusmaksu(Double toimitusmaksu) {
	this.toimitusmaksu = toimitusmaksu;
}

/**
 * @return the maksutilanne
 */
public String getMaksutilanne() {
	return maksutilanne;
}

/**
 * @param maksutilanne the maksutilanne to set
 */
public void setMaksutilanne(String maksutilanne) {
	this.maksutilanne = maksutilanne;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "TilausBean [id=" + id + ", asiakas=" + asiakas + ", tilausaika=" + tilausaika + ", toimitusaika="
			+ toimitusaika + ", toimitustapa=" + toimitustapa + ", toimitusosoite=" + toimitusosoite
			+ ", toimitusmaksu=" + toimitusmaksu + ", maksutilanne=" + maksutilanne + ", maksutapa=" + maksutapa
			+ ", pizzat=" + pizzat + "]";
}

}
