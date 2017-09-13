package apuluokat;

/*import java.util.Scanner;*/
/**
 * MuokkaaMerkkijono-luokka toimii tekstinkäsittelyn apuluokkana.
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
	 * poistaa parametrista rivi sanojen välistä ylimääräiset 
	 *tyhjät 
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
	
	// poistaa parametrin rivi alusta ja lopusta tyhjät pois, sekä
	// muokka rivin siten,että sanojen väleissä on vain yksi tyhjämerkki
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

			if (rivi.length() > 0)// eihän rivi ole pelkkää tyhjää täynnä
			{
				lause = rivi;
				// toista niin kaun kuin kaksi tyhjää merkkiä esiintyy
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
	// 1) nimen sanojen välissä on tyhjämerkki, esim, seija mirjami
	// 2) nimen sanojen välissä on viiva, esim, riitta-leena
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
			vali = ' ';// ensin etsitään sanat, joiden välissä on blankko
			
			while (lkm < 2)// toistetaan kaksi kertaa
			{
				paluu = "";
				i = nimi.indexOf(vali); // etsitään sanojen väli
				while (i != -1)
				{
					sana= nimi.substring(0, i);// erota sana
					nimi = nimi.substring(i + 1);// poista sana
												 // nimestä
					// vie sana paluu-muuttujan loppuun ja laita sanan alkukrijain suureksi
					paluu = paluu + vali + sana.substring(0, 1).toUpperCase()
							+ sana.substring(1);

					i = nimi.indexOf(vali); // etsi seuraavien sanojen väli
				}
			
				// lisää vielä viimeinen sana
				paluu = paluu + vali + nimi.substring(0, 1).toUpperCase()
									+ nimi.substring(1);
				paluu = paluu.substring(1);
				nimi = paluu;
				lkm++;
				vali = '-'; // viimeisenä etsitään sanat, 
							// joiden välissä on -
			}
			
		}
		//System.out.println("sanatSuurilla: " + paluu);
		return paluu;
	}

	// Muokkaa parametrin rivi siten, että turhat tyhjät poistetaan 
	// rivin edestä, sanojen väleistä
	// ja rivin lopusta. Rivi alkaa suurella alkukirjaimella, 
	// loput kirjaimet ovat pieniä ja viimeisenä on piste.
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
			// entä jos lauseen piste on blankon jälkeen lauseen
			// viimeisestä sanasta
			lause = lause.replace(" .", ".");
			// lisää piste lauseen loppuun
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
	// muokkaa parametrin nimi, siten, että siitä poistetaan kaikki 
	// ylimääräiset tyhjät jokainen sanan välistä ja  
	// muutetaan siten, että jokainen sana alkaa suurella alkukirjaimella 
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
			// poista merkin - edessä oleva blankko
			nimi = nimi.replace(" -","-");
			// poista merkin - perässä oleva blankko
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
		lause = "   tÄMÄ        on   Lause. tämä on toinen lause, "
			+ "joka    päättyyy    pisteeseen     .    ";

		//lause = MuokkaaMerkkijono.sanatErikseenSuuriksi(lause);
		//System.out.println("Muokattu lause: " +lause);
		
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);
		lause = "  tämä on yksi lause .  ";
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);	
		lause = "    ";
		lause = MuokkaaMerkkijono.muokkaaLauseet(lause);
		//System.out.println(lause);	
		//
		/*
		System.out.println("Anna henkilön etunimi:");
		String etunimet = input.nextLine();

		etunimet = MuokkaaMerkkijonoB.muokkaaEtunimet(etunimet);
		System.out.println(etunimet);

		//
		System.out.println("Anna henkilön sukunimi");
		String sukunimet = input.nextLine();

		sukunimet = MuokkaaMerkkijonoB.muokkaaSukunimi(sukunimet);
		System.out.println(sukunimet);*/
	}


}
