/**
 * @author Hao ZHANG
 *
 */
@XmlJavaTypeAdapters({ 
	@XmlJavaTypeAdapter(PersonneAdapter.class),
	@XmlJavaTypeAdapter(EtudiantAdapter.class)
})
package model;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
