
package kohdeluokat;

/**
 * <code>AsiakasBean</code>-luokka kuvaa pizzerian asiakasta.
 * <p>
 * Luokan tarkoitus on mallintaa pizzerian asiakas.
 * 
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.3
 *
 */
public class AsiakasBean {
	
	  private int id;					//Asiakkaan yksilöivä id-numero
	  private String etunimi;			//Asiakkaan etunimi
	  private String sukunimi;			//Asiakkaan sukunimi
	  private String osoite;			//Asiakkaan koko osoite. Käytetään vain tilauksiin, jotka tehdään ilman kirjautumista.
	  private String katuosoite;		//Asiakkaan katuosoite
	  private String postinumero;		//ASiakkaan postinumero
	  private String postitoimipaikka;	//Asiakkaan postitoimipaikka
	  private String mail;				//Asiakkaan sähköpostiosoite
	  private String puhelin;			//Aasiakkaan puhelinnumero. Toimii myös kirjautumisen tunnisteena.
	  private String salasana;			//Salasana, jolla asiakas kirjautuu palveluun
	  private String markkinointilupa;  //Saako asiakkaalle lähettää sähköpostiin markkinointiviestejä. Kylä (=on) ja Ei(=null).
	 

	/**
	 * <code>AsiakasBean</code>-luokan parametriton konstruktori. Luo uuden <code>AsiakasBean</code> olion alla olevin oletusarvoin.
	 */
	public AsiakasBean(){
		  id = 0;
		  etunimi = "";
		  sukunimi = "";
		  osoite = "";
		  katuosoite = "";
		  postinumero = "";
		  postitoimipaikka="";
		  mail = "";
		  puhelin = "";
		  salasana="";
		  markkinointilupa="";
	  }
	
	/**
	 * <code>AsiakasBean</code>-luokan parametrillinen konstruktori. 
	 * <p>
	 * Luo uuden <code>AsiakasBean</code> olion annettujen parametrien mukaisesti.
	 * @param etunimi Asiakkaan etunimi
	 * @param sukunimi Asiakkaan sukunimi
	 * @param katuosoite Asiakkaan katuosoite
	 * @param postinumero Asiakkaan postinumero
	 * @param postitoimipaikka Asiakkaan postitoimipaikka
	 * @param mail Asiakkaan sähköpostiosoite
	 * @param puhelin Asiakkaan puhelinnumero
	 * @param salasana Asiakkaan asiakkaan salasana
	 * @param markkinointilupa Asiakkaan myöntämä tai eväämä markkinointilupa
	 */
	public AsiakasBean(String etunimi, String sukunimi, String katuosoite,String postinumero,String postitoimipaikka,
			  String mail, String puhelin, String salasana, String markkinointilupa)
	  {
		  this.etunimi = etunimi;
		  this.sukunimi=sukunimi;
		  this.katuosoite = katuosoite;
		  this.postinumero=postinumero;
		  this.postitoimipaikka=postitoimipaikka;
		  this.mail = mail;
		  this.puhelin = puhelin;	
		  this.salasana=salasana;
		  this.markkinointilupa=markkinointilupa;
	  }
	
	/**
	 * <code>AsiakasBean</code>-luokan parametrillinen konstruktori. 
	 * <p>
	 * Luo uuden <code>AsiakasBean</code> olion annettujen parametrien mukaisesti.
	 * @param id Asiakkaan yksilöivä id-numero
	 * @param etunimi Asiakkaan etunimi
	 * @param sukunimi Asiakkaan sukunimi
	 * @param katuosoite Asiakkaan katuosoite
	 * @param mail Asiakkaan sähköpostiosoite
	 * @param puhelin Asiakkaan puhelinnumero
	 */
	public AsiakasBean(int id,String etunimi,String sukunimi, String katuosoite, String mail, String puhelin)
	  {
		  this.id = 0;
		  this.etunimi = etunimi;
		  this.sukunimi = sukunimi;
		  this.katuosoite = katuosoite;
		  this.mail = mail;
		  this.puhelin = puhelin;	     
	  }
	  
	/**
	 * <code>AsiakasBean</code>-luokan parametrillinen konstruktori. 
	 * <p>
	 * Luo uuden <code>AsiakasBean</code> olion annettujen parametrien mukaisesti.
	 * Tätä konstruktoria käytetään pizzan tilaukseen ilman sisään kirjautumista.
	 * @param etunimi Asiakkaan etunimi
	 * @param osoite Asiakkaan osoite
	 * @param mail Asiakkaan sähköpostiosoite
	 * @param puhelin Asiakkaan puhelinnumero
	 */	

	public AsiakasBean(String etunimi, String osoite, String mail, String puhelin)
	  {
		  this.etunimi = etunimi;
		  this.osoite = osoite;
		  this.mail = mail;
		  this.puhelin = puhelin;	     
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
	 * @return the etunimi
	 */

	public String getEtunimi() {
		return etunimi;
	}

	/**
	 * @param etunimi the etunimi to set
	 */

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	/**
	 * @return the sukunimi
	 */

	public String getSukunimi() {
		return sukunimi;
	}

	/**
	 * @param sukunimi the sukunimi to set
	 */

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	/**
	 * @return the osoite
	 */

	public String getOsoite() {
		return osoite;
	}

	/**
	 * @param osoite the osoite to set
	 */

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	/**
	 * @return the katuosoite
	 */

	public String getKatuosoite() {
		return katuosoite;
	}

	/**
	 * @param katuosoite the katuosoite to set
	 */

	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}

	/**
	 * @return the postinumero
	 */

	public String getPostinumero() {
		return postinumero;
	}

	/**
	 * @param postinumero the postinumero to set
	 */

	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}

	/**
	 * @return the postitoimipaikka
	 */

	public String getPostitoimipaikka() {
		return postitoimipaikka;
	}

	/**
	 * @param postitoimipaikka the postitoimipaikka to set
	 */

	public void setPostitoimipaikka(String postitoimipaikka) {
		this.postitoimipaikka = postitoimipaikka;
	}

	/**
	 * @return the mail
	 */

	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the puhelin
	 */

	public String getPuhelin() {
		return puhelin;
	}

	/**
	 * @param puhelin the puhelin to set
	 */

	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}

	/**
	 * @return the salasana
	 */

	public String getSalasana() {
		return salasana;
	}

	/**
	 * @param salasana the salasana to set
	 */

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	/**
	 * @return the markkinointilupa
	 */

	public String getMarkkinointilupa() {
		return markkinointilupa;
	}

	/**
	 * @param markkinointilupa the markkinointilupa to set
	 */

	public void setMarkkinointilupa(String markkinointilupa) {
		this.markkinointilupa = markkinointilupa;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "AsiakasBean [id=" + id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", osoite=" + osoite
				+ ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", postitoimipaikka="
				+ postitoimipaikka + ", mail=" + mail + ", puhelin=" + puhelin + ", salasana=" + salasana
				+ ", markkinointilupa=" + markkinointilupa + "]";
	}	  
}
