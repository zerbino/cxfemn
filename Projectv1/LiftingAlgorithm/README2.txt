Avant de lancer le projet :

1) Pour vérifier l'objectif 1, lié à l'amélioration de l'algorithme dans le cas
de la requête POST grâce à l'utilisation du contexte, vérifier que dans restful-beans.xml,
la référence vers le filtre utilisé (filter.server.ClientRequestFilter) est bien présente
et décommentée. Le client correspondant est la classe model.TestClient dans le projet 
LiftingAlgorithmClient.

2) Pour vérifier l'objectif 2, qui était d'implémenter le filtre du côté du résultat, dans 
le cas d'une requête GET où le service a été modifié, il faut au contraire commenter le filtre
utilisé pour l'objectif 1 dans restful-beans.xml pour ne pas avoir de bug (l'algorithme utilisé
normalement au moment du POST est appelé lors d'un GET, ce qui provoque un bug. Il reste une 
vérification simple à ajouter sur le type de requête pour éviter ce bug. 