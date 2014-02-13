/**
 * @author Hao ZHANG
 *
 */
@XmlJavaTypeAdapters({ 
	@XmlJavaTypeAdapter(PersonAdapter.class)
})
package example.model;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
