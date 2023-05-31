

# Table of Contents

1. [CLASS DIAGRAM](#class-diagram)
2. [DATABASE SCHEMA](#database-schema)
3. [TODO](#todo)
   1. [Entidades](#entidades)
   2. [Funcionalidades](#funcionalidades)
   3. [Enuns](#enuns-opcional-ser-enum)
4. [STATE DIAGRAM](#state-diagram)







## CLASS DIAGRAM

```mermaid
classDiagram

direction LR

class Medida{
	- id: inter
	- nome: String
	- tamanho: Double
}

class Peca{
	- id: Long
	- nome: String
	- precoBase: Double
	- medidas: List< Medida >
}

class Modelo{
	- id: Long
	- nome: String
	- pecas: List< Peca >
	- multiplicador: Double
}

class Cliente{
	- id: Long
	- nome: String
	- telefone: String
	- email: String
	- medidas: List< Medida >
}

class Orcamento{
	- id: Long
	- usuario: Usuario
	- cliente: Cliente
	- dataCriacao: Date
	- itensPedido: List< ItemPedido >
	- valorTotal: Double
	- observacoes: String
}

class Usuario{
	- id: Long
	+ nomeUsuario: String
	+ senhaUsuario: String
	+ emailUsuario: String
}

class Pedido{
	- dataEntraga: Date
	- pago: Boolean
	- dataPagamento: Date
	- tipoPagamento: Enum
	- situacao: Enum
}

class ItemPedido{
	- id: Long
	- peca: Peca
	- tamanho: Enum
	- modelo: Modelo
	- tecido: Tecido
	- cor: Enum
	- adicionais: List< Adicional >
	- valorItem: Double
}

class Adicional{
	- id: Long
	+ nome: String
	+ multiplicador: Double
}


class Tecido{
	- id: Long
	+ nome: String
	+ preco: Double
}

Modelo --> ItemPedido
Adicional --> ItemPedido
Tecido --> ItemPedido
Peca --> ItemPedido
Peca --o Modelo
ItemPedido --o Orcamento
Cliente --> Orcamento
Usuario --> Orcamento
Orcamento --|> Pedido : extend
Medida --o Cliente
Medida --o Peca



```



## DATABASE SCHEMA

```sql
CREATE TABLE Medida (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    tamanho DOUBLE
);

CREATE TABLE Peca (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    precoBase DOUBLE
);

CREATE TABLE Peca_Medida (
    peca_id INT,
    medida_id INT,
    FOREIGN KEY (peca_id) REFERENCES Peca(id),
    FOREIGN KEY (medida_id) REFERENCES Medida(id)
);

CREATE TABLE Modelo (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    multiplicador DOUBLE
);

CREATE TABLE Modelo_Peca (
    modelo_id INT,
    peca_id INT,
    FOREIGN KEY (modelo_id) REFERENCES Modelo(id),
    FOREIGN KEY (peca_id) REFERENCES Peca(id)
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    telefone VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Cliente_Medida (
    cliente_id INT,
    medida_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (medida_id) REFERENCES Medida(id)
);

CREATE TABLE Orcamento (
    id INT PRIMARY KEY,
    usuario_id INT,
    cliente_id INT,
    dataCriacao DATE,
    valorTotal DOUBLE,
    observacoes VARCHAR(255),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    nomeUsuario VARCHAR(255),
    senhaUsuario VARCHAR(255),
    emailUsuario VARCHAR(255)
);

CREATE TABLE Pedido (
    id INT PRIMARY KEY,
    dataEntrega DATE,
    pago BOOLEAN,
    dataPagamento DATE,
    tipoPagamento VARCHAR(255),
    situacao VARCHAR(255)
);

CREATE TABLE ItemPedido (
    id INT PRIMARY KEY,
    peca_id INT,
    tamanho VARCHAR(255),
    modelo_id INT,
    tecido_id INT,
    cor VARCHAR(255),
    valorItem DOUBLE,
    FOREIGN KEY (peca_id) REFERENCES Peca(id),
    FOREIGN KEY (modelo_id) REFERENCES Modelo(id),
    FOREIGN KEY (tecido_id) REFERENCES Tecido(id)
);

CREATE TABLE ItemPedido_Adicional (
    itemPedido_id INT,
    adicional_id INT,
    FOREIGN KEY (itemPedido_id) REFERENCES ItemPedido(id),
    FOREIGN KEY (adicional_id) REFERENCES Adicional(id)
);

CREATE TABLE Adicional (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    multiplicador DOUBLE
);

CREATE TABLE Tecido (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    preco DOUBLE
);

```





## TODO



### Entidades

- Usuario 

    - [ ] A classe Usuario vai armazenar dados do usuário do sistema. 



- Orcamento 

    - [ ] A classe Orcamento vai armazenar as informações de um orçamento de um pedido do ateliê. 

    - [ ] O orçamento tem um usuário e um cliente vinculados e uma lista com todos os itens do pedido. 

    - [ ] O valorTotal é calculado automaticamente baseados no valor de cada item do pedido.

 

- Pedido 

    - [ ] A classe Pedido herda de Orcamento e armazena todas as informações de um pedido do ateliê. 

    - [ ] Os atributos obrigatórios de um pedido além dos atributos do orçamento é a dataEntrega e a situação. 



- ItemPedido 

    - [ ] A classe ItemPedido armazena um item que será utilizado em um orçamento ou em um pedido. 

    - [ ] O valorItem é calculado automaticamente usando o precoBase da peça + preco do tecido + (os multiplicadores dos adicionais * o pecoBase da peça) + (o multiplicador do modelo * o precoBase da peça) + (o multiplicador do tamanho * o precoBase da peça). 



- Tecido 

    - [ ] A classe Tecido armazena o tecido e seu preço por metro. 



- Adicional 

    - [ ] A classe Adicional armazena o adicional e seu multiplicado < 1.



- Modelo 

    - [ ] A classe Modelo armazena um modelo e seu multiplicado < 1. 

    - [ ] Também apresenta a lista de peças que podem utilizar aquele modelo. 



- Peca 

    - [ ] A classe Peca armazena o nome e precoBase de uma peça. 

    - [ ] Também armazena uma lista com medidas específicas para a peça (ex. comprimento da calça) 



- Medida 

    - [ ] A classe Medida armazena uma medida em centímetros. 



- Cliente 

    - [ ] A classe Cliente armazena todas as informações pessoais de um cliente do ateliê. 

    - [ ] Também armazena uma lista de medidas base do cliente (ex. tamanho do busto) 



### Funcionalidades

- [ ] Criar conta para o usuário. 

- [ ] Recuperar senha do usuário. 

- [ ] Excluir usuário (deleta permanentemente banco de dados junto com todos os pedidos e orçamentos vinculados). 

- [ ] Cadastrar cliente (durante o cadastro do usuário é possível adicionar medidas ao cliente) 

- [ ] Editar cliente (também é possível adicionar, editar e remover uma medida do cliente). 

- [ ] Excluir cliente (remove todas as informações pessoais, mas mantém os pedidos e orçamentos como cliente desconhecido). 

- [ ] Adicionar tecido. 

- [ ] Editar tecido (altera preço). 

- [ ] Adicionar adicional. 

- [ ] Editar tecido (altera o multiplicador). 

- [ ] Adicionar modelo. 

- [ ] Editar modelo (altera o multiplicado e pode adicionar ou remover peças da lista)

- [ ] Adicionar peça. 

- [ ] Editar peça (altera o peço base e pode adicionar ou remover medidas da lista) 

- [ ] Criar orçamento (ao adicionar um orçamento é possível criar um cliente e criar itens do pedido para vincular ou orçamento) 

- [ ] Atualizar orçamento. 

- [ ] Excluir orçamento. 

- [ ] Criar pedido (ao adicionar um orçamento é possível criar um cliente e criar itens do pedido para vincular ou orçamento) 

- [ ] Atualizar pedido (não é possível com pedidos "concluído") 

- [ ] Criar item do pedido (só é possível criar durante a criação de orçamento ou de pedido, é possível adicionar um peça, modelo, adicional ou tecido durante a criação do item) 

- [ ] Listar pedidos (criar filtros) 

- [ ] Listar orçamentos. 

- [ ] Listar clientes. 

- [ ] Listar tecidos.

- [ ] Listar adicionais. 

- [ ] Listar peças. 

- [ ] Listar modelos. 

- [ ] Relatório do pedido (gerar um arquivo com os dados do pedido



### Enuns opcional ser Enum

- tipoPagamento (Pedido) 

    - [ ] débito 

    - [ ] crédito 

    - [ ] dinheiro 

    - [ ] pix 



- situacao (Pedido) 

    - [ ] criado 

    - [ ] em produção 

    - [ ] pago 

    - [ ] entregue 

    - [ ] em ajuste 

    - [ ] finalizado 



- tamanho(ItemPedido) 

    - [ ] pp (multiplicador = 0,00) 

    - [ ] p (multiplicador = 0,02) 

    - [ ] m (multiplicador = 0,04) 

    - [ ] g (multiplicador = 0,06) 

    - [ ] gg (multiplicador = 0,08) 

    - [ ] exgg (multiplicador = 0,1) 



- cor (ItemPedido) 

    - [ ] Azul 

    - [ ] Amarelo 

    - [ ] Verde 

    - [ ] Vermelho 

    - [ ] Preto 

    - [ ] Roxo 

    - [ ] Laranja 

    - [ ] Branco 

    - [ ] Marron





## STATE DIAGRAM

```mermaid
stateDiagram-v2
    state if_state <<choice>>
    state if_state2 <<choice>>
    state if_state3 <<choice>>
    state if_state4 <<choice>>
    state if_state5 <<choice>>
    state if_state6 <<choice>>
    state join_state <<join>>
    state join_state2 <<join>>
    [*] --> FazPedido
    FazPedido --> if_state
    if_state --> CadastrarCliente: Não é cliente 
    if_state --> AdicionarCliente : é Cliente
    CadastrarCliente --> AdicionarCliente
    AdicionarCliente --> AdicionarItem?
    AdicionarItem? --> if_state2
    if_state2 --> CriarItem : Yes
    if_state2 --> ConcluirDadosOrcamento : No
    CriarItem --> CriarPeca?
    CriarPeca? --> if_state3
    if_state3 --> CriarModelo : No
    if_state3 --> CadastrarPeca :Yes
    CadastrarPeca --> CriarModelo
    CriarModelo --> CadastrarModelo
    CadastrarModelo --> CriaTecido?
    CriaTecido? --> if_state4
    if_state4 --> AdicionarAdicionais? : No
    if_state4 --> CadastrarTecido : Yes
    CadastrarTecido --> AdicionarAdicionais?
    AdicionarAdicionais? --> if_state5
    if_state5 --> AdicionarOItem : No
    if_state5 --> CriarAdicional : Yes
    CriarAdicional --> AdicionarAdicionais?
    ConcluirDadosOrcamento --> Pedido?
    Pedido? --> if_state6
    if_state6 --> SalvaOrcamento : No
    if_state6 --> ConcluirDadosDoPedido : Yes
    SalvaOrcamento --> [*]
    ConcluirDadosDoPedido --> SalvarPedido
    SalvarPedido --> [*]
    AdicionarOItem --> join_state
    join_state --> join_state2
    join_state2 --> AdicionarItem?
    
    
    
    
    
 
    
    
    
```



<a href="#top" class="go-top-button">Go to Top</a>
