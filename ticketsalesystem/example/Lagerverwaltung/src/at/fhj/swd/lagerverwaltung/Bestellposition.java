package at.fhj.swd.lagerverwaltung;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity 
@NamedQueries({
	@NamedQuery(name = "Bestellposition.findAll",
			   query = "SELECT b FROM Bestellposition b")
})
@IdClass(BestellpositionId.class)
public class Bestellposition 
{
	/*
	 * Properties
	 */
	@Id 
	@ManyToOne
	@JoinColumn(name="bestellnummer")
	private Bestellkopf bestellkopf;
	@Id
	private int positionsnummer;
	@ManyToOne 
	@JoinColumn(name="artikelnummer")
	private Artikelstamm artikel;
	private int menge;
	
	Bestellposition() {}
	Bestellposition(Bestellkopf bestellkopf, int positionsnummer, Artikelstamm artikel, int menge)
	{
		setBestellkopf(bestellkopf);
		setPositionsnummer(positionsnummer);
		setArtikel(artikel);
		setMenge(menge);
	}
	
	public Bestellkopf getBestellkopf() 
	{
		return this.bestellkopf;
	}

	public void setBestellkopf(Bestellkopf bestellkopf) 
	{
		if(bestellkopf == null)
			throw new IllegalArgumentException("Bestellkopf is null!");
		
		this.bestellkopf = bestellkopf;
		bestellkopf.addPosition(this);
	}

	public int getPositionsnummer() 
	{
		return positionsnummer;
	}

	public void setPositionsnummer(int positionsnummer) 
	{
		if(positionsnummer <= 0)
			throw new IllegalArgumentException("Positionsnummer is invalid!");
		
		this.positionsnummer = positionsnummer;
	}
	public Artikelstamm getArtikel() 
	{
		return artikel;
	}
	public void setArtikel(Artikelstamm artikel) 
	{
		if(artikel == null)
			throw new IllegalArgumentException("Artikel is null!");
		
		this.artikel = artikel;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) 
	{
		if(menge <= 0)
			throw new IllegalArgumentException("Menge must be greater than zero!");
		
		this.menge = menge;
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result
				+ ((bestellkopf == null) ? 0 : bestellkopf.hashCode());
		result = prime * result + menge;
		result = prime * result + positionsnummer;
		return result;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestellposition other = (Bestellposition) obj;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (bestellkopf == null) {
			if (other.bestellkopf != null)
				return false;
		} else if (!bestellkopf.equals(other.bestellkopf))
			return false;
		if (menge != other.menge)
			return false;
		if (positionsnummer != other.positionsnummer)
			return false;
		return true;
	}
}

