```mermaid
erDiagram


Modelo {
	BIGINT id PK
    VARCHAR(255) nome
    DOUBLE multiplicador
}

Peca {
	BIGINT id PK
    VARCHAR(255) nome
    DOUBLE precoBase
	BIGINT modelo_id FK
}

Medida {
  	BIGINT id PK
  	VARCHAR(255) nome
    DOUBLE tamanho
    BIGINT peca_id FK
    BIGINT cliente_id FK
}


ItemPedido {
	BIGINT id PK
	VARCHAR(255) tamanho
	VARCHAR(255) cor
	NUMBER valorItem
	BIGINT peca_id FK
	BIGINT modelo_id FK
	BIGINT tecido_id FK
	BIGINT orcamento_id FK
}

Adicional {
	BIGINT id PK
	VARCHAR(255) nome
	multiplicador double
	BIGINT itemPedido_id FK
}

Tecido {
	BIGINT id PK
	VARCHAR(255) nome
	NUMBER preco
}

Cliente {
	BIGINT id PK
	VARCHAR(255) nome
	VARCHAR(255) telefone
	VARCHAR(255) email
}

Usuario {
	BIGINT id PK
	VARCHAR(255) nomeUsuario
	VARCHAR(255) senhaUsuario
	VARCHAR(255) emailUsuario
}

Orcamento {
	BIGINT id PK
	TIMESTAMP dataCriacao
	NUMBER valorTotal
	VARCHAR(255) observacoes
	BIGINT usuario_id FK
	BIGINT cliente_id FK
}

Pedido{
	BIGINT id PK
    TIMESTAMP dataEntrega
    BOOLEAN pago
    TIMESTAMP dataPagamento 
    VARCHAR(255) tipoPagamento
    VARCHAR(255) situacao
    BIGINT orcamento_id FK
}


Peca }o--o| Modelo : Has
Cliente |o--o{ Medida : has
Medida }o--o| Peca : Has
ItemPedido |o--o{ Adicional : Has

ItemPedido }o--o| Tecido : has
ItemPedido }o--o| Modelo : has
ItemPedido }o--o| Orcamento : has

Orcamento }o--o| Cliente : has
Orcamento }o--o| Usuario : has
Orcamento |o--o| Pedido : has


```

```sql

CREATE TABLE Tecido (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    preco DECIMAL(18,2)
);


CREATE TABLE Modelo (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    multiplicador DOUBLE
);

CREATE TABLE Cliente (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    telefone VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Usuario (
    id BIGINT PRIMARY KEY,
    nomeUsuario VARCHAR(255),
    senhaUsuario VARCHAR(255),
    emailUsuario VARCHAR(255)
);

CREATE TABLE Peca (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    precoBase DOUBLE,
    modelo_id BIGINT,
    FOREIGN KEY (modelo_id) REFERENCES Modelo(id)
);

CREATE TABLE Medida (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    tamanho DOUBLE,
    peca_id BIGINT,
    cliente_id BIGINT,
    FOREIGN KEY (peca_id) REFERENCES Peca(id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE Orcamento (
    id BIGINT PRIMARY KEY,
    dataCriacao TIMESTAMP,
    valorTotal DECIMAL(18,2),
    observacoes VARCHAR(255),
    usuario_id BIGINT,
    cliente_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE Pedido (
    id BIGINT PRIMARY KEY,
    dataEntrega TIMESTAMP,
    pago BOOLEAN,
    dataPagamento TIMESTAMP,
    tipoPagamento VARCHAR(255),
    situacao VARCHAR(255),
    orcamento_id BIGINT,
    FOREIGN KEY (orcamento_id) REFERENCES Orcamento(id)
);

CREATE TABLE ItemPedido (
    id BIGINT PRIMARY KEY,
    tamanho VARCHAR(255),
    cor VARCHAR(255),
    valorItem DECIMAL(18,2),
    peca_id BIGINT,
    modelo_id BIGINT,
    tecido_id BIGINT,
    orcamento_id BIGINT,
    FOREIGN KEY (peca_id) REFERENCES Peca(id),
    FOREIGN KEY (modelo_id) REFERENCES Modelo(id),
    FOREIGN KEY (tecido_id) REFERENCES Tecido(id),
    FOREIGN KEY (orcamento_id) REFERENCES Orcamento(id)
);

CREATE TABLE Adicional (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255),
    multiplicador DOUBLE,
    itemPedido_id BIGINT,
    FOREIGN KEY (itemPedido_id) REFERENCES ItemPedido(id)
);

```

