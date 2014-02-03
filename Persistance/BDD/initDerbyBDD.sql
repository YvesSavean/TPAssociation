drop table CATALOGUE_ARTICLE;
drop table CATALOGUE;
drop table COMMANDE_ARTICLE;
drop table COMMANDE;
drop table ARTICLE_STOCK;
drop table ARTICLE;
drop table ADHERENT;

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