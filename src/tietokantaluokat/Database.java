package tietokantaluokat;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Database-luokka toimii fyysisen tietokannan ylläpitäjänä.
 * @author Kari Silpiö
 * @author Seija Lahtinen
 * @author Juho
 * @version 0.4
 */


public class Database {

	//tietokannan tila	 
	private String sqlstate;
    
    //onko tietokantayhteys auki.    
    private boolean yhteysOnAuki = false;

    //onko transaktio kesken.
    private boolean transaktioKesken = false;

    //Tietokantayhteys.
    private Connection tietokantayhteys;

    /**
     *Metodi, jolla asetetaan tietokantayhteyttä varten tiedot ja
     *otetaan yhteys tietokantaan
     * @return paluu true tai false, true = yhteyden otto onnistui.
     * @throws SQLException Heittää SQLException-poikkeuksen, jos tietokantaan yhteyden tekemisessa on virheita.
     */
    public boolean yhdista()
    	throws SQLException 
    {
    	
		String JDBCAjuri = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:3306/projekti";

        boolean paluu = false;
        
        sqlstate = "00000";
       try
        {
			// ladataan tietokanta-ajuri
            Class.forName(JDBCAjuri).newInstance();
		}
		catch (Exception e)
        {
            for (int i = 1; i <= 80; i++)
                System.out.print("=");

            System.err.println("TIETOKANTALIITTYN VIRHETILANNE: " +
                "JDBC:n omaa tietokanta-ajuria ei loydy.\n\n" + e.getMessage() +
                " " + e.toString() + "\n");

            for (int i = 1; i <= 80; i++)
                System.out.print("=");

            System.out.print("\n");
            throw (new SQLException ("Tietokanta-ajuria ei loydy!"));
        }
		try
		{
            // otetaan yhteys tietokantaan
			tietokantayhteys = DriverManager.getConnection(
						url,"projekti", "niZYbO28r");

            System.out.println("Yhteyden muodostaminen onnistui");
            this.yhteysOnAuki = true;
            paluu = true;  // yhteyden otto onnistui
        }
        catch (SQLException sqlE)
      	{
            sqlstate = sqlE.getSQLState();

            virheilmoitus(sqlE, "avaa", url,
                "Tietokantayhteyden avaaminen ei onnistunut.");

            throw( sqlE);

        }
        return paluu;
    }

    /**
     *Metodi, joka palauttaa sqlstate-muuttujan tila
     *@return sqlstate muuttujan tila
     */

    public String getSqlstate() 
    {
        return (sqlstate);
    }



    /**
     *Metodi, joka palautettaa yhteysOnAuki-muuttujan tilan, että tiedetään
     *onko yhteys tietokantaan auki.
     *@return tietokantayhteyden tila
     */
    public boolean onAuki() 
    {
        return (yhteysOnAuki);
    }

    /**
     *Metodi, jolla katkaistaan tietokantayhteys.
     *@return paluu  true tai false, true= Katkaisu onnistui
     *@throws SQLExeption Heittää SQLExeption-poikkeuksen tietokannan virhetilanteessa.
     */
    public boolean katkaise()
    	throws SQLException
    {
        sqlstate = "00000";
        boolean paluu = false;

        try
        {
            tietokantayhteys.close();
            yhteysOnAuki = false;
            System.out.println("Yhteys tietokantaan katkaistu!");

            paluu = true;
        }
        catch (SQLException sqlE)
        {
            sqlstate = sqlE.getSQLState();
            virheilmoitus(sqlE, "sulje", "",
                "Tietokantayhteyden sulkeminen ei onnistunut.");
            throw( sqlE);

        }
        return paluu;
    }

    /**
     *Metodi, jolla suoritetaan SQL-kysely tietokannasta.
     * @param sqlTeksti Kyselyn select-lause.
     * @return tulosLista Kyselyn vastaukset listana.
     * @throws SQLException Tietokannan virhetilantessaa heittaa SQLException-poikkeuksen
     **/
    public ArrayList<HashMap>  suoritaKysely(String sqlTeksti)
    	throws SQLException 
    {
        //HashMap-olioilla on kyselyn jokaisen rivin tiedoista
        //tiedon nimi (key) ja tiedon arvo (value) String-muodossa.
    	
        Statement lause=null; // suoritettava SQL-lause
        ResultSet tulosjoukko = null; // SQL-kyselyn tulokset
        // paluulista
        ArrayList <HashMap> tulosLista=null;
        // paluulistan olio
        HashMap tulosrivi;

		if (yhteysOnAuki)  // suoritus vain jos yhteys on auki
		{
			sqlstate = "00000";
			//System.out.println("Tietokanta: "+ sqlTeksti);

			try
			{
				lause = tietokantayhteys.createStatement();
				tulosjoukko = lause.executeQuery(sqlTeksti);
				// siirra tulosjoukko tulosListaan
				while (tulosjoukko.next())
				{
					// siirra tulosjoukon alkio olioon
					tulosrivi = tallennaHashMappiin(tulosjoukko);
					if (tulosLista == null)
						tulosLista = new ArrayList <HashMap>(); 
					// vie olio listaan
					tulosLista.add(tulosrivi);

				}
				/*if (tulosLista != null)
					for (int i=0;i<tulosLista.size();i++)
						System.out.println(tulosLista.get(i).toString());*/
			}
			catch (SQLException sqlE)
			{
				sqlstate = sqlE.getSQLState();

				virheilmoitus(sqlE, "suoritaKysely", sqlTeksti,
					"SELECT-lauseen suorittaminen ei onnistunut.");
				tulosLista = null;
				throw (sqlE);
			}
			finally
			{
				try
				{
					if (tulosjoukko!= null)
						tulosjoukko.close();
					if (lause != null)
						lause.close();
				}
				catch(SQLException e)
				{

					virheilmoitus(e, "suoritaKysely","","Tulosjoukko ei sulkeudu");
					tulosLista = null;
					throw (e);
				}
			}
		}
		else
		{
			System.out.println("Yritat suorittaa kyselyn, "+
				"vaikka tietokantaan ei ole yhteytta");
		}

        return tulosLista;
    }
    /**
	 * Tallentaa annetun ResultSetin käsittelyn alla olevan
	 * rivin tiedot HashMappiin.
	 * @param resultSet Purettava ResultSet-luokan olio.
	 * @return tulosrivi ResultSetistä purettu rivi .
	 * @throws SQLException Tietokannan virhetilanteessa heittää SQLException-poikkeuksen
	 */
	public HashMap tallennaHashMappiin(ResultSet tulosjoukko)
		throws SQLException
	{
		// 1.string=tiedon nimi, 2.string=tiedon arvo
		HashMap<String, String> tulosrivi =
				new HashMap<String, String>();
		ResultSetMetaData metaData ;
		int sarakkeidenLkm ;

		try
		{
			// vie tulosjoukosta yksi rivi metaDataan
			metaData = tulosjoukko.getMetaData();
			// montako tietoa rivissa?
			sarakkeidenLkm = metaData.getColumnCount();
			// Tallenna käsittelyn alla olevan
			// rivin tiedot tulosriviin
			for (int i = 1; i <= sarakkeidenLkm; i++)
			{
				// tulosriviin tiedon nimi ja arvo
				
				tulosrivi.put(metaData.getColumnName(i),
						tulosjoukko.getString(i));
				//System.out.println("arvo:"+tulosjoukko.getString(i)+" sarake:"+metaData.getColumnName(i));
			}
		}
		catch (SQLException sqlE)
		{
			sqlstate = sqlE.getSQLState();

			virheilmoitus(sqlE, "tallennaHashMappiin", "",
				"SELECT-lauseen purkaminen ei onnistunut.");
			tulosrivi = null;
			throw (sqlE);

		}
		//System.out.println(tulosrivi);
		// palauta tulosrivi
		return tulosrivi;
    }

    /**
     * Metodi, joka suorittaa tietokannan päivityksen.
     * @param sqlTeksti Päivityslause.
     * @return lkm Käsiteltyjen rivien lukumäärä.
     * @throws SQLExcpetion Heittää SQLExcpetion-poikkeuskasittelyn tietokannan virhetilanteessa.
     */
    public int suoritaPaivitys(String sqlTeksti)
    throws SQLException
    {
        Statement lause=null; // suoritettava SQL-lause
        int lkm = 0; // ksiteltyjen rivien lkm

   		if (yhteysOnAuki)
        {
			sqlstate = "00000";

			try
			{
				lause = tietokantayhteys.createStatement();
				lkm = lause.executeUpdate(sqlTeksti);
			}
			catch (SQLException sqlE)
			{
				sqlstate = sqlE.getSQLState();

				virheilmoitus(sqlE, "suoritaPivitys", sqlTeksti,
					"INSERT, DELETE tai UPDATE-lauseen " +
					"suorittaminen ei onnistunut.");
				lkm = 0;
				throw (sqlE);
			}
			finally
			{
				try
				{
					if (lause !=null)
						lause.close();
				}
				catch(SQLException e)
				{

					virheilmoitus(e, "suoritaPaivitys","",
								"Suoritettava lause ei sulkeudu");
					lkm = 0;
					throw (e);
				}
			}
		}
		else
		{
			System.out.println("Yritat suorittaa paivityksen, "+
				"vaikka tietokantaan ei ole yhteytta");
			lkm = 0;
		}

        return (lkm);
    }

    /**
     *Metodi, joka aloittaa transaktion.
     *@return paluu true= Ei tietokantavirheitä, false=Tietokanta-virhe
     *@throws SQLException Heittaa SQLException-poikkeuksen tietokannan virhetilanteessa.
     */
    public boolean aloitaTransaktio()
    	throws SQLException
    {
        sqlstate = "00000";
        boolean paluu = true;

		if (yhteysOnAuki)
		{
			try {
				if (transaktioKesken)
				{
					System.out.println("Uutta transaktiota ei voi aloittaa " +
						"ennen kuin edellinen transaktio on paatetty.");
					paluu = false;
				}

				tietokantayhteys.setAutoCommit(false);
				transaktioKesken = true;
			}
			catch (SQLException sqlE)
			{
				sqlstate = sqlE.getSQLState();

				virheilmoitus(sqlE, "aloitaTransaktio", "",
					"Transaktion aloittaminen ei onnistunut.");

				paluu = false;
				throw (sqlE);
			}
		}
		else
		{
			System.out.println("Yritat aloittaa transaktion, "+
				"vaikka tietokantaan ei ole yhteytta");
			paluu = false;
		}
		return paluu;
    }

    /**
     *Metodi, jolla suoritetaan commit, eli paattaa transaktion hyvaksymalla.
     *@return paluu true=commit onnistui, false=commit ei onnistunut
     *@throws SQLException Heittää SQLException-poikkeuksen tietokannan virhetilanteessa.
     */
    public boolean commit()
    	throws SQLException
    {
        sqlstate = "00000";
        boolean paluu = true;

        try
        {
			// hyvaksy transaktio ja lopeta se
            tietokantayhteys.commit();
            transaktioKesken = false;
            // laita automaattinen commit paalle
            tietokantayhteys.setAutoCommit(true);
        }
        catch (SQLException sqlE)
        {
            sqlstate = sqlE.getSQLState();

            virheilmoitus(sqlE, "commit", "", "commit ei onnistunut.");
            paluu = false;
            throw (sqlE);

        }
        return paluu;
    }

    /**
     *Metodi, jolla suoritetaan rollback, , eli paattaa transaktion hylkaamalla.
     *@return paluu true=rollback onnistui, false=rollback ei onnistunut
     *@throws SQLException Heittää SQLException-poikkeuksen tietokannan virhetilanteessa.
     */
    public boolean rollback()
    	throws SQLException
    {
        sqlstate = "00000";
         boolean paluu = true;

        try
        {
			// hylkaa transaktio ja lopeta se
            tietokantayhteys.rollback();
            transaktioKesken = false;
            // laita automaattinen commit paalla
            tietokantayhteys.setAutoCommit(true);
        }
        catch (SQLException sqlE)
        {
            sqlstate = sqlE.getSQLState();

            virheilmoitus(sqlE, "rollback", "", "rollback ei onnistunut.");

            paluu = false;
            throw (sqlE);
        }
        return paluu;
    }

    /**
     * Metodi, jolla tulostetaan virheilmoituksia.
     * @param sqlE SQL-poikkeus
     * @param metodi metodi, joka aiheuttaa virheilmoituksen
     * @param parametrit virheilmoituksen aiheuttaneen metodin parametrit
     * @param teksti virheilmoituksen teksti
     */

    private void virheilmoitus(SQLException sqlE, String metodi,
        String parametrit, String teksti) {
        for (int i = 1; i <= 80; i++)
            System.out.print("=");

        System.err.println("TIETOKANTALIITTYMAN VIRHETILANNE\n");

        System.err.println("1) " + metodi + "-metodi " +
            "antaa seuraavan virheilmoituksen: \n\n    " + teksti + "\n");

        System.err.println("2) Metodin kutsu oli seuraava:\n\n    " + metodi +
            " (\"" + parametrit + "\")\n");

        System.err.println("3) JDBC kertoo virheen aiheutuneen " +
            "seuraavasta syysta:\n\n" + sqlE.getMessage());

        for (int i = 1; i <= 80; i++)
            System.out.print("=");
    }

    
}


