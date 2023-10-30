create sequence address_seq start with 1 increment by 50;
create table address (id bigint not null, street varchar(255), number varchar(10), complement varchar(50), neighborhood varchar(255), cep varchar(8), city varchar(255), uf varchar(2), primary key (id));
create table client (cpf varchar(11) not null, name varchar(255), address_id bigint unique, primary key (cpf), constraint fk_address_client foreign key (address_id) references address);