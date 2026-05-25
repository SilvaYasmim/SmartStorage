-- SmartStorage — Script de criação da base de dados
-- SGBD: PostgreSQL
-- Autora: Yasmim Silva | UFCD 5425

-- Apagar tabelas se já existirem (ordem inversa por causa das FK)
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS recipiente;
DROP TABLE IF EXISTS localizacao;
DROP TABLE IF EXISTS arrecadacao;

-- ─────────────────────────────────────────
-- Tabela: arrecadacao
-- ─────────────────────────────────────────
CREATE TABLE arrecadacao (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    descricao   VARCHAR(255)
);

-- ─────────────────────────────────────────
-- Tabela: localizacao
-- ─────────────────────────────────────────
CREATE TABLE localizacao (
    id                BIGSERIAL PRIMARY KEY,
    nome              VARCHAR(100) NOT NULL,
    descricao         VARCHAR(255),
    arrecadacao_id    BIGINT NOT NULL,
    CONSTRAINT fk_localizacao_arrecadacao
        FOREIGN KEY (arrecadacao_id)
        REFERENCES arrecadacao(id)
        ON DELETE CASCADE
);

-- ─────────────────────────────────────────
-- Tabela: recipiente
-- ─────────────────────────────────────────
CREATE TABLE recipiente (
    id               BIGSERIAL PRIMARY KEY,
    nome             VARCHAR(100) NOT NULL,
    descricao        VARCHAR(255),
    categoria        VARCHAR(100),
    qr_code          VARCHAR(255) NOT NULL UNIQUE,
    data_criacao     DATE NOT NULL DEFAULT CURRENT_DATE,
    localizacao_id   BIGINT NOT NULL,
    CONSTRAINT fk_recipiente_localizacao
        FOREIGN KEY (localizacao_id)
        REFERENCES localizacao(id)
        ON DELETE CASCADE
);

-- ─────────────────────────────────────────
-- Tabela: item
-- ─────────────────────────────────────────
CREATE TABLE item (
    id               BIGSERIAL PRIMARY KEY,
    nome             VARCHAR(100) NOT NULL,
    descricao        VARCHAR(255),
    quantidade       INTEGER NOT NULL DEFAULT 1 CHECK (quantidade >= 0),
    categoria        VARCHAR(100),
    foto_path        VARCHAR(500),
    recipiente_id    BIGINT NOT NULL,
    CONSTRAINT fk_item_recipiente
        FOREIGN KEY (recipiente_id)
        REFERENCES recipiente(id)
        ON DELETE CASCADE
);

-- ─────────────────────────────────────────
-- Índices para pesquisa e filtros
-- ─────────────────────────────────────────
CREATE INDEX idx_localizacao_arrecadacao ON localizacao(arrecadacao_id);
CREATE INDEX idx_recipiente_localizacao  ON recipiente(localizacao_id);
CREATE INDEX idx_recipiente_categoria    ON recipiente(categoria);
CREATE INDEX idx_item_recipiente         ON item(recipiente_id);
CREATE INDEX idx_item_categoria          ON item(categoria);
CREATE INDEX idx_item_nome               ON item(nome);

-- ─────────────────────────────────────────
-- Dados de exemplo (opcional — para testar)
-- ─────────────────────────────────────────
INSERT INTO arrecadacao (nome, descricao) VALUES
    ('Garagem', 'Arrecadação principal junto à entrada'),
    ('Cave', 'Cave do edifício, prateleiras ao fundo');

INSERT INTO localizacao (nome, descricao, arrecadacao_id) VALUES
    ('Prateleira A', 'Prateleira superior esquerda', 1),
    ('Canto direito', 'Junto à parede direita', 1),
    ('Prateleira B', 'Prateleira do meio', 2);

INSERT INTO recipiente (nome, descricao, categoria, qr_code, localizacao_id) VALUES
    ('Caixa Natal', 'Decorações de Natal', 'Natal', 'QR-001', 1),
    ('Saco Azul',   'Roupa de inverno',    'Roupa', 'QR-002', 1),
    ('Caixa Ferramentas', 'Chaves e alicates', 'Ferramentas', 'QR-003', 2);

INSERT INTO item (nome, descricao, quantidade, categoria, recipiente_id) VALUES
    ('Luzes de Natal',   'Fio de luzes LED brancas', 3, 'Natal',       1),
    ('Estrela do topo',  'Estrela dourada',           1, 'Natal',       1),
    ('Casaco de inverno','Casaco cinzento tamanho M',  1, 'Roupa',       2),
    ('Chave de fendas',  'Philips tamanho médio',      2, 'Ferramentas', 3),
    ('Alicate',          'Alicate universal',          1, 'Ferramentas', 3);