DROP SEQUENCE IF EXISTS public.categorie_id_seq CASCADE;
CREATE SEQUENCE public.categorie_id_seq;

DROP TABLE IF EXISTS public.categorie CASCADE;
CREATE TABLE public.categorie (
    id BIGINT NOT NULL DEFAULT nextval('public.categorie_id_seq'),
    categorie VARCHAR NOT NULL,
    CONSTRAINT categorie_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.categorie_id_seq OWNED BY public.categorie.id;


DROP SEQUENCE IF EXISTS public.livre_id_seq CASCADE;
CREATE SEQUENCE public.livre_id_seq;

DROP TABLE IF EXISTS public.livre CASCADE;
CREATE TABLE public.livre (
    id BIGINT NOT NULL DEFAULT nextval('public.livre_id_seq'),
    titre VARCHAR(70) NOT NULL,
    auteur VARCHAR(50) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    couverture VARCHAR,
    editeur VARCHAR(50),
    edition DATE,
    id_categorie BIGINT NOT NULL,
    CONSTRAINT livre_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;


DROP SEQUENCE IF EXISTS public.bibliotheque_id_seq CASCADE;
CREATE SEQUENCE public.bibliotheque_id_seq;

DROP TABLE IF EXISTS public.bibliotheque CASCADE;
CREATE TABLE public.bibliotheque (
    id BIGINT NOT NULL DEFAULT nextval('public.bibliotheque_id_seq'),
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR NOT NULL,
    CONSTRAINT bibliotheque_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.bibliotheque_id_seq OWNED BY public.bibliotheque.id;


DROP SEQUENCE IF EXISTS public.exemplaire_livre_id_seq CASCADE;
CREATE SEQUENCE public.exemplaire_livre_id_seq;

DROP TABLE IF EXISTS public.exemplaire_livre CASCADE;
CREATE TABLE public.exemplaire_livre (
    id BIGINT NOT NULL DEFAULT nextval('public.exemplaire_livre_id_seq'),
    id_bibliotheque BIGINT NOT NULL,
    id_livre BIGINT NOT NULL,
    numero_serie INTEGER NOT NULL,
    nombre_exemplaire INTEGER NOT NULL,
    disponibilite BOOLEAN NOT NULL,
    CONSTRAINT exemplaire_livre_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.exemplaire_livre_id_seq OWNED BY public.exemplaire_livre.id;


    ALTER TABLE public.livre ADD CONSTRAINT categorie_livre_fk
    FOREIGN KEY (id_categorie)
    REFERENCES public.categorie (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.exemplaire_livre ADD CONSTRAINT livre_exemplaire_fk
    FOREIGN KEY (id_livre)
    REFERENCES public.livre (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.exemplaire_livre ADD CONSTRAINT bibliotheque_exemplaire_fk
    FOREIGN KEY (id_bibliotheque)
    REFERENCES public.bibliotheque (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;