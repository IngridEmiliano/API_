CREATE DATABASE rh_tech;
USE rh_tech;

CREATE TABLE funcionarios(
id int primary key auto_increment,
nome varchar(255) not null,
email varchar(255) not null,
senha varchar(20) not null,
cep varchar(8) not null,
endereco varchar(255) not null,
numero varchar(10) not null,
bairro varchar(50) not null,
cidade varchar(50) not null,
estado varchar(50) not null
);

CREATE TABLE cargos(
id int primary key auto_increment,
nome varchar(255),
descricao varchar(300) not null
);

INSERT INTO funcionarios (nome, email, senha, cep, endereco, numero, bairro, cidade, estado) 
VALUES 
('João Silva', 'joao.silva@email.com', 'senha123', '12345678', 'Rua A, 123', '123', 'Centro', 'São Paulo', 'SP'),
('Maria Oliveira', 'maria.oliveira@email.com', 'senha456', '23456789', 'Rua B, 456', '456', 'Vila Nova', 'Rio de Janeiro', 'RJ'),
('Carlos Souza', 'carlos.souza@email.com', 'senha789', '34567890', 'Rua C, 789', '789', 'Jardim das Flores', 'Belo Horizonte', 'MG');


INSERT INTO cargos (nome, descricao)
VALUES
('Desenvolvedor', 'Responsável pelo desenvolvimento de sistemas e aplicações.'),
('Analista de Sistemas', 'Análise de requisitos e elaboração de especificações técnicas.'),
('Gerente de Projetos', 'Coordenação de equipes e acompanhamento do progresso de projetos.');

SELECT * FROM funcionarios;
SELECT * FROM cargos;