package at.fhj.swd.lagerverwaltung;

import javax.persistence.Entity;

public class ArtikelBestand
{
	/*
	 * Constructor
	 */
	public ArtikelBestand(String artikelnummer, String artikelBezeichnung, int bestandBestandsId, int bestandMenge)
	{
		setArtikelnummer(artikelnummer);
		setArtikelBezeichnung(artikelBezeichnung);
		setBestandBestandsId(bestandBestandsId);
		setBestandMenge(bestandMenge);
	}
	
	/*
	 * Property: Artikelnummer
	 */
	private String artikelnummer;
	public String getArtikelnummer()
	{
		return artikelnummer;
	}
	private void setArtikelnummer( String artikelnummer )
	{
		if((artikelnummer == null) || (artikelnummer.trim().length() == 0))
			throw new IllegalArgumentException("Artikelnummer is invalid!");
		
		this.artikelnummer = artikelnummer;
	}

	/*
	 * Property Artikelbezeichnung
	 */
	private String artikelBezeichnung;
	public String getArtikelBezeichnung()
	{
		return artikelBezeichnung;
	}
	public void setArtikelBezeichnung( String artikelBezeichnung )
	{
		if((artikelBezeichnung == null) || (artikelBezeichnung.trim().length() == 0))
			throw new IllegalArgumentException("Artikelbezeichnung is invalid!");
		
		this.artikelBezeichnung = artikelBezeichnung;
	}

	/*
	 * Property: BestandsId
	 */
	private int bestandBestandsId;
	public int getBestandBestandsId()
	{
		return bestandBestandsId;
	}
	private void setBestandBestandsId( int bestandBestandsId )
	{
		if(bestandBestandsId <= 0)
			throw new IllegalArgumentException("BestandsId is invalid!");
		
		this.bestandBestandsId = bestandBestandsId;
	}
	

	/*
	 * Property bestandMenge
	 */
	private int bestandMenge;
	public int getBestandMenge()
	{
		return bestandMenge;
	}
	private void setBestandMenge( int bestandMenge )
	{
		if(bestandMenge <= 0)
			throw new IllegalArgumentException("Menge is invalid!");
		
		this.bestandMenge = bestandMenge;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( artikelBezeichnung == null ) ? 0 : artikelBezeichnung.hashCode() );
		result = prime * result + ( ( artikelnummer == null ) ? 0 : artikelnummer.hashCode() );
		result = prime * result + bestandBestandsId;
		result = prime * result + bestandMenge;
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
		ArtikelBestand other = (ArtikelBestand) obj;
		if ( artikelBezeichnung == null )
		{
			if ( other.artikelBezeichnung != null )
				return false;
		}
		else if ( !artikelBezeichnung.equals( other.artikelBezeichnung ) )
			return false;
		if ( artikelnummer == null )
		{
			if ( other.artikelnummer != null )
				return false;
		}
		else if ( !artikelnummer.equals( other.artikelnummer ) )
			return false;
		if ( bestandBestandsId != other.bestandBestandsId )
			return false;
		if ( bestandMenge != other.bestandMenge )
			return false;
		return true;
	}
}
