package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity 
@NamedQueries({
	@NamedQuery(name = "Charge.findAll",
			   query = "SELECT c FROM Charge c")
})
public class Charge 
{
	/*
	 * Properties
	 */
	@Id
	private String charge;
	@ManyToMany(mappedBy="chargen")
	private List<Artikelstamm> artikeln = new ArrayList<Artikelstamm>();

	Charge() {}
	Charge(String charge)
	{
		setCharge(charge);
	}
	
	public String getCharge() 
	{
		return charge;
	}

	public void setCharge(String charge) 
	{
		final int LEN_CHARGE = 20;
		
		if((charge == null) ||
		   (charge.length() == 0) ||
		   (charge.length() > LEN_CHARGE))
			throw new IllegalArgumentException("Charge is invalid!");
		
		this.charge = charge;
	}

	public List<Artikelstamm> getArtikeln() 
	{
		return artikeln;
	}

	public void addArtikel(Artikelstamm artikel) 
	{
		if(artikel == null)
			throw new IllegalArgumentException("Artikel is null!");
		
		this.artikeln.add(artikel);
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artikeln == null) ? 0 : artikeln.hashCode());
		result = prime * result + ((charge == null) ? 0 : charge.hashCode());
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
		Charge other = (Charge) obj;
		if (artikeln == null) {
			if (other.artikeln != null)
				return false;
		} else if (!artikeln.equals(other.artikeln))
			return false;
		if (charge == null) {
			if (other.charge != null)
				return false;
		} else if (!charge.equals(other.charge))
			return false;
		return true;
	}
}
