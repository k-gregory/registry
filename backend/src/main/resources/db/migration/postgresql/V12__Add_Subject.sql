create table subject (
  id   bigserial not null primary key,
  name text,
  inn  text
);

insert into subject (name, inn) values ('Vasya', '133265')