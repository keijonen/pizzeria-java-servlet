package kohdeluokat;

/**
 * <code>TayteBean</code>-luokka kuvaa pizzerian myymän pizzan täytteitä.
 * <p>
 * Luokan tarkoitus on mallintaa pizzerian käyttämät täytteet.
 * 
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 *
 */
public class TayteBean {

	  private int id;		//Täytteen yksilöivä tunnus
	  private String nimi;	//Täytteen nimi
	  private double hinta; //Täytteen hinta per yksi (1) gramma.
	 
	  /**
	 * <code>TayteBean</code>-luokan parametriton konstruktori. Luo uuden <code>TayteBean</code> olion alla olevin oletusarvoin.
	 */
	public TayteBean(){
		  id = 0;
		  nimi = "";
		  hinta = 0.00;
	  }
	  
	/**
	 * <code>TayteBean</code>-luokan parametrillinen konstruktori. 
	 * <p>
	 * Luo uuden <code>TayteBean</code> olion annettujen parametrien mukaisesti.
	 * @param id täytteen id
	 * @param nimi täytteen nimi
	 * @param hinta täytteen hinta
	 */
	public TayteBean(int id, String nimi,double hinta)
	  {
	    this.id=id;
	    this.nimi=nimi;
	    this.hinta=hinta;	     
	  }

	/**
	 * @return täytteen id:n
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id tunnus, joka asetetaan täytteelle
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return täytteen nimen
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * @param nimi nimi, joka asetetaan täytteelle
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	/**
	 * @return täytteen hinnan
	 */
	public double getHinta() {
		return hinta;
	}

	/**
	 * @param hinta Täytteen hinta per yksi gramma
	 */
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TayteBean [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
	}
	  
	 
}
