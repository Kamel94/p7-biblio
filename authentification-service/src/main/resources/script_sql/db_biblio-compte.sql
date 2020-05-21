DROP SEQUENCE IF EXISTS public.genre_id_seq CASCADE;
CREATE SEQUENCE public.genre_id_seq;

DROP TABLE IF EXISTS public.genre CASCADE;
CREATE TABLE public.genre (
    id BIGINT NOT NULL DEFAULT nextval('public.genre_id_seq'),
    genre VARCHAR(5) NOT NULL,
    CONSTRAINT genre_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.genre_id_seq OWNED BY public.genre.id;


DROP SEQUENCE IF EXISTS public.utilisateur_id_seq CASCADE;
CREATE SEQUENCE public.utilisateur_id_seq;

DROP TABLE IF EXISTS public.utilisateur CASCADE;
CREATE TABLE public.utilisateur (
    id BIGINT NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    adresse VARCHAR NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    statut VARCHAR(10) NOT NULL,
    telephone VARCHAR(15),
    id_genre BIGINT NOT NULL,
    actif BOOLEAN NOT NULL,
    date_creation TIMESTAMP,
    date_modif TIMESTAMP,
    utilisateur_createur TIMESTAMP,
    utilisateur_modif TIMESTAMP,
    CONSTRAINT utilisateur_pk PRIMARY KEY (id)
);

ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id;


ALTER TABLE public.utilisateur ADD CONSTRAINT genre_utilisateur_fk
FOREIGN KEY (id_genre)
REFERENCES public.genre (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;