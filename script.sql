create table categorie
(
    refCat int         not null auto_increment primary key,
    Cat    varchar(40) not null
);

create table article(
    codeArticle  int auto_increment primary key,
    designation  varchar(30) null,
    auteur       varchar(30) not null,
    titre        varchar(30) not null,
    prix         double      not null,
    refCategorie int         not null,
    photo        varchar(30) not null,
    stock        int         not null,
    constraint article_cate__fk
        foreign key (refCategorie) references categorie (refCat)
);

create table client
(
    id          int auto_increment primary key,
    email       varchar(40)  not null,
    nom         varchar(40)  not null,
    prenom      varchar(40)  not null,
    adresse     varchar(50)  not null,
    codepostale int          not null,
    ville       varchar(50)  not null,
    tel         varchar(40)  not null,
    mdp         varchar(400) not null,
    constraint Client_email_pk_
        unique (email)
);
