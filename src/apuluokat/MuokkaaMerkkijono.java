package apuluokat;

/*import java.util.Scanner;*/
/**
 * MuokkaaMerkkijono-luokka toimii tekstink�sittelyn apuluokkana.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 *@version 1.0
 *
 */
public class MuokkaaMerkkijono 
{

	/**
	 * poistaa parametrista rivi sanojen v�list� ylim��r�iset 
	 *tyhj�t 
	 * @param rivi
	 * @return
	 */
	public static String sanatErikseen(String rivi)
	{
		String sanat [];
		String paluu = null;
		
		if (rivi != null && rivi.trim().length() > 0)
		{
			rivi = rivi.trim();
		
			sanat = rivi.split(" ");
			paluu = "";
			for (int i=0;i<sanat.length;i++)
			{
				//System.out.println("sana " + i + ":"+ sanat[i]+":");
				if (sanat[i].length() > 0)
					paluu =paluu + " " + sanat[i];
				
			}
			paluu = paluu.substring(1,2).toUpperCase() + paluu.substring(2);
		}
		return paluu;
	}
	
	// poistaa parametrin rivi alusta ja lopusta tyhj�t pois, sek�
	// muokka rivin siten,ett� sanojen v�leiss� on vain yksi tyhj�merkki
	/**
	 * @param rivi
	 * @return
	 */
	private static String poistaTurhatTyhjat(String rivi) 
	{
		String lause = null;
		if (rivi != null) 
		{
			rivi = rivi.trim();// poista tyhjat rivin edesta ja lopusta

			if (rivi.length() > 0)// eih�n rivi ole pelkk�� tyhj�� t�ynn�
			{
				lause = rivi;
				// toista niin kaun kuin kaksi tyhj�� merkki� esiintyy
				// lauseessa
				while (lause.indexOf("  ") != -1)
					lause = lause.replace("  ", " ");

			}
		}
		//System.out.println("poistaTurhatTyhjat: " + lause);
		return lause;
	}
	// muuttaa parametrin nimi kaikki sanat alkamaan suurella alkukirjaimella
	// ottaa huomioon kaksi tilannetta:
	// 1) nimen sanojen v�liss� on tyhj�merkki, esim, seija mirjami
	// 2) nimen sanojen v�liss� on viiva, esim, riitta-leena
	/**
	 * @param nimi
	 * @return
	 */
	private static String sanatSuurilla(String nimi)
	{
		String paluu = null;
		String sana;
		int i;
		char vali = ' '; 
		int lkm = 0;
		if (nimi!= null)
		{
			vali = ' ';// ensin etsit��n sanat, joiden v�liss� on blankko
			
			while (lkm < 2)// toistetaan kaksi kertaa
			{
				paluu = "";
				i = nimi.indexOf(vali); // etsit��n sanojen v�li
				while (i != -1)
				{
					sana= nimi.substring(0, i);// erota sana
					nimi = nimi.substring(i + 1);// poista sana
												 // nimest�
					// vie sana paluu-muuttujan loppuun ja laita sanan alkukrijain suureksi
					paluu = paluu + vali + sana.substring(0, 1).toUpperCase()
							+ sana.substring(1);

					i = nimi.indexOf(vali); // etsi seuraavien sanojen v�li
				}
			
				// lis�� viel� viimeinen sana
				paluu = paluu + vali + nimi.substring(0, 1).toUpperCase()
									+ nimi.substring(1);
				paluu = paluu.substring(1);
				nimi = paluu;
				lkm++;
				vali = '-'; // viimeisen� etsit��n sanat, 
							// joiden v�liss� on -
			}
			
		}
		//System.out.println("sanatSuurilla: " + paluu);
		return paluu;
	}

	// Muokkaa parametrin rivi siten, ett� turhat tyhj�t poistetaan 
	// rivin edest�, sanojen v�leist�
	// ja rivin lopusta. Rivi alkaa suurella alkukirjaimella, 
	// loput kirjaimet ovat pieni� ja viimeisen� on piste.
	/**
	 * @param rivi
	 * @return
	 */
	public static String muokkaaLause(String rivi) 
	{
		int i;
		String lause = null;
		String sana;

		lause = poistaTurhatTyhjat(rivi); 
		if (lause != null) 
		{
			// eka kirjain suureksi, loput pieniksi
			lause = lause.substring(0, 1).toUpperCase()
						+ lause.substring(1).toLowerCase();
			// ent� jos lauseen piste on blankon j�lkeen lauseen
			// viimeisest� sanasta
			lause = lause.replace(" .", ".");
			// lis�� piste lauseen loppuun
			if (lause.charAt(lause.length() - 1) != '.')
				lause = lause + ".";

		}
		return lause;
	}
	/**
	 * @param kappale
	 * @return
	 */
	public static String muokkaaLauseet (String kappale)
	{
		int i;
		String paluu = null, lause;
		
		if (kappale != null && kappale.trim().length() > 0)
		{
			paluu = "";
			kappale = kappale.trim();
			if (kappale.length() > 0 && kappale.charAt( kappale.length() - 1 ) == '.')// OTa viimeinen . pois
			{
				kappale = kappale.substring(0,kappale.length()-1);
			}
			i = kappale.indexOf('.');
			while (i != -1)
			{
				lause = kappale.substring(0,i);
				
				kappale = kappale.substring(i+1);
				//System.out.println("lause: " + lause+"\nkappale:" + kappale);
				paluu = paluu + " " + muokkaaLause(lause);
				
				//System.out.println("paluu: " + paluu);
				
				i = i = kappale.indexOf('.');
			}
			paluu = paluu +" " +  muokkaaLause(kappale);// viimeinen lause
			paluu = paluu.trim();
			//System.out.println("paluu: " + paluu);
		}
		return paluu;
	}
	// muokkaa parametrin nimi, siten, ett� siit� poistetaan kaikki 
	// ylim��r�iset tyhj�t jokainen sanan v�list� ja  
	// muutetaan siten, ett� jokainen sana alkaa suurella alkukirjaimella 
	// esim. seija -  mirjami-    riitta   -leena liisa 
	//==> Seija-Mirjami-Riitta-Leena Liisa
	// tai pizza   napoletana ==> Pizza Napoletana
	/**
	 * @param nimi
	 * @return
	 */
	public static String muokkaaNimi(String nimi) {

		String paluu = null;
		
		if (nimi != null)
		{
			nimi = nimi.toLowerCase();
			nimi = poistaTurhatTyhjat(nimi);
			//System.out.println("tyhjat positettu: " + nimi);
			// poista merkin - edess� oleva blankko
			nimi = nimi.replace(" -","-");
			// poista merkin - per�ss� oleva blankko
			nimi = nimi.replace("- ","-");
			//System.out.println("-siivottu " + nimi);
			// Nimet suurella alkukirjaimilla
			paluu = sanatSuurilla(nimi);
		}
		return paluu;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Scanner input = new Scanner(System.in);
		String lause;
		//System.out.println("Anna lause");
		//lause = input.nextLine();
		lause = "   t�M�        on   Lause. t�m� on toinen lause, "
			+ "joka    p��ttyyy    pisteeseen     .    ";

		//lause = MuokkaaMerkkijono.sanatErikseenSuuriksi(lause);
		//System.out.println("Muokattu lause: " +lause);
		
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);
		lause = "  t�m� on yksi lause .  ";
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);	
		lause = "    ";
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);	
		//
		/*
		System.out.println("Anna henkil�n etunimi:");
		String etunimet = input.nextLine();

		etunimet = MuokkaaMerkkijonoB.muokkaaEtunimet(etunimet);
		System.out.println(etunimet);

		//
		System.out.println("Anna henkil�n sukunimi");
		String sukunimet = input.nextLine();

		sukunimet = MuokkaaMerkkijonoB.muokkaaSukunimi(sukunimet);
		System.out.println(sukunimet);*/
	}


}
