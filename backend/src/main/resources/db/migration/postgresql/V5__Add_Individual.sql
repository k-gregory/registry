CREATE TABLE individual (
  uid varchar(10) primary key not null,
  firstname varchar(100) not null,
  middlename varchar(100) not null,
  lastname varchar(100) not null,
  passport varchar(8) not null,
  birthdate date not null
);

INSERT INTO individual (uid, firstName, middleName, lastName, passport, birthDate)
  VALUES
    ('0780678710', 'Вася', 'Петрович', 'Пупкин', 'РП228229', '10/04/1960'),
    ('8719680716', 'Петя', 'Евгениевич', 'Васечкин', 'КП799583', '05/10/1978'),
    ('7526314570', 'Евгений', 'Петрович', 'Васин', 'АГ568503', '10/10/1995'),
    ('4674985213', 'Виктория', 'Геннадиевна', 'Вассерман', 'ЖЗ837791', '03/10/1985'),
    ('7302684836', 'Елизавета', 'Сергеевна', 'Шульц', 'ЛЛ503632', '10/02/1991');