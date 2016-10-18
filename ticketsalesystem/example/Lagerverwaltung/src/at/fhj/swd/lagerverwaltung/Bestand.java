package at.fhj.swd.lagerverwaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity 
@NamedQueries({
	@NamedQuery(name = "Bestand.findAll",
			   query = "SELECT b FROM Bestand b")
})
public class Bestand 
{
	/*
	 * Properties
	 */
	@Id @GeneratedValue(generator="bestand_bestandsid_sequenceGenerator")
	@SequenceGenerator(name = "bestand_bestandsid_sequenceGenerator", 
		               sequenceName="bestand_bestandsid_sequence",
			           allocationSize = 1) 
	private int bestandsid;
	private int menge;
	@ManyToOne 
	@JoinColumn(name="artikelnummer")
	private Artikelstamm artikel;
	
	Bestand() {}
	Bestand(int menge, Artikelstamm artikel)
	{
		setArtikel(artikel);
		setMenge(menge);
	}
	
	public int getBestandsid() 
	{
		return bestandsid;
	}

	public int getMenge() 
	{
		return menge;
	}

	public void setMenge(int menge) 
	{
		if(menge <= 0)
			throw new IllegalArgumentException("Menge must be qreater than zero!");
		
		this.menge = menge;
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
		artikel.addBestand(this);
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + bestandsid;
		result = prime * result + menge;
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
		Bestand other = (Bestand) obj;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (bestandsid != other.bestandsid)
			return false;
		if (menge != other.menge)
			return false;
		return true;
	}	
}
