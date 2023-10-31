drop sequence address_seq;
drop table if exists address cascade;
drop table if exists client cascade;

create table address (id uuid not null, street varchar(255), number varchar(10), complement varchar(50), neighborhood varchar(255), cep varchar(8), city varchar(255), uf varchar(2), primary key (id));--alter table address modify id uuid not null;
create table client (cpf varchar(11) not null, name varchar(255), address_id uuid unique, primary key (cpf), constraint fk_address_client foreign key (address_id) references address);
create table product (id uuid not null, name varchar(255), primary key (id));
create table client_product (id uuid not null, client_id varchar(11),  product_id uuid, start_date timestamp(6), primary key (id), constraint fk_client_product_client_id foreign key (client_id) references client, constraint fk_client_product_product_id foreign key (product_id) references product);