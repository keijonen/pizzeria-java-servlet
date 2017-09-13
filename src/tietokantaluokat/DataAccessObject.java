package tietokantaluokat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import apuluokat.Pvm;
import kohdeluokat.*;

/**
 * DataAccessObject-luokka toimii tietokannan ohjausluokkana.
 * @author Juho
 * @author Asta
 * @author Tiia
 * @author Pasi
 * @author unknown?
 * @version 1.7
 */
public class DataAccessObject {

	private Database db;
	
	/** 
	 * Muodostaa database-luokan olion DataAccessObject-luokan käyttöön. 
	 * @see tietokantaluokat.database
	 */

	public DataAccessObject() {
		db = new Database();
	}

	/**
	 * Tämä metodi hakee asiakkaan tietokannasta annetuilla parametreilla.
	 * @param etunimi
	 * @param osoite
	 * @param puhelin
	 * @param mail
	 * @return Asiakkaan tiedot
	 * @throws SQLException Tietokanta-virhe.
	 * @see kohdeluokat.AsiakasBean
	 */
	public AsiakasBean haeAsiakas(String etunimi,String osoite,String puhelin,String mail)
			throws SQLException
	{
		System.out.println("** haeAsiakas()");
		AsiakasBean asiakas = null;
		String sql = "SELECT * FROM asiakas WHERE asiakas_etunimi='"+etunimi+"' AND asiakas_osoite='"+osoite+"' AND asiakas_puh='"+puhelin+"';";
		ArrayList<HashMap> lista;
		
		try
		{
			if (db.yhdista())
			{
				lista = db.suoritaKysely(sql);
				db.katkaise();
				if (lista != null)
				{
					asiakas = muodostaAsiakasOlio(lista);
				}
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		System.out.println("** haeAsiakas(): ASIAKAS HAETTU");
		return asiakas;
	}
	
	
	/**
	 * Tämän metodin avulla tarkistetaan asiakkaan sisäänkirjautumistiedot.
	 * @param puhelin Asiakkaan puhelinnumero
	 * @param salasana Asiakkaan salasana
	 * @return Asiakkaan tiedot
	 * @throws SQLException
	 * @see kohdeluokat.AsiakasBean
	 */
	public AsiakasBean kirjauduSisaan (String puhelin,String salasana)
			throws SQLException
	{
		System.out.println("** kirjauduSisaan()");
		AsiakasBean asiakas = null;
		String sql = "SELECT * FROM asiakas WHERE asiakas_puh='"+puhelin+"'AND asiakas_salasana='"+salasana+"';";
		ArrayList<HashMap> lista;
		
		try
		{
			if (db.yhdista())
			{
				lista = db.suoritaKysely(sql);
				db.katkaise();
				if (lista != null)
				{
					asiakas = muodostaAsiakasOlio(lista);
				}else{
					asiakas=null;
				}
	
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		System.out.println("** haeAsiakas(): ASIAKAS HAETTU");
		return asiakas;
	}
	
	/**
	 * Tämän metodin avulla asiakkaan luoma käyttäjätunnus viedään tietokantaan.
	 * @param asiakas
	 * @return ok true tai false. True = vienti onnistui.
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.AsiakasBean
	 */
	public boolean luoTunnus (AsiakasBean asiakas)
			throws SQLException
		{
		System.out.println("** luoTunnus()");

			String sql;
			int lkm; 
			boolean ok =false;
			
			if (asiakas != null)
			{
				String etunimi = asiakas.getEtunimi();
				String sukunimi = asiakas.getSukunimi();
				String katuosoite = asiakas.getKatuosoite();
				String postinumero = asiakas.getPostinumero();
				String postitoimipaikka = asiakas.getPostitoimipaikka();
				String salasana = asiakas.getSalasana();
				String mail = asiakas.getMail();
				String puhelin = asiakas.getPuhelin();
				String markkinointilupa = asiakas.getMarkkinointilupa();
				sql = "INSERT INTO asiakas (asiakas_etunimi,asiakas_sukunimi,asiakas_katuosoite,asiakas_postinumero,"
						+ "asiakas_postitoimipaikka,asiakas_salasana,asiakas_mail,asiakas_puh,asiakas_markkinointilupa)"+
				" VALUES ('"+etunimi+"','"+sukunimi+"','"+katuosoite+"','"+postinumero+"','"+postitoimipaikka+"','"+salasana+"','"+mail+"','"+puhelin+"','"+markkinointilupa+"');";
				System.out.println("ASIAKKAAN LUONTILAUSE: "+sql);
				try
				{
					if (db.yhdista() && db.aloitaTransaktio() )
					{
						lkm = db.suoritaPaivitys(sql);
						if (lkm == 1)
						{
							db.commit();
							db.katkaise();
							System.out.println("** lisaaAsiakas(): ASIAKAS LISÄTTY");
							ok = true;
											
						}
						else
						{
							db.rollback();
							db.katkaise();
							System.out.println("** lisaaAsiakas(): ASIAKKAAN LISÄÄMINEN EPÄONNISTUI");
							ok = false;
						}
					}
					else
						throw new SQLException();
				}
				catch (SQLException e)
				{
					throw e;
				}
			
			}
			return ok;
			
		}
	
	/**
	 * Tämä metodi muodostaa AsiakasBean-olion parametrina saamastaan listasta.
	 * @param lista Lista asiakastietoja.
	 * @return Asiakkaan tiedot.
	 * @throws SQLException Tietokanta-virhe.
	 * @see kohdeluokat.AsiakasBean
	 */
	private AsiakasBean muodostaAsiakasOlio (ArrayList<HashMap> lista) throws SQLException
	{
		AsiakasBean asiakas = null;
		HashMap<String,String> map;
		Iterator<HashMap> it;

		it=lista.iterator();
		if (it.hasNext())
		{
			map = it.next();
			
			asiakas = luoAsiakasOlio(map);
		}
		return asiakas;
	}
	/**
	 * Tämä metodi luo AsiakasBean-olion parametrina saamastaan tiedosta.
	 * @param map Asiakkaan tiedot
	 * @return asiakas Asiakkaan tiedot
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.AsiakasBean
	 */
	private AsiakasBean luoAsiakasOlio(HashMap<String,String> map) throws SQLException
	{
		System.out.println("** luoAsiakasOlio()");

		int tunnus;
		String etunimi;
		String sukunimi;
		String osoite;
		String katuosoite;
		String postinumero;
		String postitoimipaikka;
		String mail;
		String puhelin;
		String markkinointilupa;
		AsiakasBean asiakas = new AsiakasBean();
		
		tunnus =Integer.parseInt(map.get("asiakas_id"));
		etunimi = map.get("asiakas_etunimi");
		sukunimi = map.get("asiakas_sukunimi");
		/**sisalto = map.get("sisalto");*/
		osoite = map.get("asiakas_osoite");
		katuosoite = map.get("asiakas_katuosoite");
		postinumero = map.get("asiakas_postinumero");
		postitoimipaikka = map.get("asiakas_postitoimipaikka");
		mail = map.get("asiakas_mail");
		puhelin = map.get("asiakas_puh");
		markkinointilupa = map.get("asiakas_markkinointilupa");
		asiakas.setId(tunnus);
		asiakas.setEtunimi(etunimi);
		asiakas.setSukunimi(sukunimi);
		asiakas.setKatuosoite(katuosoite);
		asiakas.setOsoite(osoite);
		asiakas.setPostinumero(postinumero);
		asiakas.setPostitoimipaikka(postitoimipaikka);
		asiakas.setMail(mail);
		asiakas.setPuhelin(puhelin);
		asiakas.setMarkkinointilupa(markkinointilupa);
		return asiakas;
	}
	/**
	 * Lisää asiakkaan tietokantaan.
	 * @param asiakas Asikkaan tiedot
	 * @return jees true tai false. true=Vienti onnistui
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.AsiakasBean
	 */
	public boolean lisaaAsiakas (AsiakasBean asiakas)
			throws SQLException
		{
		System.out.println("** lisaaAsiakas()");

			String sql;
			int lkm; 
			boolean jees =false;
			
			if (asiakas != null)
			{
				String etunimi = asiakas.getEtunimi();
				String osoite = asiakas.getOsoite();
				String mail = asiakas.getMail();
				String puhelin = asiakas.getPuhelin();
				sql = "INSERT INTO asiakas (asiakas_etunimi,asiakas_osoite,asiakas_mail,asiakas_puh)"+
				" VALUES ('"+etunimi+"','"+osoite+"','"+mail+"','"+puhelin+"');";
				try
				{
					if (db.yhdista() && db.aloitaTransaktio() )
					{
						lkm = db.suoritaPaivitys(sql);
						if (lkm == 1)
						{
							db.commit();
							db.katkaise();
							System.out.println("** lisaaAsiakas(): ASIAKAS LISÄTTY");
							jees = true;
											
						}
						else
						{
							db.rollback();
							db.katkaise();
							System.out.println("** lisaaAsiakas(): ASIAKKAAN LISÄÄMINEN EPÄONNISTUI");
							jees = false;
						}
					}
					else
						throw new SQLException();
				}
				catch (SQLException e)
				{
					throw e;
				}
			
			}
			return jees;
			
		}
	
	/**
	 * Tämä metodi muuttaa aiemmin luodun käyttäjätunnuksen tietoja.
	 * @param asiakas Asiakkaan tiedot
	 * @return jees true tai false. true = Päivitys onnistui.
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.AsiakasBean
	 */
	public boolean muutaTunnusta (AsiakasBean asiakas)
			throws SQLException
		{
		System.out.println("** muutaTunnusta()");

			String sql;
			int lkm; 
			boolean jees =false;
			
			if (asiakas.getId() != 0)
			{
				int id = asiakas.getId();
				String etunimi = asiakas.getEtunimi();
				String sukunimi = asiakas.getSukunimi();
				String osoite = asiakas.getKatuosoite();
				String postinumero = asiakas.getPostinumero();
				String postitoimipaikka = asiakas.getPostitoimipaikka();
				String mail = asiakas.getMail();
				String puhelin = asiakas.getPuhelin();
				String markkinointilupa = asiakas.getMarkkinointilupa();
								
				sql= "UPDATE asiakas SET asiakas_etunimi = '"+etunimi+"',asiakas_sukunimi= '"+sukunimi+"',asiakas_katuosoite= '"+osoite+"',asiakas_postinumero= '"+postinumero+"',asiakas_postitoimipaikka= '"+postitoimipaikka+"',asiakas_mail= '"+mail+"',asiakas_puh= '"+puhelin+"',asiakas_markkinointilupa= '"+markkinointilupa+"' WHERE asiakas_id="+id+";";
				
				
				try
				{
					if (db.yhdista() && db.aloitaTransaktio() )
					{
						lkm = db.suoritaPaivitys(sql);
						if (lkm == 1)
						{
							db.commit();
							db.katkaise();
							System.out.println("** muutaTietoja(): TIETOJA MUUTETTU");
							jees = true;
											
						}
						else
						{
							db.rollback();
							db.katkaise();
							System.out.println("** muutaTietoja(): TIETOJEN MUUTTAMINEN EPÄONNISTUI");
							jees = false;
						}
					}
					else
						throw new SQLException();
				}
				catch (SQLException e)
				{
					throw e;
				}
			
			}
			return jees;
			
		}
	/**
	 * Lisää tilauksen tietokantaan.
	 * @param tilaus Tilauksen tiedot
	 * @return ok true tai false, true = Vienti onnistui
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.TilausBean
	 */
	public boolean lisaaTilaus (TilausBean tilaus,ArrayList<PizzaBean> list)
			throws SQLException
		{
		System.out.println("** dao.lisaaTilaus();");
			String sql,sql2;
			int lkm,lkm2; 
			boolean ok =false;


				//Viedään asiakas tietokantaan ja haetaan sen yksilöivä asiakas_id tietokannasta.
				AsiakasBean asiakas = tilaus.getAsiakas();
				String osoite;
				int asiakas_id;
				
				if(asiakas.getId()==0){
				System.out.print("LISÄTÄÄN ASIAKAS TIETOKANTAAN.");
				lisaaAsiakas(asiakas);
				String etunimi=asiakas.getEtunimi();
				osoite=asiakas.getOsoite();
				System.out.print("Asiakkaan osoite: "+osoite);

				String puhelin=asiakas.getPuhelin();
				String mail=asiakas.getMail();
				System.out.print("HAETAAN ASIAKAS TIETOKANNASTA.");
				AsiakasBean asiakasSQL = haeAsiakas(etunimi,osoite,puhelin,mail);
				System.out.print("Asiakkaan tostring: "+asiakasSQL.toString());

				asiakas_id = asiakasSQL.getId();
				String toimitustapa = tilaus.getToimitustapa();
				sql = "INSERT INTO tilaus (asiakas_id,toimitustapa,toimitusosoite)"+
				" VALUES ("+asiakas_id+",'"+toimitustapa+"','"+osoite+"');";
				System.out.print("ALOITETAAN TILAUKSEN VIENTI KANTAAN.");
				}else{
					asiakas_id = asiakas.getId();
					osoite = asiakas.getKatuosoite()+", "+asiakas.getPostinumero()+", "+asiakas.getPostitoimipaikka();
					String toimitustapa = tilaus.getToimitustapa();

					sql = "INSERT INTO tilaus (asiakas_id,toimitustapa,toimitusosoite)"+
							" VALUES ("+asiakas_id+",'"+toimitustapa+"','"+osoite+"');";
				}
			
					if (db.yhdista() && db.aloitaTransaktio() )
					{
						lkm = db.suoritaPaivitys(sql);
						if (lkm == 1)
						{
							db.commit();
							db.katkaise();
							System.out.print("NYT TILAUS ON VIETY KANTAAN.");											
						}
						else
						{
							db.rollback();
							db.katkaise();
							System.out.print("TILAUKSEN VIENTI EI ONNISTUNUT, ROLLBACK");
							ok = false;
						}
					}
					else{
						throw new SQLException();}
	
					
					//Haetaan tilauksen yksilöivä tilaus_id tietokannasta, jotta se voidaan liittää tilauksen_pizzat tauluun tietokannassa.
					System.out.print("HAETAAN TILAUSNUMERO KANNASTA.");
					String toimitustapa = tilaus.getToimitustapa();
					int tilaus_id = haeTilausnumero(asiakas_id,osoite,toimitustapa);
					System.out.print("TILAUSNUMERO ON: "+tilaus_id);

				
					for(int i=0;i<list.size();i++){
					PizzaBean pizza = list.get(i);
					int tunnus = pizza.getTunnus();
					String valkosipuli=pizza.getValkosipuli();
					String oregano=pizza.getOregano();
					String tabasco = pizza.getTabasco();
					ok=lisaaTilausrivi(tilaus_id,tunnus,valkosipuli,oregano,tabasco);
					
					}

			return ok;
			
		}
	
	/**
	 * Tämä metodi lisää tilausrivin tietokantaan.
	 * @param tilaus_id Tilauksen yksilöivä numero.
	 * @param tunnus Pizzan yksilöivä tunnus.
	 * @param valkosipuli Lisämauste pizzalle
	 * @param oregano Lisämauste pizzalle
	 * @param tabasco Lisämauste pizzalle
	 * @return ok true tai false, true = Vienti tieokantaan onnistui.
	 * @throws SQLException Tietokanta-virhe.
	 * @see kohdeluokat.TilausriviBean
	 * @see kohdeluokat.PizzaBean
	 */
	public boolean lisaaTilausrivi (int tilaus_id,int tunnus,String valkosipuli,String oregano,String tabasco)
			throws SQLException
		{
		System.out.println("** lisaaTilausrivi()");

			String sql;
			int lkm; 
			boolean ok =false;
			
			if (db.yhdista() && db.aloitaTransaktio() )
			{
			sql = "INSERT INTO tilauksen_pizzat (tilaus_id,pizza_id,valkosipuli,tabasco,oregano)"+
					" VALUES ("+tilaus_id+","+tunnus+",'"+valkosipuli+"','"+oregano+"','"+tabasco+"');";
			lkm = db.suoritaPaivitys(sql);
				
				if(lkm == 1){
					db.commit();
					db.katkaise();
					System.out.println("** dao.lisaaTilaus(): TILAUKSEN_PIZZAT rivi viety kantaan");
					ok = true;
				}
					else{						
					db.rollback();
					db.katkaise();
					System.out.println("** dao.lisaaTilaus(): TILAUKSEN_PIZZAT rivin vienti epäonnistui");
					ok = false;}
			
				}else{
					throw new SQLException();}

			return ok;
			
		}
	
	/**
	 * Hakee tilausnumeron tietokannasta.
	 * @param asiakas_id Asiakkaan yksilöivä tunnus.
	 * @param osoite Asiakkaan osoite.
	 * @param toimitustapa Asiakkaan määrittämä toimitustapa.
	 * @return tilaus_id Tilausnumero aiemmin tietokantaan viedystä tilauksesta.
	 * @throws SQLException Tietokanta-virhe.
	 */
	public int haeTilausnumero(int asiakas_id,String osoite,String toimitustapa)
			throws SQLException
	{
		int tilaus_id = 0;
		TilausBean tilaus = null;
		String sql = "SELECT tilaus_id FROM tilaus WHERE asiakas_id="+asiakas_id+" AND toimitusosoite='"+osoite+"' AND toimitustapa='"+toimitustapa+"';";
		ArrayList<HashMap> lista;
		
		try
		{
			if (db.yhdista())
			{
				System.out.println("** dao.haeTilausnumero: "+sql);
				lista = db.suoritaKysely(sql);
				db.katkaise();
				if (lista != null)
				{
					tilaus = muodostaTilausOlio(lista);
					tilaus_id=tilaus.getId();
				}
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return tilaus_id;
	}
	/**
	 * Tämä metodi muodostaa TilausBean-olion parametrina saamastaan listasta.
	 * @param lista Lista tilaustietoja.
	 * @return tilaus Tilauksen tiedot.
	 * @throws SQLException Tietokanta-virhe.
	 * @see kohdeluokat.TilausBean
	 */
	private TilausBean muodostaTilausOlio (ArrayList<HashMap> lista) throws SQLException
	{
		TilausBean tilaus = null;
		HashMap<String,String> map;
		Iterator<HashMap> it;
		int id;
		AsiakasBean asiakas;
		Date tilausaika;
		Date toimitusaika;
		String toimitustapa;
		String toimitusosoite;
		Double toimitusmaksu;
		String maksutilanne;
		String maksutapa;
		ArrayList<PizzaBean> pizzat;

		it=lista.iterator();
		if (it.hasNext())
		{
			map = it.next();
			
			tilaus = luoTilausOlio(map);
		}
		return tilaus;
	}
	
	/**
	 *Tämä metodi luo TilausBean-olion saamansa parametrin tiedoista.
	 * @param map Lista tilaustietoja.
	 * @return tilaus Tilauksen tiedot.
	 * @throws SQLException Tietokanta-virhe.
	 * @see kohdeluokat.TilausBean
	 */
	private TilausBean luoTilausOlio(HashMap<String,String> map) throws SQLException
	{
		System.out.println("** luoTilausOlio()");

		int id;
		TilausBean tilaus;
		
		id =Integer.parseInt( map.get("tilaus_id"));

		
		tilaus = new TilausBean();
		tilaus.setId(id);
		return tilaus;
	}
	
	/**
	 * Hakee pizzan tietokannasta saamansa parametrin mukaan.
	 * @param nimi Pizzan nimi
	 * @return pizza Pizzan tiedot
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.PizzaBean
	 */
	public PizzaBean haePizza(String nimi)
			throws SQLException
	{
		PizzaBean pizza = null;
		String sql = "SELECT * FROM pizzat WHERE pizza_nimi='";
		sql = sql + nimi+"'";
		ArrayList<HashMap> lista;
		
		try
		{
			if (db.yhdista())
			{
				lista = db.suoritaKysely(sql);
				db.katkaise();
				if (lista != null)
				{
					pizza = muodostaPizzaOlio(lista);
				}
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return pizza;
		
		/**
		 * Hakee pizzan tietokannasta saamansa parametrin mukaan.
		 * @param tunnus Pizzan yksilöivä tunnus
		 * @return pizza Pizzan tiedot
		 * @throws SQLException Tietokanta-virhe
		 * @see kohdeluokat.PizzaBean
		 */
	}
	public PizzaBean haePizza(int tunnus)
		throws SQLException
	{
		PizzaBean pizza = null;
		String sql = "SELECT * FROM pizzat WHERE pizza_id=";
		sql = sql + tunnus;
		ArrayList<HashMap> lista;

		try
		{
			if (db.yhdista())
			{
				lista = db.suoritaKysely(sql);
				db.katkaise();
				if (lista != null)
				{
					pizza = muodostaPizzaOlio(lista);
				}
		
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return pizza;
}
	
	/**
	 * Tämä metodi muodostaa PizzaBean-olion saamastaan listasta.
	 * @param lista Lista pizzan tietoja
	 * @return pizza Pizzan tiedot.
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.PizzaBean
	 */
	private PizzaBean muodostaPizzaOlio (ArrayList<HashMap> lista) throws SQLException
	{
		PizzaBean pizza = null;
		HashMap<String,String> map;
		Iterator<HashMap> it;


		it=lista.iterator();
		if (it.hasNext())
		{
			map = it.next();
			
			pizza = luoPizzaOlio(map);
		}
		return pizza;
	}
	
	/**
	 * Hakee kaikki pizzat tietokannasta.
	 * @return lista Lista pizzoja
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.PizzaBean
	 */
	public ArrayList<PizzaBean> haePizzat()
			throws SQLException
	{
		ArrayList<PizzaBean> lista=null;
		ArrayList<TayteBean> list=null;
		String sql = "select * from pizzat ORDER by pizza_id";
		ArrayList<HashMap> tklista;
		
		try
		{
			if (db.yhdista())
			{
				tklista = db.suoritaKysely(sql);
				db.katkaise();
				if (tklista != null)
				{
					lista = muodostaPizzaLista(tklista);
				}
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return lista;
	}
	
	/**
	 * Tämä metodi muodostaa pizzalistan.
	 * @param tklista Lista pizzojen tietoja.
	 * @return lista Lista pizzojen tietoja
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.PizzaBean
	 */
	private ArrayList<PizzaBean> muodostaPizzaLista (ArrayList<HashMap> tklista) throws SQLException
	{
		PizzaBean pizza = null;
		HashMap<String,String> map;
		ArrayList<PizzaBean> lista=null;
		Iterator<HashMap> it;
		int tunnus;
		String nimi,sisalto;
		double hinta;
		
		it=tklista.iterator();
		while  (it.hasNext())
		{
			map = it.next();
			pizza = luoPizzaOlio(map);
			
			if (lista==null)
				lista = new ArrayList<PizzaBean>();
			
			lista.add(pizza);
		}
		return lista;
	}

	/**
	 * Tämä metodi luo PizzaBean-olion parametrina saamastaan tiedosta.
	 * @param map Pizzan tiedot
	 * @return pizza Pizzan tiedot
	 * @throws SQLException Tietokanta-virhe
	 * @see kohdeluokat.PizzaBean
	 */
	private PizzaBean luoPizzaOlio(HashMap<String,String> map) throws SQLException
	{
		int tunnus;
		String nimi;
		double hinta;
		PizzaBean pizza = null;
		String  pvmString;
		Pvm pvm=null;
		ArrayList<TayteBean> tayteLista;

		
		tunnus =Integer.parseInt( map.get("pizza_id"));
		nimi = map.get("pizza_nimi");
		/**sisalto = map.get("sisalto");*/
		hinta = Double.parseDouble(map.get("pizza_hinta"));
		pvmString = map.get("pizza_poistomerkitty");
		tayteLista = haePizzanTaytteet(tunnus);

		if (pvmString != null)
			pvm = new Pvm (pvmString);
		//System.out.println("pvm: " + pvmString + " olio: " + pvm);
		
			
		
		pizza = new PizzaBean(tunnus,nimi,hinta, pvm, tayteLista);
		return pizza;
	}
	
	 /**
	  * Hakee tietokannasta täytteet pizzalle saamansa pizzan tunnuksen mukaan.
	  * @param tunnus Pizzan yksilöivä tunnus.
	  * @return lista Lista pizzoja.
	  * @throws SQLException Tietokantavirhe.
	  * @see kohdeluokat.PizzaBean
	  */
	public ArrayList<TayteBean> haePizzanTaytteet(int tunnus)
			throws SQLException
	{
		ArrayList<TayteBean> lista=null;
		String sql = "select t.tayte_id, t.tayte_nimi, t.tayte_hinta from tayte t JOIN pizzan_tayte pt ON t.tayte_id = pt.tayte_id JOIN pizzat p ON p.pizza_id = pt.pizza_id WHERE pt.pizza_id=";
		ArrayList<HashMap> tklista;
		
		try
		{
			if (db.yhdista())
			{
				tklista = db.suoritaKysely(sql+tunnus);
				db.katkaise();
				if (tklista != null)
				{
					lista = muodostaTayteLista(tklista);
				}
				
			}
			else
				throw new SQLException();
		}
		catch (SQLException e)
		{
			throw e;
		}
		return lista;
	}
	
	/**
	 * Tämä metodi muodostaa täytelistan parametrina saamistaan tiedoista.
	 * @param tklista Lista täytteiden tietoja.
	 * @return lista Lista pizzan täytteitä.
	 * @see kohdeluokat.TayteBean
	 */
	private ArrayList<TayteBean> muodostaTayteLista (ArrayList<HashMap> tklista)
	{
		TayteBean tayte = null;
		HashMap<String,String> map;
		ArrayList<TayteBean> lista=null;
		Iterator<HashMap> it;
		
		it=tklista.iterator();
		while  (it.hasNext())
		{
			map = it.next();
			tayte = luoTayteOlio(map);
			
			if (lista==null)
				lista = new ArrayList<TayteBean>();
			
			lista.add(tayte);
		}
		return lista;
	}
	
	/**
	 * Tämä metodi luo TayteBean-olion parametrina saamastaan tiedosta.
	 * @param map Täytteen tiedot.
	 * @return tayte Täytteen tiedot.
	 * @see kohdeluokat.TayteBean
	 */
	private TayteBean luoTayteOlio(HashMap<String,String> map)
	{
		int id;
		String nimi;
		double hinta;
		TayteBean tayte = null;
		
		id =Integer.parseInt( map.get("tayte_id"));
		nimi = map.get("tayte_nimi");
		/**sisalto = map.get("sisalto");*/
		hinta = Double.parseDouble(map.get("tayte_hinta"));
		
		tayte = new TayteBean(id,nimi,hinta);
		return tayte;
	}
	

	
}
