//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.27 at 03:57:10 PM CET 
//


package example.model;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;


/**
 * <p>Java class for etudiant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="etudiant">
 *   &lt;complexContent>
 *     &lt;extension base="{}personne">
 *       &lt;sequence>
 *         &lt;element name="promo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlJavaTypeAdapter(AnyTypeAdapter.class)
//@XmlSeeAlso({EtudiantImpl.class})
public interface Student
    extends Person
{
    /**
     * Gets the value of the promo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@GET
	@Path("promo")
    public String getSpecialField();

    /**
     * Sets the value of the promo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@PUT
	@Path("promo")
    public void setSpecialField(String value);

}
