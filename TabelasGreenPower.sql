/*********************
Grupo: GreenPower
Jo�o Gabriel Boaventura Marques e Silva - RM554874
Lucas de Melo Pinho Pinheiro - RM558791
Lucas Leal das Chagas - RM551124
*********************/

DROP TABLE PAGAMENTO CASCADE CONSTRAINTS;
DROP TABLE PEDIDO CASCADE CONSTRAINTS;
DROP TABLE ITEM_COMPRADO CASCADE CONSTRAINTS;
DROP TABLE PRODUTO CASCADE CONSTRAINTS;
DROP TABLE CLIENTE CASCADE CONSTRAINTS;
DROP TABLE PAINELSOLAR CASCADE CONSTRAINTS;

CREATE TABLE CLIENTE (
    email_cliente VARCHAR2(100) CONSTRAINT pk_email_cliente PRIMARY KEY,
    senha_cliente VARCHAR2(16) CONSTRAINT senha_cliente NOT NULL,
    nome_cliente VARCHAR2(100) CONSTRAINT nome_cliente NOT NULL,
    sobrenome_cliente VARCHAR2(100) CONSTRAINT sobrenome_cliente NOT NULL,
    cpf_cliente VARCHAR2(14) CONSTRAINT cpf_cliente UNIQUE NOT NULL,
    rua_cliente VARCHAR2(120) CONSTRAINT rua_cliente NOT NULL,
    numero_cliente NUMBER CONSTRAINT numero_cliente NOT NULL,
    complemento_cliente VARCHAR2(120),
    bairro_cliente VARCHAR2(120) CONSTRAINT bairro_cliente NOT NULL,
    cidade_cliente VARCHAR2(120) CONSTRAINT cidade_cliente NOT NULL,
    estado_cliente VARCHAR2(2) CONSTRAINT estado_cliente NOT NULL,
    cep_cliente VARCHAR2(9) CONSTRAINT cep_cliente NOT NULL
);

CREATE TABLE PRODUTO (
    id_produto NUMBER CONSTRAINT pk_id_produto PRIMARY KEY,
    nome_produto VARCHAR2(100) CONSTRAINT nome_produto NOT NULL,
    descricao_produto VARCHAR2(500),
    preco_produto NUMBER(10, 2) CONSTRAINT preco_produto NOT NULL,
    tipo_produto VARCHAR2(50) CONSTRAINT tipo_produto NOT NULL
);

CREATE TABLE PEDIDO (
    id_pedido NUMBER CONSTRAINT pk_id_pedido PRIMARY KEY,
    email_cliente VARCHAR2(100) CONSTRAINT fk_email_cliente_pedido REFERENCES CLIENTE(email_cliente),
    data_pedido DATE DEFAULT TRUNC(SYSDATE) CONSTRAINT data_pedido NOT NULL,
    status_pedido VARCHAR2(20) CONSTRAINT status_pedido CHECK (status_pedido IN ('Novo', 'Em Andamento', 'Enviado', 'Entregue', 'Cancelado')),
    status_pagamento VARCHAR2(20) CONSTRAINT status_pagamento CHECK (status_pagamento IN ('Pendente', 'Conclu�do', 'Cancelado')),
    valor_total NUMBER(10, 2) CONSTRAINT valor_total_pedido NOT NULL
);

CREATE TABLE ITEM_COMPRADO (
    id_item NUMBER CONSTRAINT id_item UNIQUE,
    id_pedido NUMBER CONSTRAINT fk_id_pedido_item REFERENCES PEDIDO(id_pedido),
    id_produto NUMBER CONSTRAINT fk_id_produto_item REFERENCES PRODUTO(id_produto),
    quantidade NUMBER CONSTRAINT quantidade_item NOT NULL,
    preco_unitario NUMBER(10, 2) CONSTRAINT preco_unitario_item NOT NULL,
    preco_final NUMBER(10, 2) CONSTRAINT preco_final NOT NULL
);

CREATE TABLE PAGAMENTO (
    id_pagamento NUMBER CONSTRAINT pk_id_pagamento PRIMARY KEY,
    id_pedido NUMBER CONSTRAINT fk_id_pedido_pagamento REFERENCES PEDIDO(id_pedido),
    id_transacao VARCHAR2(50) CONSTRAINT id_transacao_pagamento UNIQUE NOT NULL,
    forma_pagamento VARCHAR2(50) CONSTRAINT forma_pagamento CHECK (forma_pagamento IN ('Cart�o', 'PIX', 'Boleto')) NOT NULL,
    status_pagamento VARCHAR2(20) CONSTRAINT status_pagamento_pgt CHECK (status_pagamento IN ('Pendente', 'Conclu�do', 'Cancelado')),
    data_pagamento DATE DEFAULT TRUNC(SYSDATE),
    valor_pagamento NUMBER(10, 2) CONSTRAINT valor_pagamento NOT NULL,
    qtd_parcelas NUMBER(2) DEFAULT 1 CHECK (qtd_parcelas BETWEEN 1 AND 10)
);

CREATE TABLE PAINELSOLAR (
    id_painelsolar NUMBER CONSTRAINT pk_id_painelsolar PRIMARY KEY,
    id_pedido NUMBER CONSTRAINT fk_id_pedido_painelsolar REFERENCES PEDIDO(id_pedido),
    energia_gerada_kwh NUMBER(10, 2) CONSTRAINT energia_gerada_positivo CHECK (energia_gerada_kwh >= 0),
    energia_consumida_kwh NUMBER(10, 2) CONSTRAINT energia_consumida_positivo CHECK (energia_consumida_kwh >= 0),
    data_registro DATE DEFAULT TRUNC(SYSDATE) CONSTRAINT data_registro NOT NULL
);
