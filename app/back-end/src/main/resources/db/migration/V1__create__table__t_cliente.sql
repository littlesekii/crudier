create table t_Cliente (
    id bigserial primary key,
    nome varchar(255),
    cpf varchar(11),
    email varchar(255),
    telefone varchar(20),
    estado varchar(100),
    cidade varchar(100),
    endereco varchar (255)
)