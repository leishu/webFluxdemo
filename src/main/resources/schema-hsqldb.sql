DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30) UNIQUE not null,
  status INTEGER not null
);

insert into customer (name, status) values ('github', 0);