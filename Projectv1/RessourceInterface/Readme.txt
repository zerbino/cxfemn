Hao

//J'ai ajouté PersonneAdapteur et EtudiantAdapteur, mais ça marche pas encore.

//Je vais continuer à le déboguer.

Ca marche maintenant. J'ai change deux parties:
1. Pour la classe d'Adapteur, on doit implementer la fonction marshalling aussi.
2. On doit ajouter l'annotation XmlJavaTypeAdapter sur le type au lieu de la fonction.

8/1/2014
