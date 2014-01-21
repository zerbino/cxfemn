Hao ZHANG:
21/01/2014

Ce projet est pour implementer les interfaces pour personne et etudiant.

L'interface marche maintenant. Cependant, il y a des problemes:
1. On doit ajouter l'annotation sur la methode de ServiceImpl aussi.
2. Le resultat est ce qu'on attend, mais il y a aussi erreur de unmarshalling, je sais pas pourquoi.

Http-Method: POST
Content-Type: application/xml
Headers: {Accept=[application/xml], cache-control=[no-cache], connection=[keep-alive], Content-Length=[140], content-type=[application/xml], host=[localhost:8080], pragma=[no-cache], user-agent=[Apache CXF 2.7.7]}
Payload: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><etudiant><id>1</id><nom>ZHANG</nom><prenom>Hao</prenom><promo>GSI</promo></etudiant>
--------------------------------------
Corps du message :
Before: 
etudiant
	id: 1
	nom: ZHANG
	prenom: Hao
	promo: GSI
op
Annotations:1
Annotation: interface javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
XmlJavaTypeAdapter
model.PersonneImpl
After: 
personne
	id: 1
	nom: ZHANG
	prenom: Hao
Jan 21, 2014 6:23:53 PM org.apache.cxf.jaxrs.provider.AbstractJAXBProvider handleExceptionStart
WARNING: javax.xml.bind.UnmarshalException
 - with linked exception:
[com.sun.istack.SAXParseException2; lineNumber: 1; columnNumber: 39; unexpected element (uri:"", local:"etudiant"). Expected elements are <{}personne>]