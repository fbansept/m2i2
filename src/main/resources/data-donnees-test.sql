INSERT INTO role (id, nom) VALUES
       (1, 'CLIENT'),
       (2, 'VENDEUR'),
       (3, 'ADMINISTRATEUR');

INSERT INTO utilisateur (id, email, password, role_id) VALUES
    (1, 'a@a', 'root' , 1),
    (2, 'b@b', 'root' , 2),
    (3, 'c@c', 'root' , 3);

INSERT INTO produit (nom, prix, vendeur_id) VALUES
    ('filament PLA', 25.99, NULL),
    ('buse 2mm', 50.20, 2);

INSERT INTO etiquette (nom) VALUES
        ('best seller'),
        ('promo');

INSERT INTO etiquette_produit (produit_id, etiquette_id) VALUES
        (1 , 1),
        (1 , 2),
        (2, 2);

