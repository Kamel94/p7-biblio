TRUNCATE TABLE pret CASCADE;
INSERT INTO pret (id, date_pret, date_retour, utilisateur_id, exemplaire_id, statut, prolongation)
VALUES
(1, '2020/03/21', '2020/04/18', 3, 1, 'PRET', 0),
(2, '2020/04/20', '2020/06/15', 2, 3, 'PRET', 1),
(3, '2020/04/20', '2020/05/18', 3, 2, 'RENDU', 0);