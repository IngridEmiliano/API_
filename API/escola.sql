create database escola;
use escola;

create table alunos(
id_alunos int auto_increment primary key,
nome varchar(200) not null,
email varchar(200)
);

INSERT INTO alunos(nome, email) VALUES
('João da Silva', 'joao.silva@email.com'),
('Maria Oliveira', 'maria.oliveira@email.com'),
('Pedro Santos', null);

select * from alunos;