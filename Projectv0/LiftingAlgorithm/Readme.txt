1) Utilisation du projet avec import : si le projet ne peut se lancer après 
import, lire cette section :

Lorsque vous essayez de lancer le projet sur un serveur tomcat, vous avez peut être
le message suivant :
"The selection cannot be run on any server", ou "aucune ressource ne peut être ajoutée"
lorsque vous faites : clic droit sur le serveur > add and remove
Le problème vient du fait qu'à l'import du projet, eclipse créer par défaut un projet
java classique.

Le problème peut être résolu de la façon suivante : il faut convertir le projet en web dynamic
project. Pour cela :
- aller dans les propriétés du projet (clic droit > propriétés)
- > project facets
- cocher "Dynamic Web Module" et "Java"

Ces informations peuvent être retrouvées ici :
http://www.mkyong.com/java/how-to-convert-java-project-to-web-project-in-eclipse/


2) L'import de la bibliothèque org.jdom2 au lien suivant sera peut être nécessaire :
http://www.jdom.org/downloads/

3) Structure : Il y a un client (LiftingAlgorithmClient), et un serveur (LiftingAlgorithm)

4) Lancer le projet :
- Serveur : Dans LiftingAlgorithm, clic droit sur model.ServiceImpl > run as > run on server
- Client : Dans LiftingAlgorithmClient, clic droit sur client.TestClient > run as > java application
