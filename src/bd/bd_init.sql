-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--

DROP TABLE RETRAITS;
DROP TABLE ARTICLES_VENDUS;
DROP TABLE ENCHERES;
DROP TABLE CATEGORIES;
DROP TABLE UTILISATEURS;


CREATE TABLE CATEGORIES (
                            id   INTEGER IDENTITY(1,1) NOT NULL,
                            libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (id)



CREATE TABLE UTILISATEURS (
                              id   INTEGER IDENTITY(1,1) NOT NULL,
                              pseudo           VARCHAR(30) NOT NULL,
                              nom              VARCHAR(30) NOT NULL,
                              prenom           VARCHAR(30) NOT NULL,
                              email            VARCHAR(50) NOT NULL,
                              tel              VARCHAR(15),
                              rue              VARCHAR(30) NOT NULL,
                              CP               VARCHAR(10) NOT NULL,
                              ville            VARCHAR(50) NOT NULL,
                              mdp              VARCHAR(30) NOT NULL,
                              credit           INTEGER NOT NULL,
                              admin            bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (id)


CREATE TABLE ARTICLES_VENDUS (
                                 id                            INTEGER IDENTITY(1,1) NOT NULL,
                                 nom                           VARCHAR(30) NOT NULL,
                                 description                   VARCHAR(300) NOT NULL,
                                 date_debut_encheres           DATE NOT NULL,
                                 date_fin_encheres             DATE NOT NULL,
                                 prix_initial                  INTEGER,
                                 prix_vente                    INTEGER,
                                 id_utilisateur                INTEGER NOT NULL,
                                 id_categorie                  INTEGER NOT NULL
)



ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (id)


CREATE TABLE RETRAITS (
                          id_article       INTEGER NOT NULL,
                          rue              VARCHAR(30) NOT NULL,
                          CP      VARCHAR(15) NOT NULL,
                          ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (id_article)
ALTER TABLE RETRAITS
    ADD CONSTRAINT retrait_article_fk FOREIGN KEY ( id_article ) REFERENCES  ARTICLES_VENDUS (id)
        ON DELETE NO ACTION
        ON UPDATE no action


CREATE TABLE ENCHERES(
                         id  INTEGER IDENTITY(1,1) NOT NULL,
                         date datetime NOT NULL,
                         montant INTEGER NOT NULL,
                         id_article INTEGER NOT NULL,
                         id_utilisateur INTEGER NOT NULL
)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY ( id)

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( id_utilisateur ) REFERENCES UTILISATEURS ( id )
        ON DELETE NO ACTION
        ON UPDATE no action

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY ( id_article ) REFERENCES ARTICLES_VENDUS ( id )
        ON DELETE NO ACTION
        ON UPDATE no action


ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( id_categorie ) REFERENCES categories ( id )
        ON DELETE NO ACTION
        ON UPDATE no action

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( id_utilisateur ) REFERENCES utilisateurs ( id )
        ON DELETE NO ACTION
        ON UPDATE no action


