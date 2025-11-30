-- Arquivo: src/main/resources/schema-snpcars-sql.sql

-- 1. Tabela para armazenar os Vendedores
CREATE TABLE IF NOT EXISTS vendedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    cargo VARCHAR(50),
    especialidade VARCHAR(100),
    foto_url VARCHAR(255)
);

-- 2. Tabela para armazenar os Carros do Catálogo
CREATE TABLE IF NOT EXISTS carro (
    id SERIAL PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    quilometragem INT,
    cor VARCHAR(30),
    combustivel VARCHAR(30),
    preco NUMERIC(10, 2) NOT NULL, 
    foto_url VARCHAR(255),
    
    -- Chave Estrangeira: vincula o carro ao seu vendedor responsável
    vendedor_id INT, 
    FOREIGN KEY (vendedor_id) REFERENCES vendedor (id) ON DELETE SET NULL
);