TRUNCATE TABLE categorie CASCADE;
INSERT INTO categorie (id, categorie)
VALUES
(1, Science-fiction),
(2, Informatique)
(3, Bande dessinée),
(4, Science),
(5, Littérature),
(6, Histoire),
(7, Education);

TRUNCATE TABLE livre CASCADE;
INSERT INTO livre (id, titre, auteur, description, couverture, editeur, edition, id_categorie)
VALUES
(1, 'Le livre de Java premier langage', 'Anne Tasso', 'Vous avez décidé de vous initier à la programmation et souhaitez opter pour un langage largement utilisé dans le monde professionnel ? Java se révèle un choix idéal comme vous le constaterez dans ce livre conçu pour les vrais débutants en programmation.
Vous apprendrez d''abord, à travers des exemples simples en Java, à maîtriser les notions communes à tous les langages : variables, types de données, boucles et instructions conditionnelles, etc. Vous franchirez un nouveau pas en découvrant par la pratique les concepts de la programmation orientée objet (classes, objets, héritage), puis le fonctionnement des librairies graphiques AWT et Swing (fenêtres, gestion de la souris, tracé de graphiques).', 'https://cdn1.booknode.com/book_cover/31/full/le-livre-de-java-premier-langage-avec-80-exercices-corriges-31011.jpg', 'Eyrolles', '2017-03-30', 2),
(2, 'Tout JavaScript', 'Olivier Hondermarck', 'Ce livre s’adresse à tous les développeurs web, qu’ils soient débutants ou avancés. Le JavaScript sert avant tout à rendre les pages web interactives  et dynamiques du côté de l’utilisateur, mais il est également de plus en plus souvent utilisé côté serveur. La première partie de ce livre explique les bases et les bonnes pratiques de la programmation en JavaScript en se fondant sur la version ECMAScript 6. La deuxième partie porte sur l’interactivité avec les utilisateurs (interfaces, formulaires, gestion des erreurs, géolocalisation…). La troisième partie permet de s’initier aux aspects les plus avancés de JavaScript tels que Node.js, React ou les Web Workers.', 'https://www.dunod.com/sites/default/files/styles/principal_desktop/public/thumbnails/image/9782100807598-001-X.jpeg', 'Dunod', '2019-11-14', 2),
(3, 'La cité et les astres', 'Clark Arthur', 'Selon la légende, les hommes auraient jadis conquis les étoiles. Jadis, d''immenses villes auraient fleuri à la surface de la Terre. Puis les Envahisseurs sont venus, laissant l''Humanité exsangue, confinée sur sa planète natale. Pendant des millénaires, la cité de Diaspar a servi de refuge aux rares rescapés. Une prison dorée, close sur elle-même, sagement gérée par un ordinateur omnipotent. Dix millions d''habitants y naissent et y renaissent artificiellement, sans jamais vraiment mourir... Jusqu''à l''apparition d''un être unique, Alvin, qui refuse cette existence pétrifiée et sans but. Bravant les lois de Diaspar, il va entamer un fantastique voyage parmi les mondes morts, qui le mènera aux confins de la galaxie.', 'https://assets.edenlivres.fr/medias/9c/857cf5edb9c4bb2f501a1080adfe163de213f3.jpg?h=-&w=1000', 'Follio', '2013-06-17', 1);

TRUNCATE TABLE bibliotheque CASCADE;
INSERT INTO bibliotheque (id, nom, adresse)
VALUES
(1, '5 rue Jean Moulin, 96400 Openville', 'Bibliothèque Moulin'),
(2, '3 avenue Rouget de Lisle, 96400 Openville', 'Bibliothèque Rouget'),
(3, '3 rue de la mairie, 96400 Openville', 'Bibliothèque de la mairie');

TRUNCATE TABLE exemplaire_livre CASCADE;
INSERT INTO exemplaire_livre (id, id_bibliotheque, id_livre, numero_serie, nombre_exemplaire, disponibilite)
VALUES
(1, 1, 1, 16546, 5, TRUE),
(2, 1, 2, 98746, 3, TRUE),
(3, 2, 3, 6546, 1, TRUE),
(4, 3, 2, 05456, 8, TRUE);
