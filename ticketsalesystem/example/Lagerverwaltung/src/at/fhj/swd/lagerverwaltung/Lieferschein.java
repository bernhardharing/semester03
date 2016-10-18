package at.fhj.swd.lagerverwaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Lieferschein 
{
	/*
	 * Properties
	 */
	@Id @GeneratedValue(generator="lieferschein_lieferscheinnummer_sequenceGenerator")
	@SequenceGenerator(name = "lieferschein_lieferscheinnummer_sequenceGenerator", 
		               sequenceName="lieferschein_lieferscheinnummer_sequence",
			           allocationSize = 1) 
	private int lieferscheinnummer;
	
	@OneToOne
	@JoinColumn(name = "bestellnummer")
	private Bestellkopf bestellkopf;
	
	Lieferschein() {}
	Lieferschein(Bestellkopf bestellkopf) {
		this.setBestellkopf(bestellkopf);
	}
	
	
	public int getLieferscheinnummer()
	{
		return lieferscheinnummer;
	}
	public Bestellkopf getBestellkopf() {
		return bestellkopf;
	}
	
	public void setBestellkopf(Bestellkopf bestellkopf) {
		if(bestellkopf == null) {
			throw new IllegalArgumentException();
		}
		this.bestellkopf = bestellkopf;
		this.bestellkopf.setLieferschein(this);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestellkopf == null) ? 0 : bestellkopf.hashCode());
		result = prime * result + lieferscheinnummer;
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
		Lieferschein other = (Lieferschein) obj;
		if (bestellkopf == null) {
			if (other.bestellkopf != null)
				return false;
		} else if (!bestellkopf.equals(other.bestellkopf))
			return false;
		if (lieferscheinnummer != other.lieferscheinnummer)
			return false;
		return true;
	}
	
}
