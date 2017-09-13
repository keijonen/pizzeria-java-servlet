package apuluokat;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Pvm-luokka toimii päivämäärien käsittelyn apuluokkana.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 *@version 1.0
 */
public class Pvm extends GregorianCalendar {


	private static final long serialVersionUID = 1L;

	// muodostimet
	// oletusmuodostin
	/**
	 * 
	 */
	public Pvm() {
		super(); // nykypäivä
	}

	// parametrill. muodostin
	/**
	 * @param pp
	 * @param kk
	 * @param vv
	 */
	public Pvm(int pp, int kk, int vv) {
		super(vv, kk - 1, pp);
	}

	// kopiointimuodostin
	/**
	 * @param olio
	 */
	public Pvm(Pvm olio) {
		super();
		if (olio != null)
			super.set(olio.getVuosi(), 
					(olio.getKuukausi() - 1), 
					olio.getPaiva());

	}
	// partametri pvm on muodossa 2010-04-16 00:00:00.0
	/**
	 * @param pvm
	 * @param oracle
	 */
	public Pvm (String pvm, boolean oracle)
	{
		this();
		int i, pp,kk,vv;
		if (pvm != null && pvm.matches(
				"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{1}") )
		{
			i = pvm.indexOf('-');
			vv = Integer.parseInt(pvm.substring(0,i));
			pvm = pvm.substring(i+1);
			i = pvm.indexOf('-');
			kk= Integer.parseInt(pvm.substring(0,i));
			pvm=pvm.substring(i+1);
			i = pvm.indexOf(' ');
			pp = Integer.parseInt(pvm.substring(0,i));
			super.set(vv, kk-1, pp);
		}
	}
	// partametri pvm on muodossa 2010-04-16 
	/**
	 * @param pvm
	 */
	public Pvm (String pvm)
	{
		this();
		int i, pp,kk,vv;
		if (pvm != null && pvm.trim().matches(
				"\\d{4}-\\d{2}-\\d{2}") )
		{
			pvm = pvm.trim();
			i = pvm.indexOf('-');
			vv = Integer.parseInt(pvm.substring(0,i));
			pvm = pvm.substring(i+1);
			i = pvm.indexOf('-');
			kk= Integer.parseInt(pvm.substring(0,i));
			pp = Integer.parseInt(pvm.substring(i+1));
			super.set(vv, kk-1, pp);
		}
	}
	/**
	 * @return
	 */
	public int getPaiva() {
		return super.get(Calendar.DATE);
	}

	/**
	 * @return
	 */
	public int getKuukausi() {
		return super.get(Calendar.MONTH) + 1;
	}

	/**
	 * @return
	 */
	public int getVuosi() {
		return super.get(Calendar.YEAR);
	}

	/**
	 * @param pp
	 */
	public void setPaiva(int pp) {
		int[] kuut = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (pp > 0 && pp <= kuut[getKuukausi()])
			super.set(Calendar.DATE, pp);
	}

	/**
	 * @param kk
	 */
	public void setKuukausi(int kk) {
		if (kk > 0 && kk <= 12)
			super.set(Calendar.MONTH, kk - 1);
	}

	/**
	 * @param vv
	 */
	public void setVuosi(int vv) {
		if (vv > 0)
			super.set(Calendar.YEAR, vv);
	}

	/**
	 * @param pp
	 * @param kk
	 * @param vv
	 */
	public void setPvm(int pp, int kk, int vv) {
		super.set(vv, kk - 1, pp);
	}

	/**
	 * @param arvo
	 */
	public void lisaaPaiva(int arvo) {
		super.add(Calendar.DATE, arvo);
	}

	/**
	 * @param arvo
	 */
	public void lisaaKuukausi(int arvo) {
		super.add(Calendar.MONTH, arvo);
	}

	/**
	 * @param arvo
	 */
	public void lisaaVuosi(int arvo) {
		super.add(Calendar.YEAR, arvo);
	}

	/* (non-Javadoc)
	 * @see java.util.Calendar#toString()
	 */
	public String toString() {
		return super.get(Calendar.DATE) + "." + (super.get(Calendar.MONTH) + 1)
				+ "." + super.get(Calendar.YEAR);

	}

} // end of class Pvm


