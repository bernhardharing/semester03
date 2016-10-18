package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Kunde 
{	
	/*
	 * Properties
	 */
	@Id @GeneratedValue(generator="kunde_kundennummer_sequenceGenerator")
	@SequenceGenerator(name = "kunde_kundennummer_sequenceGenerator", 
		               sequenceName="kunde_kundennummer_sequence",
			           allocationSize = 1) 
	private int kundennummer;
	private String name;
	@OneToMany(mappedBy="kunde")
	private List<Bestellkopf> bestellungen = new ArrayList<Bestellkopf>();
	
	Kunde() {}
	Kunde(String name)
	{
		setName(name);
	}

	public int getKundennummer() 
	{
		return kundennummer;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		final int LEN_KUNDENNAME = 40;
		
		if((name == null) ||
		   (name.length() == 0) ||
		   (name.length() > LEN_KUNDENNAME))
			throw new IllegalArgumentException("Kundenname is invalid!");
		
		this.name = name;
	}
	public List<Bestellkopf> getBestellungen() 
	{
		return bestellungen;
	}
	public void addBestellung(Bestellkopf bestellung) 
	{
		this.bestellungen.add(bestellung);
	}
	public void removeBestellung(Bestellkopf bestellung) 
	{
		if(bestellung == null)
			throw new IllegalArgumentException("Bestellung is null!");
		
		this.bestellungen.remove(bestellung);
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + kundennummer;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Kunde other = (Kunde) obj;
		if (kundennummer != other.kundennummer)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
	

}
