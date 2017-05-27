DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30) UNIQUE not null,
  status INTEGER not null
);

insert into customer (name, status) values ('github', 0);
insert into customer (name, status) values ('linux', 0);
insert into customer (name, status) values ('nginx', 0);
insert into customer (name, status) values ('spring', 0);