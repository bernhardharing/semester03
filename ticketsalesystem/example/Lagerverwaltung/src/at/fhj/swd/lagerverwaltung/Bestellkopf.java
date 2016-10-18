package at.fhj.swd.lagerverwaltung;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Bestellkopf 
{
	/*
	 * Properties
	 */
	@Id @GeneratedValue(generator="bestellkopf_bestellnummer_sequenceGenerator")
	@SequenceGenerator(name = "bestellkopf_bestellnummer_sequenceGenerator", 
		               sequenceName="bestellkopf_bestellnummer_sequence",
			           allocationSize = 1) 
	private int bestellnummer;
	@ManyToOne 
	@JoinColumn(name="kundennummer")
	private Kunde kunde;
	@OneToOne (mappedBy = "bestellkopf")
	private Lieferschein lieferschein;
	@OneToMany(mappedBy = "bestellkopf")
	private List<Bestellposition> positionen = new ArrayList<Bestellposition>();
	
	
	public Lieferschein getLieferschein() {
		return lieferschein;
	}
	public void setLieferschein(Lieferschein lieferschein) {
		this.lieferschein = lieferschein;
	}
	Bestellkopf() {}
	Bestellkopf(Kunde kunde)
	{
		setKunde(kunde);
	}

	public int getBestellnummer() 
	{
		return bestellnummer;
	}
	public Kunde getKunde() 
	{
		return kunde;
	}
	public void setKunde(Kunde kunde) 
	{
		if(kunde == null)
			throw new IllegalArgumentException("Kunde is null!");
		
		if(this.kunde != null)
			this.kunde.removeBestellung( this );
		
		this.kunde = kunde;
		kunde.addBestellung(this);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bestellnummer;
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestellkopf other = (Bestellkopf) obj;
		if (bestellnummer != other.bestellnummer)
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;		
		return true;
	}
	public List<Bestellposition> getPositionen() 
	{
		return positionen;
	}
	public void addPosition(Bestellposition position) 
	{
		if(position == null)
			throw new IllegalArgumentException("Position is null!");
		
		this.positionen.add(position);
	}
	
	
	
	
}
