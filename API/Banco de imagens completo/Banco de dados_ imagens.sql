create database banco_imagens;
use banco_imagens;

create table imagens(
id int auto_increment primary key,
nome varchar(255),
url text
);

insert into imagens (nome, url) values
("Anavitória", "anavitoria.png");

select * from imagens;