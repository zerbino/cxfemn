/**
 * @author Hao ZHANG
 *
 */
@XmlJavaTypeAdapters({ 
	@XmlJavaTypeAdapter(PersonAdapter.class),
	@XmlJavaTypeAdapter(EtudiantAdapter.class)
})
package example.model;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
