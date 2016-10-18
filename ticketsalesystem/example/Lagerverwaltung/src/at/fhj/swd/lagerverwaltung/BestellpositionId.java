package at.fhj.swd.lagerverwaltung;

import java.io.Serializable;

public class BestellpositionId implements Serializable 
{
	private static final long serialVersionUID = 4689498480232800379L;
	int bestellkopf;
    int positionsnummer;
    
    public BestellpositionId() {}
    
    public BestellpositionId(int bestellnummer, int positionsnummer)
    {
    	this.bestellkopf = bestellnummer;
    	this.positionsnummer = positionsnummer;	
    }

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + bestellkopf;
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
		BestellpositionId other = (BestellpositionId) obj;
		if (bestellkopf != other.bestellkopf)
			return false;
		if (positionsnummer != other.positionsnummer)
			return false;
		return true;
	}

	
}
