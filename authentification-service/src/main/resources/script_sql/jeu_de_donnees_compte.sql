TRUNCATE TABLE genre CASCADE;
INSERT INTO genre (id, genre)
VALUES
(1, 'Homme'),
(2, 'Femme');

TRUNCATE TABLE utilisateur CASCADE;
INSERT INTO utilisateur (id, nom, prenom, adresse, email, password, statut, telephone, id_genre, actif, date_creation, date_modif, utilisateur_createur, utilisateur_modif)
VALUES
(1, 'Selhemi', 'Ahmed', '7 rue la glacière, 96400 Openville', 'ahmedselhemi@gmail.com', '$2y$10$A6xxolOQel7DO0WUNzJcU.0CbJcmvdmlqhkwINMhdZJwje/SFHa36', 'USAGER', '06 86 39 04 27', 1, TRUE, '2019-05-19', '2019-05-19', 1, 1),
(2, 'Mercier', 'Robert', '6 rue Rogger Derry, 96400 Openville', 'robertmercier@gmail.com', '$2a$08$7TzsnvtVaFvE4DnnXh9tJu5YoXDfv6sTSija4zNpvlith7yqYHi82', 'USAGER', '06 54 03 49 81', 1, TRUE, '2019-05-19', '2019-05-19', 2, 2),
(3, 'Dupont', 'Jean', '4 rue d''Alger, 75001 Paris', 'jeandupont@gmail.com', '$2y$10$hBaR97B3V6K/n7WoShHLg.ImjFOU6ZGDIhbx86AVA2Lr8VxLhZbuG', 'USAGER', '07 32 76 04 38', 1, TRUE, '2019-05-19', '2019-05-19', 3, 3);