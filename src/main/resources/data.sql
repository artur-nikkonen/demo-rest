drop table if exists clients cascade;

create table clients (
  id serial primary key,
  name varchar(250) not null,
  email varchar(250) not null
);

insert into clients (name, email) values
  ('bob', 'bob@gmail.com'),
  ('john', 'john@gmail.com');

drop table if exists items cascade;

create table items (
  id serial primary key,
  title varchar(250) not null,
  client_id bigint,
  foreign key (client_id) references clients (id)
);

insert into items (title, client_id) values
('Box', 1), ('Square', 1), ('Book', 2);