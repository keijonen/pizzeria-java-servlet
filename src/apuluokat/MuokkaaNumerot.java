package apuluokat;

import java.math.BigDecimal;


/**
 * MuokkaaNumerot-luokka toimii kohdeluokkien matemaattisena apuluokkana.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 *@version 1.0
 */

public class MuokkaaNumerot {
	
	/**
	 * Tämä metodi pyöristää kahden desimaalin tarkkuudella saamansa arvon. Se saa String-luokan parametrin, jonka se muuttaa BigDecimal muotoon
	 * kahden desimaalin tarkkuudella.
	 * @param rivi Annettu numero String-luokan muodossa. 
	 * @return Muunnetun numeerisen arvon kahden desimaalin tarkkuudella.
	 */
	public static BigDecimal pyoristysKahdella(String rivi)
	{
				
	    BigDecimal paluu = new BigDecimal(rivi);
	    paluu = paluu.setScale(2, BigDecimal.ROUND_HALF_UP);
		return paluu;
	}
	
  

    /**
     * Tämä metodi suorittaa kertolaskun Integer-luokan olion ja BigDecimal-luokan olioiden välillä.
     * @param maara Integer luokan olio maara
     * @param hinta BigDecimal luokan olio hinta
     * @return kertolaskun tuloksen
     */
    public static BigDecimal bigDecimalMultiply(int maara, BigDecimal hinta)
    {
    	BigDecimal itemCost  = BigDecimal.ZERO;
    	BigDecimal totalCost = BigDecimal.ZERO;
        itemCost  = hinta.multiply(new BigDecimal(maara));
        totalCost = totalCost.add(itemCost);
        return totalCost.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

