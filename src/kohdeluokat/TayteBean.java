package kohdeluokat;

/**
 * <code>TayteBean</code>-luokka kuvaa pizzerian myym�n pizzan t�ytteit�.
 * <p>
 * Luokan tarkoitus on mallintaa pizzerian k�ytt�m�t t�ytteet.
 * 
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @version 1.2
 *
 */
public class TayteBean {

	  private int id;		//T�ytteen yksil�iv� tunnus
	  private String nimi;	//T�ytteen nimi
	  private double hinta; //T�ytteen hinta per yksi (1) gramma.
	 
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
	 * @param id t�ytteen id
	 * @param nimi t�ytteen nimi
	 * @param hinta t�ytteen hinta
	 */
	public TayteBean(int id, String nimi,double hinta)
	  {
	    this.id=id;
	    this.nimi=nimi;
	    this.hinta=hinta;	     
	  }

	/**
	 * @return t�ytteen id:n
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id tunnus, joka asetetaan t�ytteelle
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return t�ytteen nimen
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * @param nimi nimi, joka asetetaan t�ytteelle
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	/**
	 * @return t�ytteen hinnan
	 */
	public double getHinta() {
		return hinta;
	}

	/**
	 * @param hinta T�ytteen hinta per yksi gramma
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
