CREATE DATABASE rh_tech;
USE rh_tech;

CREATE TABLE funcionarios(
id int primary key auto_increment,
nome varchar(255),
email varchar(255),
senha varchar(20),
cep varchar(8),
endereco varchar(255),
nomero varchar(10),
bairro varchar(50),
cidade varchar(50),
estado varchar(50)
);

CREATE TABLE cargos(
id int primary key auto_increment,
nome varchar(255),
descriçao varchar(300)
);

INSERT INTO funcionarios (nome, email, senha, cep, endereco, nomero, bairro, cidade, estado) 
VALUES 
('João Silva', 'joao.silva@email.com', 'senha123', '12345678', 'Rua A, 123', '123', 'Centro', 'São Paulo', 'SP'),
('Maria Oliveira', 'maria.oliveira@email.com', 'senha456', '23456789', 'Rua B, 456', '456', 'Vila Nova', 'Rio de Janeiro', 'RJ'),
('Carlos Souza', 'carlos.souza@email.com', 'senha789', '34567890', 'Rua C, 789', '789', 'Jardim das Flores', 'Belo Horizonte', 'MG');


INSERT INTO cargos (nome, descriçao)
VALUES
('Desenvolvedor', 'Responsável pelo desenvolvimento de sistemas e aplicações.'),
('Analista de Sistemas', 'Análise de requisitos e elaboração de especificações técnicas.'),
('Gerente de Projetos', 'Coordenação de equipes e acompanhamento do progresso de projetos.');

SELECT * INTO funcionarios;
SELECT * INTO cargos;