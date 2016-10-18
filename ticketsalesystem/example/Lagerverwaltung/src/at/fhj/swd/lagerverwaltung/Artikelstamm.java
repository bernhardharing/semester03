package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Artikelstamm.findAll",
				query = "SELECT a FROM Artikelstamm a"),
		@NamedQuery(name = "Artikelstamm.findByBezeichnung",
				query = "SELECT a FROM Artikelstamm a WHERE a.bezeichnung = :bezeichnung"),
		@NamedQuery(name = "Artikelstamm.findBestaende",
				query = "SELECT NEW at.fhj.swd.lagerverwaltung.ArtikelBestand(a.artikelnummer, a.bezeichnung, b.bestandsid, b.menge) FROM Artikelstamm a JOIN a.bestaende b WHERE a.artikelnummer = :artikelnummer")
})
public class Artikelstamm
{
	/*
	 * Properties
	 */
	@Id
	private String artikelnummer;
	private String bezeichnung;
	@OneToMany(mappedBy = "artikel")
	private List<Bestand> bestaende = new ArrayList<Bestand>();

	@OneToMany(mappedBy = "artikel")
	private List<Bestellposition> bestellpositionen = new ArrayList<Bestellposition>();
	@ManyToMany
	@JoinTable(name = "artikelstamm_charge",
			joinColumns =
			@JoinColumn(name = "artikelnummer", referencedColumnName = "artikelnummer"),
			inverseJoinColumns =
			@JoinColumn(name = "charge", referencedColumnName = "charge"))
	private List<Charge> chargen = new ArrayList<Charge>();

	Artikelstamm()
	{}

	Artikelstamm(String artikelnummer, String bezeichnung)
	{
		setArtikelnummer( artikelnummer );
		artikelnummer = "REFERENZ";
		setBezeichnung( bezeichnung );
	}

	public String getArtikelnummer()
	{
		return artikelnummer;
	}

	public void setArtikelnummer( String artikelnummer )
	{
		final int LEN_ARTIKELNUMMER = 20;

		if ( ( artikelnummer == null ) ||
				( artikelnummer.length() == 0 ) ||
				( artikelnummer.length() > LEN_ARTIKELNUMMER ) )
			throw new IllegalArgumentException( "Artikelnummer is invalid!" );

		this.artikelnummer = artikelnummer;
	}
//
	public String getBezeichnung()
	{
		return bezeichnung;
	}

	public void setBezeichnung( String bezeichnung )
	{
		if ( bezeichnung == null )
			throw new IllegalArgumentException( "Bezeichnung is null" );

		this.bezeichnung = bezeichnung;
	}

	public List<Bestand> getBestaende()
	{
		return bestaende;
	}

	public void addBestand( Bestand bestand )
	{
		if ( bestand == null )
			throw new IllegalArgumentException( "Bestand is null!" );

		bestaende.add( bestand );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ( ( artikelnummer == null ) ? 0 : artikelnummer.hashCode() );
		result = prime * result
				+ ( ( bezeichnung == null ) ? 0 : bezeichnung.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Artikelstamm other = (Artikelstamm) obj;
		if ( artikelnummer == null )
		{
			if ( other.artikelnummer != null )
				return false;
		}
		if ( !artikelnummer.equals( other.artikelnummer ) )
			return false;
		if ( bezeichnung == null )
		{
			if ( other.bezeichnung != null )
				return false;
		}
		else if ( !bezeichnung.equals( other.bezeichnung ) )
			return false;
		return true;
	}

	public List<Charge> getChargen()
	{
		return chargen;
	}

	public void addCharge( Charge charge )
	{
		if ( charge == null )
			throw new IllegalArgumentException( "Charge is null!" );

		this.chargen.add( charge );
		charge.addArtikel( this );
	}

	public List<Bestellposition> getBestellpositionen()
	{
		return bestellpositionen;
	}

	public void addBestellposition( Bestellposition bestellposition )
	{
		if ( bestellposition == null )
			throw new IllegalArgumentException( "Bestellposition is null!" );

		this.bestellpositionen.add( bestellposition );
	}
}
