package kohdeluokat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <code>TilausriviBean</code>-luokka kuvaa pizzerian asiakkaan tekem‰‰ tilausrivi‰.
 * @author Juho
 *@author Asta
 *@author Tiia
 *@author Pasi
 *@version 1.5
 */
public class TilausriviBean implements java.io.Serializable {
	
	DecimalFormat f = new DecimalFormat("0.00");

	private static final long serialVersionUID = 1L;
	private String nimi;			//Pizzan nimi tilausriviss‰ @see kohdeluokat.PizzaBean
	private int maara;				//Pizzojen m‰‰r‰ nimen mukaisia pizzoja
	private BigDecimal hinta;		//Pizzan hinta
	private BigDecimal hintayht;	//Koko tilausrivin hinta (maara * hinta)
	private String tabasco;			//Tuleeko tilattuun pizzaan tabascoa? Kyll‰ (=on) ja Ei (=null)
	private String valkosipuli;		//Tuleeko tilattuun pizzaan valkosipulia? Kyll‰ (=on) ja Ei (=null)
	private String oregano;			//Tuleeko tilattuun pizzaan oreganoa? Kyll‰ (=on) ja Ei (=null)
	
	/**
	 * <code>TilausriviBean</code>-luokan parametriton konstruktori. Luo uuden <code>TilausriviBean</code> olion alla olevin oletusarvoin.
	 */
	public TilausriviBean(){
		nimi="";
		maara=0;
		hinta=BigDecimal.ZERO;;
		hintayht=apuluokat.MuokkaaNumerot.bigDecimalMultiply(maara,hinta);
		tabasco=null;
		valkosipuli=null;
		oregano=null;
	}
	
	/**
	 * <code>TilausriviBean</code>-luokan parametrillinen konstruktori. 
     * <p>
 	 * Luo uuden <code>TilausriviBean</code>-olion annettujen parametrien mukaisesti.
	 * @param nimi Pizzan nimi
	 * @param maara Pizzojen m‰‰r‰
	 * @param hinta	Pizzan hinta
	 * @param hintayht	Pizzojen hinta yhteens‰
	 */
	public TilausriviBean(String nimi, int maara, BigDecimal hinta,BigDecimal hintayht){
		this.nimi=nimi;
		this.maara=maara;
		this.hinta=hinta;
		this.hintayht=hintayht;
	}

	/**
	 * @return the nimi
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * @param nimi the nimi to set
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	/**
	 * @return the maara
	 */
	public int getMaara() {
		return maara;
	}

	/**
	 * @param maara the maara to set
	 */
	public void setMaara(int maara) {
		this.maara = maara;
	}

	/**
	 * @return the hinta
	 */
	public BigDecimal getHinta() {
		return hinta;
	}

	/**
	 * @param hinta the hinta to set
	 */
	public void setHinta(BigDecimal hinta) {
		this.hinta = hinta;
	}

	/**
	 * @return the hintayht
	 */
	public BigDecimal getHintayht() {
		return hintayht;
	}

	/**
	 * @param hintayht the hintayht to set
	 */
	public void setHintayht(BigDecimal hintayht) {
		this.hintayht = hintayht;
	}

	/**
	 * @return the tabasco
	 */
	public String getTabasco() {
		return tabasco;
	}

	/**
	 * @param tabasco the tabasco to set
	 */
	public void setTabasco(String tabasco) {
		this.tabasco = tabasco;
	}

	/**
	 * @return the valkosipuli
	 */
	public String getValkosipuli() {
		return valkosipuli;
	}

	/**
	 * @param valkosipuli the valkosipuli to set
	 */
	public void setValkosipuli(String valkosipuli) {
		this.valkosipuli = valkosipuli;
	}

	/**
	 * @return the oregano
	 */
	public String getOregano() {
		return oregano;
	}

	/**
	 * @param oregano the oregano to set
	 */
	public void setOregano(String oregano) {
		this.oregano = oregano;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TilausriviBean [nimi=" + nimi + ", maara=" + maara + ", hinta=" + hinta + ", hintayht=" + hintayht
				+ ", tabasco=" + tabasco + ", valkosipuli=" + valkosipuli + ", oregano=" + oregano + "]";
	}
	
	
}
