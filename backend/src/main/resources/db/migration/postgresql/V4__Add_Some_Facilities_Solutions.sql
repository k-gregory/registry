insert into facility (name) values ('Приморський ВДВС м. Маріуполь ГТУЮ у Донецькій області');
insert into facility (name) values ('Ніжинський міськрайонний відділ ДВС ГТУЮ у Чернігівській області');

insert into solution (sender, receiver, facility_id)
  select
    'Парасюк Галина Василівна',
    'ПАТ "Райффайзен Банк Аваль',
    f.id
  from facility f
  where f.name like 'Приморський%';

insert into solution (sender, receiver, facility_id)
  select
    'Криченков Сергій Іванович',
    'ПАТ "Київоблгаз"',
    f.id
  from facility f
  where f.name like 'Приморський%';

insert into solution (sender, receiver, facility_id)
  select
    'Криченко Патріну Олексіївна',
    'Південна об''єднана державна податкова інспекція Головного управління ДФС у Харківській області',
    f.id
  from facility f
  where f.name like 'Ніжинський%';