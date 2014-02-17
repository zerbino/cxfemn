/**
 * @author Hao ZHANG
 *
 */
@XmlJavaTypeAdapters({ 
	@XmlJavaTypeAdapter(PersonAdapter.class),
	@XmlJavaTypeAdapter(StudentAdapter.class)
})
package model;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
