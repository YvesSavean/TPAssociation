/* Configuration Base de données */
/* 1/ Lancer Derby sur le pc */
/* 2/ puis configurer derby sur eclipse window preference */
/* 3/ puis new Derby sur eclipse perspective Database Development */
/* 4/ Infos : */
/* name : DerbyBDDAssociation */
/* data base : DerbyBDDAssociation */
/* host : localhost */
/* port : 1527 */
/* login : sony */
/* mdp : sony */
/* --> par défaut le schéma a le même nom que le login --> schéma : sony */

/* Suppression des tables */
drop table CATALOGUE_ARTICLE;
drop table CATALOGUE;
drop table COMMANDE_ARTICLE;
drop table COMMANDE;
drop table ARTICLE_STOCK;
drop table ARTICLE;
drop table ADHERENT;

/* Création des tables */
create table ADHERENT(
    IDENTIFIANT varchar(30),
    MOTDEPASSE varchar(20),
    NOMDEFAMILLE varchar(30),
    PRENOM varchar(30),
    ADRESSE varchar(50),
    COMPLEMENTADRESSE varchar(50),
    CODEPOSTAL varchar(5),
    VILLE varchar(30),
    PAYS varchar(30),
    constraint ADHERENT_PK primary key (IDENTIFIANT)
);

create table ARTICLE(
    CODE varchar(10),
    NOM varchar(30),
    PRIX numeric,
    constraint ARTICLE_PK primary key (CODE)
);

create table COMMANDE(
    ID int,
    DATECOMMANDE date,
    IDENTIFIANT varchar(30),
    constraint COMMANDE_PK primary key (ID),
    constraint COMMANDE_FK foreign key (IDENTIFIANT) references ADHERENT
);

create table COMMANDE_ARTICLE(
    ID int,
    CODE varchar(10),
    QUANTITE int,
    constraint COMMANDE_ARTICLE_PK primary key (ID, CODE),
    constraint COMMANDE_ARTICLE_FK1 foreign key (CODE) references ARTICLE,
    constraint COMMANDE_ARTICLE_FK2 foreign key (ID) references COMMANDE
);

create table CATALOGUE(
    NOM varchar(30),
    constraint CATALOGUE_PK primary key (NOM)
);

create table CATALOGUE_ARTICLE(
    NOM varchar(30),
    CODE varchar(10),
    constraint CATALOGUE_ARTICLE_PK primary key (NOM, CODE),
    constraint CATALOGUE_ARTICLE_FK1 foreign key (NOM) references CATALOGUE,
    constraint CATALOGUE_ARTICLE_FK2 foreign key (CODE) references ARTICLE
);

create table ARTICLE_STOCK(
    CODE varchar(10),
    NOMBREDISPONIBLE int,
    constraint ARTICLE_STOCK_PK primary key (CODE),
    constraint ARTICLE_STOCK_FK foreign key (CODE) references ARTICLE
);

/* Données de test */
insert into ADHERENT values('alan', 'alan', 'lb', 'alan', '155 Rue Général Buat', '', '44000', 'Nantes', 'France');
insert into ADHERENT values('yves', 'yves', 'la', 'yves', '212 Rue Joffre', '', '44000', 'Nantes', 'France');
insert into ADHERENT values('mickael', 'alan', 'lc', 'mickael', '13 Rue des Plantes', '2ème étage', '44000', 'Nantes', 'France');
insert into ADHERENT values('gildas', 'alan', 'lg', 'gildas', '12 Rue Foch', '', '44000', 'Nantes', 'France');
insert into ADHERENT values('leo', 'alan', 'ld', 'leo', '1 Rue Camelia', '', '44000', 'Nantes', 'France');

insert into CATALOGUE values('Divers');

insert into ARTICLE values('aq', 'Aquarium', 230.00);
insert into ARTICLE values('ec', 'Ecran LG', 270.00);
insert into ARTICLE values('pc', 'PC DELL', 100.00);
insert into ARTICLE values('po', 'Portable Sony', 500.00);
insert into ARTICLE values('ba', 'Bateau Leader', 2000.00);

insert into CATALOGUE_ARTICLE values('Divers', 'aq');
insert into CATALOGUE_ARTICLE values('Divers', 'ec');
insert into CATALOGUE_ARTICLE values('Divers', 'pc');
insert into CATALOGUE_ARTICLE values('Divers', 'po');
insert into CATALOGUE_ARTICLE values('Divers', 'ba');

insert into ARTICLE_STOCK values('aq', 3);
insert into ARTICLE_STOCK values('ec', 1);
insert into ARTICLE_STOCK values('pc', 4);
insert into ARTICLE_STOCK values('po', 2);
insert into ARTICLE_STOCK values('ba', 3);

insert into COMMANDE values(1, '2013-01-01', 'alan'); /*yyyy-mm-dd*/
insert into COMMANDE values(2, '2013-01-10', 'alan');
insert into COMMANDE values(3, '2013-02-10', 'yves');
insert into COMMANDE values(4, '2013-02-05', 'alan');
insert into COMMANDE values(5, '2013-02-04', 'yves');

insert into COMMANDE_ARTICLE values(1, 'aq', 1);
insert into COMMANDE_ARTICLE values(1, 'ba', 1);
insert into COMMANDE_ARTICLE values(2, 'po', 1);
insert into COMMANDE_ARTICLE values(2, 'ec', 1);
insert into COMMANDE_ARTICLE values(2, 'pc', 1);


select * from ADHERENT;
select * from ARTICLE;
select * from ARTICLE_STOCK;
select * from CATALOGUE;
select * from CATALOGUE_ARTICLE;
select * from COMMANDE;
select * from COMMANDE_ARTICLE;