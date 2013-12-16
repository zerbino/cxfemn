Hao

Interfaces pour personne et etudiant, ca marche pas.
Il y a des problemes comme ci-dessous:

Caused by: com.sun.xml.bind.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions
model.Etudiant is an interface, and JAXB can't handle interfaces.
	this problem is related to the following location:
		at model.Etudiant
		
http://www.attivio.com/blog/56-java-development/636-configuring-java-interfaces-to-work-with-jaxb-and-web-services.html


Pour RestClient, ca marche maintenant.

