create table t_Cliente (
    id bigserial primary key,
    nome varchar(255) not null,
    cpf varchar(11) not null unique,
    email varchar(255) null,
    telefone varchar(20) not null,
    estado varchar(100) not null,
    cidade varchar(100) not null,
    endereco varchar (255) not null
)