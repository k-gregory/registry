create table executant (
  id      bigserial primary key  not null,
  first_name text not null,
  middle_name text not null,
  last_name text not null,
  phone_number varchar(128) not null
);

insert into executant (last_name, first_name, middle_name, phone_number)
VALUES
  ('Мандрыка', 'Борис', 'Родионович', '+380564445878'),
  ('Маринина', 'Лиана', 'Евлампиевна', '+380429525839'),
  ('Ермаков', 'Ефим', 'Кондратович', '+380529442819'),
  ('Цекало', 'Милена', 'Карповна', '+380234438773');