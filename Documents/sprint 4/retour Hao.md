# Sprint 4, concernant l'annotation
Hao

## Correction d'un bug sur le côté Serveur(10/02/2014)
dans AbstractLifting::removeExtraFields, ajoute un *break* pour sauter le boucle de while.

## L'ajout de l'annotation AdjustClient sur le côté Serveur(10/02/2014)
1. Définir l'annotation AjustClient.
2. Ajouter l'annotation sur la classe ClientRequestFilter.
3. Ajouter l'annotation sur les méthodes dans Service, qui demandent la fonction de lifting.

## Usage de l'annotation AjustClient
Ajouter l'annotation AjustClient sur la méthode dans Service, qui demande ma fonction de lifting.

## L'ajout de interface substitution(11/02/2014)
1. Changer la définition de la méthode op() et opInt() entre le serveur et le client.
	Pour le serveur:
	EtudiantImpl op();
	@XmlJavaTypeAdapter(EtudiantAdapter.class)Etudiant opInt();

	Pour le client:
	PersonneImpl op();
	@XmlJavaTypeAdapter(PersonneAdapter.class)Personne opInt();

2. Modifier le nom du fichier TestClient4.java à TestClientHao.java pour examiner l'utilisation de l'interface.

## L'ajout de adapter à Client(11/02/2014)
1. Ajouter la classe AdapterTrackle à Client
2. Modifier le code de lifting pour ajouter l'adapter

