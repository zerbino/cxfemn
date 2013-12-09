
package model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Op_QNAME = new QName("http://model/", "op");
    private final static QName _OpResponse_QNAME = new QName("http://model/", "opResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Op }
     * 
     */
    public Op createOp() {
        return new Op();
    }

    /**
     * Create an instance of {@link OpResponse }
     * 
     */
    public OpResponse createOpResponse() {
        return new OpResponse();
    }

    /**
     * Create an instance of {@link Personne }
     * 
     */
    public Personne createPersonne() {
        return new Personne();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Op }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model/", name = "op")
    public JAXBElement<Op> createOp(Op value) {
        return new JAXBElement<Op>(_Op_QNAME, Op.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model/", name = "opResponse")
    public JAXBElement<OpResponse> createOpResponse(OpResponse value) {
        return new JAXBElement<OpResponse>(_OpResponse_QNAME, OpResponse.class, null, value);
    }

}
