package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class extends PersonneImpl and is unknown server side. PersonneImpl is known
 * server side.
 * @author raphael
 *
 */

@XmlRootElement(name="clientmodel")
public class ClientModel extends PersonneImpl {

}
