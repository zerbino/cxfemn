package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import rest.Personne;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Etudiant extends Personne {
	@XmlElement
	protected String promo;
	
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public Etudiant() {
		super();
	}
}
