package kohdeluokat;

import java.util.ArrayList;
import apuluokat.MuokkaaMerkkijono;
import apuluokat.Pvm;

/**
 * <code>PizzaBean</code>-luokka kuvaa pizzerian myym‰‰ pizzaa.
 * <p>
 * Luokan tarkoitus on mallintaa pizzerian myym‰ pizza t‰ytteineen.
 * 
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.3
 *
 */
public class PizzaBean 
{
  private int tunnus;						//Pizzan yksilˆiv‰ tunnus.
  private String nimi;						//Pizzan nimi
 private double hinta;						//Pizzan hinta
  private Pvm poistoPvm;					//P‰iv‰m‰‰r‰, jolloin pizza poistuu myynnist‰
  private String tabasco;					//Tuleeko tilattuun pizzaan tabascoa? Kyll‰ (=on) ja Ei (=null)
  private String valkosipuli;				//Tuleeko tilattuun pizzaan valkosipulia? Kyll‰ (=on) ja Ei (=null)
  private String oregano;					//Tuleeko tilattuun pizzaan oreganoa? Kyll‰ (=on) ja Ei (=null)
  private ArrayList<TayteBean> tayteLista;	//Lista t‰ytteist‰, jotka kuuluvat pizzaan. @see kohdeluokat.TayteBean
 
	/**
	 * <code>PizzaBean</code>-luokan parametriton konstruktori. Luo uuden <code>PizzaBean</code> olion alla olevin oletusarvoin.
	 */

public PizzaBean(){
	  tunnus = 0;
	  nimi="";
	  hinta = 0;
	  poistoPvm = null;
	  tayteLista = null;
	  tabasco = null;
	  valkosipuli=null;
	  oregano=null;
  }

/**
 * <code>PizzaBean</code>-luokan parametrillinen konstruktori. 
 * <p>
 * Luo uuden <code>PizzaBean</code> olion annettujen parametrien mukaisesti.
 * @param tunnus pizzan tunnus
 * @param nimi pizzan nimi
 * @param hinta pizzan hinta
 * @param pvm p‰iv‰m‰‰r‰ jolloin pizza poistuu myyntilistalta
 * @param tayteLista lista pizzan sis‰lt‰misat‰ t‰ytteist‰
 */
public PizzaBean(int tunnus, String nimi,double hinta, Pvm pvm,ArrayList<TayteBean> tayteLista)
  {
     setTunnus(tunnus);
     setNimi(nimi);
     setTayteLista (tayteLista);
     setHinta(hinta);
     setPoistoPvm (pvm);
    
     
  }
  
/**
 * <code>PizzaBean</code>-luokan parametrillinen konstruktori. 
 * <p>
 * Luo uuden <code>PizzaBean</code> olion annettujen parametrien mukaisesti.
 * @param tunnus pizzan tunnus
 * @param nimi pizzan nimi
 * @param hinta pizzan hinta
 * @param pvm p‰iv‰m‰‰r‰ jolloin pizza poistuu myyntilistalta
 */
  public PizzaBean(int tunnus, String nimi,double hinta, Pvm pvm)
  {
     setTunnus(tunnus);
     setNimi(nimi);
     setHinta( hinta);
     setPoistoPvm (pvm);     
  }
  
  /**
   * <code>PizzaBean</code>-luokan parametrillinen konstruktori. 
   * <p>
   * Luo uuden <code>PizzaBean</code> olion annettujen parametrien mukaisesti.
   * @param tunnus pizzan tunnus
   * @param nimi pizzan nimi
   * @param hinta pizzan hinta
   * @param pvm p‰iv‰m‰‰r‰ jolloin pizza poistuu myyntilistalta
   */
  public PizzaBean(int tunnus, String nimi,String hinta, Pvm pvm)
  {
     setTunnus(tunnus);
     setNimi(nimi);
     setHinta(hinta);
     setPoistoPvm(pvm);
  }
  
  /**
   * <code>PizzaBean</code>-luokan parametrillinen konstruktori. 
   * <p>
   * Luo uuden <code>PizzaBean</code> olion annettujen parametrien mukaisesti.
   * @param tunnus pizzan tunnus
   */
  public PizzaBean (String tunnus)
  {
	  setTunnus(tunnus);
	  nimi = null;
	  hinta =0;
  }
  
  

/**
 * @param tunnus oliolle asetettava tunnus
 */
public void setTunnus(int tunnus)
  {
	  this.tunnus = 0;
	  if (tunnus > 0)
		  this.tunnus = tunnus;
  }

/**
 * @param tunnus oliolle asetettava tunnus
 */
  public void setTunnus(String tunnus)
  {
	  this.tunnus = 0;
	  if (tunnus != null)
	  {
		  try
		  {
			  this.tunnus = Integer.parseInt(tunnus);
		  }
		  catch (Exception e)
		  {
			  this.tunnus = 0;
		  }
	  }
  }
  

  /**
 * @return t‰m‰n tunnuksen
 */
public int getTunnus()
  {
     return tunnus;
  }

 /**
 * @param nimi asettaa oliolle nimen
 */
public void setNimi(String nimi)
  {
     this.nimi = MuokkaaMerkkijono.sanatErikseen(nimi);
  }


  /**
 * @return olion nimen
 */
public String getNimi()
  {
     return nimi;
  }

  /**
 * @param hinta hinta joka asetetaan oliolle
 */
public void setHinta(double hinta)
  {
	  this.hinta = 0;
	  if (hinta > 0)
	  {
		  this.hinta = hinta;
	  }
  }
  /**
 * @param hinta hinta joka asetetaan oliolle
 */
public void setHinta(String hinta)
  {
	 double apu;

	 try
	 {
	  	 if (hinta!= null && hinta.trim().length()>0)
	   	 {
	   		 hinta = hinta.trim();
	   		// hinta.replace(',','.');
	   		 int i= hinta.indexOf(',');
	   		 System.out.println("hinta: " + hinta);
	   		 if (i != -1)
	   		 {
	   			 hinta = hinta.substring(0,i)+"." + hinta.substring (i+1);
	   			 System.out.println("hinta: " + hinta);
	   		 }
	   		 
	   		 apu = Double.parseDouble(hinta);
	   		 setHinta(apu);
	   	 }
	  	 else
	  		 this.hinta = 0;
	 }
	 catch (Exception e)
	 {
	   	 this.hinta  = 0;
	 } 
  }
  // palauttaa hinnna desimaalipilkulla
  /**
 * @return pizzan hinnan muokattuna String-luokan oliona
 */
public String getHinta()
  {
	String paluu = "" + hinta;
	int i =paluu.indexOf('.');
	if (i != -1)
		paluu = paluu.substring (0,i)+","+paluu.substring(i+1);
	i = paluu.indexOf(',');
	if(paluu.substring(i+1).length()==1)
		paluu = paluu+"0";
	return paluu;
  }
  /**
 * @return pizzan hinnan
 */
public double getHintaB()
  {
	return hinta;
  }
  
  
  /**
 * @return p‰iv‰m‰‰r‰n, jolloin pizza poistuu listalta
 */
public Pvm getPoistoPvm()
  {
	  Pvm paluu=null;
	  if (poistoPvm != null)
		  paluu = new Pvm (poistoPvm);
	  return paluu;
  }
/**
 * @param poistoPvm P‰iv‰m‰‰r‰, jolloin pizza poistuu listalta
 */
public void setPoistoPvm(Pvm poistoPvm) {
	this.poistoPvm = null;
	if (poistoPvm != null)
		this.poistoPvm = new Pvm(poistoPvm);
}

/**
 * @return oreganon arvon
 */
public String getOregano() {
	return oregano;
}
/**
 * @param oregano asettaa oreganon arvon
 */
public void setOregano(String oregano) {
	this.oregano = oregano;
}
/**
 * @return tabascon arvon
 */
public String getTabasco() {
	return tabasco;
}
/**
 * @param tabasco asettaa tabascon arvon
 */
public void setTabasco(String tabasco) {
	this.tabasco = tabasco;
}
/**
 * @return valkosipulin arvon
 */
public String getValkosipuli() {
	return valkosipuli;
}
/**
 * @param valkosipuli asettaa valkosipulin arvon
 */
public void setValkosipuli(String valkosipuli) {
	this.valkosipuli = valkosipuli;
}

/**
 * @return pizzan t‰ytelistan
 */
public ArrayList<TayteBean> getTayteLista() {
	return tayteLista;
}
/**
 * @param tayteLista asettaa pizzan t‰ytelistan
 */
public void setTayteLista(ArrayList<TayteBean> tayteLista) {
	this.tayteLista = tayteLista;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "PizzaBean [tunnus=" + tunnus + ", nimi=" + nimi + ", hinta=" + hinta + ", poistoPvm=" + poistoPvm
			+ ", tabasco=" + tabasco + ", valkosipuli=" + valkosipuli + ", oregano=" + oregano + ", tayteLista="
			+ tayteLista + "]";
}
  
}
