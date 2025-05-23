create database ControleEstoque;
USE ControleEstoque;

create table usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    setor VARCHAR(100)
);

create table produtos (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    status VARCHAR(50) NOT NULL
);

create table saida (
    id_saida INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    id_usuario INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade_saida INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY (id_produto) REFERENCES produtos(id_produto)
    );
    
INSERT INTO usuario (nome, endereco, setor) VALUES
('João Silva', 'Rua das Flores, 123', 'Financeiro'),
('Maria Souza', 'Av. Brasil, 456', 'Compras'),
('Carlos Pereira', 'Rua Central, 789', 'Vendas'),
('Ana Costa', 'Av. Paulista, 321', 'Almoxarifado');

INSERT INTO produtos (nome, quantidade, status) VALUES
('Teclado', 50, 'Ativo'),
('Mouse', 100, 'Ativo'),
('Monitor', 30, 'Ativo'),
('Cabo HDMI', 200, 'Ativo'),
('Notebook', 15, 'Ativo');

INSERT INTO saida (data, id_usuario, id_produto, quantidade_saida) VALUES
('2025-05-01', 1, 2, 5),   
('2025-05-02', 2, 4, 10),  
('2025-05-03', 3, 1, 3),   
('2025-05-04', 4, 5, 1),  
('2025-05-05', 1, 3, 2);   

SELECT * FROM usuario;
SELECT * FROM produtos;
SELECT * FROM saida;

    